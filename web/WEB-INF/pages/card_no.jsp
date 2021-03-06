<%@page session="true" %>
<%@ page contentType="text/html; charset=UTF-8" %>


<!Doctype html>
<html xmlns=http://www.w3.org/1999/xhtml>
<head>
    <meta http-equiv=Content-Type content="text/html;charset=utf-8">
    <title>卡号管理</title>
    <base href="/">

    <link rel="stylesheet" type="text/css" href="/js/extjs6/build/MyApp-all.css"/>

    <style>

        /* text for actioncolumn start */
        .x-action-col-icon {
            width: auto;
            margin-left: 1em;
            font: 300 13px/19px 'Open Sans', 'Helvetica Neue', helvetica, arial, verdana, sans-serif;
            border: 1px solid #5897ce;
            background-color: #5fa2dd;
            color: white;
            padding: 0px 8px;
        }

        .x-action-col-icon:first-child {
            margin-left: 0em;
        }

        /* text for actioncolumn end */

        /* 发现一个displayfield的bug */
        .x-form-display-field-default {
            margin-top: 0px;
            padding-top: 8px;
        }
    </style>
</head>

<body></body>

<script src="/js/extjs6/ext-all.js"></script>
<script src="/js/admin/common/columnAction.js"></script>
<script src="/js/admin/common.js"></script>
<script src="/js/admin/card/card_no_grid.js"></script>
<script src="/js/admin/card/card_no_form.js"></script>
<script src="/js/admin/card/card_no_batch_form.js"></script>
<script src="/js/admin/card/card_no.js"></script>

</html>