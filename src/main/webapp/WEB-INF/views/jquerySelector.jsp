<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 16-11-9
  Time: 上午10:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Jquery</title>
    <script src="http://js.biocloud.cn/jquery/1.11.3/jquery.min.js"></script>
</head>
<body>
<a href="/user/index">back to index</a><br>
<h5>------------------------------******-------------------------------</h5>
<p id="demo" class="demo">click me will hind</p>

<a href="#">I am a no  #</a><br><br>
<a href="#">I am a has #</a><br><br>

<a href="#.user">I am a has #</a><br><br>
<a href="#.user">I am a has #</a><br><br>
<a href="#.user">I am a has #</a><br><br>

<p id="css">click change my background color</p><br><br>

<button id="hideOrShow">hide or show</button><br><br>

<button id="toggle">toggle</button><br><br>



<script type="text/javascript">
    $(document).ready(function () {//当文档完成加载时,将函数绑定到文档的就绪事件

        /*jQuery 元素选择器*/
       $("p#demo").click(function () {
           //$(this).hide(); //选取当前元素
           //$("p").hide(); //选取<p>元素
           //$("#demo").hide();//选取所有id="demo"的元素
           //$("p#demo").hide();//选取所有id="demo"的<p>元素
           //$(".demo").hide();//选取所有class="demo"的元素。
           $("p.demo").hide();//选取所有class="demo"的<p>元素。
       });

        /*jQuery 属性选择器*/
        $("[href]").click(function () {
            //$(this).hide();//点击哪个哪个隐藏
            //$("[href]").hide();//选取所有带有 href 属性的元素。
            //$("[href='#']").hide();//选取所有带有 href 值等于 "#" 的元素。
            //$("[href!='#']").hide();//选取所有带有 href 值不等于 "#" 的元素。
            $("[href$='.user']").hide();//选取所有 href 值以".user" 结尾的元素。
        });

        /*jQuery CSS 选择器*/
        $("p#css").click(function () {
           $("p#css").css("background-color", "red");
        });

        /*jQuery hide() 和 show()*/
        $("button#hideOrShow").click(function () {
            $("[href$='.user']").hide(1000, showHref);//showHref为隐藏完成后所执行的函数名称
        });

        function showHref(){
            $("[href$='.user']").show(1000);
        }

        /*使用 toggle() 方法来切换 hide() 和 show() 方法*/
        $("button#toggle").click(function () {
            $("a").toggle(1000);
        });
    });
</script>
</body>
</html>
