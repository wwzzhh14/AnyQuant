<%--
  Created by IntelliJ IDEA.
  User: elva
  Date: 2016/6/7
  Time: 17:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>AnyQuant | Login</title>
    <link rel="stylesheet" type="text/css" href="styles-login.css">
    <link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon" />
</head>

<body>

<div class="htmleaf-container">
    <div class="wrapper">
        <div class="container">
            <h1>Welcome</h1>

            <form class="form" name="loginForm" id='loginForm' action="loginin" method="post">
                <span style=" color:red ">${loginResult}</span>
                <input type="text" placeholder="用户名" name="username">
                <input type="password" placeholder="密码" name="password">
                <button type="submit" id="login-button">登录</button>
                <li ><a href="register">没有账号？点此注册吧！</a></li>
                <li ><a href="home">回到主页</a></li>
            </form>
        </div>

        <ul class="bg-bubbles">

        </ul>
    </div>
</div>


<script src="js/jquery-2.1.1.min.js" type="text/javascript"></script>
<%--<script>--%>
    <%--$('#login-button').click(function (event) {--%>
        <%--event.preventDefault();--%>
        <%--$('form').fadeOut(500);--%>
        <%--$('.wrapper').addClass('form-success');--%>
    <%--});--%>
<%--</script>--%>

</div>
</body>

</html>
