<%--
  Created by IntelliJ IDEA.
  User: elva
  Date: 2016/6/7
  Time: 18:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>AnyQuant | Register</title>
    <link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" type="text/css" href="styles-login.css">
    <script src="themes/js/jquery.min.js" type="text/javascript"></script>
    <script src="themes/js/jquery.form.js" type="text/javascript"></script>
    <script src="themes/js/json.parse.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(function(){
            $(".btn").click(function(){



                var userName= $("#username").val();
                var userPass= $("#password1").val();
                var userPass2=$("#password2").val();


                userPass=$.trim(userPass);
                userPass2=$.trim(userPass2);

                $(".input_div1 span,.input_div2 span,.input_div3 span,.input_div4 span").html("");
                $(".btn").val('注册中...').attr('disabled','disabled');

                if(userPass != userPass2){
                    $(".input_div4 span").html('<img src="themes/images/v3/text_error.png"><font color=red>两次输入的密码不一致!</font>');
                    $("#password2").focus();
                    $(".btn").val('注册').removeAttr('disabled');
                    return false;
                } else{
                    $("#registerForm").ajaxSubmit(function(e){
                        var obj=json_parse(e);
                        var code=obj.code;
                        var info = '<img src="themes/images/v3/text_error.png"><font color=red>'+obj.info+"</font>";
                        if(code=='-1' || code=='-5'){
                            $(".input_div1 span").html(info);
                            $("#username").focus();
                        }else if(code =='-2' || code == '-4'){
                            $(".input_div3 span").html(info);
                            $("#password1").focus();
                        }else if(code == '1'){

                            window.location.href=obj.acturl;
                        }

                    })
                }

            })
    </script>
</head>

<body>

<div class="htmleaf-container">
    <div class="wrapper">
        <div class="container">
            <h1>Welcome</h1>

            <form class="form" name="registerForm" id='registerForm' action="registerin" method="post" >
                <span style=" color:red ">${registerResult}</span>
                <div class="div_form clear ">
                    <div class="input_div input_div1">
                        <input id="username" name="usercode" type="text" placeholder="账号" maxlength="24">
                        <span></span>
                    </div>
                </div>
                <div class="div_form clear ">
                    <div class="input_div input_div2">
                        <input id="usercode" name="username" type="text" placeholder="用户名" maxlength="24">
                        <span></span>
                    </div>
                </div>

                <div class="div_form clear ">

                    <div class="input_div input_div3">
                        <input id="password1" name="password" type="password" placeholder="密码" maxlength="32">
                        <span></span>
                    </div>
                </div>
                <div class="div_form clear ">

                    <div class="input_div input_div4">
                        <input id="password2" name="password2" type="password" placeholder="确认密码" maxlength="32">
                        <span></span>
                    </div>
                </div>



                <div class="div_form clear ">

                    <div class="input_div">
                        <button type="submit" id="register-button">注册</button>
                    </div>
                </div>
                <li ><a href="login">点此登录</a></li>
                <li ><a href="home">回到主页</a></li>
            </form>
        </div>

        <ul class="bg-bubbles">
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
        </ul>
    </div>
</div>





</body>

</html>
