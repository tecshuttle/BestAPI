var Docs = {};
Docs.Menu = [
    {
        id: 'user',
        text: '健康卡',
        isMutilLevel: false,
        children: [
            {
                href: "/card/type",
                text: "卡管理",
                leaf: true
            },
            {
                href: "/card/no",
                text: "卡号管理",
                leaf: true
            },
            {
                href: "/card/package",
                text: "卡套餐",
                leaf: true
            }
        ]
    },
    {
        id: 'admin',
        text: '产品',
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
                text: "供应商管理",
                leaf: true
            },
            {
                href: "/supplier/org",
                text: "门店管理",
                leaf: true
            },
            {
                href: "/supplier/service",
                text: "产品管理",
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