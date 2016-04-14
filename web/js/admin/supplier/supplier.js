Ext.onReady(function () {
    var fields = ['id', 'name', 'password'];
    var columns = [
        {
            header: "ID", dataIndex: 'id', hidden: true
        },
        {
            header: "供应商代号", dataIndex: 'supplier_code'
        },
        {
            header: "供应商名称", dataIndex: 'supplier_name'
        },
        {
            header: "创建人", dataIndex: 'creator'
        },
        {
            header: "创建时间", dataIndex: 'create_date'
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