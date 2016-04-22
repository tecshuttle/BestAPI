Ext.ns('Tomtalk.grid');

Ext.define('Tomtalk.grid.FormUI', {
    extend: 'Ext.form.Panel',
    constructor: function (config) {
        var me = this;
        config = Ext.apply({
            title: '编辑',
            bodyStyle: 'padding:10px;',
            layout: 'anchor'
        }, config);

        me.COMPONENTS = {};

        Tomtalk.grid.FormUI.superclass.constructor.call(me, config);
    },

    initComponent: function () {
        var me = this;

        me.items = [
            {xtype: 'hiddenfield', id: this.id + '_rec_id', name: 'id', value: 0},
            {
                xtype: 'fieldcontainer', layout: 'hbox', defaults: {flex: 1, margin: '0 0 0 10'},
                items: [
                    {xtype: 'textfield', fieldLabel: '供应商名称', name: 'supplier_name', margin: 0, allowBlank: false, emptyText: '请输入…'},
                    {xtype: 'textfield', fieldLabel: '供应商简称', name: 'supplier_abbr', allowBlank: false, emptyText: '请输入…'},
                    {xtype: 'textfield', fieldLabel: '供应商代码', name: 'supplier_code', allowBlank: false, emptyText: '请输入…'},
                    {xtype: 'displayfield'}
                ]
            }, {
                xtype: 'fieldcontainer', layout: 'hbox', defaults: {flex: 1, margin: '0 0 0 10'},
                items: [
                    {
                        xtype: 'combo', fieldLabel: '激活标记', store: activeFlagStore, displayField: 'name', margin: 0,
                        valueField: 'active_flag', name: 'active_flag', queryMode: 'local'
                    },
                    {
                        xtype: 'combo', fieldLabel: '使用状态', store: supplierStatusStore, displayField: 'name',
                        valueField: 'status', name: 'status', queryMode: 'local'
                    },
                    {xtype: 'textfield', fieldLabel: '联络人', name: 'contact', allowBlank: false, emptyText: '请输入…'},
                    {xtype: 'displayfield'}
                ]
            }, {
                xtype: 'fieldcontainer', layout: 'hbox', defaults: {flex: 1, margin: '0 0 0 10'},
                items: [
                    {xtype: 'textfield', fieldLabel: '联络人手机', name: 'contact_mobile', margin: 0, allowBlank: false, emptyText: '请输入…'},
                    {xtype: 'textfield', fieldLabel: '联络人电话', name: 'contact_phone', allowBlank: false, emptyText: '请输入…'},
                    {xtype: 'textfield', fieldLabel: '联络人邮箱', name: 'contact_mail', allowBlank: false, emptyText: '请输入…'},
                    {xtype: 'textfield', fieldLabel: '联络人传真', name: 'contact_fax', allowBlank: false, emptyText: '请输入…'}
                ]
            },
            {xtype: 'textarea', fieldLabel: '介绍', name: 'intro', anchor: '100%', allowBlank: false, emptyText: '请输入…'},
            {xtype: 'button', text: '保存', id: this.id + '_save', width: 100},
            {xtype: 'button', text: '返回', id: this.id + '_return', style: 'margin-left: 50px;', width: 100}
        ];

        Tomtalk.grid.FormUI.superclass.initComponent.call(me);
    }
});

Ext.define('Tomtalk.grid.FormAction', {
    extend: 'Tomtalk.grid.FormUI',
    constructor: function (config) {
        Tomtalk.grid.FormAction.superclass.constructor.call(this, config);
    },

    initComponent: function () {
        Tomtalk.grid.FormAction.superclass.initComponent.call(this);

        Ext.apply(this.COMPONENTS, {
            recId: Ext.getCmp(this.id + '_rec_id'),
            saveBtn: Ext.getCmp(this.id + '_save'),
            returnBtn: Ext.getCmp(this.id + '_return')
        });
    },

    initEvents: function () {
        var me = this;
        var $c = this.COMPONENTS;

        Tomtalk.grid.FormAction.superclass.initEvents.call(me);

        $c.saveBtn.on('click', me._save, me);
        $c.returnBtn.on('click', me._return, me);
    },

    _return: function () {
        this.getForm().reset();

        if (this.up()) {
            this.up()._returnFrom();
        }
    },

    _save: function () {
        var me = this;
        var form = me;
        var $c = this.COMPONENTS;
        var recId = $c.recId.getValue();

        console.log('result');

        if (form.isValid()) {
            form.getForm().submit({
                url: '/supplier/' + (recId == 0 ? 'insertSupplier' : 'updateSupplier'),   //后台处理的页面
                submitEmptyText: false,
                success: function (fp, o) {
                    var result = Ext.decode(o.response.responseText);

                    if (result.success) {
                        me._return();
                    } else {
                        alert('See error info by console.');
                        console.log(result);
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

Tomtalk.grid.AccountForm = Tomtalk.grid.FormAction;

//end file