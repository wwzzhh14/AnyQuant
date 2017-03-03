<%--
  Created by IntelliJ IDEA.
  User: Jiayiwu
  Date: 16/6/13
  Time: 下午8:03
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
    <title>AnyQuant | Download</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width; initial-scale=1.0; maximum-scale=1.0; minimum-scale=1.0; user-scalable=0;">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">

    <meta name="format-detection" content="telephone=no">
    <link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" href="styles.css">

    <script src="js/modernizr-2.6.2.min.js"></script>
</head>
<body>
<div id="wrapper_content">
    <!--[if lt IE 7]>
    <p class="chromeframe">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> or <a href="http://www.google.com/chromeframe/?redirect=true">activate Google Chrome Frame</a> to improve your experience.</p>
    <![endif]-->
    <div class="wrapper">
        <header>
            <a href="home.html" id="logo" class="ir">Website</a>
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
                        <input  name="result"type="text" id="search_text" placeholder="Search">
                        <input type="submit" value="GO" id="search_submit">
                        <p class="btn_search">search</p>
                    </fieldset>
                </form>
            </div>
            <div class="clearfix"></div>
        </div><!-- /wrapper -->
    </div>
    <div id="featured">
        <div class="wrapper">
            <h1></h1>
        </div>
    </div><!-- /featured -->
    <div class="wrapper">
        <div id="content" class="full">
            <article class="post">
                <div class="entry entry-404">
                    <h2> AnyQuant股票桌面版 </h2>

                    <p>AnyQuant桌面版是基于AnyQuant网站深度定制的一个版本,<br>适用于Mac/PC,您可以使用该版本进行更为详细的股票分析.</p>
                    <h3><span class="highlighted"><a href="http://yun.baidu.com/share/link?shareid=2774753286&uk=2064160078" target="_blank">点此下载</a></span></h3>
                    <p>因服务器压力的原因,我们现已把相关下载资源转移到百度云上</p>
                </div>
            </article>
        </div><!-- /content -->
        <div class="clearfix"></div>
    </div><!-- /wrapper -->
</div>

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
