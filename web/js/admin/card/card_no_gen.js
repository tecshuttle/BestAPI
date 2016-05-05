Ext.onReady(function () {
    var fields = ['id', 'name', 'password'];
    var columns = [
        {header: "ID", dataIndex: 'id', hidden: true},
        {header: "发卡机构", dataIndex: 'company_name', flex: 1},
        {header: "卡名", dataIndex: 'card_name', align: 'center', flex: 2},
        {header: "提请人", dataIndex: 'proposer', align: 'center'},
        {header: "发卡数量", dataIndex: 'gen_quantity', align: 'center'},
        {
            header: "卡类型", dataIndex: 'card_type', align: 'center', width: 80,
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
        {header: "批次号", dataIndex: 'batch_no', align: 'center'},
        {header: "备注", dataIndex: 'memo', flex: 2}
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