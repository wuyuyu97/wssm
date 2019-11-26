<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>主页</title>
    <!-- style -->
    <link rel="stylesheet" href="//static2.cnodejs.org/public/stylesheets/index.min.23a5b1ca.min.css" media="all">
</head>
<body>

<div class="navbar">
    <div class="navbar-inner">
        <div class="container">
            <ul class="nav pull-right">
                <li><a href='/login'>登录</a></li>
                <li><a href='/todo'>待办事项</a></li>
                <li><a href="/register">注册</a></li>
                <li><a href="/topic">论坛</a></li>
                <li>    <a href='/topic'>板块总</a></li>
                <li>    <a href='/topic/board/1'>板块1</a></li>
                <li><a href='/topic/board/2'>板块2</a></li>
                <li><a href='/'>主页</a></li>

            </ul>
        </div>
    </div>
</div>
<br>



<h1><a href="/topic/user_index/${currentUser.id}">欢迎${currentUser.username}</a></h1>
<br>
<img src="/wyf.gif" />
<br>

</body>

</html>


