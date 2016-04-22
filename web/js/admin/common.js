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

//end file