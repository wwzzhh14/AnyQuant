<%--
  Created by IntelliJ IDEA.
  User: Jiayiwu
  Date: 16/5/30
  Time: 下午7:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>AnyQuant</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width; initial-scale=1.0; maximum-scale=1.0; minimum-scale=1.0; user-scalable=0;">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">

    <meta name="format-detection" content="telephone=no">
    <link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" href="styles.css">

    <script src="js/modernizr-2.6.2.min.js"></script>
    <script src="js/jquery.js"></script>
</head>
<body class="home">
<div id="wrapper_content">
    <!--[if lt IE 7]>
    <p class="chromeframe">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> or <a href="http://www.google.com/chromeframe/?redirect=true">activate Google Chrome Frame</a> to improve your experience.</p>
    <![endif]-->
    <div id="home_header">

        <<div class="slider-banner">
        <ul class="slides">
            <li class="slide" style="background-image:url(../images/pic_home_01.jpg);">
                <div class="info">
                    <h2>我们的 <strong>任务</strong> 是让您的投资更有效！</h2>
                    <p>你的私人订制</p>
                    <a href="personal" class="btn-white">个人资产</a>
                </div>
            </li>
            <li class="slide" style="background-image:url(../images/pic_home_02.jpg);">
                <div class="info">
                    <h2>这里可能有你想要的全部 <strong>股票</strong> 信息！</h2>
                    <p>一入股市深如海</p>
                    <a href="stocklist" class="btn-white">股票</a>
                </div>
            </li>
            <li class="slide" style="background-image:url(../images/pic_home_05.jpg);">
                <div class="info">
                    <h2>我们尝试 <strong>努力</strong> 提供更专业的推荐!</h2>
                    <p>相信这里能看到你喜欢的</p>
                    <a href="recommend" class="btn-white">推荐</a>
                </div>
            </li>
        </ul>
    </div>
        <div class="wrapper">
            <header style="color: #f3f3f3 " id="login-header">
                <a id="logout" style="float: right;color: #f3f3f3" href="loginOut"><a/>
                    <span id="line"style="float: right;color: #f3f3f3"></span>
                    <a id="username" style="float: right;color: #f3f3f3" href="personal"><a/>
                        <a href="home" id="logo" class="ir">Website</a>
            </header>
        </div><!-- /wrapper -->
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
                <div class="clearfix"></div>
            </div><!-- /wrapper -->
        </div><!-- /menu -->
    </div><!-- /home_header -->

    <script type="text/javascript">
        $(document).ready(function () {
            $.ajax({
                type:'POST',
                url:'refreshStock',
                contentType:"application/json",
                cache:false,
                dataType:'json',
                success:function(data){
                    if(data.success==false){

                        $("#login-header").append(
                                "<a style=\"float: right ;color: #f3f3f3\"  href=\"login\">登录</a>"+
                                "<span style=\"float: right\" >|</span>"+
                                "<a style=\"float: right;color: #f3f3f3\"  href=\"register\">注册</a>"
                        )
                    }else{
                        $("#username").text(data.user.name);
                        $("#line").text("|");
                        $("#logout").text("登出");
                    }
                }
            });
        })
    </script>

    <div class="wrapper">
        <section class="call_action">
            <div>

                <%--<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent justo ligula, interdum ut lobortis quis, interdum vitae metus. Proin fringilla metus non nulla cursus, sit amet rutrum est pretium.</p>--%>
            </div>
        </section>
        <div id="content" class="full">
            <article class="post">
                <div class="entry">
                    <div class="col col_1_3">
                        <div class="centered">
                            <p><a href="plate"><img src="images/ico_home_service_1.png" alt=""></a></p>
                            <h3>大盘</h3>
                            <p><a href="plate">这里你可以看到大盘在任意时间段的信息</a></p>
                            <p><a href="plate">更多</a></p>
                        </div>
                    </div>
                    <div class="col col_1_3">
                        <div class="centered">
                            <p><img src="images/ico_home_service_2.png" alt=""></p>
                            <h3>股票</h3>
                            <p><a href="stocklist">这里提供了上证所有股票的信息</a></p>
                            <p><a href="stocklist">更多</a></p>
                        </div>
                    </div>
                    <div class="col col_1_3 last">
                        <div class="centered">
                            <p><img src="images/ico_home_service_3.png" alt=""></p>
                            <h3>推荐</h3>
                            <p><a href="recommendation">这里是我们为你推荐的概念热点模块</a></p>
                            <p><a href="recommendation">更多</a></p>
                        </div>
                    </div>
                    <div class="clearfix"></div>
                </div>
            </article>
        </div><!-- /content -->
    </div><!-- /wrapper -->
    <div id="team"><!--team-->
        <div class="wrapper">
            <section>
                <h2>Meet Our Team</h2>
                <p>我们是立志做大事的一群小码农</p>
            </section>
            <div id="members">
                <div class="member">
                    <img src="images/wzh.png" alt="">
                    <h3>王志豪</h3>
                    <p class="job">President</p>
                    <p class="info">项目组长 负责逻辑层、web前后端交互,目前单身</p>
                    <!--<ul class="socials">
                        <li><a href="https://twitter.com/bestpsdfreebies" class="ir twitter">twitter</a></li>
                        <li><a href="https://www.facebook.com/bestpsdfreebies" class="ir facebook">facebook</a></li>
                        <li><a href="https://plus.google.com/102784875057987299787/posts" class="ir rss">rss</a></li>
                    </ul>-->
                </div>
                <div class="member">
                    <img src="images/wjy.png" alt="">
                    <h3>伍佳艺</h3>
                    <p class="job">Developer</p>
                    <p class="info">←_←左边那个确实没有女朋友.假装什么都没做</p>
                    <!--<ul class="socials">
                        <li><a target="_blank" href="https://twitter.com/bestpsdfreebies" class="ir twitter">twitter</a></li>
                        <li><a target="_blank" href="https://www.facebook.com/bestpsdfreebies" class="ir facebook">facebook</a></li>
                        <li><a target="_blank" href="https://plus.google.com/102784875057987299787/posts" class="ir rss">rss</a></li>
                    </ul>-->
                </div>
                <div class="member">
                    <img src="images/swr.png" alt="">
                    <h3>石婉蓉</h3>
                    <p class="job">Designer</p>
                    <p class="info">一只萌萌的小码农 负责前端开发</p>
                    <!--<ul class="socials">
                        <li><a target="_blank" href="https://twitter.com/bestpsdfreebies" class="ir twitter">twitter</a></li>
                        <li><a target="_blank" href="https://www.facebook.com/bestpsdfreebies" class="ir facebook">facebook</a></li>
                        <li><a target="_blank" href="https://plus.google.com/102784875057987299787/posts" class="ir rss">rss</a></li>
                    </ul>-->
                </div>
                <div class="member last">
                    <img src="images/sxc.png" alt="">
                    <h3>孙晓晨</h3>
                    <p class="job">Human Resources</p>
                    <p class="info">一只可爱的小码农 负责UI设计、算法设计</p>
                    <!--<ul class="socials">
                        <li><a target="_blank" href="https://twitter.com/bestpsdfreebies" class="ir twitter">twitter</a></li>
                        <li><a target="_blank" href="https://www.facebook.com/bestpsdfreebies" class="ir facebook">facebook</a></li>
                        <li><a target="_blank" href="https://plus.google.com/102784875057987299787/posts" class="ir rss">rss</a></li>
                    </ul>-->
                </div>
            </div>
        </div><!-- /wrapper -->
    </div><!--team-->
    <div class="wrapper">
        <section class="call_action bottom">
            <div>
                <h2>你喜欢这个页面么</h2>
                <p>如果你有任何建议，我们诚挚的希望和你交流</p>
                <p><a href="contact.html" class="btn">反馈</a></p>
            </div>
        </section>
    </div><!-- /wrapper -->
</div>

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
                <p>一个基于Java的股票分析展现软件，通过对股票数据的展现和分析，能给出让人比较感兴趣的结论和报告，有比较新颖的展现图表等方式。</p>
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
                        <li><a href="home.html">首页</a></li>
                        <li><a href="plate.html">大盘</a></li>
                        <li><a href="StockList.html.html">股票</a></li>
                        <li><a href="Recommend.html">推荐</a></li>
                        <li><a href="about.html">关于我们</a></li>
                        <li><a href="contact.html">联系我们</a></li>
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

<script src="js/jquery.js"></script>
<script src="js/jquery.isotope.min.js"></script>
<script src="js/jquery.placeholder.js"></script>
<script src="js/jquery.flexslider-min.js"></script>
<script src="js/jquery.magnific.popup.min.js"></script>
<script src="js/jquery-ui-1.10.3.custom.min.js"></script>
<script src="js/jquery.magnific.popup.min.js"></script>

<script src="js/main.js"></script>
</body>
</html>
