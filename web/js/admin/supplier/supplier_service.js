Ext.onReady(function () {
    var fields = ['id', 'name', 'password'];
    var columns = [
        {header: "ID", dataIndex: 'id', hidden: true},
        {header: '供应商', dataIndex: 'supplier_name', flex: 1},
        {
            header: '产品类型', dataIndex: 'service_type', align: 'center', width: 80,
            renderer: function (v, b, rec) {
                if (v == 'PHYSICAL') {
                    return '体检';
                } else if (v == 'TEETH') {
                    return '齿科';
                } else if (v == 'GENE') {
                    return '基因';
                } else {
                    return v;
                }
            }
        },
        {header: '产品名称', dataIndex: 'service_name', flex: 2},
        {
            header: '性别要求', dataIndex: 'sex_select', align: 'center', width: 80,
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
            header: '提供卡密', dataIndex: 'is_use_verify_code', align: 'center', width: 80,
            renderer: function (v, b, rec) {
                if (v == 'Y') {
                    return '提供';
                } else {
                    return v;
                }
            }
        },
        {
            header: '状态', dataIndex: 'status', align: 'center', width: 80,
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
            header: "市场价格", dataIndex: 'market_price', align: 'right',
            renderer: function (val) {
                if (val == null) return val;
                var out = Ext.util.Format.number(val, '0.00');
                return '￥' + out;
            }
        },
        {
            header: "采购价格", dataIndex: 'cost_price', align: 'right',
            renderer: function (val) {
                if (val == null) return val;
                var out = Ext.util.Format.number(val, '0.00');
                return '￥' + out;
            }
        }
    ];

    new Ext.Viewport({
        renderTo: Ext.getBody(),
        autoScroll: 'y',
        style: 'background-color: white;',
        items: [
            new Tomtalk.Idc({
                module: 'admins',
                fields: fields,
                columns: columns
            })
        ]
    });
});

//end file