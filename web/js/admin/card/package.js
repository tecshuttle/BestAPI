Ext.onReady(function () {
    var fields = ['id', 'name', 'password'];
    var columns = [
        {header: "ID", dataIndex: 'id', hidden: true},
        {header: "卡名", dataIndex: 'card_name', flex: 1},
        {header: "套餐名称", dataIndex: 'package_name', flex: 2},
        {
            header: "性别要求", dataIndex: 'sex_select', align: 'center', width: 80,
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
        {header: '服务总数', dataIndex: 'dtl_count', align: 'right', width: 100},
        {
            header: "价格", dataIndex: 'price', align: 'right',
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
                fields: fields,
                columns: columns
            })
        ]
    });
});

//end file