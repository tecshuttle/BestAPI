Ext.onReady(function () {
    var fields = ['id', 'name', 'password'];
    var columns = [
        {header: "ID", dataIndex: 'id', hidden: true},
        {header: "发卡机构", dataIndex: 'company_name', flex: 1},
        {header: "卡号", dataIndex: 'card_no', align: 'center', flex: 2},
        {header: "卡密", dataIndex: 'card_code', align: 'center'},
        {
            header: "卡类型", dataIndex: 'card_no_type', align: 'center', width: 80,
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
        {
            header: '激活标记', dataIndex: 'active_flag', align: 'center', width: 80,
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
        {header: "验证次数", dataIndex: 'verify_count', width: 80},
        {header: "创建人", dataIndex: 'creator'}
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