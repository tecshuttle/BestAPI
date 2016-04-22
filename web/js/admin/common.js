var cardTypeStore = Ext.create('Ext.data.Store', {
    fields: ['cardType', 'name'],
    data: [
        {cardType: "REAL", name: "实体卡"},
        {cardType: "VIRTUAL", name: "虚拟卡"}
    ]
});

var statusStore = Ext.create('Ext.data.Store', {
    fields: ['validDateType', 'name'],
    data: [
        {status: 0, name: "禁用"},
        {status: 1, name: "启用"}
    ]
});

var activeFlagStore = Ext.create('Ext.data.Store', {
    fields: ['active_flag', 'name'],
    data: [
        {active_flag: 0, name: "未激活"},
        {active_flag: 1, name: "已激活"}
    ]
});

var companyStore = Ext.create('Ext.data.Store', {
    fields: ['id', 'company_name'],
    autoLoad: true,
    proxy: {
        type: 'ajax',
        url: '/company/getList',
        reader: {
            root: 'response',
            //totalProperty: 'total',
            type: 'json'
        }
    }
});

var sexSelectStore = Ext.create('Ext.data.Store', {
    fields: ['sex_select', 'name'],
    data: [
        {sex_select: 'ALL', name: "全部"},
        {sex_select: 'M', name: "男"},
        {sex_select: 'F', name: "女"}
    ]
});

var supplierStatusStore = Ext.create('Ext.data.Store', {
    fields: ['status', 'name'],
    data: [
        {status: 'INIT', name: '储备'},
        {status: 'IN_SERVICE', name: '使用'},
        {status: 'STOP_SERVICE', name: '停用'},
        {status: 'OUT', name: '淘汰'}
    ]
});

//使用场景：是否有体验  是否有洁牙  是否基因检测
var yesCheckStore = Ext.create('Ext.data.Store', {
    fields: ['has_x', 'name'],
    data: [
        {has_x: null, name: '无'},
        {has_x: 'Y', name: '有'}
    ]
});

var serviceTypeStore = Ext.create('Ext.data.Store', {
    fields: ['service_type', 'name'],
    data: [
        {service_type: 'PHYSICAL', name: '体检'},
        {service_type: 'TEETH', name: '护牙'},
        {service_type: 'GENE', name: '基因'}
    ]
});

//end file