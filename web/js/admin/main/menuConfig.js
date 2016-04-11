var Docs = {};
Docs.Menu = [
    {
        id: 'user',
        text: '卡管理',
        isMutilLevel: false,
        children: [
            {
                href: "/card/type",
                text: "卡类型",
                leaf: true
            },
            {
                href: "/card/list",
                text: "卡列表",
                leaf: true
            },
            {
                href: "/card/suit",
                text: "卡套餐",
                leaf: true
            }
        ]
    },
    {
        id: 'admin',
        text: '服务管理',
        isMutilLevel: false,
        children: [
            {
                href: "/service",
                text: "服务管理",
                leaf: true
            }
        ]
    },
    {
        id: 'job',
        text: '供应商',
        isMutilLevel: false,
        children: [
            {
                href: "/supplier/list",
                text: "供应商",
                leaf: true
            },
            {
                href: "/supplier/store",
                text: "门店",
                leaf: true
            },
            {
                href: "/supplier/service",
                text: "服务",
                leaf: true
            }
        ]
    },
    {
        id: 'set',
        text: '系统设置',
        isMutilLevel: false,
        children: [
            {
                href: "/settings",
                text: "全站设置",
                leaf: true
            },
            {
                href: "/user",
                text: "管理员帐号",
                leaf: true
            }
        ]
    }
];

//end file