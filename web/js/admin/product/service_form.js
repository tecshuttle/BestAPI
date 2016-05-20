Ext.ns('Best.product');

Ext.define('Best.product.FormUI', {
    extend: 'Ext.form.Panel',
    constructor: function (config) {
        var me = this;
        config = Ext.apply({
            title: '编辑',
            bodyStyle: 'padding:10px;',
            layout: 'anchor'
        }, config);

        me.COMPONENTS = {};

        Best.product.FormUI.superclass.constructor.call(me, config);
    },

    initComponent: function () {
        var me = this;

        me.items = [
            {xtype: 'hiddenfield', id: this.id + '_rec_id', name: 'id', value: 0},
            {xtype: 'hiddenfield', id: this.id + '_active_flag', name: 'active_flag', value: 1},
            {
                xtype: 'fieldcontainer', layout: 'hbox', defaults: {flex: 1, margin: '0 0 0 10'},
                items: [
                    {xtype: 'textfield', fieldLabel: '服务名称', name: 'service_name', margin: 0, allowBlank: false, emptyText: '请输入…'},
                    {xtype: 'textfield', fieldLabel: '服务代码', name: 'service_code', emptyText: '请输入…'},
                    {
                        xtype: 'combo', fieldLabel: '服务类型', store: productServiceTypeStore, displayField: 'name',
                        valueField: 'service_type', name: 'service_type', queryMode: 'local'
                    },
                    {
                        xtype: 'combo', fieldLabel: '关联类型', store: productRelTypeStore, displayField: 'name',
                        valueField: 'rel_type', name: 'rel_type', queryMode: 'local'
                    }
                ]
            }, {
                xtype: 'fieldcontainer', layout: 'hbox', defaults: {flex: 1, margin: '0 0 0 10'},
                items: [
                    {
                        xtype: 'combo', fieldLabel: '状态', store: statusStore, displayField: 'name', margin: 0,
                        valueField: 'status', name: 'status', queryMode: 'local'
                    },
                    {xtype: 'textfield', fieldLabel: 'LOGO', name: 'logo_img', emptyText: '请输入…'},
                    {xtype: 'numberfield', fieldLabel: '价格', name: 'price', minValue: 0, emptyText: '请输入…'},
                    {xtype: 'displayfield'}
                ]
            }, {
                xtype: 'fieldcontainer', layout: 'hbox', defaults: {flex: 1, margin: '0 0 0 10'},
                items: [
                    {
                        xtype: 'combo', fieldLabel: '性别要求', store: sexSelectStore, displayField: 'name', margin: 0,
                        valueField: 'sex_select', name: 'sex_select', queryMode: 'local'
                    },
                    {xtype: 'textfield', fieldLabel: '覆盖城市', name: 'city_select', emptyText: '请输入…'},
                    {xtype: 'displayfield'},
                    {xtype: 'displayfield'}
                ]
            },
            {xtype: 'textarea', fieldLabel: '服务简介', name: 'intro', anchor: '100%', emptyText: '请输入…'},
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

        Best.product.FormUI.superclass.initComponent.call(me);
    }
});

Ext.define('Best.product.FormAction', {
    extend: 'Best.product.FormUI',
    constructor: function (config) {
        Best.product.FormAction.superclass.constructor.call(this, config);
    },

    initComponent: function () {
        Best.product.FormAction.superclass.initComponent.call(this);

        Ext.apply(this.COMPONENTS, {
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

        Best.product.FormAction.superclass.initEvents.call(me);

        $c.saveBtn.on('click', me._save, me);
        $c.delBtn.on('click', me._del, me);
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
                url: '/service/' + (recId == 0 ? 'insertProdService' : 'updateProdService'),   //后台处理的页面
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
        var delBtn = this.COMPONENTS.delBtn;

        delBtn.setHidden(status === -1);     //新增隐藏删除按钮
        delBtn.setDisabled(status !== '0');  //仅禁用的项目可删除
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

Best.product.serviceInfo = Best.product.FormAction;

//end file