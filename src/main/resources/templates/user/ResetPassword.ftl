<!DOCTYPE html>
<html lang="en" >
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
            </ul>
        </div>
    </div>
</div>

    <h1>更改密码</h1>
    <form action="/reset/passwordupdate" method="post">
        <input type="hidden" name="token" value="${token}" >
        <br>
        <input type="text" name="password" placeholder="请输入新的密码" >
        <br>
        <button type="submit">确认更改</button>
    </form>



</body>
</html>