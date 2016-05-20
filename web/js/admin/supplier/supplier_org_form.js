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
                    {xtype: 'textfield', fieldLabel: '门店名字', name: 'org_name', margin: 0, allowBlank: false, emptyText: '请输入…'},
                    {
                        xtype: 'combo', fieldLabel: '供应商', id: this.id + '_supplier_combo', allowBlank: false, store: supplierStore,
                        displayField: 'supplier_name', valueField: 'id', name: 'supplier_id', queryMode: 'local'
                    },
                    {xtype: 'textfield', fieldLabel: '联系电话', name: 'contact_phone', emptyText: '请输入…'},
                    {xtype: 'displayfield'}
                ]
            },
            {
                xtype: 'fieldcontainer', layout: 'hbox', defaults: {flex: 1, margin: '0 0 0 10'},
                items: [
                    {
                        xtype: 'combo', fieldLabel: '是否有体检', store: yesCheckStore, displayField: 'name', margin: 0,
                        valueField: 'has_x', name: 'has_physical', queryMode: 'local'
                    },
                    {
                        xtype: 'combo', fieldLabel: '是否有洁牙', store: yesCheckStore, displayField: 'name',
                        valueField: 'has_x', name: 'has_tooth_care', queryMode: 'local'
                    },
                    {
                        xtype: 'combo', fieldLabel: '是否有基因', store: yesCheckStore, displayField: 'name',
                        valueField: 'has_x', name: 'has_gene', queryMode: 'local'
                    },
                    {xtype: 'displayfield'}
                ]
            },
            {
                xtype: 'fieldcontainer', layout: 'hbox', defaults: {flex: 1, margin: '0 0 0 10'},
                items: [
                    {xtype: 'numberfield', fieldLabel: '评分', name: 'review_score', minValue: 0, margin: 0, emptyText: '请输入…'},
                    {xtype: 'textfield', fieldLabel: '营业时间', name: 'business_hours', emptyText: '请输入…'},
                    {xtype: 'displayfield'},
                    {xtype: 'displayfield'}
                ]
            }, {
                xtype: 'fieldcontainer', layout: 'hbox', defaults: {flex: 1, margin: '0 0 0 10'},
                items: [
                    {xtype: 'textfield', fieldLabel: '周结束', name: 'weekday_end', margin: 0, emptyText: '请输入…'},
                    {xtype: 'textfield', fieldLabel: '周开始', name: 'weekday_start', emptyText: '请输入…'},
                    {xtype: 'textfield', fieldLabel: '预约开始', name: 'reserve_time_start', emptyText: '请输入…'},
                    {xtype: 'textfield', fieldLabel: '预约结束', name: 'reserve_time_end', emptyText: '请输入…'}
                ]
            },
            {
                xtype: 'fieldcontainer', layout: 'hbox', defaults: {flex: 1, margin: '0 0 0 10'},
                items: [
                    {xtype: 'textfield', fieldLabel: '省', name: 'province_id', margin: 0, emptyText: '请输入…'},
                    {xtype: 'textfield', fieldLabel: '市', name: 'city_id', emptyText: '请输入…'},
                    {xtype: 'textfield', fieldLabel: '区', name: 'area_id', emptyText: '请输入…'},
                    {xtype: 'textfield', fieldLabel: '地址', name: 'address', emptyText: '请输入…'}
                ]
            },
            {xtype: 'textarea', fieldLabel: '交通指引', name: 'traffic_guide', anchor: '100%', emptyText: '请输入…'},
            {xtype: 'textarea', fieldLabel: '简介', name: 'intro', anchor: '100%', emptyText: '请输入…'},
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

    _save: function () {
        var me = this;
        var form = me;
        var $c = this.COMPONENTS;
        var recId = $c.recId.getValue();

        if (form.isValid()) {
            form.getForm().submit({
                url: '/supplier/' + (recId == 0 ? 'insertSupplierOrg' : 'updateSupplierOrg'),   //后台处理的页面
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
        //delBtn.setDisabled(status !== '0');  //仅禁用的项目可删除
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