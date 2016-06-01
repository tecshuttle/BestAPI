Ext.onReady(function () {
    var fields = ['id', 'name', 'password'];
    var columns = [
        {header: "ID", dataIndex: 'id', hidden: true},
        {header: "供应商", dataIndex: 'supplier_name', flex: 1},
        {header: "门店名称", dataIndex: 'org_name', flex: 1.5},
        {header: "门店地址", dataIndex: 'address', flex: 2},
        {
            header: "所在省市", dataIndex: 'province_id', align: 'center',
            renderer: function (v, b, rec) {
                var data = rec.data;
                return data.province_id + ' ' + data.city_id;
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
        {header: '联系电话', dataIndex: 'contact_phone', flex: 1.5},
        {header: '评分', dataIndex: 'review_score', align: 'right', width: 70},
        {
            header: "创建时间", dataIndex: 'create_date', align: 'center',
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