<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 16-10-8
  Time: 下午1:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <form action="/other/login" method="post">
        user name:<input type="text" name="userName">${message}<br><br>
        user pass:<input type="text" name="password"><br><br>
        <input type="submit" value="Login">
    </form>
</body>
</html>
