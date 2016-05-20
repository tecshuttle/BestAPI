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
                xtype: 'combo', fieldLabel: '供应商', labelWidth: 60, id: this.id + '_supplier_combo', store: supplierStore,
                displayField: 'supplier_name', valueField: 'id', name: 'supplier_id', queryMode: 'local'
            }, {
                xtype: 'combo', fieldLabel: '产品类型', margin: '0 0 0 20', labelWidth: 80, id: this.id + '_service_type_combo', store: productRelTypeStore,
                displayField: 'name', valueField: 'rel_type', name: 'rel_type', queryMode: 'local'
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
                url: '/supplier/getSupplierServiceList',
                extraParams: {
                    supplier_id: '',
                    service_type: ''
                },
                reader: {
                    type: 'json',
                    root: 'response',
                    totalProperty: 'total'
                }
            }
        });

        var linkcolumn = [{
            glyph: '编辑',
            handler: function (grid, rowIndex, colIndex) {
                var rec = grid.getStore().getAt(rowIndex);
                me._edit(rec);
            }
        }];

        me.columns.push({
            header: "操作",
            dataIndex: 'id',
            align: 'center',
            xtype: 'actioncolumn',
            name: 'opertation',
            items: linkcolumn
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
                inputItemWidth: 50,
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
            supplierCombo: Ext.getCmp(this.id + '_supplier_combo'),
            serviceTypeCombo: Ext.getCmp(this.id + '_service_type_combo'),

            toolBar: Ext.getCmp(this.id + '_toolbar'),
            grid: Ext.getCmp(this.id + '_grid'),
            form: Ext.getCmp(this.id + '_form')
        });
    },

    initEvents: function () {
        var me = this;
        var $c = this.COMPONENTS;

        Tomtalk.IdcAction.superclass.initEvents.call(me);

        this.on('boxready', me._afterrender, me);
        $c.supplierCombo.getStore().on('load', me._onLoadSupplierCombo, me);
        $c.supplierCombo.on('change', me._onChangeSupplierCombo, me);
        $c.serviceTypeCombo.getStore().on('load', me._onLoadServiceTypeCombo, me);
        $c.serviceTypeCombo.on('change', me._onChangeServiceTypeCombo, me);
        $c.addBtn.on('click', me._add, me);
    },

    _afterrender: function () {
        this.COMPONENTS.supplierCombo.getStore().load();
    },

    _onLoadSupplierCombo: function (store, records, successful, eOpts) {
        store.insert(0, [{id: 0, supplier_name: '全部'}]);
    },

    _onLoadServiceTypeCombo: function (store, records, successful, eOpts) {
        //store.insert(0, [{id: 0, rel_type: '', name: '全部'}]);
    },

    _onChangeSupplierCombo: function (combo, newValue, oldValue, eOpts) {
        var store = this.COMPONENTS.grid.getStore();
        var proxy = store.getProxy();

        Ext.apply(proxy.extraParams, {
            supplier_id: (newValue === 0 ? '' : newValue)
        });

        store.load();
    },

    _onChangeServiceTypeCombo: function (combo, newValue, oldValue, eOpts) {
        var store = this.COMPONENTS.grid.getStore();
        var proxy = store.getProxy();

        Ext.apply(proxy.extraParams, {
            service_type: newValue
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
        $c.toolBar.hide();
        $c.form.getForm().reset();
        $c.form._delToggle(-1);
        $c.form._loadSupplierCombo();
        $c.form.show();
    },

    _edit: function (rec) {
        var $c = this.COMPONENTS;

        $c.grid.hide();
        $c.toolBar.hide();
        $c.form.getForm().setValues(rec.data);
        $c.form._delToggle(rec.data.status);
        $c.form._loadSupplierCombo();
        $c.form.show();
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