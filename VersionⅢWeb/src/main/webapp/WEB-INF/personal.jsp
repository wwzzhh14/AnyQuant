<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 2016/6/5
  Time: 22:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" href="styles-sxc.css" />
    <title>AnyQuant | Personal</title>
    <script src="js/echarts.min.js"></script>
    <script src="js/prefixfree.min.js.js"></script>
    <script src="js/jquery.js"></script>
    <link rel="stylesheet" type="text/css" href="dialog/css/sweet-alert.css">
    <script src="dialog/js/sweet-alert.min.js"></script>
    <link rel="stylesheet" href="table/css/style2.css" type="text/css">
    <style>
        @import url(http://fonts.googleapis.com/css?family=Expletus+Sans);

        /* Basic resets */



        /* Styling an indeterminate progress bar */

        progress:not(value) {
            /* Add your styles here. As part of this walkthrough we will focus only on determinate progress bars. */
        }

        /* Styling the determinate progress element */

        progress[value] {
            /* Get rid of the default appearance */
            appearance: none;

            /* This unfortunately leaves a trail of border behind in Firefox and Opera. We can remove that by setting the border to none. */
            border: none;

            /* Add dimensions */
            width: 100%; height: 20px;

            /* Although firefox doesn't provide any additional pseudo class to style the progress element container, any style applied here works on the container. */
            background-color: whiteSmoke;
            border-radius: 3px;
            box-shadow: 0 2px 3px rgba(0,0,0,.5) inset;

            /* Of all IE, only IE10 supports progress element that too partially. It only allows to change the background-color of the progress value using the 'color' attribute. */
            color: royalblue;

            position: relative;
            margin: 0 0 1.5em;
        }

        /*
        Webkit browsers provide two pseudo classes that can be use to style HTML5 progress element.
        -webkit-progress-bar -> To style the progress element container
        -webkit-progress-value -> To style the progress element value.
        */

        progress[value]::-webkit-progress-bar {
            background-color: whiteSmoke;
            border-radius: 3px;
            box-shadow: 0 2px 3px rgba(0,0,0,.5) inset;
        }

        progress[value]::-webkit-progress-value {
            position: relative;

            background-size: 35px 20px, 100% 100%, 100% 100%;
            border-radius:3px;

            /* Let's animate this */
            animation: animate-stripes 5s linear infinite;
        }

        @keyframes animate-stripes { 100% { background-position: -100px 0; } }

        /* Let's spice up things little bit by using pseudo elements. */

        progress[value]::-webkit-progress-value:after {
            /* Only webkit/blink browsers understand pseudo elements on pseudo classes. A rare phenomenon! */
            content: '';
            position: absolute;

            width:5px; height:5px;
            top:7px; right:7px;

            background-color: white;
            border-radius: 100%;
        }

        /* Firefox provides a single pseudo class to style the progress element value and not for container. -moz-progress-bar */

        progress[value]::-moz-progress-bar {
            /* Gradient background with Stripes */
            background-image:
                    -moz-linear-gradient( 135deg,
                    transparent,
                    transparent 33%,
                    rgba(0,0,0,.1) 33%,
                    rgba(0,0,0,.1) 66%,
                    transparent 66%),
                    -moz-linear-gradient( top,
                    rgba(255, 255, 255, .25),
                    rgba(0,0,0,.2)),
                    -moz-linear-gradient( left, #09c, #f44);

            background-size: 35px 20px, 100% 100%, 100% 100%;
            border-radius:3px;

            /* Firefox doesn't support CSS3 keyframe animations on progress element. Hence, we did not include animate-stripes in this code block */
        }

        /* Fallback technique styles */
        .progress-bar {
            background-color: whiteSmoke;
            border-radius: 3px;
            box-shadow: 0 2px 3px rgba(0,0,0,.5) inset;

            /* Dimensions should be similar to the parent progress element. */
            width: 100%; height:20px;
        }

        .progress-bar span {
            background-color: royalblue;
            border-radius: 3px;

            display: block;
            text-indent: -9999px;
        }

        p[data-value] {

            position: relative;
        }

        /* The percentage will automatically fall in place as soon as we make the width fluid. Now making widths fluid. */

        p[data-value]:after {
            content: attr(data-value) ;
            position: absolute; right:0;
        }

        .progress1::-webkit-progress-value,
        .python::-webkit-progress-value  {
            /* Gradient background with Stripes */
            background-image:
                    -webkit-linear-gradient( 135deg,
                    transparent,
                    transparent 33%,
                    rgba(0,0,0,.1) 33%,
                    rgba(0,0,0,.1) 66%,
                    transparent 66%),
                    -webkit-linear-gradient( top,
                    rgba(255, 255, 255, .25),
                    rgba(0,0,0,.2)),
                    -webkit-linear-gradient( left, #09c, #f44);
        }

        .progress2::-webkit-progress-value,
        .php::-webkit-progress-value
        {
            /* Gradient background with Stripes */
            background-image:
                    -webkit-linear-gradient( 135deg,
                    transparent,
                    transparent 33%,
                    rgba(0,0,0,.1) 33%,
                    rgba(0,0,0,.1) 66%,
                    transparent 66%),
                    -webkit-linear-gradient( top,
                    rgba(255, 255, 255, .25),
                    rgba(0,0,0,.2)),
                    -webkit-linear-gradient( left, #09c, #ff0);
        }

        .progress3::-webkit-progress-value,
        .node-js::-webkit-progress-value
        {
            /* Gradient background with Stripes */
            background-image:
                    -webkit-linear-gradient( 135deg,
                    transparent,
                    transparent 33%,
                    rgba(0,0,0,.1) 33%,
                    rgba(0,0,0,.1) 66%,
                    transparent 66%),
                    -webkit-linear-gradient( top,
                    rgba(255, 255, 255, .25),
                    rgba(0,0,0,.2)),
                    -webkit-linear-gradient( left, #09c, #690);
        }

        /* Similarly, for Mozillaa. Unfortunately combining the styles for different browsers will break every other browser. Hence, we need a separate block. */

        .progress1
        ::-moz-progress-bar,
        .php::-moz-progress-bar {
            /* Gradient background with Stripes */
            background-image:
                    -moz-linear-gradient( 135deg,
                    transparent,
                    transparent 33%,
                    rgba(0,0,0,.1) 33%,
                    rgba(0,0,0,.1) 66%,
                    transparent 66%),
                    -moz-linear-gradient( top,
                    rgba(255, 255, 255, .25),
                    rgba(0,0,0,.2)),
                    -moz-linear-gradient( left, #09c, #f44);
        }

        .progress2::-moz-progress-bar,
        .php::-moz-progress-bar {
        {
        /* Gradient background with Stripes */
            background-image:
                    -moz-linear-gradient( 135deg,
                    transparent,
                    transparent 33%,
                    rgba(0,0,0,.1) 33%,
                    rgba(0,0,0,.1) 66%,
                    transparent 66%),
                    -moz-linear-gradient( top,
                    rgba(255, 255, 255, .25),
                    rgba(0,0,0,.2)),
                    -moz-linear-gradient( left, #09c, #ff0);
        }

        .progress3::-moz-progress-bar,
        .node-js::-moz-progress-bar {
            /* Gradient background with Stripes */
            background-image:
                    -moz-linear-gradient( 135deg,
                    transparent,
                    transparent 33%,
                    rgba(0,0,0,.1) 33%,
                    rgba(0,0,0,.1) 66%,
                    transparent 66%),
                    -moz-linear-gradient( top,
                    rgba(255, 255, 255, .25),
                    rgba(0,0,0,.2)),
                    -moz-linear-gradient( left, #09c, #690);
        }

        /* Now we are good to duplicate html code for other skills and then add the css code for the new skill based on data-skill */

        /* THE END */
    </style>

    <script src="progress_bar/js/prefixfree.min.js"></script>


</head>

<body>

<div id="wrapper_content">
    <!--[if lt IE 7]>
    <p class="chromeframe">You are using an <strong>outdated<> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> or <a href="http://www.google.com/chromeframe/?redirect=true">activate Google Chrome Frame</a> to improve your experience.</p>
    <![endif]-->
    <div class="wrapper">
        <header style="color: #3f3f3f" id="login-header">
            <a id="logout" style="float: right" href="loginOut"><a/>
                <span id="line"style="float: right"></span>
                <a id="username" style="float: right" href="personal"><a/>
                    <a href="home" id="logo" class="ir">Website</a>
        </header>
    </div><!-- /wrapper -->

    <script type="text/javascript">

        var my_stocklist;
        $(document).ready(function () {

            $.ajax({
                type:'POST',
                url:'refreshStock',
                contentType:"application/json",
                cache:false,
                dataType:'json',
                success:function(data){
                    if(data.success==false){
                        swal({
                            title: "信息错误错误",
                            text: data.result,
                            type: "warning",
                        })
                        $("#login-header").append(

                                "<a style=\"float: right ;color: #3f3f3f\"  href=\"login\">登录</a>"+
                                "<span style=\"float: right\" >|</span>"+
                                "<a style=\"float: right;color: #3f3f3f\"  href=\"register\">注册</a>"
                        )
                    }else{

                        var my_user=data.user;
                        console.log(my_user)

                        $("#username").text(my_user.name);
                        $("#line").text("|");
                        $("#logout").text("登出");

                        $("#name").text(my_user.name);
                        $(".progress1").attr("max",my_user.total);
                        $(".progress1").attr("value",my_user.total);
                        $(".progress-p1").attr("data-value",my_user.total);

                        $(".progress2").attr("max",my_user.total);
                        $(".progress2").attr("value",my_user.stockPrice);
                        $(".progress-p2").attr("data-value",my_user.stockPrice);

                        $(".progress3").attr("max",my_user.total);
                        $(".progress3").attr("value",my_user.cash);
                        $(".progress-p3").attr("data-value",my_user.cash);

                        my_stocklist=my_user.list;
                        var data2= my_user.list;
                        var n=data2.length;
                        for(var i=0;i<n;i++){
                            $("#tb").append( "<tr id="+"\'row"+i+"\'>"+
                                    "<td>"+data2[i].stockName+"</td>"+
                                    "<td >"+data2[i].price+"</td>"+
                                    "<td class="+"\'num\'>"+data2[i].num+"</td>"+
                                    "<td >"+
                                    "<input type=\"image\" src=\"images/minus.png\" onclick="+"\'decNum("+i+")\'"+"/>"+
                                    " <textarea rows=\'1\' cols='10' input type=text >"+data2[i].num+"</textarea>"+
                                    "<input type=\"image\" src=\"images/plus.png\" onclick="+"\'addNum("+i+")\'"+"/>"+
                                    "</td>"+
                                    "</tr>");
                        }

                    }
//
                }
            });
        })
        function save() {
            var updata_data=[];
            var row=[];
            $("#tb").children().each(function(i){
                if( $(this).find("[type=text]").val()=='0'){
                    row.push($(this));
                }
                $(this).find("[class=num]").text( $(this).find("[type=text]").val());
                var name;
                var price;
                var num;
                $(this).children().each(function(j){
                    if(j==0){name=$(this).text()}
                    if(j==1){price=$(this).text()}
                    if(j==2){num=$(this).text()}
                });
                updata_data.push({"name":name,"code":my_stocklist[i].codeNum,"price":price,"num":num});


            });
            for(var i=0;i<row.length;i++){
                row[i].remove();
            }
            $.ajax({
                type:'POST',
                url:'trade',
                data:JSON.stringify(updata_data),
                contentType:"application/json",
                cache:false,
                dataType:'json',
                success:function(data){
                    var my_list2=data.user.list;
                    $("#tb").children().each(function(i){
                        $(this).find("[class=num]").text( $(this).find("[type=text]").val());
                        $(this).children().each(function(j){
                            if(j==0){$(this).text(my_list2[i].stockName)}
                            if(j==1){$(this).text(my_list2[i].price)}
                            if(j==2){$(this).text(my_list2[i].num)}
                        });
                    });

                    if(data.success){
                        swal("交易成功", "", "success");
                        var my_user=data.user;
                        my_stocklist=data.user.list;
                        $(".progress1").attr("max",my_user.total);
                        $(".progress1").attr("value",my_user.total);
                        $(".progress-p1").attr("data-value",my_user.total);

                        $(".progress2").attr("max",my_user.total);
                        $(".progress2").attr("value",my_user.stockPrice);
                        $(".progress-p2").attr("data-value",my_user.stockPrice);

                        $(".progress3").attr("max",my_user.total);
                        $(".progress3").attr("value",my_user.cash);
                        $(".progress-p3").attr("data-value",my_user.cash);

                    }else{
                        swal({
                            title: "交易失败！",
                            text: data.result,
                            type: "warning",
                        })
                    }

                }
            });

        }
    </script>



    <div id="menu">
        <div class="wrapper">
            <div id="menu_trigger" class="mobile">menu</div>
            <nav>
                <ul>
                    <li><a href="home">首页</a></li>
                    <li><a href="plate">大盘</a></li>
                    <li><a href="stocklist">股票</a></li>
                    <li><a href="recommend">推荐</a></li>
                    <li><a href="download">下载</a></li>
                    <li><a href="about">关于我们</a></li>
                    <li><a href="contact">联系我们</a></li>
                </ul>
            </nav>
            <div class="search">
                <form method="post" action="Search" id="search_form">
                    <fieldset>
                        <input name="result" type="text" id="search_text" placeholder="Search">
                        <input type="submit" value="GO" id="search_submit">
                        <p class="btn_search">search</p>
                    </fieldset>
                </form>
            </div>
            <script type="text/javascript">
                jQuery(document).on('click', "#menu .btn_search", function (e) {
                    e.preventDefault()
                    jQuery('#menu #search_text').stop(true,true).animate({width:'show'},400).focus();
                    jQuery(this).hide(0);
                    jQuery('#menu #search_submit').show(0);
                });
                if(!isIE()){
                    jQuery(document).on('blur', "#search_form", function (e) {
                        e.preventDefault()
                        var search_text = jQuery('#search_text').val();
                        if(search_text==''){
                            jQuery('#menu #search_text').stop(true,true).animate({width:'hide'},100);
                            jQuery('#menu .btn_search').show(0);
                            jQuery('#menu #search_submit').hide(0);
                        }
                    });
                }
            </script>

            <div class="clearfix"></div>
        </div><!-- /wrapper -->
    </div>
    <div id="featured">
        <div class="wrapper">
            <h1>个人资产</h1>
        </div>

    </div><!-- /featured -->
    <div class="clearfix"></div>
    <div style="width: 80%;height: 300px;margin-left: 10%;">
        <div style="width:20%;height: 100%;float: left">
            <img src="images/head.png" alt="" style="width: 90%;height: 70%"/>
            <br/>
            <h2 style="text-align: center" id="name"></h2>
        </div>
        <div style="width:50%;height: 100%;float: left;">
            <div style="width:100%;height: 50%;">
                <p class="progress-p1" style="width:100%" data-value="0">总资产</p>
                <progress max="100" value="0" class="progress1"><!-- Browsers that support HTML5 progress element will ignore the html inside `progress` element. Whereas older browsers will ignore the `progress` element and instead render the html inside it. -->
                </progress>

                <!-- CSS3 -->
                <p class="progress-p2"style="width:100%" data-value="0">股票资产</p>
                <progress max="100" value="0" class="progress2">
                </progress>

                <!-- jQuery -->
                <p class="progress-p3"style="width:100%" data-value="0">现金流</p>
                <progress max="100" value="0" class="progress3">
                </progress>

            </div>

        </div>
        <div style="width:25%;height: 100%;float: left;margin-left: 5%">
            <h2>热点股票信息</h2>
            <table id="tb-stockinfo" >
                <tr>
                    <td  style="padding: 8px;border-bottom: 1px solid #9d9d9d"><a href="Search?result=sh601998">中信银行</a></td>
                </tr>
                <tr>
                    <td  style="padding: 8px;border-bottom: 1px solid #9d9d9d"><a href="Search?result=sh601009">南京银行</a></td>
                </tr>
                <tr>
                    <td  style="padding: 8px;border-bottom: 1px solid #9d9d9d"><a href="Search?result=sh601398">工商银行</a></td>
                </tr>
                <tr>
                    <td  style="padding: 8px;border-bottom: 1px solid #9d9d9d"><a href="Search?result=sz002024">苏宁云商</a></td>
                </tr>
            </table>

        </div>
    </div>
    <script type="text/javascript">

        $(document).ready(function () {
            var data=[100,20,80];


        })
    </script>
    <br/>
    <br/>
    <div style="width: 80%;margin-left: 10%;">
        <table class="zebra">
            <!--<caption>机构评级</caption>-->
            <thead>
            <tr>
                <th>股票名称</th>
                <th>价格</th>
                <th>数量</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody id="tb" >


            </tbody>
        </table>
        <br/>
        <input type="image" src="images/deal.png" onclick="save() " style="width: 100px;height: 50px;float: right"/>
    </div>
    <script type="text/javascript">

        var mydata;
        function addNum(n) {
            var value=parseInt($("#row"+n).find("[type=text]").val())+1;
            $("#row"+n).find("[type=text]").val(value);

        }
        function decNum(n) {
            var value=parseInt($("#row"+n).find("[type=text]").val())-1;
            if(value<0){
                value=0;
            }

            $("#row"+n).find("[type=text]").val(value);
        }


    </script>
    <br/>
    <br/>
    <br/>


</div><!-- /wrapper -->
<div class="wrapper">
    <footer>
        <section class="top">
            <div class="info">
                <a href="#" class="ir" id="logo_footer">Website</a>
                <!--<ul class="socials">
                    <li><a target="_blank" href="https://twitter.com/bestpsdfreebies" class="ir twitter">twitter</a></li>
                    <li><a target="_blank" href="https://www.facebook.com/bestpsdfreebies" class="ir facebook">facebook</a></li>
                    <li><a target="_blank" href="http://feeds.feedburner.com/bestpsdfreebies" class="ir rss">rss</a></li>
                    <li><a target="_blank" href="http://www.pinterest.com/mjreimer/psd-freebies/" class="ir pinterest">pinterest</a></li>
                </ul>-->
                <p>一个基于Java Applet的股票分析展现软件，通过对股票数据的展现和分析，能给出让人比较感兴趣的结论和报告，有比较新颖的展现图表等方式。</p>
            </div>
            <div class="widgets">
                <div class="widget widget_text">
                    <h3 class="widgettitle">联系方式</h3>
                    <div class="textwidget">
                        <p>栖霞区，仙林大道163号<br>南京, 江苏 <br>中国, 210023</p>
                        <p>info@AnyQuant.ca</p>
                        <h4>114.55.35.12</h4>
                    </div>
                </div>
                <div class="widget widget_menu">
                    <h3 class="widgettitle">快速链接</h3>
                    <ul>
                        <li><a href="home">首页</a></li>
                        <li><a href="plate">大盘</a></li>
                        <li><a href="stockList">股票</a></li>
                        <li><a href="recommend">推荐</a></li>
                        <li><a href="download">下载</a></li>
                        <li><a href="about">关于我们</a></li>
                        <li><a href="contact">联系我们</a></li>
                    </ul>
                </div>
                <div class="widget widget_newsletter last">
                    <h3 class="widgettitle">通讯</h3>
                    <div class="textwidget">
                        <p>你的任何意见对我们来说都很宝贵</p>
                        <form method="post" action="#">
                            <fieldset>
                                <input type="text" placeholder="Email">
                                <input type="submit" value="OK">
                            </fieldset>
                        </form>
                    </div>
                </div>
            </div>
        </section><!-- /top -->
        <section class="bottom">
            <p class="copyrights">股市有风险，投资需谨慎。Copyright &copy; 2015-2016 LGDreamer工作室  AnyQuant. All Rights Reserved.</p>
            <ul>
                <li><a href="about">About</a></li>
                <li><a href="www.lgdreamer.com">Privacy Policy</a></li>
                <li><a href="contact">Contact</a></li>
            </ul>
        </section><!-- /bottom -->
    </footer><!-- /footer -->
</div><!-- /wrapper -->
<script type="text/javascript">
    $(document).ready(function () {

        function update() {

            $.ajax({
                type:'POST',
                url:'refreshStock',
                contentType:"application/json",
                cache:false,
                dataType:'json',
                success:function(data){
                    if(data.success==false){
                        swal({
                            title: "信息错误",
                            text: data.result,
                            type: "warning",
                        })
                    }else{


                        var my_user=data.user;
                        console.log(my_user)

                        $(".progress1").attr("max",my_user.total);
                        $(".progress1").attr("value",my_user.total);
                        $(".progress-p1").attr("data-value",my_user.total);

                        $(".progress2").attr("max",my_user.total);
                        $(".progress2").attr("value",my_user.stockPrice);
                        $(".progress-p2").attr("data-value",my_user.stockPrice);

                        $(".progress3").attr("max",my_user.total);
                        $(".progress3").attr("value",my_user.cash);
                        $(".progress-p3").attr("data-value",my_user.cash);

                        var data2= my_user.list;
                        $("#tb").children().each(function(i){
                            // $(this).find("[class=num]").text( $(this).find("[type=text]").val());
                            $(this).children().each(function(j){
                                if(j==0){$(this).text(data2[i].stockName)}
                                if(j==1){$(this).text(data2[i].price)}
                                if(j==2){$(this).text(data2[i].num)}
                            });
                        });

                    }
//
                }
            });
        }

        setInterval(update,60000);
    })



</script>
</body>
</html>


