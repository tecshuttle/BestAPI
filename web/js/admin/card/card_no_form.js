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
            {
                xtype: 'hiddenfield',
                id: this.id + '_rec_id',
                name: 'id',
                value: 0
            },
            {
                xtype: 'fieldcontainer', layout: 'hbox', defaults: {flex: 1, margin: '0 0 0 10'},
                items: [
                    {xtype: 'textfield', fieldLabel: '卡号', name: 'card_no', margin: 0, allowBlank: false, emptyText: '请输入…'},
                    {xtype: 'textfield', fieldLabel: '卡密', name: 'card_code', allowBlank: false, emptyText: '请输入…'},
                    {xtype: 'textfield', fieldLabel: '卡类型', name: 'card_no_type', allowBlank: false, emptyText: '请输入…'},
                    {xtype: 'textfield', fieldLabel: '归属机构', name: 'dept_id', allowBlank: false, emptyText: '请输入…'}
                ]
            }, {
                xtype: 'fieldcontainer', layout: 'hbox', defaults: {flex: 1, margin: '0 0 0 10'},
                items: [
                    {xtype: 'textfield', fieldLabel: '状态', name: 'status', margin: 0, allowBlank: false, emptyText: '请输入…'},
                    {xtype: 'textfield', fieldLabel: '激活标记', name: 'active_flag', allowBlank: false, emptyText: '请输入…'},
                    {xtype: 'textfield', fieldLabel: '有效使用期起', name: 'open_date', emptyText: '请输入…'},
                    {xtype: 'textfield', fieldLabel: '有效使用期止', name: 'close_date', emptyText: '请输入…'}
                ]
            }, {
                xtype: 'fieldcontainer', layout: 'hbox', defaults: {flex: 1, margin: '0 0 0 10'},
                items: [
                    {xtype: 'textfield', fieldLabel: '验证次数', name: 'verify_count', margin: 0, emptyText: '请输入…'},
                    {xtype: 'textfield', fieldLabel: '关联订单号', name: 'rel_order_id', emptyText: '请输入…'},
                    {xtype: 'displayfield', hideLabel: true},
                    {xtype: 'displayfield', hideLabel: true}
                ]
            },
            {xtype: 'textarea', fieldLabel: '备注', name: 'memo', anchor: '100%', emptyText: '请输入…'},
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

        if (form.isValid()) {
            form.getForm().submit({
                url: '/card/' + (recId == 0 ? 'insertCardNo' : 'updateCardNo'),   //后台处理的页面
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