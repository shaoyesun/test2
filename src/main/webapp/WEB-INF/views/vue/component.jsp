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
<h3>component</h3>
<div id="example">
    <my-component></my-component>
</div>
<%--局部组件--%>
<h5>局部组件</h5>
<div id="example1">
    <my-component></my-component>
    <%--构成组件--%>
    <h5>构成组件</h5>
    <h5>构成组件--父组件通过 props 向下传递数据给子组件</h5>
    <my-component1 message="hello props" class="change"></my-component1>
    <h5>构成组件--子组件通过 events 给父组件发送消息</h5>
</div>
</body>
<style>
    .change {
        color: red;
    }
</style>
<script id="myComponent" type="text/x-template">
    <div>
        <ul>
            <li v-for="item in userItems">
                {{ item.userName }} - {{ $index }} - {{ item.password }}
            </li>
        </ul>
    </div>
</script>
<script>
    /*父组件通过 props 向下传递数据给子组件*/
    var myComponent1 = {
        // 声明 props
        props: ['message'],
        // 就像 data 一样，prop 可以用在模板内
        // 同样也可以在 vm 实例中像 “this.message” 这样使用
        template: '<span>{{ message }}</span>'
    };

    /*局部组件*/
    //确保在初始化根实例之前注册了组件,data 必须是函数
    var myComponent = {
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
    };
    new Vue({
        el: '#example1',
        components: {
            'my-component': myComponent,
            'my-component1': myComponent1
        }
    });

    /*component*/
    // 注册 确保在初始化根实例之前注册了组件,data 必须是函数
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
                //console.log(response.data);
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
