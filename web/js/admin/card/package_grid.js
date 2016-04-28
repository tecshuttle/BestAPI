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
            Ext.create('Best.product.packageGrid', {
                id: me.id + '_package_grid',
                parent: me,
                hidden: true
            }),
            Ext.create('Best.product.packageForm', {
                id: me.id + '_package_form',
                parent: me,
                hidden: true
            })
        ];

        Tomtalk.IdcUI.superclass.initComponent.call(me);
    },


    _toolbar: function () {
        return {
            xtype: 'fieldcontainer', layout: 'hbox', bodyPadding: 10,
            id: this.id + '_toolbar',
            items: [{
                xtype: 'button', text: '新建', margin: '0 20 0 0', id: this.id + '_add'
            }, {
                xtype: 'combo', fieldLabel: '卡名', labelWidth: 40, id: this.id + '_card_combo',
                store: cardStore, displayField: 'card_name', valueField: 'id', name: 'card_id', queryMode: 'local'
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
                url: '/card/getPackageList',
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
            width: 130,
            xtype: 'actioncolumn',
            name: 'opertation',
            items: [{
                glyph: '编辑',
                handler: function (grid, rowIndex, colIndex) {
                    var rec = grid.getStore().getAt(rowIndex);
                    me._edit(rec);
                }
            }, {
                glyph: '服务',
                handler: function (grid, rowIndex, colIndex) {
                    var rec = grid.getStore().getAt(rowIndex);
                    me._package_edit(rec);
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
            /*dockedItems: [
             {
             xtype: 'toolbar',
             items: [
             {
             text: '新建',
             id: this.id + '_add'
             }
             ]
             }
             ],*/
            bbar: {
                xtype: 'pagingtoolbar',
                store: store,
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
            toolbar: Ext.getCmp(this.id + '_toolbar'),
            addBtn: Ext.getCmp(this.id + '_add'),
            cardCombo: Ext.getCmp(this.id + '_card_combo'),

            grid: Ext.getCmp(this.id + '_grid'),
            form: Ext.getCmp(this.id + '_form'),

            //套餐服务管理
            packageGrid: Ext.getCmp(this.id + '_package_grid'),
            packageForm: Ext.getCmp(this.id + '_package_form')
        });
    },

    initEvents: function () {
        var me = this;
        var $c = this.COMPONENTS;

        Tomtalk.IdcAction.superclass.initEvents.call(me);

        this.on('boxready', me._afterrender, me);
        $c.addBtn.on('click', me._add, me);
        $c.cardCombo.on('change', me._onChangeCardCombo, me);
        $c.cardCombo.getStore().on('load', me._onLoadCardCombo, me);
    },

    _afterrender: function () {
        this.COMPONENTS.cardCombo.getStore().load();
    },

    _onChangeCardCombo: function (combo, newValue, oldValue, eOpts) {
        var store = this.COMPONENTS.grid.getStore();
        var proxy = store.getProxy();

        if (newValue == 0) {
            delete proxy.extraParams.card_id;
        } else {
            Ext.apply(proxy.extraParams, {
                card_id: newValue
            });
        }

        store.load();
    },

    _onLoadCardCombo: function (store, records, successful, eOpts) {
        store.insert(0, [{id: 0, card_name: '全部'}]);
    },

    _add: function () {
        var $c = this.COMPONENTS;

        $c.grid.hide();
        $c.form.getForm().reset();
        $c.form.show();
    },

    _edit: function (rec) {
        var $c = this.COMPONENTS;

        $c.grid.hide();
        $c.toolbar.hide();

        $c.form.getForm().setValues(rec.data);
        $c.form.show();
    },

    _package_edit: function (rec) {
        var $c = this.COMPONENTS;

        $c.grid.hide();
        $c.toolbar.hide();

        $c.packageGrid.loadList(rec.data);
        $c.packageGrid.show();
    },

    _returnFrom: function () {
        var $c = this.COMPONENTS;

        $c.form.hide();

        $c.grid.show();
        $c.toolbar.show();

        $c.grid.getStore().reload();
    },

    _return: function () {
        this.COMPONENTS.grid.show();
        this.COMPONENTS.toolbar.show();
    }
});

Tomtalk.Idc = Tomtalk.IdcAction;

//end file