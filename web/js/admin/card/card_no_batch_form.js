Ext.ns('Best.card');

var cardCodeTypeStore = Ext.create('Ext.data.Store', {
    fields: ['code_type', 'name'],
    data: [
        {code_type: 'number', name: '6位随机数字'},
        {code_type: 'number_and_letter', name: '6位随机数字字母组合'}
    ]
});

Ext.define('Best.card.BatchFormUI', {
    extend: 'Ext.form.Panel',
    constructor: function (config) {
        var me = this;
        config = Ext.apply({
            title: '批量新建卡号',
            bodyStyle: 'padding:10px;',
            layout: 'anchor'
        }, config);

        me.COMPONENTS = {};

        Best.card.BatchFormUI.superclass.constructor.call(me, config);
    },

    initComponent: function () {
        var me = this;

        me.items = [
            {
                xtype: 'fieldcontainer', layout: 'hbox', defaults: {flex: 1, margin: '0 0 0 10'},
                items: [
                    {
                        xtype: 'combo', fieldLabel: '发卡机构', id: this.id + '_company_combo', store: companyStore,
                        displayField: 'company_name', margin: 0, valueField: 'id', name: 'dept_id', allowBlank: false, queryMode: 'local'
                    },
                    {
                        xtype: 'combo', fieldLabel: '下级部门', store: companyStore, displayField: 'company_name',
                        valueField: 'id', name: 'dept2_id', queryMode: 'local'
                    },
                    {
                        xtype: 'displayfield'
                    }
                ]
            }, {
                xtype: 'fieldcontainer', layout: 'hbox', defaults: {flex: 1, margin: '0 0 0 10'},
                items: [
                    {
                        xtype: 'combo', fieldLabel: '卡类型', store: cardTypeStore, displayField: 'name', margin: 0,
                        valueField: 'cardType', name: 'card_no_type', allowBlank: false, queryMode: 'local'
                    },
                    {
                        xtype: 'combo', fieldLabel: '卡密规则', store: cardCodeTypeStore, displayField: 'name',
                        valueField: 'code_type', name: 'card_code_type', allowBlank: false, queryMode: 'local'
                    },
                    {xtype: 'numberfield', fieldLabel: '卡数量', name: 'amount', minValue: 0, allowBlank: false, emptyText: '请输入…'}
                ]
            },
            {xtype: 'textarea', fieldLabel: '批次号', name: 'batch_id', anchor: '100%', allowBlank: false, emptyText: '请输入…'},
            {xtype: 'button', text: '保存', id: this.id + '_save', width: 100},
            {xtype: 'button', text: '返回', id: this.id + '_return', style: 'margin-left: 50px;', width: 100}
        ];

        Best.card.BatchFormUI.superclass.initComponent.call(me);
    }
});

Ext.define('Best.card.BatchFormAction', {
    extend: 'Best.card.BatchFormUI',
    constructor: function (config) {
        Best.card.BatchFormAction.superclass.constructor.call(this, config);
    },

    initComponent: function () {
        Best.card.BatchFormAction.superclass.initComponent.call(this);

        Ext.apply(this.COMPONENTS, {
            companyCombo: Ext.getCmp(this.id + '_company_combo'),
            saveBtn: Ext.getCmp(this.id + '_save'),
            returnBtn: Ext.getCmp(this.id + '_return')
        });
    },

    initEvents: function () {
        var me = this;
        var $c = this.COMPONENTS;

        Best.card.BatchFormAction.superclass.initEvents.call(me);

        this.on('boxready', me._afterrender, me);

        $c.saveBtn.on('click', me._save, me);
        $c.returnBtn.on('click', me._return, me);
    },

    _afterrender: function () {
        this.COMPONENTS.companyCombo.getStore().load();
    },

    _return: function () {
        this.getForm().reset();

        this.hide();

        if (this.up()) {
            this.up()._returnFrom();
        }
    },

    _save: function () {
        var me = this;
        var form = me;
        var $c = this.COMPONENTS;

        if (form.isValid()) {
            form.getForm().submit({
                url: '/card/insertCardNoBatch',   //后台处理的页面
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

Best.card.BatchForm = Best.card.BatchFormAction;

//end file