<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>添加 帖子 评论的页面</title>
</head>
<body>
    <h1>添加 帖子 评论</h1>

    <form action="/topic/commentadd" method="post">
        <input name="id" placeholder="id" value="${topicid}" hidden>
        <input name="content" placeholder="content" value="">
        <br>
        <button type="submit">提交评论</button>
    </form>


</body>
</html>
