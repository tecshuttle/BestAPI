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
            }),
            Ext.create('Best.product.packageServiceGrid', {
                id: me.id + '_package_service_grid',
                hidden: true,
                parent: me
            }),
            Ext.create('Best.product.packageServiceForm', {
                id: me.id + '_package_service_form',
                hidden: true,
                parent: me
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
                xtype: 'textfield', fieldLabel: '卡名称', margin: '0 20 0 0', labelWidth: 60, id: this.id + '_card_name'
            }, {
                xtype: 'textfield', fieldLabel: '卡代号', margin: '0 20 0 0', labelWidth: 60, id: this.id + '_card_code'
            }, {
                xtype: 'textfield', fieldLabel: '发卡机构', margin: '0 20 0 0', labelWidth: 80, id: this.id + '_company_name'
            }, {
                xtype: 'textfield', fieldLabel: '服务名称', labelWidth: 80, id: this.id + '_service_name'
            }, {
                xtype: 'button', text: '查询', margin: '0 0 0 20', id: this.id + '_search_btn'
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
                url: '/card/getTypeList',
                extraParams: {
                    //module: me.module
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
            width: 80,
            name: 'opertation',
            items: [{
                glyph: '编辑',
                handler: function (grid, rowIndex, colIndex) {
                    var rec = grid.getStore().getAt(rowIndex);
                    me._edit(rec);
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
            grid: Ext.getCmp(this.id + '_grid'),
            form: Ext.getCmp(this.id + '_form'),

            //查询条件
            cardName: Ext.getCmp(this.id + '_card_name'),
            cardCode: Ext.getCmp(this.id + '_card_code'),
            companyName: Ext.getCmp(this.id + '_company_name'),
            serviceName: Ext.getCmp(this.id + '_service_name'),
            searchBtn: Ext.getCmp(this.id + '_search_btn'),

            addBtn: Ext.getCmp(this.id + '_add'),
            saveBtn: Ext.getCmp(this.id + '_save'),
            returnBtn: Ext.getCmp(this.id + '_return'),

            //套餐管理
            packageGrid: Ext.getCmp(this.id + '_package_grid'),
            packageForm: Ext.getCmp(this.id + '_package_form'),

            //套餐服务管理
            packageServiceGrid: Ext.getCmp(this.id + '_package_service_grid'),
            packageServiceForm: Ext.getCmp(this.id + '_package_service_form'),

            queryForm: Ext.getCmp(this.id + '_query'),
            id: Ext.getCmp(this.id + '_id'),
            name: Ext.getCmp(this.id + '_name'),
            query: Ext.getCmp(this.id + '_btn_query'),
            reset: Ext.getCmp(this.id + '_btn_reset')
        });
    },

    initEvents: function () {
        var me = this;
        var $c = this.COMPONENTS;

        Tomtalk.IdcAction.superclass.initEvents.call(me);

        this.on('boxready', me._afterrender, me);
        $c.addBtn.on('click', me._add, me);
        $c.searchBtn.on('click', me._search, me);
    },

    _afterrender: function () {
        var $c = this.COMPONENTS;
    },

    _search: function () {
        var $c = this.COMPONENTS;
        var store = this.COMPONENTS.grid.getStore();
        var proxy = store.getProxy();

        Ext.apply(proxy.extraParams, {
            card_name: $c.cardName.getValue(),
            card_code: $c.cardCode.getValue(),
            company_name: $c.companyName.getValue(),
            service_name: $c.serviceName.getValue()
        });

        store.load();
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
        $c.toolbar.hide();
        $c.form.getForm().reset();
        $c.form._delToggle(-1);
        $c.form.show();
        $c.form.setTitle('建新卡');
    },

    _edit: function (rec) {
        var $c = this.COMPONENTS;

        $c.grid.hide();
        $c.toolbar.hide();
        $c.form.getForm().setValues(rec.data);
        $c.form._delToggle(rec.data.status);
        $c.form.show();
        $c.form.setTitle(rec.data.card_name + '编辑');

        $c.packageGrid.loadList(rec.data);
        $c.packageGrid.show();
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
        $c.packageGrid.hide();
        $c.packageForm.hide();

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