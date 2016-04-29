Ext.ns('Tomtalk');

Tomtalk.IdcUI = Ext.extend(Ext.Panel, {
    KE: false,
    constructor: function (config) {
        var me = this;
        config = Ext.apply({
            style: 'padding:10px;background-color: white;',
            border: false
        }, config);

        me.COMPONENTS = {};

        Tomtalk.IdcUI.superclass.constructor.call(me, config);
    },

    initComponent: function () {
        var me = this;
        me.items = [
            me._grid(),
            Ext.create('Tomtalk.grid.AccountForm', {
                id: me.id + '_form',
                hidden: true
            }),
            Ext.create('Best.card.BatchForm', {
                id: me.id + '_batch_form',
                hidden: true
            })
        ];

        Tomtalk.IdcUI.superclass.initComponent.call(me);
    },

    _grid: function () {
        var me = this;

        var store = Ext.create('Ext.data.Store', {
            autoLoad: true,
            pageSize: 20,
            fields: me.fields,
            proxy: {
                type: 'ajax',
                url: '/card/getCardNoList',
                extraParams: {
                    module: me.module
                },
                reader: {
                    type: 'json',
                    root: 'response',
                    totalProperty: 'total'
                }
            }
        });

        me.columns.push({
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
        });

        var grid = new Ext.grid.GridPanel({
            id: this.id + '_grid',
            title: '我被授权的作业',
            header: false,
            columnLines: true,
            store: store,
            columns: me.columns,
            dockedItems: [{
                xtype: 'toolbar',
                items: [{
                    text: '新建',
                    id: this.id + '_add'
                }, {
                    text: '批量新建',
                    id: this.id + '_add_batch'
                }]
            }],
            bbar: {
                xtype: 'pagingtoolbar',
                store: store,
                inputItemWidth: 50,
                displayInfo: true,
                beforePageText: '页',
                afterPageText: '/ {0}',
                displayMsg: "显示第 {0} 条到 {1} 条记录，一共 {2} 条",
                emptyMsg: "没有记录"
            },
            forceFit: true,
            viewConfig: {
                stripeRows: true,
                enableRowBody: true,
                showPreview: true
            }
        });

        return grid;
    }
});

Tomtalk.IdcAction = Ext.extend(Tomtalk.IdcUI, {
    constructor: function (config) {
        Tomtalk.IdcAction.superclass.constructor.call(this, config);
    },

    initComponent: function () {
        Tomtalk.IdcAction.superclass.initComponent.call(this);

        Ext.apply(this.COMPONENTS, {
            addBtn: Ext.getCmp(this.id + '_add'),
            addBatchBtn: Ext.getCmp(this.id + '_add_batch'),
            grid: Ext.getCmp(this.id + '_grid'),
            form: Ext.getCmp(this.id + '_form'),
            batchForm: Ext.getCmp(this.id + '_batch_form')
        });
    },

    initEvents: function () {
        var me = this;
        var $c = this.COMPONENTS;

        Tomtalk.IdcAction.superclass.initEvents.call(me);

        this.on('boxready', me._afterrender, me);
        $c.addBtn.on('click', me._add, me);
        $c.addBatchBtn.on('click', me._add_batch, me);
    },

    _afterrender: function () {
        var $c = this.COMPONENTS;
    },

    _delete: function (id) {
        var me = this;

        Ext.Ajax.request({
            url: '/admin/gridDelete',
            params: {
                module: me.module,
                id: id
            },
            success: function (res) {
                var result = Ext.decode(res.responseText);
                me.COMPONENTS.grid.getStore().reload();
            }
        });
    },

    _add: function () {
        var $c = this.COMPONENTS;

        $c.grid.hide();
        $c.form.getForm().reset();
        $c.form.show();
    },
    
    _add_batch: function () {
        var $c = this.COMPONENTS;

        $c.grid.hide();
        $c.batchForm.getForm().reset();
        $c.batchForm.show();
    },

    _edit: function (rec) {
        var $c = this.COMPONENTS;

        $c.grid.hide();
        $c.form.getForm().setValues(rec.data);
        $c.form.show();
    },

    _returnFrom: function () {
        var $c = this.COMPONENTS;

        $c.form.hide();

        $c.grid.show();
        $c.grid.getStore().reload();
    }
});

Tomtalk.Idc = Tomtalk.IdcAction;

//end file