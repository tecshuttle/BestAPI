Ext.ns('Best.product');

Best.product.packageServiceGridUI = Ext.extend(Ext.grid.GridPanel, {
    constructor: function (config) {
        var me = this;
        config = Ext.apply({
            title: '套餐服务列表',
            margin: '10 0 0 0',
            columnLines: true,
            dockedItems: [{
                xtype: 'toolbar',
                items: [
                    {text: '返回', hidden: true, ref: 'return'},
                    {text: '新建', ref: 'add'}
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

        Best.product.packageServiceGridUI.superclass.constructor.call(me, config);
    },

    initComponent: function () {
        var me = this;

        var store = Ext.create('Ext.data.Store', {
            //autoLoad: true,
            pageSize: 20,
            //fields: me.fields,
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
            {
                header: '服务名称', dataIndex: 'service_name', width: 200,
                renderer: function (v, b, rec) {
                    return v ? v : rec.data.service_alias;
                }
            },
            {
                header: '服务类型', dataIndex: 'cp_code_name', width: 200,
                renderer: function (v, b, rec) {
                    return v ? v : rec.data.service_alias;
                }
            },
            {header: '数量', dataIndex: 'quantity', align: 'right'},
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
                header: '状态', dataIndex: 'status', align: 'center',
                renderer: function (v, b, rec) {
                    if (v == 0) {
                        return '新建立';
                    } else if (v == 1) {
                        return '使用中';
                    } else if (v == 2) {
                        return '下线';
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

        Best.product.packageServiceGridUI.superclass.initComponent.call(me);
    }
});

Best.product.packageServiceGridAction = Ext.extend(Best.product.packageServiceGridUI, {
    constructor: function (config) {
        Best.product.packageServiceGridAction.superclass.constructor.call(this, config);
    },

    initComponent: function () {
        Best.product.packageServiceGridAction.superclass.initComponent.call(this);

        Ext.apply(this.COMPONENTS, {
            addBtn: this.down('button[ref*=add]')
        });
    },

    initEvents: function () {
        var me = this;
        var $c = this.COMPONENTS;

        Best.product.packageServiceGridAction.superclass.initEvents.call(me);

        this.on('boxready', me._afterrender, me);
        this.on('boxready', me._afterrender, me);
        this.getStore().on('load', me._onLoad, me);
        this.down('button[ref*=return]').on('click', me._returnFrom, me);
        this.down('button[ref*=add]').on('click', me._add, me);
    },

    _afterrender: function () {
        var $c = this.COMPONENTS;
    },

    _onLoad: function (store, records, successful, eOpts) {
        this.COMPONENTS.addBtn.setDisabled(store.totalCount >= 4);
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
        this.hide();

        var packageForm = this.parent.COMPONENTS.packageForm;
        packageForm.setCollapsed(true);

        var packageServiceForm = this.parent.COMPONENTS.packageServiceForm;
        packageServiceForm.getForm().reset();
        packageServiceForm.getForm().setValues({package_id: this.package_id});
        packageServiceForm._delToggle(-1);
        packageServiceForm.show();
        packageServiceForm.setTitle('新建套餐服务');
    },

    _edit: function (rec) {
        this.hide();

        var packageForm = this.parent.COMPONENTS.packageForm;
        packageForm.setCollapsed(true);

        var packageServiceform = this.parent.COMPONENTS.packageServiceForm;

        packageServiceform.getForm().setValues(rec.data);
        packageServiceform._delToggle(0);
        packageServiceform.show();
        packageServiceForm.setTitle('套餐服务编辑');
    },

    _returnFrom: function () {
        this.hide();
        this.parent.COMPONENTS.grid.getStore().load();
        this.parent._return();
    }
});

Best.product.packageServiceGrid = Best.product.packageServiceGridAction;

//end file