<%@page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix='security' uri='http://www.springframework.org/security/tags' %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=9"/>
    <meta http-equiv=Content-Type content="text/html;charset=utf-8">
    <title>选最好 - 产品管理后台</title>


    <link rel="stylesheet" type="text/css" href="/js/extjs4/resources/css/ext-all.css"/>
    <link rel="stylesheet" type="text/css" href="/css/admin/themes/css/index.css"/>
    <link rel="stylesheet" type="text/css" href="/css/admin/themes/css/common.css"/>

    <script type="text/javascript">
        var uploadUserName = "ijobsadmin";
        var department = "部门";
        var isAdmin = true;
        var isAllApp = true;
        //var roleInfo = {'roleName':'超级管理员', 'appsName':''};
        var userRole = [];//用户所属业务
        var isGeneralVersion = false;
        var basePath = 'http://127.0.0.1:8080/tms2web/';
        var url = window.encodeURIComponent(basePath);
    </script>

</head>

<body id="ijobs-main">

<!--顶部开始-->
<div id="header" class="top">
    <!--  <div class="logo f_l"></div>  左侧logo -->
    <div class="f_l"></div>

    <ul id="topmenu" class="nav-wrap f_l"></ul>
    <!-- 中部菜单 -->

    <!-- 右侧信息栏 -->
    <div class="user-wrap f_r">
        <div class="user-options-wrap">
            <ul>
                <li>
                    <b class="user-role" id="lblAppNames">
                        Good luck：<security:authentication property="principal.username"></security:authentication>
                    </b>
                </li>
                <li><s class="sepe-hr"></s></li>
                <li><a target='_self' href="/j_spring_security_logout" id="lnkLogout" class="layout" title="退出"></a></li>
            </ul>
        </div>
    </div>
    <!-- 右侧信息栏 end -->
</div>
<!--顶部结束--></body>

<script src="/js/extjs4/bootstrap.js"></script>
<script src="/js/extjs4/locale/ext-lang-zh_CN.js"></script>
<script src="/js/extjs4/ux/TabCloseMenu.js"></script>
<script src="/js/admin/main/menuConfig.js"></script>
<script src="/js/admin/main/main.js"></script>

</html>