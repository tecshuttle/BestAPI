Ext.ns('Best.product');

Best.product.serviceListGridUI = Ext.extend(Ext.grid.GridPanel, {
    constructor: function (config) {
        var me = this;
        config = Ext.apply({
            title: '供应商服务列表',
            margin: '10 0 0 0',
            columnLines: true,
            dockedItems: [{
                xtype: 'toolbar',
                items: [
                    {text: '返回', hidden: true, id: this.id + '_return', ref: 'return'},
                    {text: '新建', id: this.id + '_add', ref: 'add'}
                ]
            }],
            forceFit: true,
            viewConfig: {
                stripeRows: true,
                enableRowBody: true,
                showPreview: true
            }
        }, config);

        me.COMPONENTS = {};

        Best.product.serviceListGridUI.superclass.constructor.call(me, config);
    },

    initComponent: function () {
        var me = this;

        var store = Ext.create('Ext.data.Store', {
            //autoLoad: true,
            pageSize: 20,
            fields: me.fields,
            proxy: {
                type: 'ajax',
                url: '/service/getProdServiceMapList',
                reader: {
                    type: 'json',
                    root: 'response',
                    totalProperty: 'total'
                }
            }
        });

        me.store = store;

        me.columns = [
            {header: "ID", dataIndex: 'id', hidden: true},
            {header: '供应商', dataIndex: 'supplier_name'},
            {header: '服务名称', dataIndex: 'supplier_service_name', flex: 1},
            {
                header: '服务类型', dataIndex: 'service_type', align: 'center',
                renderer: function (v, b, rec) {
                    var type = {
                        MAN: '男科',
                        HEALTH_CARE: '护理',
                        MOUTH: '口腔',
                        PHYSICAL: '体检',
                        WOMAN: '妇科',
                        HEALTH: '保健',
                        NUTRI: '营养',
                        SALES: '销售',
                        CHRONIC_ILLNESS: '慢性病',
                        MSG: '消息',
                        GENE: '基因',
                        TEETH: '齿科',
                        DOCTOR_ACCOMPANY: '名医会诊',
                        DOCTOR_RESERVE: '名医预约'
                    };

                    if (type[v]) {
                        return type[v];
                    } else {
                        return v;
                    }
                }
            },
            {
                header: '市场价格', dataIndex: 'market_price', align: 'right',
                renderer: function (val) {
                    if (val == null) return val;
                    var out = Ext.util.Format.number(val, '0.00');
                    return '￥' + out;
                }
            },
            {
                header: '采购价格', dataIndex: 'cost_price', align: 'right',
                renderer: function (val) {
                    if (val == null) return val;
                    var out = Ext.util.Format.number(val, '0.00');
                    return '￥' + out;
                }
            },
            {
                header: '业务状态', dataIndex: 'status', align: 'center',
                renderer: function (v, b, rec) {
                    if (v == 'IN_SERVICE') {
                        return '使用中';
                    } else {
                        return v;
                    }
                }
            },
            {
                header: '排序', dataIndex: 'seq', align: 'right'
            },
            {
                header: "操作",
                dataIndex: 'id',
                align: 'center',
                xtype: 'actioncolumn',
                name: 'opertation',
                width: 80,
                items: [{
                    glyph: '编辑',
                    handler: function (grid, rowIndex, colIndex) {
                        var rec = grid.getStore().getAt(rowIndex);
                        me._edit(rec);
                    }
                }]
            }
        ];

        me.bbar = {
            xtype: 'pagingtoolbar',
            store: store,
            inputItemWidth: 50,
            displayInfo: true,
            beforePageText: '页',
            afterPageText: '/ {0}',
            displayMsg: "显示第 {0} 条到 {1} 条记录，一共 {2} 条",
            emptyMsg: "没有记录"
        };

        Best.product.serviceListGridUI.superclass.initComponent.call(me);
    }
});

Best.product.serviceListGridAction = Ext.extend(Best.product.serviceListGridUI, {
    constructor: function (config) {
        Best.product.serviceListGridAction.superclass.constructor.call(this, config);
    },

    initComponent: function () {
        Best.product.serviceListGridAction.superclass.initComponent.call(this);
    },

    initEvents: function () {
        var me = this;
        var $c = this.COMPONENTS;
        Best.product.serviceListGridAction.superclass.initEvents.call(me);

        this.on('boxready', me._afterrender, me);
        this.down('button[ref*=return]').on('click', me._returnFrom, me);
        this.down('button[ref*=add]').on('click', me._add, me);
    },

    _afterrender: function () {
        var $c = this.COMPONENTS;
    },

    loadList: function (rec) {
        this.xzh_service_id = rec.id;
        this.rel_type = rec.rel_type;

        var store = this.getStore();
        var proxy = store.getProxy();

        Ext.apply(proxy.extraParams, {
            xzh_service_id: rec.id
        });

        store.load();
    },

    _add: function () {
        var form = this.parent.COMPONENTS.serviceListForm;

        this.hide();

        form.getForm().reset();
        form._loadServiceCombo();
        form.getForm().setValues({xzh_service_id: this.xzh_service_id});
        form.show();
    },

    _edit: function (rec) {
        var form = this.parent.COMPONENTS.serviceListForm;

        this.hide();
        form._loadServiceCombo();
        form.getForm().setValues(rec.data);
        form.show();
    },

    _returnFrom: function () {
        this.hide();
        this.parent.COMPONENTS.grid.getStore().load();
        this.parent._return();
    }
});

Best.product.serviceListGrid = Best.product.serviceListGridAction;

//end file