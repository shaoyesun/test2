<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 17-1-5
  Time: 下午1:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="/static/js/vue/vue.js"></script>
    <script src="/static/js/vue/vue-resource.js"></script>
    <script src="/static/js/vue/vuex.2.0.0.js"></script>
    <script src="/static/js/vue/vue-router.2.0.2.js"></script>
    <script src="/static/js/jquery/jquery-2.1.1.min.js"></script>
    <script src="/static/js/jquery/jquery.tmpl.min.js"></script>
</head>
<body>
<a href="/user/index">back to index</a><br><br>
<%--v-...--%>
<h3>v-...</h3>
<div id="app">
    <a @click="change" :class="{change: isActive}">click me -- {{isActive}}</a><br>
    <ul>
        <li v-for="item in userItems" :class="{change: isActive}">
            {{ item.userName }} - {{ $index }} - {{ item.password }}
        </li>
    </ul>
</div>
<%--component--%>
<div id="example">
    <my-component></my-component>
</div>
</body>
<style>
    .change {
        color: red;
    }
</style>
<script id="myComponent" type="text/x-template">
    <div>
        <h3>component</h3>
        <ul>
            <li v-for="item in userItems">
                {{ item.userName }} - {{ $index }} - {{ item.password }}
            </li>
        </ul>
    </div>
</script>
<script>
    /*component*/
    // 注册 确保在初始化根实例之前注册了组件
    Vue.component('my-component', {
        template: '#myComponent',
        data: function () {
            return {
                userItems: []
            }
        },
        init: function () {
            this.$http.post('/user/findAll').then(function (response) {
                this.userItems = response.data;
                console.log(response.data);
            }, function (response) {
                console.log("请求数据出现异常");
            });
        }

    })
    // 创建根实例
    var compo = new Vue({
        el: '#example'
    });

    /*v-...*/
    var app = new Vue({
        el: '#app',
        data: {
            isActive: false,
            userItems: []
        },
        methods: {
            change: function () {
                var that = this;
                if (this.isActive) {
                    this.isActive = false;
                } else {
                    this.isActive = true;
                }
                $.post('/user/findAll', function (res) {
                    that.userItems = res;
                    //console.log(res);
                }, "JSON");
            }
        }
    });
</script>
</html>
