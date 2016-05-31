Ext.ns('Best.product');

var packageStore = Ext.create('Ext.data.Store', {
    fields: ['id', 'package_name'],
    autoLoad: true,
    proxy: {
        type: 'ajax',
        url: '/card/getPackageList',
        reader: {
            root: 'response',
            type: 'json'
        }
    }
});

Ext.define('Best.product.packageFormUI', {
    extend: 'Ext.form.Panel',
    constructor: function (config) {
        var me = this;
        config = Ext.apply({
            title: '编辑',
            bodyStyle: 'padding:10px;',
            layout: 'anchor'
        }, config);

        me.COMPONENTS = {};

        Best.product.packageFormUI.superclass.constructor.call(me, config);
    },

    initComponent: function () {
        var me = this;

        me.items = [
            {xtype: 'hiddenfield', id: this.id + '_rec_id', name: 'id', value: 0},
            {xtype: 'hiddenfield', fieldLabel: '健康卡ID', name: 'card_id'},
            {
                xtype: 'fieldcontainer', layout: 'hbox', defaults: {flex: 1, margin: '0 0 0 10'},
                items: [
                    {xtype: 'textfield', fieldLabel: '套餐名字', name: 'package_name', margin: 0, allowBlank: false, emptyText: '请输入…'},
                    {xtype: 'textfield', fieldLabel: '套餐代码', name: 'package_code', emptyText: '请输入…'},
                    {xtype: 'numberfield', fieldLabel: '排序', name: 'seq', minValue: 0, emptyText: '请输入…'}
                ]
            },
            {
                xtype: 'fieldcontainer', layout: 'hbox', defaults: {flex: 1, margin: '0 0 0 10'},
                items: [
                    {
                        xtype: 'combo', fieldLabel: '性别要求', store: sexSelectStore, displayField: 'name', margin: '0 0 0 0',
                        valueField: 'sex_select', name: 'sex_select', queryMode: 'local'
                    },
                    {xtype: 'numberfield', fieldLabel: '服务总数', name: 'dtl_count', minValue: 0, emptyText: '请输入…'},
                    {xtype: 'numberfield', fieldLabel: '价格', name: 'price', minValue: 0, emptyText: '请输入…'},
                ]
            },
            {xtype: 'textarea', fieldLabel: '说明', name: 'intro', anchor: '100%', emptyText: '请输入…'},
            {xtype: 'button', text: '保存', id: this.id + '_save', width: 100},
            {xtype: 'button', text: '返回', id: this.id + '_return', style: 'margin-left: 50px;', width: 100}
        ];

        Best.product.packageFormUI.superclass.initComponent.call(me);
    }
});

Ext.define('Best.product.packageFormAction', {
    extend: 'Best.product.packageFormUI',
    constructor: function (config) {
        Best.product.packageFormAction.superclass.constructor.call(this, config);
    },

    initComponent: function () {
        Best.product.packageFormAction.superclass.initComponent.call(this);

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

        Best.product.packageFormAction.superclass.initEvents.call(me);

        //$c.supplierCombo.on('change', me._onChangeSupplierCombo, me);

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
            this.up().COMPONENTS.packageGrid.show();
        }
    },

    _save: function () {
        var me = this;
        var form = me;
        var $c = this.COMPONENTS;
        var recId = $c.recId.getValue();
        packageGrid = this.up().COMPONENTS.packageGrid;

        if (form.isValid()) {
            form.getForm().submit({
                url: '/card/' + (recId == 0 ? 'insertPackage' : 'updatePackage'),   //后台处理的页面
                submitEmptyText: false,
                success: function (fp, o) {
                    var result = Ext.decode(o.response.responseText);

                    if (result.success) {
                        me._return();
                        packageGrid.getStore().load();
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

Best.product.packageForm = Best.product.packageFormAction;

//end file