<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 16-11-10
  Time: 下午2:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Jquery Method</title>
    <script src="http://js.biocloud.cn/jquery/1.11.3/jquery.min.js"></script>
</head>
<body>
    <a href="/user/index">back to index</a><br>
    <h5>------------------------------******-------------------------------</h5>
    <div id="load1"></div>
    <div id="load2"></div>
    <button id="load">load</button>

<script>
    $(document).ready(function () {
        $("button#load").click(function () {
            $("div#load1").load("/user/findAll",function(responseTxt,statusTxt,xhr){
                $("div#load2").text(responseTxt + " | " + statusTxt + " | " + xhr);
            });
        });

    });
</script>
</body>
</html>
