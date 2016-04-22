Ext.onReady(function () {
    var fields = ['id', 'name', 'password'];
    var columns = [
        {header: "ID", dataIndex: 'id', hidden: true},
        {header: "名称", dataIndex: 'package_name'},
        {
            header: '激活状态', dataIndex: 'active_flag',
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
            header: "性别要求", dataIndex: 'sex_select',
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
        {header: '服务总数', dataIndex: 'dtl_count'},
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
                module: 'admins',
                fields: fields,
                columns: columns
            })
        ]
    });
});

//end file