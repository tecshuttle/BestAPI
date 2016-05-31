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
                url: '/card/getPackageListByCardId',
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
            {header: "名称", dataIndex: 'package_name'},

            {
                header: "性别要求", dataIndex: 'sex_select', align: 'center',
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
            {header: '服务总数', dataIndex: 'dtl_count', align: 'right'},
            {
                header: "价格", dataIndex: 'price', align: 'right',
                renderer: function (val) {
                    if (val == null) return val;

                    var out = Ext.util.Format.number(val, '0.00');
                    return '￥' + out;
                }
            },
            {header: '排序', dataIndex: 'seq', align: 'right'},
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
        this.card_id = rec.id;

        var store = this.getStore();
        var proxy = store.getProxy();

        Ext.apply(proxy.extraParams, {
            card_id: rec.id
        });

        store.load();
    },

    _add: function () {
        var form = this.parent.COMPONENTS.packageForm;

        this.hide();

        form.getForm().reset();
        form.getForm().setValues({card_id: this.card_id});
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