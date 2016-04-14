Ext.onReady(function () {
    var fields = ['id', 'name', 'password'];
    var columns = [
        {
            header: "ID", dataIndex: 'id', hidden: true
        },
        {
            header: "分类代号", dataIndex: 'card_code'
        },
        {
            header: "分类名称", dataIndex: 'card_name'
        },
        {
            header: "价格", dataIndex: 'price'
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