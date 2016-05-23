Ext.ns('Tomtalk');

Tomtalk.IdcUI = Ext.extend(Ext.Panel, {
    KE: false,
    constructor: function (config) {
        var me = this;
        config = Ext.apply({
            style: 'padding:10px;background-color: white;',
            border: false
        }, config);

        me.COMPONENTS = {};

        Tomtalk.IdcUI.superclass.constructor.call(me, config);
    },

    initComponent: function () {
        var me = this;
        me.items = [
            me._toolbar(),
            me._grid(),
            Ext.create('Tomtalk.grid.AccountForm', {
                id: me.id + '_form',
                hidden: true
            }),
            Ext.create('Best.card.BatchForm', {
                id: me.id + '_batch_form',
                hidden: true
            })
        ];

        Tomtalk.IdcUI.superclass.initComponent.call(me);
    },

    //查卡号、批次号、提请人、卡类型、发卡机构、卡名
    _toolbar: function () {
        return {
            xtype: 'fieldcontainer', id: this.id + '_toolbar',
            layout: 'hbox',
            items: [{
                xtype: 'button', text: '新建卡号', id: this.id + '_add_batch', margin: '0 20 0 0'
            }, {
                xtype: 'combo', fieldLabel: '发卡机构', labelWidth: 60, id: this.id + '_company_combo', store: companyStore,
                displayField: 'company_name', valueField: 'id', queryMode: 'local', margin: '0 20 0 0'
            }, {
                xtype: 'combo', fieldLabel: '卡名称', labelWidth: 50, id: this.id + '_card_combo', store: cardTypeByCompanyStore,
                displayField: 'card_name', valueField: 'id', name: 'card_id', queryMode: 'local', margin: '0 20 0 0'
            }, {
                xtype: 'combo', fieldLabel: '卡类型', labelWidth: 60, id: this.id + '_card_type', store: cardTypeStore,
                displayField: 'name', valueField: 'cardType', queryMode: 'local'
            }, {
                xtype: 'textfield', id: this.id + '_batch_no', name: 'batch_no', enableKeyEvents: true, margin: '0 0 0 20', emptyText: '批次号'
            }, {
                xtype: 'textfield', id: this.id + '_proposer', name: 'proposer', enableKeyEvents: true, margin: '0 0 0 20', emptyText: '提请人'
            }, {
                xtype: 'textfield', id: this.id + '_card_no', name: 'card_no', enableKeyEvents: true, margin: '0 0 0 20', emptyText: '卡号'
            }]
        }
    },

    _grid: function () {
        var me = this;

        var store = Ext.create('Ext.data.Store', {
            autoLoad: true,
            pageSize: 20,
            fields: me.fields,
            proxy: {
                type: 'ajax',
                url: '/card/getCardNoGenBatchList',
                extraParams: {
                    module: me.module
                },
                reader: {
                    type: 'json',
                    root: 'response',
                    totalProperty: 'total'
                }
            }
        });

        me.columns.push({
            header: "操作",
            dataIndex: 'id',
            align: 'center',
            xtype: 'actioncolumn',
            name: 'opertation',
            width: 120,
            items: [{
                glyph: '编辑',
                handler: function (grid, rowIndex, colIndex) {
                    var rec = grid.getStore().getAt(rowIndex);
                    me._edit(rec);
                }
            }, {
                glyph: '导出',
                handler: function (grid, rowIndex, colIndex) {
                    var rec = grid.getStore().getAt(rowIndex);
                    me._export(rec);
                }
            }]
        });

        var grid = new Ext.grid.GridPanel({
            id: this.id + '_grid',
            title: '我被授权的作业',
            header: false,
            columnLines: true,
            store: store,
            columns: me.columns,
            bbar: {
                xtype: 'pagingtoolbar',
                store: store,
                inputItemWidth: 50,
                displayInfo: true,
                beforePageText: '页',
                afterPageText: '/ {0}',
                displayMsg: "显示第 {0} 条到 {1} 条记录，一共 {2} 条",
                emptyMsg: "没有记录"
            },
            forceFit: true,
            viewConfig: {
                stripeRows: true,
                enableRowBody: true,
                showPreview: true
            }
        });

        return grid;
    }
});

Tomtalk.IdcAction = Ext.extend(Tomtalk.IdcUI, {
    constructor: function (config) {
        Tomtalk.IdcAction.superclass.constructor.call(this, config);
    },

    initComponent: function () {
        Tomtalk.IdcAction.superclass.initComponent.call(this);

        Ext.apply(this.COMPONENTS, {
            addBtn: Ext.getCmp(this.id + '_add'),
            addBatchBtn: Ext.getCmp(this.id + '_add_batch'),

            companyCombo: Ext.getCmp(this.id + '_company_combo'),
            cardCombo: Ext.getCmp(this.id + '_card_combo'),
            cardTypeCombo: Ext.getCmp(this.id + '_card_type'),
            cardNo: Ext.getCmp(this.id + '_card_no'),
            proposer: Ext.getCmp(this.id + '_proposer'),
            batchNo: Ext.getCmp(this.id + '_batch_no'),

            toolBar: Ext.getCmp(this.id + '_toolbar'),
            grid: Ext.getCmp(this.id + '_grid'),
            form: Ext.getCmp(this.id + '_form'),
            batchForm: Ext.getCmp(this.id + '_batch_form')
        });
    },

    initEvents: function () {
        var me = this;
        var $c = this.COMPONENTS;

        Tomtalk.IdcAction.superclass.initEvents.call(me);

        this.on('boxready', me._afterrender, me);
        $c.companyCombo.on('change', me._onChangeCompanyCombo, me);
        $c.cardCombo.on('change', me._onChangeCardCombo, me);
        $c.cardTypeCombo.on('change', me._onChangeCardTypeCombo, me);
        $c.cardNo.on('keyup', me._onKeyUp, me);
        $c.batchNo.on('keyup', me._onKeyUp, me);
        $c.proposer.on('keyup', me._onKeyUp, me);
        $c.addBatchBtn.on('click', me._add_batch, me);
    },

    _afterrender: function () {
        var $c = this.COMPONENTS;
        $c.companyCombo.getStore().load();
    },

    _onChangeCompanyCombo: function (combo, company_id, oldValue, eOpts) {
        //更新卡名
        var cardCombo = this.COMPONENTS.cardCombo;

        var store = cardCombo.getStore();
        var proxy = store.getProxy();

        Ext.apply(proxy.extraParams, {
            company_id: company_id
        });

        store.load();

        //更新列表
        var store = this.COMPONENTS.grid.getStore();
        var proxy = store.getProxy();

        Ext.apply(proxy.extraParams, {
            company_id: (company_id === 0 ? '' : company_id)
        });

        store.load();

        //清除表单值：卡名
        this.COMPONENTS.cardCombo.setValue('');
    },

    _onChangeCardCombo: function (combo, card_id, oldValue, eOpts) {
        //更新卡名
        var cardCombo = this.COMPONENTS.cardCombo;

        var store = cardCombo.getStore();
        var proxy = store.getProxy();

        Ext.apply(proxy.extraParams, {
            card_id: card_id
        });

        store.load();

        //更新列表
        var store = this.COMPONENTS.grid.getStore();
        var proxy = store.getProxy();

        Ext.apply(proxy.extraParams, {
            card_id: (card_id === 0 ? '' : card_id)
        });

        store.load();
    },


    _onChangeCardTypeCombo: function (combo, newValue, oldValue, eOpts) {
        var store = this.COMPONENTS.grid.getStore();
        var proxy = store.getProxy();

        Ext.apply(proxy.extraParams, {
            card_type: (newValue === 0 ? '' : newValue)
        });

        store.load();
    },

    _onKeyUp: function (txt, e, eOpts) {
        var $c = this.COMPONENTS;

        if (e.keyCode === 13) {
            var keyword = {};
            keyword.card_no = $c.cardNo.getValue().trim();
            keyword.batch_no = $c.batchNo.getValue().trim();
            keyword.proposer = $c.proposer.getValue().trim();

            var store = this.COMPONENTS.grid.getStore();
            var proxy = store.getProxy();
            Ext.apply(proxy.extraParams, keyword);

            store.load();
        }
    },

    _delete: function (id) {
        var me = this;

        Ext.Ajax.request({
            url: '/admin/gridDelete',
            params: {
                module: me.module,
                id: id
            },
            success: function (res) {
                var result = Ext.decode(res.responseText);
                me.COMPONENTS.grid.getStore().reload();
            }
        });
    },

    _add: function () {
        var $c = this.COMPONENTS;

        $c.grid.hide();
        $c.toolBar.hide();
        $c.form.getForm().reset();
        $c.form.show();
    },

    _add_batch: function () {
        var $c = this.COMPONENTS;

        $c.grid.hide();
        $c.toolBar.hide();
        $c.batchForm.getForm().reset();
        $c.batchForm.show();
    },

    _edit: function (rec) {
        var $c = this.COMPONENTS;

        $c.grid.hide();
        $c.toolBar.hide();
        rec.data.gen_quantity_for_display = rec.data.gen_quantity;  //新增变量，用于给displayfield赋值。
        $c.form.getForm().setValues(rec.data);
        $c.form.show();
    },

    _export: function (rec) {
        window.location.href = '/card/batchCardNoExport?batch_id=' + rec.data.id;
    },

    _returnFrom: function () {
        var $c = this.COMPONENTS;

        $c.form.hide();
        $c.grid.show();
        $c.toolBar.show();
        $c.grid.getStore().reload();
    }
});

Tomtalk.Idc = Tomtalk.IdcAction;

//end file