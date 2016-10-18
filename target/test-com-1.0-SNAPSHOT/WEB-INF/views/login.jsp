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
<body onload="reLogin()">
    <form action="/other/login" method="post">
        user name:<input type="text" name="userName">${message}<br><br>
        user pass:<input type="text" name="password"><br><br>
        <input type="submit" value="Login">${userName}
    </form>
    <input type="hidden" name="userName" value="${userName}" id="un">

<script src="http://js.biocloud.cn/jquery/1.11.3/jquery.min.js"></script>
<script type="text/javascript">
    function reLogin(){
        var un = $("#un").val();
        if(un != ""){
            if(confirm('帐号已在别处登录，确认要重新登录吗？')){
                $.ajax({
                   type: "POST",
                   data:{userName: un},
                   url: "/user/clearUserSession",
                   success: function (data) {
                       alert(data);
                   }
               });
            }
        }
    }
</script>
</body>
</html>
