Ext.onReady(function () {
    var fields = ['id', 'name', 'password'];
    var columns = [
        {header: "ID", dataIndex: 'id', hidden: true},
        {header: '服务名称', dataIndex: 'service_name', width: 200},
        {
            header: '服务类型', dataIndex: 'service_type', align: 'center',
            renderer: function (v, b, rec) {
                if (v == 'OUTSOURCE') {
                    return '外部资源';
                } else if (v == 'MSG_PUSH') {
                    return '资讯推送';
                } else if (v == 'LECTURE') {
                    return '健康讲座';
                } else {
                    return v;
                }
            }
        },
        {
            header: '关联类型', dataIndex: 'rel_type', align: 'center',
            renderer: function (v, b, rec) {
                var type = {
                    MAN: '男科',
                    HEALTH_CARE: '护理',
                    MOUTH: '口腔',
                    PHYSICAL: '体检',
                    WOMAN: '女科',
                    HEALTH: '保健',
                    NUTRI: '营养',
                    SALES: '销售',
                    CHRONIC_ILLNESS: '慢性病',
                    MSG: '消息',
                    GENE: '基因',
                    TEETH: '齿科',
                    DOCTOR_ACCOMPANY: '名医会诊',
                    DOCTOR_RESERVE: '名医预约'
                };

                if (type[v]) {
                    return type[v];
                } else {
                    return v;
                }
            }
        },
        {
            header: '性别要求', dataIndex: 'sex_select', align: 'center',
            renderer: function (v, b, rec) {
                if (v == 'ALL') {
                    return '全部';
                } else if (v == 'M') {
                    return '男';
                } else if (v == 'F') {
                    return '女';
                } else {
                    return v;
                }
            }
        },
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
            header: "使用状态", dataIndex: 'status', align: 'center',
            renderer: function (v, b, rec) {
                if (v == 0) {
                    return '禁用';
                } else if (v == 1) {
                    return '启用';
                } else {
                    return v;
                }
            }
        },
        {header: '包含服务数', dataIndex: 'count_service_map', align: 'right'},
        {
            header: '售价', dataIndex: 'price', align: 'right',
            renderer: function (val) {
                if (val == null) return val;
                var out = Ext.util.Format.number(val, '0.00');
                return '￥' + out;
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