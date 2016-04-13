Ext.onReady(function () {
    var fields = ['id', 'name', 'password'];
    var columns = [
        {
            header: "ID", dataIndex: 'id', hidden: true
        },
        {
            header: "供应商ID", dataIndex: 'supplier_id'
        },
        {
            header: "门店名称", dataIndex: 'org_name'
        },
        {
            header: "激活状态", dataIndex: 'active_flag'
        },
        {
            header: "所在地", dataIndex: 'province_id'
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
        layout: 'fit',
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