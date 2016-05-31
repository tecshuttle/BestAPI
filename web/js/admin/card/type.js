Ext.onReady(function () {
    var fields = ['id', 'name', 'password'];
    var columns = [
        {header: "ID", dataIndex: 'id', hidden: true},
        {header: "卡名称", dataIndex: 'card_name', flex: 1},
        {header: "卡代号", dataIndex: 'card_code', align: 'center', width: 130},
        {header: "卡号前缀", dataIndex: 'card_no_prefix', align: 'center', width: 130},
        {header: '发卡机构', dataIndex: 'company_name', flex: 1},
        {
            header: '有效期', dataIndex: 'valid_date_value', align: 'center', width: 100,
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
            header: "价格", dataIndex: 'price', align: 'right', width: 100,
            renderer: function (val) {
                var out = Ext.util.Format.number(val, '0.00');
                return '￥' + out;
            }
        },
        {header: '套餐总数', dataIndex: 'package_total', align: 'right', width: 100}
    ];

    new Ext.Viewport({
        renderTo: Ext.getBody(),
        autoScroll: 'y',
        style: 'background-color: white;',
        items: [
            new Tomtalk.Idc({
                fields: fields,
                columns: columns
            })
        ]
    });
});

//end file