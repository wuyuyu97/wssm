<!DOCTYPE HTML>
<html>
<head>
    <title>待办事项</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
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



<form action="/todo/add" method="POST">
    <input type="text" name="content" placeholder="请输入todo">
    <br>
    <button type="submit">添加</button>
</form>

<div>

    <#list todos as t>
    <h3>${t.id} : ${t.content}</h3>
    <a href="/todo/edit?id=${t.id}">编辑</a>
    <a href="/todo/delete?id=${t.id}">删除</a>
</#list>
</div>
</body>
</html>