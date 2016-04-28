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

var productServiceTypeStore = Ext.create('Ext.data.Store', {
    fields: ['service_type', 'name'],
    data: [
        {service_type: 'OUTSOURCE', name: '外部资源'},
        {service_type: 'MSG_PUSH', name: '资讯推送'},
        {service_type: 'LECTURE', name: '健康讲座'}
    ]
});

var productRelTypeStore = Ext.create('Ext.data.Store', {
    fields: ['rel_type', 'name'],
    data: [
        {rel_type: 'MAN', name: '男科'},
        {rel_type: 'HEALTH_CARE', name: '护理'},
        {rel_type: 'MOUTH', name: '口腔'},
        {rel_type: 'PHYSICAL', name: '体检'},
        {rel_type: 'WOMAN', name: '妇科'},
        {rel_type: 'HEALTH', name: '保健'},
        {rel_type: 'NUTRI', name: '营养'},
        {rel_type: 'SALES', name: '销售'},
        {rel_type: 'CHRONIC_ILLNESS', name: '慢性病'},
        {rel_type: 'MSG', name: '消息'},
        {rel_type: 'GENE', name: '基因'},
        {rel_type: 'TEETH', name: '齿科'},
        {rel_type: 'DOCTOR_ACCOMPANY', name: '名医会诊'},
        {rel_type: 'DOCTOR_RESERVE', name: '名医预约'}
    ]
});

var companyStore = Ext.create('Ext.data.Store', {
    fields: ['id', 'company_name'],
    proxy: {
        type: 'ajax',
        url: '/company/getList',
        reader: {
            root: 'response',
            type: 'json'
        }
    }
});

var supplierStore = Ext.create('Ext.data.Store', {
    fields: ['id', 'supplier_name'],
    proxy: {
        type: 'ajax',
        url: '/supplier/getSupplierList',
        reader: {
            root: 'response',
            type: 'json'
        }
    }
});

//end file