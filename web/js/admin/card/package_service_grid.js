Ext.ns('Best.product');

Best.product.packageGridUI = Ext.extend(Ext.grid.GridPanel, {
    constructor: function (config) {
        var me = this;
        config = Ext.apply({
            columnLines: true,
            dockedItems: [{
                xtype: 'toolbar',
                items: [
                    {text: '返回', id: this.id + '_return', ref: 'return'},
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

        Best.product.packageGridUI.superclass.constructor.call(me, config);
    },

    initComponent: function () {
        var me = this;

        var store = Ext.create('Ext.data.Store', {
            //autoLoad: true,
            pageSize: 20,
            fields: me.fields,
            proxy: {
                type: 'ajax',
                url: '/service/getProdServiceListByPackageId',
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
            {header: '服务名称', dataIndex: 'service_name', width: 200},
            {
                header: '服务类型', dataIndex: 'service_type', align: 'center',
                renderer: function (v, b, rec) {
                    if (v == 'OUTSOURCE') {
                        return '外部资源';
                    } else if (v == 'MSG_PUSH') {
                        return '资讯推送';
                    } else if (v == 'LECTURE') {
                        return '健康讲座';
                    } else {
                        return v;
                    }
                }
            },
            {
                header: '关联类型', dataIndex: 'rel_type', align: 'center',
                renderer: function (v, b, rec) {
                    var type = {
                        MAN: '男科',
                        HEALTH_CARE: '护理',
                        MOUTH: '口腔',
                        PHYSICAL: '体检',
                        WOMAN: '女科',
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
                header: '性别要求', dataIndex: 'sex_select', align: 'center',
                renderer: function (v, b, rec) {
                    if (v == 'ALL') {
                        return '全部';
                    } else if (v == 'M') {
                        return '男';
                    } else if (v == 'F') {
                        return '女';
                    } else {
                        return v;
                    }
                }
            },
            {
                header: "激活状态", dataIndex: 'active_flag', align: 'center',
                renderer: function (v, b, rec) {
                    if (v == 0) {
                        return '未激活';
                    } else if (v == 1) {
                        return '已激活';
                    } else {
                        return v;
                    }
                }
            },
            {
                header: "使用状态", dataIndex: 'status', align: 'center',
                renderer: function (v, b, rec) {
                    if (v == 0) {
                        return '禁用';
                    } else if (v == 1) {
                        return '启用';
                    } else {
                        return v;
                    }
                }
            },
            {
                header: '售价', dataIndex: 'price', align: 'right',
                renderer: function (val) {
                    if (val == null) return val;
                    var out = Ext.util.Format.number(val, '0.00');
                    return '￥' + out;
                }
            },
            {
                header: "操作",
                dataIndex: 'id',
                align: 'center',
                xtype: 'actioncolumn',
                name: 'opertation',
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

        Best.product.packageGridUI.superclass.initComponent.call(me);
    }
});

Best.product.packageGridAction = Ext.extend(Best.product.packageGridUI, {
    constructor: function (config) {
        Best.product.packageGridAction.superclass.constructor.call(this, config);
    },

    initComponent: function () {
        Best.product.packageGridAction.superclass.initComponent.call(this);
    },

    initEvents: function () {
        var me = this;
        var $c = this.COMPONENTS;
        Best.product.packageGridAction.superclass.initEvents.call(me);

        this.on('boxready', me._afterrender, me);
        this.down('button[ref*=return]').on('click', me._returnFrom, me);
        this.down('button[ref*=add]').on('click', me._add, me);
    },

    _afterrender: function () {
        var $c = this.COMPONENTS;
    },

    loadList: function (rec) {
        this.package_id = rec.id;

        var store = this.getStore();
        var proxy = store.getProxy();

        Ext.apply(proxy.extraParams, {
            package_id: rec.id
        });

        store.load();
    },

    _add: function () {
        var form = this.parent.COMPONENTS.packageForm;

        this.hide();

        form.getForm().reset();
        form.getForm().setValues({package_id: this.package_id});
        form.show();
    },

    _edit: function (rec) {
        var form = this.parent.COMPONENTS.packageForm;

        this.hide();

        form.getForm().setValues(rec.data);
        form.show();
    },

    _returnFrom: function () {
        this.hide();
        this.parent.COMPONENTS.grid.getStore().load();
        this.parent._return();
    }
});

Best.product.packageGrid = Best.product.packageGridAction;

//end file