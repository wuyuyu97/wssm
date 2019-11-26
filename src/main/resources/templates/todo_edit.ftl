<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>修改 待办事项 的页面</title>
</head>
<body>

    <h1>修改 待办事项</h1>
    <form action="/todo/update" method="post">
        <input name="id" placeholder="id" value="${todo.id}" hidden>
        <input name="content" placeholder="content" value="${todo.content}">
        <br>
        <button type="submit">提交修改</button>
    </form>
</body>
</html>
