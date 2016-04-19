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
            { xtype: 'textfield', fieldLabel: '激活标记', name: 'active_flag', anchor: '50%', allowBlank: false, emptyText: '请输入…'},
            { xtype: 'textfield', fieldLabel: '健康卡ID', name: 'card_id', anchor: '50%', allowBlank: false, emptyText: '请输入…'},
            { xtype: 'textfield', fieldLabel: '名字', name: 'package_name', anchor: '50%', allowBlank: false, emptyText: '请输入…'},
            { xtype: 'textfield', fieldLabel: '套餐号', name: 'package_code', anchor: '50%', allowBlank: false, emptyText: '请输入…'},
            { xtype: 'textfield', fieldLabel: '性别要求', name: 'sex_select', anchor: '50%', allowBlank: false, emptyText: '请输入…'},
            { xtype: 'textfield', fieldLabel: '服务总数', name: 'dtl_count', anchor: '50%', allowBlank: false, emptyText: '请输入…'},
            { xtype: 'textfield', fieldLabel: '说明', name: 'intro', anchor: '50%', allowBlank: false, emptyText: '请输入…'},
            { xtype: 'textfield', fieldLabel: '价格', name: 'price', anchor: '50%', allowBlank: false, emptyText: '请输入…'},
            {
                xtype: 'button',
                text: '保存',
                id: this.id + '_save',
                width: 100
            },
            {
                xtype: 'button',
                text: '返回',
                id: this.id + '_return',
                style: 'margin-left: 50px;',
                width: 100
            }
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
                url: '/card/' + (recId == 0 ? 'insertPackage' : 'updatePackage'),   //后台处理的页面
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