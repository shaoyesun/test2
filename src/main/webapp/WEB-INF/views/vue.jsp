<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 16-11-17
  Time: 下午2:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>vue study</title>
    <script src="/static/js/vue/vue.js"></script>
    <script src="/static/js/vue/vue-resource.js"></script>
    <script src="/static/js/vue/vuex.2.0.0.js"></script>
    <script src="/static/js/vue/vue-router.2.0.2.js"></script>
    <script src="/static/js/jquery/jquery-2.1.1.min.js"></script>
</head>
<body>
<a href="/user/index">back to index</a><br>
<h5>-----------------------单向数据绑定-------------------------</h5>
<div id="app0">
    <p>{{* message }}</p>
    <p>{{* message.split('').reverse().join('') }}</p>
    <p>{{ message + message}}</p>
    <input>
</div>
<h5>-----------------------双向数据绑定-------------------------</h5>
<div id="app1">
    <p>{{* message }}</p>
    <p>{{* message.split('').reverse().join('') }}</p>
    <p>{{ message + message}}</p>
    <input v-model="message">
</div>
<h5>-----------------------列表输出-------------------------</h5>
<div id="app2">
    <ul>
        <li v-for="todo in todos">
            {{ todo.text }}
        </li>
    </ul>
</div>
<h5>-----------------------多维数组实例-------------------------</h5>
<div id="app">
    <ul id="app3">
        <li v-for="item in items">
            {{ parentMessage }} - {{ $index }} - {{ item.message }}
        </li>
    </ul>
</div>
<h5>-----------------------条件判断-------------------------</h5>
<div id="app4">
    {{ message }}
    <div v-if="{{ message }} > 0.5" v-model="{{ message }}">
        随机数大于 0.5
    </div>
    <div v-else>
        随机数不大于 0.5
    </div>
    <button v-on:click="condition">click me</button>

</div>
<div id="alluser"></div>
<h5>-----------------------POST-------------------------</h5>
<div id="app5">
    <ul>
        <li v-for="item in tabItems" @click="findAll()">
            {{ $index + 1 }} - {{ item.userName }} - {{ item.password }}
        </li>
    </ul>
    <button @click="findAll()">find all user</button>
</div>

<script>
    new Vue({
        el: '#app0',
        data: {
            message: '菜鸟教程!'
        }
    })

    new Vue({
        el: '#app1',
        data: {
            message: '菜鸟教程!'
        }
    })

    new Vue({
        el: '#app2',
        data: {
            todos: [
                { text: 'Learn JavaScript' },
                { text: 'Learn Vue.js' },
                { text: 'Build Something Awesome' }
            ]
        }
    })

    var app3 = new Vue({
        el: '#app3',
        data: {
            parentMessage: '多维数组实例',
            items: [
                { message: 'www.runoob.com' },
                { message: 'www.w3cschool.cc' }
            ]
        }
    })

    new Vue({
        el: '#app4',
        data: {
            message: 0.1
        },
        methods: {
            condition: function () {
                this.message = Math.random()
                $.post('/user/findAll', {}, function (data) {
                    var str = "";
                    $.each(data, function (index, item) {
                        str += "<table><tr><td>"+item.userName+"</td>"+
                               "<td>"+item.password+"</td>"+
                               "<td><input type=\"button\" value=\"delete\" onclick=\"del("+item.id+")\"></td>"+
                               "<td><input type=\"button\" value=\"edit\" onclick=\"editShow("+item.id+",'"+item.userName+"','"+item.password+"')\"></td></tr></table>";
                    });
                    $("#alluser").html(str);
                })
            }
        }
    })

    new Vue({
        el: '#app5',
        data: function() {
            return {
                tabItems: []
            }
        },
        /*beforeCreate: function () {
            alert("123");
            var that = this;
            $.post('/user/findAll', function (res) {
                that.tabItems = res;
            },"JSON")
        },*/
        methods: {
            findAll: function () {
                var that = this;
                $.post('/user/findAll', {}, function (res) {
                    that.tabItems = res;
                },"JSON")
            }
        }
    })
</script>
</body>
</html>
