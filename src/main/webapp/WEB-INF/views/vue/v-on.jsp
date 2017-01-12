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
<div id="counter-event-example">
    <p>{{ total }}</p>
    <%--v-on 监听事件,increment 自定义事件--%>
    <button-counter v-on:increment="incrementTotal"></button-counter>
    <button-counter v-on:increment="incrementTotal"></button-counter>
</div>
</body>
<script>
    Vue.component('button-counter', {
        template: '<button v-on:click="increment">{{ counter }}</button>',
        data: function () {
            return {
                counter: 0
            }
        },
        methods: {
            increment: function () {
                this.counter += 1
                this.$emit('increment') //触发事件
            }
        },
    })
    new Vue({
        el: '#counter-event-example',
        data: {
            total: 0
        },
        methods: {
            incrementTotal: function () {
                this.total += 1
            }
        }
    })

</script>
</html>
