Ext.ns('Best.card');

var cardTypeByCompanyStore = Ext.create('Ext.data.Store', {
    fields: ['id', 'card_name'],
    pageSize: 9999,
    proxy: {
        type: 'ajax',
        url: '/card/getTypeList',
        extraParams: {
            company_id: ''
        },
        reader: {
            root: 'response',
            type: 'json'
        }
    }
});

var companyDept1Store = Ext.create('Ext.data.Store', {
    fields: ['id', 'company_name'],
    proxy: {
        type: 'ajax',
        url: '/company/getList',
        extraParams: {
            company_type: 1,
            parent_id: ''
        },
        reader: {
            root: 'response',
            type: 'json'
        }
    }
});

var companyDept2Store = Ext.create('Ext.data.Store', {
    fields: ['id', 'company_name'],
    proxy: {
        type: 'ajax',
        url: '/company/getList',
        extraParams: {
            company_type: 2,
            parent_id: ''
        },
        reader: {
            root: 'response',
            type: 'json'
        }
    }
});

var cardCodeTypeStore = Ext.create('Ext.data.Store', {
    fields: ['code_type', 'name'],
    data: [
        {code_type: 'number', name: '随机数字'},
        {code_type: 'number_and_letter', name: '随机数字字母组合'}
    ]
});

Ext.define('Best.card.BatchFormUI', {
    extend: 'Ext.form.Panel',
    constructor: function (config) {
        var me = this;
        config = Ext.apply({
            title: '新建卡号',
            bodyStyle: 'padding:10px;',
            layout: 'anchor'
        }, config);

        me.COMPONENTS = {};

        Best.card.BatchFormUI.superclass.constructor.call(me, config);
    },

    initComponent: function () {
        var me = this;

        me.items = [
            {xtype: 'hiddenfield', id: this.id + '_rec_id', name: 'id', value: 0},
            {xtype: 'hiddenfield', id: this.id + '_status', name: 'status'},
            {
                xtype: 'fieldcontainer', layout: 'hbox', defaults: {flex: 1, margin: '0 0 0 10'},
                items: [
                    {
                        xtype: 'combo', fieldLabel: '发卡机构', id: this.id + '_company_combo', store: companyStore,
                        displayField: 'company_name', margin: 0, valueField: 'id', name: 'company_id', allowBlank: false, queryMode: 'local'
                    },
                    {
                        xtype: 'combo', fieldLabel: '卡名称', id: this.id + '_card_combo', store: cardTypeByCompanyStore,
                        displayField: 'card_name', valueField: 'id', name: 'card_id', allowBlank: false, queryMode: 'local'
                    },
                    {xtype: 'displayfield'}
                ]
            },
            {
                xtype: 'fieldcontainer', layout: 'hbox', defaults: {flex: 1, margin: '0 0 0 10'},
                items: [
                    {
                        xtype: 'combo', fieldLabel: '一级部门', id: this.id + '_dept1_combo', store: companyDept1Store,
                        displayField: 'company_name', valueField: 'id', name: 'dept_id', margin: 0, queryMode: 'local'
                    },
                    {
                        xtype: 'combo', fieldLabel: '二级部门', id: this.id + '_dept2_combo', store: companyDept2Store,
                        displayField: 'company_name', valueField: 'id', name: 'dept2_id', queryMode: 'local'
                    },
                    {xtype: 'displayfield'}
                ]
            },
            {
                xtype: 'fieldcontainer', layout: 'hbox', defaults: {flex: 1, margin: '0 0 0 10'},
                items: [
                    {
                        xtype: 'combo', fieldLabel: '卡类型', id: this.id + '_card_type_combo', store: cardTypeStore, displayField: 'name', margin: 0,
                        valueField: 'cardType', name: 'card_type', allowBlank: false, queryMode: 'local'
                    },
                    {
                        xtype: 'combo', fieldLabel: '卡密规则', id: this.id + '_code_type_combo', store: cardCodeTypeStore, displayField: 'name',
                        valueField: 'code_type', name: 'card_code_gen_type', allowBlank: false, queryMode: 'local'
                    },
                    {xtype: 'displayfield'}
                ]
            },
            {
                xtype: 'fieldcontainer', layout: 'hbox', defaults: {flex: 1, margin: '0 0 0 10'},
                items: [
                    {
                        xtype: 'numberfield', fieldLabel: '发卡数量', id: this.id + '_gen_quantity', name: 'gen_quantity',
                        minValue: 0, maxValue: 9999, margin: 0, allowBlank: false, emptyText: '请输入…'
                    },
                    {xtype: 'textfield', fieldLabel: '批次号', name: 'batch_no', emptyText: '请输入…'},
                    {xtype: 'textfield', fieldLabel: '提请人', name: 'proposer', emptyText: '请输入…'}
                ]
            },
            {xtype: 'textarea', fieldLabel: '批次说明', name: 'memo', anchor: '100%', emptyText: '请输入…'},
            {xtype: 'textarea', fieldLabel: '卡号列表', height: 250, id: this.id + '_gen_no', anchor: '100%', emptyText: '卡号…', editable: false},
            {xtype: 'button', text: '保存', id: this.id + '_save', width: 100},
            {xtype: 'button', text: '生成', id: this.id + '_gen', style: 'margin-left: 50px;', width: 100},
            {xtype: 'button', text: '导出', id: this.id + '_export', style: 'margin-left: 50px;', width: 100},
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
            recId: Ext.getCmp(this.id + '_rec_id'),
            status: Ext.getCmp(this.id + '_status'),
            companyCombo: Ext.getCmp(this.id + '_company_combo'),
            cardCombo: Ext.getCmp(this.id + '_card_combo'),
            codeTypeCombo: Ext.getCmp(this.id + '_code_type_combo'),
            cardTypeCombo: Ext.getCmp(this.id + '_card_type_combo'),
            dept1Combo: Ext.getCmp(this.id + '_dept1_combo'),
            dept2Combo: Ext.getCmp(this.id + '_dept2_combo'),

            genQuantity: Ext.getCmp(this.id + '_gen_quantity'),
            genNo: Ext.getCmp(this.id + '_gen_no'),

            saveBtn: Ext.getCmp(this.id + '_save'),
            genBtn: Ext.getCmp(this.id + '_gen'),
            exportBtn: Ext.getCmp(this.id + '_export'),
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
        $c.genBtn.on('click', me._gen, me);
        $c.exportBtn.on('click', me._export, me);

        $c.companyCombo.on('change', me._onChangeCompanyCombo, me);
        $c.dept1Combo.on('change', me._onChangeDept1Combo, me);
        $c.cardCombo.getStore().on('load', me._onLoadCardCombo, me);
        $c.dept2Combo.getStore().on('load', me._onLoadDept2Combo, me);

        $c.genQuantity.on('change', me._previewGenNo, me);
        $c.cardCombo.on('change', me._previewGenNo, me);
    },

    _previewGenNo: function () {
        var $c = this.COMPONENTS;
        var card_id = $c.cardCombo.getValue();
        var gen_quantity = $c.genQuantity.getValue();

        if (card_id && gen_quantity) {
            Ext.Ajax.request({
                url: '/card/previewGenNo',
                params: {
                    card_id: card_id,
                    gen_quantity: gen_quantity
                },
                success: function (res) {
                    var result = Ext.decode(res.responseText);
                    var no_list = Ext.decode(result.response);

                    if ($c.status.getValue() !== 'GEN') {
                        $c.genNo.setValue(no_list.join('   '));
                    }
                }
            });
        }
    },


    _onLoadCardCombo: function (combo, company_id, oldValue, eOpts) {
        var cardCombo = this.COMPONENTS.cardCombo;
        cardCombo.setValue(cardCombo.getValue());
    },

    _onLoadDept2Combo: function (combo, company_id, oldValue, eOpts) {
        var dept2Combo = this.COMPONENTS.dept2Combo;
        dept2Combo.setValue(dept2Combo.getValue());
    },

    _onLoadCardCombo: function (combo, company_id, oldValue, eOpts) {
        var cardCombo = this.COMPONENTS.cardCombo;
        cardCombo.setValue(cardCombo.getValue());
    },

    _onChangeCompanyCombo: function (combo, company_id, oldValue, eOpts) {
        //更新卡名
        var cardCombo = this.COMPONENTS.cardCombo;

        var store = cardCombo.getStore();
        var proxy = store.getProxy();

        Ext.apply(proxy.extraParams, {
            company_id: company_id
        });

        store.load();

        //更新一级部门名
        var dept1Combo = this.COMPONENTS.dept1Combo;

        var store1 = dept1Combo.getStore();
        var proxy1 = store1.getProxy();

        Ext.apply(proxy1.extraParams, {
            parent_id: company_id
        });

        store1.load();

        //清除表单值：卡名、一级部门、二级部门
        var recId = this.COMPONENTS.recId.getValue();
        if (cardCombo.oldValue) {
            cardCombo.setValue('');
        } else {
            cardCombo.oldValue = true;
        }

        this.COMPONENTS.dept1Combo.setValue('');
        this.COMPONENTS.dept2Combo.setValue('');
    },

    _onChangeDept1Combo: function (combo, parent_id, oldValue, eOpts) {
        //更新二级部门名
        var dept2Combo = this.COMPONENTS.dept2Combo;

        var store2 = dept2Combo.getStore();
        var proxy2 = store2.getProxy();

        Ext.apply(proxy2.extraParams, {
            parent_id: parent_id
        });

        store2.load();

        //清除表单值：二级部门
        if (dept2Combo.oldValue) {
            this.COMPONENTS.dept2Combo.setValue('');
        } else {
            dept2Combo.oldValue = true;
        }
    },

    _afterrender: function () {
        this.COMPONENTS.companyCombo.getStore().load();
    },

    _return: function () {
        this.getForm().reset();

        this.hide();

        this.COMPONENTS.dept2Combo.oldValue = null;
        this.COMPONENTS.cardCombo.oldValue = null;

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
                url: '/card/' + (recId == 0 ? 'insertCardNoGenBatch' : 'updateCardNoGenBatch'),   //后台处理的页面
                submitEmptyText: false,
                success: function (fp, o) {
                    var result = Ext.decode(o.response.responseText);

                    if (result.success) {
                        me._return();
                    } else {
                        alert('See error info by console.');
                    }
                }
            });
        }
    },

    _gen: function () {
        var me = this;
        var form = me;
        var $c = this.COMPONENTS;
        var recId = $c.recId.getValue();

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
                    }
                }
            });
        }
    },

    _export: function () {
        window.location.href = '/card/batchCardNoExport?batch_id=' + this.COMPONENTS.recId.getValue();
    },

    _delToggle: function (status) {
        var $c = this.COMPONENTS;

        if (status === -1 || status === 'GEN') {
            $c.genBtn.hide();
        } else {
            $c.genBtn.show();
        }

        if (status === 'GEN') {
            $c.exportBtn.show();
        } else {
            $c.exportBtn.hide();
        }

        if (status === 'GEN') {
            $c.companyCombo.disable();
            $c.cardCombo.disable();
            $c.dept1Combo.disable();
            $c.dept2Combo.disable();
            $c.codeTypeCombo.disable();
            $c.genQuantity.disable();
            $c.cardTypeCombo.disable();
        } else {
            $c.companyCombo.enable();
            $c.cardCombo.enable();
            $c.dept1Combo.enable();
            $c.dept2Combo.enable();
            $c.codeTypeCombo.enable();
            $c.genQuantity.enable();
            $c.cardTypeCombo.enable();
        }

        if (status === 'GEN') {
            Ext.Ajax.request({
                url: '/card/getCardNoList',
                params: {
                    batch_id: $c.recId.getValue(),
                    start: 0,
                    limit: 9999
                },
                method: 'get',
                success: function (res) {
                    var result = Ext.decode(res.responseText);
                    var no_list = [];

                    for (var i in result.response) {
                        var no = result.response[i].card_no;
                        no_list.push(no);
                    }

                    $c.genNo.setValue(no_list.join('   '));
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