<%@page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>选最好 - 产品管理后台</title>
    <meta http-equiv=Content-Type content="text/html;charset=utf-8">
    <link rel="stylesheet" type="text/css" href="/css/bootstrap-3.3.4/css/bootstrap.min.css"/>
    <style>
        body {
            background-color: #8bc13c;
        }
    </style>
</head>

<body onload='document.f.j_username.focus();'>

<div class="container">
    <div class="row">
        <div style="padding: 2em;text-align: center;color:white;">
            <h2>选最好 - 产品管理后台</h2>
        </div>
    </div>

    <div class="row" style="background-color: #fff;padding: 3em;">

        <div class="col-xs-6">
            <form class="form-horizontal" name='f' action='/static/j_spring_security_check' method='POST'>
                <div class="form-group">
                    <label for="j_username" class="col-xs-4 control-label">用户名</label>
                    <div class="col-xs-8">
                        <input type="text" name="j_username" id="j_username" class="form-control" placeholder="用户名">
                    </div>
                </div>

                <div class="form-group">
                    <label for="j_password" class="col-xs-4 control-label">密码</label>
                    <div class="col-xs-8">
                        <input type="password" name="j_password" id="j_password" class="form-control" placeholder="密码">
                    </div>
                </div>

                <div class="form-group" style="display: none;">
                    <div class="col-xs-offset-4 col-xs-8">
                        <div class="checkbox">
                            <label>
                                <input type="checkbox"> Remember me
                            </label>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-xs-offset-4 col-xs-8">
                        <button type="submit" class="btn btn-default">登 入</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

</body>
<script src="/js/jquery-1.11.1.min.js"></script>
<script src="/css/bootstrap-3.3.4/js/bootstrap.min.js"></script>
</html>