Ext.onReady(function () {
    var fields = ['id', 'name', 'password'];
    var columns = [
        {header: "ID", dataIndex: 'id', hidden: true},
        {header: "卡号", dataIndex: 'card_no'},
        {header: "卡密", dataIndex: 'card_code'},
        {header: "卡类型", dataIndex: 'card_no_type'},
        {header: "激活状态", dataIndex: 'active_flag'},
        {header: "归属公司", dataIndex: 'dept_id', width: 220},
        {header: "创建人", dataIndex: 'creator'},
        {header: "验证次数", dataIndex: 'verfiy_count'},
        {header: "使用状态", dataIndex: 'status'}
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