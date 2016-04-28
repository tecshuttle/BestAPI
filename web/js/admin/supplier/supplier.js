Ext.onReady(function () {
    var columns = [
        {header: "ID", dataIndex: 'id', hidden: true},
        {header: "名称", dataIndex: 'supplier_name'},
        {header: '简称', dataIndex: 'supplier_abbr'},
        {header: "代号", dataIndex: 'supplier_code'},
        {
            header: '使用状态', dataIndex: 'status', align: 'center',
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
        {header: "联络人", dataIndex: 'contact'},
        {header: "联络人手机", dataIndex: 'contact_mobile'},
        {header: "联络人邮箱", dataIndex: 'contact_mail'},
        {
            header: "创建时间", dataIndex: 'create_date',
            renderer: function (v) {
                return v.substr(0, 10);
            }
        }
    ];


    new Ext.Viewport({
        renderTo: Ext.getBody(),
        autoScroll: 'y',
        style: 'background-color: white;',
        items: [
            new Tomtalk.Idc({
                columns: columns
            })
        ]
    });
});

//end file