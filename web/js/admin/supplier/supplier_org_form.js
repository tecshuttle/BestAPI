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
            { xtype: 'textfield', fieldLabel: '供应商ID', name: 'supplier_id', anchor: '50%', allowBlank: false, emptyText: '请输入…'},
            { xtype: 'textfield', fieldLabel: '门店名字', name: 'org_name', anchor: '50%', allowBlank: false, emptyText: '请输入…'},
            { xtype: 'textfield', fieldLabel: '省', name: 'province_id', anchor: '50%', allowBlank: false, emptyText: '请输入…'},
            { xtype: 'textfield', fieldLabel: '市', name: 'city_id', anchor: '50%', allowBlank: false, emptyText: '请输入…'},
            { xtype: 'textfield', fieldLabel: '区', name: 'area_id', anchor: '50%', allowBlank: false, emptyText: '请输入…'},
            { xtype: 'textfield', fieldLabel: '地址', name: 'address', anchor: '50%', allowBlank: false, emptyText: '请输入…'},
            { xtype: 'textfield', fieldLabel: '联系电话', name: 'contact_phone', anchor: '50%', allowBlank: false, emptyText: '请输入…'},
            { xtype: 'textfield', fieldLabel: '交通指引', name: 'traffic_guide', anchor: '50%', allowBlank: false, emptyText: '请输入…'},
            { xtype: 'textfield', fieldLabel: '是否有体检', name: 'has_physical', anchor: '50%', allowBlank: false, emptyText: '请输入…'},
            { xtype: 'textfield', fieldLabel: '是否有洁牙', name: 'has_tooth_care', anchor: '50%', allowBlank: false, emptyText: '请输入…'},
            { xtype: 'textfield', fieldLabel: '是否基因', name: 'has_gene', anchor: '50%', allowBlank: false, emptyText: '请输入…'},
            { xtype: 'textfield', fieldLabel: '评分', name: 'review_score', anchor: '50%', allowBlank: false, emptyText: '请输入…'},
            { xtype: 'textfield', fieldLabel: '营业时间', name: 'business_hours', anchor: '50%', allowBlank: false, emptyText: '请输入…'},
            { xtype: 'textfield', fieldLabel: '周开始', name: 'weekday_start', anchor: '50%', allowBlank: false, emptyText: '请输入…'},
            { xtype: 'textfield', fieldLabel: '周结束', name: 'weekday_end', anchor: '50%', allowBlank: false, emptyText: '请输入…'},
            { xtype: 'textfield', fieldLabel: '预约开始', name: 'reserve_time_start', anchor: '50%', allowBlank: false, emptyText: '请输入…'},
            { xtype: 'textfield', fieldLabel: '预约结束', name: 'reserve_time_end', anchor: '50%', allowBlank: false, emptyText: '请输入…'},
            { xtype: 'textfield', fieldLabel: '简介', name: 'intro', anchor: '50%', allowBlank: false, emptyText: '请输入…'},
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