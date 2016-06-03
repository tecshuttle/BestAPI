Ext.ns('Tomtalk.grid');

var validDateTypeStore = Ext.create('Ext.data.Store', {
    fields: ['validDateType', 'name'],
    data: [
        {validDateType: "DAY", name: "天"},
        {validDateType: "MONTH", name: "月"},
        {validDateType: "YEAR", name: "年"}
    ]
});

Ext.define('Tomtalk.grid.FormUI', {
    extend: 'Ext.form.Panel',
    constructor: function (config) {
        var me = this;
        config = Ext.apply({
            title: '卡编辑',
            collapsible: true,
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
            {xtype: 'hiddenfield', id: this.id + '_active_flag', name: 'active_flag', value: 1},
            {
                xtype: 'fieldcontainer', layout: 'hbox', defaults: {flex: 1, margin: '0 0 0 10'},
                items: [
                    {
                        xtype: 'textfield', fieldLabel: '卡名', id: this.id + '_card_name', name: 'card_name',
                        margin: '0 0 0 0', allowBlank: false, emptyText: '请输入…'
                    },
                    {xtype: 'textfield', fieldLabel: '卡代码', id: this.id + '_card_code', name: 'card_code', allowBlank: false, emptyText: '请输入…'},
                    {xtype: 'textfield', fieldLabel: 'LOGO', name: 'logo_img', flex: 2.02, emptyText: '请输入…'}
                ]
            }, {
                xtype: 'fieldcontainer', layout: 'hbox', defaults: {flex: 1, margin: '0 0 0 10'},
                items: [
                    {
                        xtype: 'combo', fieldLabel: '状态', store: statusStore, displayField: 'name', margin: '0 0 0 0',
                        valueField: 'status', name: 'status', queryMode: 'local'
                    },
                    {xtype: 'numberfield', fieldLabel: '价格', name: 'price', padding: 0, minValue: 0, allowBlank: false, emptyText: '请输入…'},
                    {
                        xtype: 'combo', fieldLabel: '发卡机构', id: this.id + '_company_combo', store: companyStore, displayField: 'company_name',
                        valueField: 'id', name: 'company_id', queryMode: 'local', allowBlank: false, flex: 1
                    },
                    {xtype: 'textfield', fieldLabel: '发卡机构别名', name: 'company_abbr', emptyText: '请输入…'}
                ]
            }, {
                xtype: 'fieldcontainer', layout: 'hbox', defaults: {flex: 1, padding: '0 0 0 10'},
                items: [
                    {xtype: 'numberfield', fieldLabel: '套餐总数', name: 'package_total', minValue: 0, margin: '0 0 0 0', allowBlank: false, emptyText: '请输入…'},
                    {xtype: 'numberfield', fieldLabel: '可选套餐数', name: 'select_count', minValue: 0, allowBlank: false, emptyText: '请输入…'},
                    {xtype: 'numberfield', fieldLabel: '允许修改套餐数', name: 'allow_change_count', minValue: 0, allowBlank: false, emptyText: '请输入…'},
                    {xtype: 'displayfield'}
                ]
            }, {
                xtype: 'fieldcontainer', layout: 'hbox', defaults: {flex: 1, padding: '0 0 0 10'},
                items: [
                    {xtype: 'numberfield', fieldLabel: '卡号长度', name: 'card_no_length', minValue: 0, padding: '0 0 0 0', emptyText: '请输入…'},
                    {xtype: 'numberfield', fieldLabel: '卡密长度', name: 'card_code_length', minValue: 0, emptyText: '请输入…'},
                    {xtype: 'textfield', fieldLabel: '卡号前缀', id: this.id + '_card_no_prefix', name: 'card_no_prefix', emptyText: '请输入…'},
                    {xtype: 'textfield', fieldLabel: '卡号末位流水号', id: this.id + '_card_no_sn_length', name: 'card_no_sn_length', emptyText: '请输入…'}
                ]
            }, {
                xtype: 'fieldcontainer', layout: 'hbox', defaults: {flex: 1},
                items: [
                    {
                        xtype: 'fieldcontainer', layout: 'hbox', flex: 0.98, defaults: {padding: '0 0 0 0'},
                        fieldLabel: '有效期',
                        items: [
                            {
                                xtype: 'numberfield', hideLabel: true, fieldLabel: '有效期值', name: 'valid_date_value', minValue: 0,
                                flex: 1, padding: '0 10 0 0', allowBlank: false, emptyText: '请输入…'
                            },
                            {
                                xtype: 'combo', hideLabel: true, fieldLabel: '有效期单位', store: validDateTypeStore, displayField: 'name',
                                valueField: 'validDateType', name: 'valid_date_type', queryMode: 'local', allowBlank: false, flex: 1
                            }
                        ]
                    },
                    {xtype: 'textfield', fieldLabel: '可选城市', name: 'city_select', flex: 3, padding: '0 0 0 10', emptyText: '请输入…'}
                ]
            },
            {xtype: 'textarea', fieldLabel: '简介', anchor: '100%', name: 'intro', emptyText: '请输入…'},
            {xtype: 'textarea', fieldLabel: '使用规则', anchor: '100%', name: 'use_rule', emptyText: '请输入…'},
            {
                xtype: 'fieldcontainer', layout: 'hbox', defaults: {}, margin: 0,
                items: [
                    {xtype: 'button', text: '保存', id: this.id + '_save', style: 'margin-right: 50px;', width: 100},
                    {xtype: 'button', text: '返回', id: this.id + '_return', width: 100},
                    {xtype: 'displayfield', flex: 1},
                    {xtype: 'button', text: '删除', cls: 'del-btn', id: this.id + '_delete', style: 'margin-left: 50px;', width: 100}
                ]
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
            companyCombo: Ext.getCmp(this.id + '_company_combo'),
            activeFlag: Ext.getCmp(this.id + '_active_flag'),
            recId: Ext.getCmp(this.id + '_rec_id'),

            //使用中的，不能编辑项
            cardName: Ext.getCmp(this.id + '_card_name'),
            cardCode: Ext.getCmp(this.id + '_card_code'),
            cardNoPrefix: Ext.getCmp(this.id + '_card_no_prefix'),
            cardNoSnLength: Ext.getCmp(this.id + '_card_no_sn_length'),

            saveBtn: Ext.getCmp(this.id + '_save'),
            delBtn: Ext.getCmp(this.id + '_delete'),
            returnBtn: Ext.getCmp(this.id + '_return')
        });
    },

    initEvents: function () {
        var me = this;
        var $c = this.COMPONENTS;

        Tomtalk.grid.FormAction.superclass.initEvents.call(me);

        this.on('boxready', me._afterrender, me);

        $c.saveBtn.on('click', me._save, me);
        $c.delBtn.on('click', me._del, me);
        $c.returnBtn.on('click', me._return, me);
    },

    _afterrender: function () {
        this.COMPONENTS.companyCombo.getStore().load();
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
                url: '/card/' + (recId == 0 ? 'insertType' : 'updateType'),   //后台处理的页面
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
        var $c = this.COMPONENTS;
        var delBtn = this.COMPONENTS.delBtn;
        var saveBtn = this.COMPONENTS.saveBtn;

        //使用中的产品，部分可编辑
        $c.cardName.setDisabled(status === '1');
        $c.cardCode.setDisabled(status === '1');
        $c.companyCombo.setDisabled(status === '1');
        $c.cardNoPrefix.setDisabled(status === '1');
        $c.cardNoSnLength.setDisabled(status === '1');

        //下线的产品，不能编辑，只有查看
        if (status === '2') {
            delBtn.setHidden(true);
            saveBtn.setHidden(true);
            return;
        } else {
            saveBtn.show(true);
        }

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

Tomtalk.grid.AccountForm = Tomtalk.grid.FormAction;

//end file