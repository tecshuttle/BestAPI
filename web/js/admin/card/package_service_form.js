Ext.ns('Best.product');

var productServiceStore = Ext.create('Ext.data.Store', {
    fields: ['id', 'service_name_combo'],
    autoLoad: true,
    pageSize: 9999,
    proxy: {
        type: 'ajax',
        url: '/service/getProdServiceList',
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

Ext.define('Best.product.packageFormServiceUI', {
    extend: 'Ext.form.Panel',
    constructor: function (config) {
        var me = this;
        config = Ext.apply({
            title: '套餐服务编辑',
            margin: '10 0 0 0',
            bodyStyle: 'padding:10px;',
            layout: 'anchor'
        }, config);

        me.COMPONENTS = {};

        Best.product.packageFormServiceUI.superclass.constructor.call(me, config);
    },

    initComponent: function () {
        var me = this;

        me.items = [
            {xtype: 'hiddenfield', id: this.id + '_rec_id', name: 'id', value: 0},
            {xtype: 'hiddenfield', fieldLabel: '套餐ID', name: 'package_id'},
            {xtype: 'hiddenfield', id: this.id + '_active_flag', name: 'active_flag', value: 1},
            {
                xtype: 'fieldcontainer', layout: 'hbox', defaults: {flex: 1, margin: '0 0 0 10'},
                items: [
                    {
                        xtype: 'combo', fieldLabel: '服务', store: productServiceStore, displayField: 'service_name_combo', margin: '0 0 0 0',
                        valueField: 'id', name: 'service_id', allowBlank: false, queryMode: 'local'
                    },
                    {xtype: 'displayfield'},
                    {xtype: 'displayfield'}
                ]
            }, {
                xtype: 'fieldcontainer', layout: 'hbox', defaults: {flex: 1, margin: '0 0 0 10'},
                items: [
                    {
                        xtype: 'combo', fieldLabel: '状态', store: statusStore, displayField: 'name', allowBlank: false, margin: '0 0 0 0',
                        valueField: 'status', name: 'status', queryMode: 'local'
                    },
                    {xtype: 'numberfield', fieldLabel: '数量', name: 'quantity', minValue: 0, allowBlank: false, emptyText: '请输入…'},
                    {xtype: 'numberfield', fieldLabel: '排序', name: 'seq', minValue: 0, allowBlank: false, emptyText: '请输入…'}
                ]
            },
            {
                xtype: 'fieldcontainer', layout: 'hbox', defaults: {}, margin: 0,
                items: [
                    {xtype: 'button', text: '保存', id: this.id + '_save', width: 100},
                    {xtype: 'button', text: '返回', id: this.id + '_return', style: 'margin-left: 50px;', width: 100},
                    {xtype: 'displayfield', flex: 1},
                    {xtype: 'button', text: '删除', cls: 'del-btn', id: this.id + '_delete', style: 'margin-left: 50px;', width: 100}
                ]
            }
        ];

        Best.product.packageFormServiceUI.superclass.initComponent.call(me);
    }
});

Ext.define('Best.product.packageFormServiceAction', {
    extend: 'Best.product.packageFormServiceUI',
    constructor: function (config) {
        Best.product.packageFormServiceAction.superclass.constructor.call(this, config);
    },

    initComponent: function () {
        Best.product.packageFormServiceAction.superclass.initComponent.call(this);

        Ext.apply(this.COMPONENTS, {
            supplierCombo: Ext.getCmp(this.id + '_supplier_combo'),
            supplierServiceCombo: Ext.getCmp(this.id + '_supplier_service_combo'),
            recId: Ext.getCmp(this.id + '_rec_id'),
            activeFlag: Ext.getCmp(this.id + '_active_flag'),
            saveBtn: Ext.getCmp(this.id + '_save'),
            delBtn: Ext.getCmp(this.id + '_delete'),
            returnBtn: Ext.getCmp(this.id + '_return')
        });
    },

    initEvents: function () {
        var me = this;
        var $c = this.COMPONENTS;

        Best.product.packageFormServiceAction.superclass.initEvents.call(me);

        //$c.supplierCombo.on('change', me._onChangeSupplierCombo, me);

        $c.saveBtn.on('click', me._save, me);
        $c.delBtn.on('click', me._del, me);
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
            this.up().COMPONENTS.packageServiceGrid.show();

            var packageForm = this.up().COMPONENTS.packageForm;
            packageForm.setCollapsed(false);
        }
    },

    _save: function () {
        var me = this;
        var form = me;
        var $c = this.COMPONENTS;
        var recId = $c.recId.getValue();
        var packageServiceGrid = this.up().COMPONENTS.packageServiceGrid;

        if (form.isValid()) {
            form.getForm().submit({
                url: '/card/' + (recId == 0 ? 'insertPackageDtl' : 'updatePackageDtl'),   //后台处理的页面
                submitEmptyText: false,
                success: function (fp, o) {
                    var result = Ext.decode(o.response.responseText);

                    if (result.success) {
                        me._return();
                        packageServiceGrid.getStore().load();
                    } else {
                        alert('See error info by console.');
                    }
                }
            });
        }
    },

    _del: function () {
        var me = this;
        var $c = this.COMPONENTS;

        Ext.Msg.buttonText = {
            yes: '是',
            no: '否',
            ok: '确定',
            cancel: '取消'
        };

        Ext.MessageBox.confirm('确认', '真的要删除吗?', function (btn, text) {
            if (btn === 'yes') {
                $c.activeFlag.setValue(0);
                me._save();
            }
        }, this);
    },

    _delToggle: function (status) {
        this.COMPONENTS.delBtn.setHidden(status === -1);     //新增隐藏删除按钮
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

Best.product.packageServiceForm = Best.product.packageFormServiceAction;

//end file