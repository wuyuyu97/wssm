<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>注册页面</title>
    <link rel="stylesheet" href="//static2.cnodejs.org/public/stylesheets/index.min.23a5b1ca.min.css" media="all">
</head>
<body>

<div class="navbar">
    <div class="navbar-inner">
        <div class="container">
            <ul class="nav pull-right">
                <li><a href='/'>主页</a></li>
            </ul>
        </div>
    </div>
</div>

    <h1>注册</h1>
    <form action="/user/register" method="post">
        <input type="text" name="username" placeholder="请输入用户名">
        <br>
        <input type="text" name="password" placeholder="请输入密码">
        <br>
        <input type="text" name="mail" placeholder="请输入邮箱">
        <br>
        <button type="submit">注册</button>
    </form>
</body>
</html>