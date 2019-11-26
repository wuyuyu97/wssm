<!DOCTYPE HTML>
<html lang="zh">
<head>
    <title>找回密码</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" href="//static2.cnodejs.org/public/stylesheets/index.min.23a5b1ca.min.css" media="all">
</head>
<body>
<div class="navbar">
    <div class="navbar-inner">
        <div class="container">
            <ul class="nav pull-right">
                <li><a href='/'>主页</a></li>
                <li><a href="/register">注册</a></li>
            </ul>
        </div>
    </div>
</div>
<form action="/rest/send" method="post">
    <br>
    <label>
        <input type="text" name="username" placeholder="用户名">
    </label>

    <button type="submit">点击发送邮件找回密码</button>
</form>

</body>
</html>
