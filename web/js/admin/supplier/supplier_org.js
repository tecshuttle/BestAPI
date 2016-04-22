Ext.onReady(function () {
    var fields = ['id', 'name', 'password'];
    var columns = [
        {header: "ID", dataIndex: 'id', hidden: true},
        {header: "供应商ID", dataIndex: 'supplier_id'},
        {header: "门店名称", dataIndex: 'org_name'},
        {
            header: "激活状态", dataIndex: 'active_flag', align: 'center',
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
            header: '是否有体检', dataIndex: 'has_physical', align: 'center',
            renderer: function (v, b, rec) {
                if (v == 'Y') {
                    return '有';
                } else {
                    return v;
                }
            }
        },
        {
            header: '是否有洁牙', dataIndex: 'hat_tooth_care', align: 'center',
            renderer: function (v, b, rec) {
                if (v == 'Y') {
                    return '有';
                } else {
                    return v;
                }
            }
        },
        {
            header: '是否基因', dataIndex: 'has_gene', align: 'center',
            renderer: function (v, b, rec) {
                if (v == 'Y') {
                    return '有';
                } else {
                    return v;
                }
            }
        },
        {
            header: "所在地", dataIndex: 'province_id', align: 'center',
            renderer: function (v, b, rec) {
                var data = rec.data;
                return data.province_id + data.city_id;
            }
        },
        {header: '联系电话', dataIndex: 'contact_phone'},
        {header: '评分', dataIndex: 'review_score'},
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