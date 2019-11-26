<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>注册登录页面</title>
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

<#--<a href='/'>HOME</a>-->
    <h1>登录</h1>
    <form action="/user/login" method="post">
        <input type="text" name="username" placeholder="请输入用户名">
        <br>
        <input type="text" name="password" placeholder="请输入密码">
        <br>
        <button type="submit">登录</button>
    </form>

<li><a href="/reset">找回密码</a></li>

</form>
</body>
</html>