Ext.onReady(function () {
    var fields = ['id', 'name', 'password'];
    var columns = [
        {
            header: "ID", dataIndex: 'id', hidden: true
        },
        {header: '供应商ID', dataIndex: 'supplier_id'},
        {header: '产品名称', dataIndex: 'service_name', width: 200},
        {header: '产品类型', dataIndex: 'service_type'},
        {
            header: "市场价格", dataIndex: 'market_price'
        },
        {
            header: "采购价格", dataIndex: 'cost_price'
        },
        {
            header: "创建人", dataIndex: 'creator'
        },
        { header: "创建时间", dataIndex: 'create_date', width: 110}
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