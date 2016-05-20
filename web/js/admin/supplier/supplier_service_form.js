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
            {xtype: 'hiddenfield', id: this.id + '_active_flag', name: 'active_flag', value: 1},
            {
                xtype: 'fieldcontainer', layout: 'hbox', defaults: {flex: 1, margin: '0 0 0 10'},
                items: [
                    {xtype: 'textfield', fieldLabel: '服务名称 ', name: 'service_name', margin: 0, allowBlank: false, emptyText: '请输入…'},
                    {
                        xtype: 'combo', fieldLabel: '供应商', id: this.id + '_supplier_combo', store: supplierStore,
                        displayField: 'supplier_name', valueField: 'id', name: 'supplier_id', queryMode: 'local'
                    },
                    {xtype: 'textfield', fieldLabel: '服务代码', name: 'service_code', emptyText: '请输入…'},
                    {
                        xtype: 'combo', fieldLabel: '服务类型', store: serviceTypeStore, displayField: 'name',
                        valueField: 'service_type', name: 'service_type', queryMode: 'local'
                    }
                ]
            }, {
                xtype: 'fieldcontainer', layout: 'hbox', defaults: {flex: 1, margin: '0 0 0 10'},
                items: [
                    {
                        xtype: 'combo', fieldLabel: '状态', store: statusStore, displayField: 'name', margin: 0,
                        valueField: 'status', name: 'status', queryMode: 'local'
                    },
                    {xtype: 'numberfield', fieldLabel: '市场价格', name: 'market_price', minValue: 0, emptyText: '请输入…'},
                    {xtype: 'numberfield', fieldLabel: '采购价格', name: 'cost_price', minValue: 0, emptyText: '请输入…'},
                    {xtype: 'displayfield'}
                ]
            }, {
                xtype: 'fieldcontainer', layout: 'hbox', defaults: {flex: 1, margin: '0 0 0 10'},
                items: [
                    {
                        xtype: 'combo', fieldLabel: '是否提供卡密', store: yesCheckStore, displayField: 'name', margin: 0,
                        valueField: 'has_x', name: 'is_use_verify_code', queryMode: 'local'
                    },
                    {xtype: 'textfield', fieldLabel: 'LOGO', name: 'logo_img', emptyText: '请输入…'},
                    {
                        xtype: 'combo', fieldLabel: '性别要求', store: sexSelectStore, displayField: 'name',
                        valueField: 'sex_select', name: 'sex_select', queryMode: 'local'
                    },
                    {xtype: 'textfield', fieldLabel: '覆盖城市', name: 'city_select', emptyText: '请输入…'}
                ]
            }, {
                xtype: 'fieldcontainer', layout: 'hbox', defaults: {flex: 1, margin: '0 0 0 10'},
                items: [
                    {xtype: 'textfield', fieldLabel: '工作时间', name: 'business_hours', margin: 0, emptyText: '请输入…'},
                    {xtype: 'textfield', fieldLabel: '预约时段起', name: 'reserve_period_start', emptyText: '请输入…'},
                    {xtype: 'textfield', fieldLabel: '预约时段止', name: 'reserve_period_end', emptyText: '请输入…'},
                    {xtype: 'displayfield'}
                ]
            },
            {xtype: 'textarea', fieldLabel: '服务简介', name: 'intro', anchor: '100%', emptyText: '请输入…'},
            {xtype: 'textarea', fieldLabel: '备注', name: 'memo', anchor: '100%', emptyText: '请输入…'},
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
            activeFlag: Ext.getCmp(this.id + '_active_flag'),
            supplierCombo: Ext.getCmp(this.id + '_supplier_combo'),
            saveBtn: Ext.getCmp(this.id + '_save'),
            delBtn: Ext.getCmp(this.id + '_delete'),
            returnBtn: Ext.getCmp(this.id + '_return')
        });
    },

    initEvents: function () {
        var me = this;
        var $c = this.COMPONENTS;

        Tomtalk.grid.FormAction.superclass.initEvents.call(me);

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

    _loadSupplierCombo: function () {
        this.COMPONENTS.supplierCombo.getStore().load();
    },

    _save: function () {
        var me = this;
        var form = me;
        var $c = this.COMPONENTS;
        var recId = $c.recId.getValue();

        if (form.isValid()) {
            form.getForm().submit({
                url: '/supplier/' + (recId == 0 ? 'insertSupplierService' : 'updateSupplierService'),   //后台处理的页面
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

Tomtalk.grid.AccountForm = Tomtalk.grid.FormAction;

//end file