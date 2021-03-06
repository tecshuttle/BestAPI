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
                xtype: 'textfield', fieldLabel: '门点名称', id: this.id + '_org_name', enableKeyEvents: true, name: 'org_name',
                margin: '0 0 0 20', emptyText: '请输入…', labelWidth: 80
            }, {
                xtype: 'textfield', fieldLabel: '省份', id: this.id + '_province', enableKeyEvents: true,
                margin: '0 0 0 20', emptyText: '请输入…', labelWidth: 40
            }, {
                xtype: 'textfield', fieldLabel: '城市', id: this.id + '_city', enableKeyEvents: true,
                margin: '0 0 0 20', emptyText: '请输入…', labelWidth: 40
            }, {
                xtype: 'checkboxfield', id: this.id + '_has_physical', hideLabel: true, margin: '0 20 0 20', boxLabel: '是否体检'
            }, {
                xtype: 'checkboxfield', id: this.id + '_has_tooth_care', hideLabel: true, boxLabel: '是否洁牙'
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
                url: '/supplier/getSupplierOrgList',
                extraParams: {
                    supplier_id: '',
                    org_name: ''
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

            //搜索项
            supplierCombo: Ext.getCmp(this.id + '_supplier_combo'),
            orgName: Ext.getCmp(this.id + '_org_name'),
            province: Ext.getCmp(this.id + '_province'),
            city: Ext.getCmp(this.id + '_city'),
            hasPhysical: Ext.getCmp(this.id + '_has_physical'),
            hasToothCare: Ext.getCmp(this.id + '_has_tooth_care'),

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

        $c.supplierCombo.on('change', me._onChangeSupplierCombo, me);
        $c.supplierCombo.getStore().on('load', me._onLoadSupplierCombo, me);
        $c.orgName.on('keyup', me._onKeyUp, me);
        $c.province.on('keyup', me._onKeyUp, me);
        $c.city.on('keyup', me._onKeyUp, me);
        $c.hasPhysical.on('change', me._chkChange, me);
        $c.hasToothCare.on('change', me._chkChange, me);
        $c.addBtn.on('click', me._add, me);
    },

    _afterrender: function () {
        this.COMPONENTS.supplierCombo.getStore().load();
    },

    _onLoadSupplierCombo: function (store, records, successful, eOpts) {
        store.insert(0, [{id: 0, supplier_name: '全部'}]);
    },

    _onChangeSupplierCombo: function (combo, newValue, oldValue, eOpts) {
        var store = this.COMPONENTS.grid.getStore();
        var proxy = store.getProxy();

        Ext.apply(proxy.extraParams, {
            supplier_id: (newValue === 0 ? '' : newValue)
        });

        store.load();
    },

    _chkChange: function (chk, newValue, oldValue, eOpts) {
        var $c = this.COMPONENTS;

        var store = this.COMPONENTS.grid.getStore();
        var proxy = store.getProxy();

        Ext.apply(proxy.extraParams, {
            has_physical: ($c.hasPhysical.getValue() ? 'Y' : ''),
            has_tooth_care: ($c.hasToothCare.getValue() ? 'Y' : '')
        });

        store.load();
    },

    _onKeyUp: function (txt, e, eOpts) {
        var $c = this.COMPONENTS;

        if (e.keyCode === 13) {
            var store = this.COMPONENTS.grid.getStore();
            var proxy = store.getProxy();

            Ext.apply(proxy.extraParams, {
                org_name: $c.orgName.getValue().trim(),
                province_id: $c.province.getValue().trim(),
                city_id: $c.city.getValue().trim()
            });

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
        $c.form._delToggle(-1);
        $c.form.show();
    },

    _edit: function (rec) {
        var $c = this.COMPONENTS;

        $c.grid.hide();
        $c.toolBar.hide();
        $c.form.getForm().setValues(rec.data);
        $c.form._delToggle(rec.data.status);
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