Ext.onReady(function () {
    var fields = ['id', 'name', 'password'];
    var columns = [
        {header: "ID", dataIndex: 'id', hidden: true},
        {header: "卡名称", dataIndex: 'card_name'},
        {header: "卡代号", dataIndex: 'card_code'},
        {
            header: "卡类型", dataIndex: 'card_type',
            renderer: function (v, b, rec) {
                if (v == 'REAL') {
                    return '实体卡';
                } else if (v == 'VIRTUAL') {
                    return '虚拟卡';
                } else {
                    return v;
                }
            }
        },
        {header: '发卡机构', dataIndex: 'company_name'},
        {
            header: '有效期', dataIndex: 'valid_date_value',
            renderer: function (v, b, rec) {
                var row = rec.data
                var valid_type = row.valid_date_type;
                if (row.valid_date_type == 'DAY') {
                    valid_type = '天';
                } else if (row.valid_date_type == 'MONTH') {
                    valid_type = '月';
                } else if (row.valid_date_type == 'YEAR') {
                    valid_type = '年';
                }

                return row.valid_date_value + ' ' + valid_type;
            }
        },
        {
            header: '激活标记', dataIndex: 'active_flag',
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
        {header: '套餐总数', dataIndex: 'package_total', align: 'right'},
        {
            header: "价格", dataIndex: 'price', align: 'right',
            renderer: function (val) {
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