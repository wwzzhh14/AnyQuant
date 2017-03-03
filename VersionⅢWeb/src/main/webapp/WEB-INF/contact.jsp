<%--
  Created by IntelliJ IDEA.
  User: elva
  Date: 2016/6/7
  Time: 17:57
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
    <title>AnyQuant | Contact</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width; initial-scale=1.0; maximum-scale=1.0; minimum-scale=1.0; user-scalable=0;">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">

    <meta name="format-detection" content="telephone=no">
    <link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" href="styles-sxc.css">

    <script src="js/modernizr-2.6.2.min.js"></script>
    <script type="text/javascript" >var base_url='http://themes.bestpsdfreebies.com/deliver/'</script>
</head>
<body>
<div id="wrapper_content">
    <!--[if lt IE 7]>
    <p class="chromeframe">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> or <a href="http://www.google.com/chromeframe/?redirect=true">activate Google Chrome Frame</a> to improve your experience.</p>
    <![endif]-->
    <div class="wrapper">
        <header>
            <a href="home.html" id="logo" class="ir">Website</a>
            <!--<ul class="socials">
                <li><a target="_blank" href="https://twitter.com/bestpsdfreebies" class="ir twitter">twitter</a></li>
                <li><a target="_blank" href="https://www.facebook.com/bestpsdfreebies" class="ir facebook">facebook</a></li>
                <li><a target="_blank" href="http://feeds.feedburner.com/bestpsdfreebies" class="ir rss">rss</a></li>
                <li><a target="_blank" href="http://www.pinterest.com/mjreimer/psd-freebies/" class="ir pinterest">pinterest</a></li>
                <li><a target="_blank" href="https://plus.google.com/102784875057987299787/posts" class="ir google">google+</a></li>
                <li><a target="_blank" href="http://dribbble.com/bestpsdfreebies" class="ir dribbble">dribbble</a></li>
                <li><a href="#" class="ir linkedin">linkedin</a></li>
                <li><a href="#" class="ir flickr">flickr</a></li>
                <li><a href="#" class="ir youtube">youtube</a></li>
            </ul>-->
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
    </div>
    <div id="featured">
        <div class="wrapper">
            <h1>联系我们</h1>
        </div>
    </div><!-- /featured -->
    <div class="wrapper contact_page">
        <section class="call_action">
            <div>
                <h2>我们希望听到你的宝贵意见!</h2>
                <%--<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent justo ligula, interdum ut lobortis quis, interdum vitae metus. Proin fringilla metus non nulla cursus, sit amet rutrum est pretium.</p>--%>
            </div>
        </section>
        <div id="content" class="not_single">
            <article class="post">
                <div class="entry">
                    <h3>建议表</h3>
                    <p>请在这里写下你对我们的任何看法，当然啦求组员联系方式什么的也完全OK！嘤嘤嘤.</p>
                    <div id="success" class="alert_box success" style="display:none"><p>Form was submitted.</p></div>
                    <form action="contactresult" method="post" name="contact" id="contact">
                        <fieldset>
                            <label class="label">姓名 <span class="req">*</span></label>
                            <small class="note">并非真实姓名也可以.</small>
                            <small class="error">Name required</small>
                            <input type="text" id="f_name" name="f_name">
                        </fieldset>
                        <fieldset>
                            <label class="label">邮箱地址 <span class="req">*</span></label>
                            <small class="note">这将是你收到回复邮件的地方.</small>
                            <small class="error">incorrect email address</small>
                            <input type="email" id="f_email" name="f_email">
                        </fieldset>
                        <fieldset>
                            <label class="label">主题 <span class="req">*</span></label>
                            <small class="note">想聊什么都可以.</small>
                            <small class="error">Subject required</small>
                            <input type="text" name="f_subj" id="f_subj">
                        </fieldset>
                        <fieldset>
                            <label class="label">信息 <span class="req">*</span></label>
                            <small class="note">请尽量不要太长好么 ... 嘤嘤嘤!</small>
                            <small class="error">Message required</small>
                            <textarea name="f_message" id="f_message"></textarea>
                        </fieldset>
                        <fieldset class="submit">
                            <input type="submit" value="发送信息" class="btn">
                        </fieldset>
                    </form>
                    <div class="clearfix"></div>
                </div>
            </article>
        </div><!-- /content -->
        <aside id="sidebar">
            <div class="sidebar_widgets">
                <div class="widget widget_text">
                    <h3 class="widgettitle">我们的地址</h3>
                    <div class="textwidget">
                        <p>欢迎你前来探访</p>
                        <p>仙林大道163号<br>
                            南京市栖霞区<br>
                            中国江苏</p>
                    </div>
                </div>
                <div class="widget widget_text">
                    <h3 class="widgettitle">联系方式</h3>
                    <div class="textwidget">
                        <p>
                            <strong>Email:</strong> info@AnyQuant.ca<br>
                            <strong>Primary Phone:</strong> 1 (306) 222 - 3456<br>
                            <strong>Alternate Phone:</strong> 1 (306) 222 - 4567<br>
                            <strong>Fax:</strong> 1 (306) 222 - 5678
                        </p>
                    </div>
                </div>
                <div class="widget widget_text">
                    <h3 class="widgettitle">工作时间</h3>
                    <div class="textwidget">
                        <p>
                            <span>周一 - 周五</span> 7 pm - 9 pm<br>
                            <span>周六 - 周日</span> 不在！嘤嘤嘤<br>
                            <span>节假日</span> 失联啦！嘤嘤嘤
                        </p>
                        <p>* 任何时刻都可以用邮件或者电话联系我们</p>
                    </div>
                </div>
            </div>
        </aside><!-- /sidebar -->
        <div class="clearfix"></div>
    </div><!-- /wrapper -->
    <div class="wrapper">
        <section class="call_action bottom">
            <div>
                <h2>如果你喜欢我们这个团队，加入我们！</h2>
                <%--<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent justo ligula, interdum ut lobortis quis, interdum vitae metus. Proin fringilla metus non nulla cursus, sit amet rutrum est pretium.</p>--%>
                <p><a href="#" class="btn">加入我们</a></p>
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
                        <form method="post" action="contactresult">
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
                <li><a href="http://www.lgdreamer.com">Privacy Policy</a></li>
                <li><a href="contact">Contact</a></li>
            </ul>
        </section><!-- /bottom -->
    </footer><!-- /footer -->
</div><!-- /wrapper -->

<script src="js/jquery.js"></script>
<%--<script src="js/jquery.isotope.min.js"></script>--%>
<%--<script src="js/jquery.placeholder.js"></script>--%>
<%--<script src="js/jquery.flexslider-min.js"></script>--%>
<%--<script src="js/jquery.magnific.popup.min.js"></script>--%>
<%--<script src="js/jquery-ui-1.10.3.custom.min.js"></script>--%>
<%--<script src="js/jquery.magnific.popup.min.js"></script>--%>

<%--<script src="js/main.js"></script>--%>
</body>
</html>
