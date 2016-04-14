Ext.onReady(function () {
    var fields = ['id', 'name', 'password'];
    var columns = [
        {header: "ID", dataIndex: 'id', hidden: true},
        {header: '名称', dataIndex: 'service_name'},
        {header: '服务形式', dataIndex: 'service_type'},
        {header: '服务种类', dataIndex: 'rel_type'},
        {header: '适用性别', dataIndex: 'sex_select'},
        {header: '售价价格', dataIndex: 'price'},
        {header: "激活状态", dataIndex: 'active_flag'},
        {header: "使用状态", dataIndex: 'status'},
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