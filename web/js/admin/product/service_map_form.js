Ext.ns('Best.product');

var supplierStore = Ext.create('Ext.data.Store', {
    fields: ['id', 'supplier_name'],
    autoLoad: true,
    proxy: {
        type: 'ajax',
        url: '/supplier/getSupplierList',
        reader: {
            //totalProperty: 'total',
            root: 'response',
            type: 'json'
        }
    }
});

var supplierServiceStore = Ext.create('Ext.data.Store', {
    fields: ['id', 'service_name'],
    //autoLoad: true,
    proxy: {
        type: 'ajax',
        url: '/supplier/getServiceListBySupplier',
        reader: {
            //totalProperty: 'total',
            root: 'response',
            type: 'json'
        }
    }
});

//使用场景：是否有体验  是否有洁牙  是否基因检测
var serviceMapStatusStore = Ext.create('Ext.data.Store', {
    fields: ['status', 'name'],
    data: [
        {status: null, name: '未使用'},
        {status: 'IN_SERVICE', name: '使用中'}
    ]
});

Ext.define('Best.product.serviceListFormUI', {
    extend: 'Ext.form.Panel',
    constructor: function (config) {
        var me = this;
        config = Ext.apply({
            title: '编辑',
            bodyStyle: 'padding:10px;',
            layout: 'anchor'
        }, config);

        me.COMPONENTS = {};

        Best.product.serviceListFormUI.superclass.constructor.call(me, config);
    },

    initComponent: function () {
        var me = this;

        me.items = [
            {xtype: 'hiddenfield', id: this.id + '_rec_id', name: 'id', value: 0},
            {xtype: 'hiddenfield', name: 'xzh_service_id'},
            {
                xtype: 'fieldcontainer', layout: 'hbox', defaults: {flex: 1, margin: '0 0 0 10'},
                items: [
                    {
                        xtype: 'combo', fieldLabel: '激活标记', store: activeFlagStore, displayField: 'name', margin: 0,
                        valueField: 'active_flag', name: 'active_flag', queryMode: 'local'
                    },
                    {
                        xtype: 'combo', fieldLabel: '业务状态', store: serviceMapStatusStore, displayField: 'name',
                        valueField: 'status', name: 'status', queryMode: 'local'
                    },
                    {xtype: 'numberfield', fieldLabel: '显示顺序', name: 'seq', minValue: 0, allowBlank: false, emptyText: '请输入…'},
                ]
            },
            {
                xtype: 'fieldcontainer', layout: 'hbox', defaults: {flex: 1, margin: '0 0 0 10'},
                items: [
                    {
                        xtype: 'combo', fieldLabel: '供应商', store: supplierStore,
                        id: this.id + '_supplier_combo',
                        displayField: 'supplier_name', margin: 0, valueField: 'id', name: 'supplier_id', queryMode: 'local'
                    },
                    {
                        xtype: 'combo', fieldLabel: '服务', store: supplierServiceStore, displayField: 'service_name',
                        id: this.id + '_supplier_service_combo',
                        valueField: 'id', name: 'supplier_service_id', queryMode: 'local'
                    },
                    {xtype: 'displayfield'}
                ]
            },
            {xtype: 'button', text: '保存', id: this.id + '_save', width: 100},
            {xtype: 'button', text: '返回', id: this.id + '_return', style: 'margin-left: 50px;', width: 100}
        ];

        Best.product.serviceListFormUI.superclass.initComponent.call(me);
    }
});

Ext.define('Best.product.serviceListFormAction', {
    extend: 'Best.product.serviceListFormUI',
    constructor: function (config) {
        Best.product.serviceListFormAction.superclass.constructor.call(this, config);
    },

    initComponent: function () {
        Best.product.serviceListFormAction.superclass.initComponent.call(this);

        Ext.apply(this.COMPONENTS, {
            supplierCombo: Ext.getCmp(this.id + '_supplier_combo'),
            supplierServiceCombo: Ext.getCmp(this.id + '_supplier_service_combo'),
            recId: Ext.getCmp(this.id + '_rec_id'),
            saveBtn: Ext.getCmp(this.id + '_save'),
            returnBtn: Ext.getCmp(this.id + '_return')
        });
    },

    initEvents: function () {
        var me = this;
        var $c = this.COMPONENTS;

        Best.product.serviceListFormAction.superclass.initEvents.call(me);

        $c.supplierCombo.on('change', me._onChangeSupplierCombo, me);

        $c.saveBtn.on('click', me._save, me);
        $c.returnBtn.on('click', me._return, me);
    },

    _onChangeSupplierCombo: function (combo, supplier_id, oldValue, eOpts) {
        var serviceCombo = this.COMPONENTS.supplierServiceCombo;

        var store = serviceCombo.getStore();
        var proxy = store.getProxy();

        Ext.apply(proxy.extraParams, {
            supplier_id: supplier_id
        });

        store.load();
    },

    _return: function () {
        this.getForm().reset();

        this.hide();

        if (this.up()) {
            //this.up()._returnFrom();
            this.up().COMPONENTS.serviceListGrid.show();
        }
    },

    _save: function () {
        var me = this;
        var form = me;
        var $c = this.COMPONENTS;
        var recId = $c.recId.getValue();
        serviceListGrid = this.up().COMPONENTS.serviceListGrid;

        if (form.isValid()) {
            form.getForm().submit({
                url: '/service/' + (recId == 0 ? 'insertProdServiceMap' : 'updateProdServiceMap'),   //后台处理的页面
                submitEmptyText: false,
                success: function (fp, o) {
                    var result = Ext.decode(o.response.responseText);

                    if (result.success) {
                        me._return();
                        serviceListGrid.getStore().load();
                    } else {
                        alert('See error info by console.');
                    }
                }
            });
        }
    },

    _getValue: function () {
        var me = this;
        var $c = this.COMPONENTS;
        var addForm = me.getForm();
        if (!addForm.isValid()) {
            return false;
        }

        return addForm.getValues();
    }
});

Best.product.serviceListForm = Best.product.serviceListFormAction;

//end file