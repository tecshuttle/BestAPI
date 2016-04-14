Ext.onReady(function () {
    var fields = ['id', 'name', 'password'];
    var columns = [
        {header: "ID", dataIndex: 'id', hidden: true},
        {header: "名称", dataIndex: 'package_name'},
        {header: '激活状态', dataIndex: 'active_flag'},
        {header: "性别要求", dataIndex: 'sex_select'},
        {header: '服务总数', dataIndex: 'dtl_count'},
        {header: "价格", dataIndex: 'price'}
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