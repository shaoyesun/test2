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
    <style>
        .ancestors *
        {
            display: block;
            border: 2px solid lightgrey;
            color: lightgrey;
            padding: 5px;
            margin: 15px;
        }
    </style>
</head>
<body>

<a href="#">I am a no  #</a><br><br>
<a href="#">I am a has #</a><br><br>

<a href="#.user">I am a has #</a><br><br>
<a href="#.user">I am a has #</a><br><br>
<a href="#.user">I am a has #</a><br><br>
<h5>------------------------------toggle-------------------------------</h5>
<button id="toggle">toggle</button>
<h5>------------------------------fadeIn() fadeOut() fadeToggle() fadeTo()-------------------------------</h5>
<button id="fadeIn">fadeIn</button>
<button id="fadeOut">fadeOut</button>
<button id="fadeToggle">fadeToggle</button>
<button id="fadeTo">fadeTo</button>
<h5>------------------------------slideDown() slideUp() slideToggle()-------------------------------</h5>
<button id="slideDown">slideDown</button>
<button id="slideUp">slideUp</button>
<button id="slideToggle">slideToggle</button>
<h5>------------------------------attr()-------------------------------</h5>
<input type="button" id="attr" value="click will change">
<h5>------------------------------append() prepend() after() before()-------------------------------</h5>
<button id="append">append</button>
<button id="prepend">prepend</button>
<button id="after">after</button>
<button id="before">before</button>
<h5>------------------------------remove() empty()-------------------------------</h5>
<button id="remove">remove</button>
<button id="empty">empty</button>
<h5>------------------------------parent() parents() parentsUntil()-------------------------------</h5>
<div class="ancestors">
    <div style="width:500px;">div (曾祖父) click me
        <ul>ul (祖父)
            <li>li (直接父)
                <span>span click me</span>
                <p class="1">p</p>
            </li>
        </ul>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        //toggle
        $("button#toggle").click(function () {
           $("a").toggle(1000);
        });

        /*fade*/
        $("button#fadeIn").click(function () {
            $("a").fadeIn(1000);
        });
        $("button#fadeOut").click(function () {
            $("a").fadeOut(1000);
        });
        $("button#fadeToggle").click(function () {
            $("a").fadeToggle(1000);
        });
        $("button#fadeTo").click(function () {
            $("a").fadeTo("slow", 0.3);
        });

        /*slide*/
        $("button#slideUp").click(function () {
            $("a").slideUp(1000);
        });
        $("button#slideDown").click(function () {
            $("a").slideDown(1000);
        });
        $("button#slideToggle").click(function () {
            $("a").slideToggle(1000);
        });

        /*attr*/
        $("input#attr").click(function () {
            $(this).val("change value and attr");
            $(this).attr("type", "text");
        });

        // 在被选元素的结尾插入内容
        $("button#append").click(function () {
            $(this).append("-test");
        });
        // 在被选元素的开头插入内容
        $("button#prepend").click(function () {
            $(this).prepend("test-");
        });
        //在被选元素之后插入内容
        $("button#after").click(function () {
            $(this).after("-test ");
        });
        //在被选元素之前插入内容
        $("button#before").click(function () {
            $(this).before(" test-");
        });

        //删除被选元素（及其子元素）
        $("button#remove").click(function () {
            //$("button").remove();
            $("button").remove("#remove");//允许您对被删元素进行过滤,删除id="remove"的所有<button>元素：
        });
        //从被选元素中删除子元素
        $("button#empty").click(function () {
            $(this).empty();
        });

        /*parent() parents() parentsUntil()*/
        $("span").click(function () {
            //$("span").parent().parent().css({"color":"red","border":"2px solid red"});
            $("span").parents().css({"color":"red","border":"2px solid red"});
            //$("span").parentsUntil("div").css({"color":"red","border":"2px solid red"});//返回介于 <span> 与 <div> 元素之间的所有祖先元素
            var str = "";
            $.each($("span").parents(), function (index, item) {
                str += item.tagName + index + "  |  ";
            });
            alert(str);
        });

        /*children() find()*/
        $("div").click(function () {
            //$("li").children("p.1").css({"color":"red","border":"2px solid red"});//返回类名为 "1" 的所有 <p> 元素
            //$("[style='width:500px;']").children().css({"color":"red","border":"2px solid red"});//返回被选元素的所有直接子元素
            //$("[style='width:500px;']").find("*").css({"color":"red","border":"2px solid red"});//返回 <div> 的所有后代
        });
    });
</script>
</body>
</html>
