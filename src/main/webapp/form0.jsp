<%--
  Created by IntelliJ IDEA.
  User: altscherzxu
  Date: 2024/10/9
  Time: 20:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="Myservlet" method="get">
    用户名：<input type="text" name="name" value="张三"><br/>
    兴趣爱好：<input type="checkbox" name="hobby" value="shopping" checked>购物
    <input type="checkbox" name="hobby" value="music" checked>音乐<br/>
    <input type="submit" value="发送"><input type="reset" value="重置">
</form>

</body>
</html>
