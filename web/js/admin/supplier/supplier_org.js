Ext.onReady(function () {
    var fields = ['id', 'name', 'password'];
    var columns = [
        {header: "ID", dataIndex: 'id', hidden: true},
        {header: "供应商", dataIndex: 'supplier_name', flex: 1},
        {header: "门店名称", dataIndex: 'org_name', flex: 2},
        {
            header: "激活状态", dataIndex: 'active_flag', align: 'center', width: 80,
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
            header: '是否体检', dataIndex: 'has_physical', align: 'center', width: 80,
            renderer: function (v, b, rec) {
                if (v == 'Y') {
                    return '有';
                } else {
                    return v;
                }
            }
        },
        {
            header: '是否洁牙', dataIndex: 'has_tooth_care', align: 'center', width: 80,
            renderer: function (v, b, rec) {
                if (v == 'Y') {
                    return '有';
                } else {
                    return v;
                }
            }
        },
        {
            header: '是否基因', dataIndex: 'has_gene', align: 'center', width: 80,
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
        {header: '联系电话', dataIndex: 'contact_phone', flex: 2},
        {header: '评分', dataIndex: 'review_score', align: 'right', width: 70},
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
                fields: fields,
                columns: columns
            })
        ]
    });
});

//end file