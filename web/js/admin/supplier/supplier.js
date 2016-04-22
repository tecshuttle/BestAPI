Ext.onReady(function () {
    var fields = ['id', 'name', 'password'];
    var columns = [
        {header: "ID", dataIndex: 'id', hidden: true},
        {header: "名称", dataIndex: 'supplier_name'},
        {header: '简称', dataIndex: 'supplier_abbr'},
        {header: "代号", dataIndex: 'supplier_code'},
        {
            header: '激活标记', dataIndex: 'active_flag',
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
            header: '使用状态', dataIndex: 'status',
            renderer: function (v, b, rec) {
                if (v == 'INIT') {
                    return '储备';
                } else if (v == 'IN_SERVICE') {
                    return '使用';
                } else if (v == 'STOP_SERVICE') {
                    return '停用';
                } else if (v == 'OUT') {
                    return '淘汰';
                } else {
                    return v;
                }
            }
        },
        {header: "创建时间", dataIndex: 'create_date'}
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