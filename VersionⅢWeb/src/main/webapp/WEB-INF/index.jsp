<%--
  Created by IntelliJ IDEA.
  User: Jiayiwu
  Date: 16/5/14
  Time: 下午12:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ch">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>AnyQuant | StockInfo</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width; initial-scale=1.0; maximum-scale=1.0; minimum-scale=1.0; user-scalable=0;">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">


    <meta name="format-detection" content="telephone=no">
    <link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" href="styles.css">
    <link rel="stylesheet" href="table/css/style.css" type="text/css">

    <!--<script src="js/modernizr-2.6.2.min.js"></script>-->
    <script src="js/echarts.min.js"></script>
    <script src="js/jquery.js"></script>
    <script src="js/Kline.js"></script>
    <link rel="stylesheet" type="text/css" href="dialog/css/sweet-alert.css">
    <script src="dialog/js/sweet-alert.min.js"></script>
    <script type="text/javascript" >var base_url='http://themes.bestpsdfreebies.com/deliver/'</script>
    <%--<script type="text/javascript">--%>
        <%--$(function () {--%>
            <%--$("#div1-star1").attr('src',"stars/img/star-on.png");--%>
            <%--$("#div2-star1").attr('src',"stars/img/star-on.png");--%>
            <%--$("#div3-star1").attr('src',"stars/img/star-on.png");--%>

        <%--});--%>
    <%--</script>--%>
</head>
<body>
<div id="wrapper_content" style="width: 100%;height: 2300px">
    <!--[if lt IE 7]>
    <p class="chromeframe">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> or <a href="http://www.google.com/chromeframe/?redirect=true">activate Google Chrome Frame</a> to improve your experience.</p>
    <![endif]-->
    <div class="wrapper">
        <header style="color: #3f3f3f" id="login-header">
            <a id="logout" style="float: right" href="loginOut"><a/>
                <span id="line"style="float: right"></span>
                <a id="username" style="float: right" href="personal"><a/>
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
            <h1 id="stockname"></h1>
        </div>
    </div><!-- /featured -->
    <div class="clearfix"></div>
</div><!-- /wrapper -->
</div>

<div style="position: absolute;margin-left:10%;height: 800px;width: 50%;top: 280px;">
    <div style="position: relative;height: 500px;">
        </br>
        <div align="top" style="position: relative ;height: 15%">
            <span style="font-size: 12px; margin-right:50px;color: inherit"> 最高 </span>
            <span style="font-size: 12px; margin-right:50px;color: inherit"> 最低 </span>
            <span style="font-size: 12px; margin-right:50px;color: inherit"> 今开</span>
            <span style="font-size: 12px; margin-right:50px;color: inherit"> 昨收 </span>
            <span style="font-size: 12px; margin-right:50px;color: inherit"> 成交量</span>
            <span style="font-size: 12px; margin-right:50px;color: inherit"> 成交额 </span>
            
            <br/>
            <span style="font-size: 12px; margin-right:40px;color: inherit" id="max"></span>
            <span style="font-size: 12px; margin-right:40px;color: inherit" id="min"></span>
            <span style="font-size: 12px; margin-right:40px;color: inherit" id="open"></span>
            <span style="font-size: 12px; margin-right:40px;color: inherit" id="close"></span>
            <span style="font-size: 12px; margin-right:40px;color: inherit" id="volume"></span>
            <span style="font-size: 12px; margin-right:40px;color: inherit" id="deal"></span>
        </div>
        <script type="text/javascript">

            function addStock() {
                $.ajax({
                    type: 'POST',
                    url: 'buy',
                    contentType: "application/json",
                    cache: false,
                    dataType: 'json',
                    success: function (data) {
                        if(data.success){
                            swal("操作成功！", "成功添加股票!", "success")
                        }else{
                            swal({
                                title: "添加失败！",
                                text: data.result,
                                type: "warning",
                            })
                        }

                    }
                })

            }

            $(document).ready(function(){
                $.ajax({
                    type:'POST',
                    url:'refreshStock',
                    contentType:"application/json",
                    cache:false,
                    dataType:'json',
                    success:function(data){
                        if(data.success==false){

                            $("#login-header").append(
                                    "<a style=\"float: right ;color: #3f3f3f\"  href=\"login\">登录</a>"+
                                    "<span style=\"float: right\" >|</span>"+
                                    "<a style=\"float: right;color: #3f3f3f\"  href=\"register\">注册</a>"
                            )
                        }else{
                            $("#username").text(data.user.name);
                            $("#line").text("|");
                            $("#logout").text("登出");
                        }
                    }
                });
                jQuery.getJSON("stockInfo",function(data){
                    $("#max").text(data.high);
                    $("#min").text(data.low);
                    $("#open").text(data.todayStart);
                    $("#close").text(data.yesterdayEnd);
                    $("#volume").text(data.traNumber);
                    $("#deal").text(data.traAmount);
                    $("#stockname").text(data.codeName);

                });
            });

        </script>

        <div style="position: relative ;height: 75%;width: 100%" id="k_line">

        </div>

    </div>
    <div style="position: relative;height: 300px;top:20px;" id="volume_chart">
    </div>

</div>
<script type="text/javascript">
    $(document).ready(function(){
        jQuery.getJSON("volume",function(data){
            var my_date=[];
            var my_volume=[];
            var my_total=[];

            console.log(data);
            for(var i=0;i<data.length;i++){
                my_date.push(data[i].date);
                my_volume.push(data[i].volume);
                my_total.push(data[i].total);
            }
            console.log(my_date);
            console.log(my_volume);
            console.log(my_total);
            var myChart = echarts.init(document.getElementById("volume_chart"));
            option = {
                title : {
                    text: '最近成交量和成交额',

                },
                tooltip : {
                    trigger: 'axis'
                },
                legend: {
                    data:['成交量','成交额']
                },
                toolbox: {
                    show : true,
                    feature : {
                        dataView : {show: true, readOnly: false},
                        magicType : {show: true, type: ['line', 'bar']},

                    }
                },
                calculable : true,
                xAxis : [
                    {
                        type : 'category',
                        data : my_date,
                    }
                ],
                yAxis : [
                    {
                        type : 'value'
                    }
                ],
                series : [
                    {
                        name:'成交量',
                        type:'bar',
                        data:my_volume,
                        markPoint : {
                            data : [
                                {type : 'max', name: '最大值'},
                                {type : 'min', name: '最小值'}
                            ]
                        },
                        markLine : {
                            data : [
                                {type : 'average', name: '平均值'}
                            ]
                        }
                    },
                    {
                        name:'成交额',
                        type:'bar',
                        data:my_total,
                        markPoint : {
                            data : [
                                {type : 'max', name: '最大值'},
                                {type : 'min', name: '最小值'}
                            ]
                        },
                        markLine : {
                            data : [
                                {type : 'average', name : '平均值'}
                            ]
                        }
                    }
                ]
            };
            myChart.setOption(option);
        });
    });

</script>
<div style="position: absolute;right:10%;height: 800px;width: 30%;top:280px;float: right">
    <div style="position: relative;height: 80px">
        </br>
        <span style="font-size: 20px">牛叉诊股</span>
        <input type="image" src="images/add.png" onclick="addStock() " style="width: 150px;height: 45px;float: right;"/>
    </div>
    <div style="position: relative;height: 180px;top: 0">
        <span>技术面诊股：</span><span style="color: #9e010f;" id="star1_points">5.0</span><div  style="position: relative;height: 10%;width: 50%;float: right">
        <img src="stars/img/star-off.png" height="15px" width="15px"  id="div1-star1" >
        <img src="stars/img/star-off.png" height="15px" width="15px"  id="div1-star2" >
        <img src="stars/img/star-off.png" height="15px" width="15px"  id="div1-star3" >
        <img src="stars/img/star-off.png" height="15px" width="15px"  id="div1-star4" >
        <img src="stars/img/star-off.png" height="15px" width="15px"  id="div1-star5" >

    </div><br/>
        <span>资金面诊股：</span><span style="color: #9e010f;"  id="star2_points">5.0</span><div  style="position: relative;height: 10%;width: 50%;float: right">
        <img src="stars/img/star-off.png" height="15px" width="15px"  id="div2-star1" >
        <img src="stars/img/star-off.png" height="15px" width="15px"  id="div2-star2" >
        <img src="stars/img/star-off.png" height="15px" width="15px"  id="div2-star3" >
        <img src="stars/img/star-off.png" height="15px" width="15px"  id="div2-star4" >
        <img src="stars/img/star-off.png" height="15px" width="15px"  id="div2-star5" >

    </div><br/>
        <span>消息面诊股：</span><span style="color: #9e010f;"  id="star3_points">5.0</span><div  style="position: relative;height: 10%;width: 50%;float: right">
        <img src="stars/img/star-off.png" height="15px" width="15px"  id="div3-star1" >
        <img src="stars/img/star-off.png" height="15px" width="15px"  id="div3-star2" >
        <img src="stars/img/star-off.png" height="15px" width="15px"  id="div3-star3" >
        <img src="stars/img/star-off.png" height="15px" width="15px"  id="div3-star4" >
        <img src="stars/img/star-off.png" height="15px" width="15px"  id="div3-star5" >

    </div><br/>
        <span>行业面诊股：</span><span style="color: #9e010f;"  id="star4_points">5.0</span><div  style="position: relative;height: 10%;width: 50%;float: right">
        <img src="stars/img/star-off.png" height="15px" width="15px"  id="div4-star1" >
        <img src="stars/img/star-off.png" height="15px" width="15px"  id="div4-star2" >
        <img src="stars/img/star-off.png" height="15px" width="15px"  id="div4-star3" >
        <img src="stars/img/star-off.png" height="15px" width="15px"  id="div4-star4" >
        <img src="stars/img/star-off.png" height="15px" width="15px"  id="div4-star5" >

    </div><br/>
        <span>基本面诊股：</span><span style="color: #9e010f;"  id="star5_points">5.0</span><div  style="position: relative;height: 10%;width: 50%;float: right">
        <img src="stars/img/star-off.png" height="15px" width="15px"  id="div5-star1" >
        <img src="stars/img/star-off.png" height="15px" width="15px"  id="div5-star2" >
        <img src="stars/img/star-off.png" height="15px" width="15px"  id="div5-star3" >
        <img src="stars/img/star-off.png" height="15px" width="15px"  id="div5-star4" >
        <img src="stars/img/star-off.png" height="15px" width="15px"  id="div5-star5" >

    </div>
    </div>
    <div style="position: relative;height: 350px;top:0;">
        <div style="position: relative ;width: 45%;height: 60%;float: left;"  >
            <img src="images/Score.png" style="width: 100%;height: 100% ;margin-top: 10%">
            <h1 style="position: absolute;left: 35%;top:30%;" id="analysis_points">5.6</h1>
            <h5 style="position: absolute;left: 15%;top:70%;"id="analysis_words">击败了89%股票！</h5>
        </div>
        <div style="position: relative ;width: 55%;height: 60%;float: left" >
            <br/>
            <span style="margin-right:10%;color: #1f2838 ">短期趋势：</span><span id="short-term">强势上涨过程，可逢低买进，不考虑做空。</span><br/>
            <span style="margin-right:10% ;color: #1f2838">中期趋势：</span><span id="mid-term">强势上涨过程，可逢低买进，不考虑做空。</span><br/>
            <span style="margin-right:10% ;color: #1f2838">长期趋势：</span><span id="long-term">强势上涨过程，可逢低买进，不考虑做空。</span><br/><br/>
        </div>
        </br>
        <div style="position: relative ;width: 100%;height: 40%;top:10px">

                <span id="rec">近期的平均成本为17.10元，股价在成本下方运行、多头行情中，目前处于回落整理阶段且下跌有加速趋势。
                该股资金方面呈流出状态，投资者请谨慎投资。该公司运营状况尚可，多数机构认为该股投资价值较高，投资者可加强关注
                【诊断日期：2016年05月23日】</span>
        </div>


    </div>
    <div style="position: relative;height: 300px;top:0">

        <table class="zebra">
            <!--<caption>机构评级</caption>-->
            <thead>
            <tr>
                <th>研究机构</th>
                <th>评级日期</th>
                <th>最新评级</th>
            </tr>
            </thead>
            <tbody id="or-tb">

            </tbody>
        </table>

    </div>


</div>

<script type="text/javascript" charset="UTF-8">
    $(document).ready(function(){
        jQuery.getJSON("orangePre",function(data){
            console.log(data);
            var star_nums=data.start;
            for(var i=1;i<6;i++){
                var points=star_nums[i-1];
                if(points>=2){  $("#div"+i+"-star1").attr('src',"stars/img/star-on.png");}
                if(points>=4){  $("#div"+i+"-star2").attr('src',"stars/img/star-on.png");}
                if(points>=6){  $("#div"+i+"-star3").attr('src',"stars/img/star-on.png");}
                if(points>=8){  $("#div"+i+"-star4").attr('src',"stars/img/star-on.png");}
                if(points>=10){  $("#div"+i+"-star5").attr('src',"stars/img/star-on.png");}
                $("#star"+i+"_points").text(points);
            }

            $("#analysis_points").text(data.analyzeNum);
            $("#analysis_words").text(data.analyzeString);
            $("#short-term").text(data.mtrend);
            $("#mid-term").text(data.itrend);
            $("#long-term").text(data.ltrend);
            $("#rec").text(data.analString);

            var orangeData=data.orangeAna;
//
            for(var i=0;i<orangeData.length;i++){
                $("#or-tb").append(
                "<tr>"+
                "<td >"+orangeData[i].orangeName+"</td>"+
                       "<td >"+orangeData[i].date+"</td>"+
                        "<td >"+orangeData[i].recomendMessage+"</td>"+
                        "</tr>");

            }

        });
    })

</script>

<div style="position: absolute;right:10%;left: 10%;top:1100px;margin-top:50px;height: 500px;width: 80%">
    <div style="height: 40%;width: 100%;background-color: #efefef;">
            <div id="team"><!--team-->
                <div class="wrapper">
                    <section>
                        <h2>拟合数据</h2>
                        <p>我们从这只股票的历史表现中拟合了与最近一周最相像的走势来辅助你的判断。</p>
                    </section>
                </div>
            </div>
    </div>
    <div style="position: relative ;height: 70%;width: 50%;float: left" id="line1">
    </div>
    <script type="text/javascript">
        $(document).ready(function(){
            jQuery.getJSON("stockPre",{isRecommend:true},function(data){

                console.log(data);

                var myChart = echarts.init(document.getElementById('line1'));
                var my_date = [];
                var my_value = [];
                for (var i = 0; i < data.length; i++) {
                    my_date.push(data[i].date);
                    my_value.push(data[i].close);
                }
                option = {
                    title: {
                        text: '推荐历史参考趋势',

                    },
                    tooltip: {
                        trigger: 'axis'
                    },

                    toolbox: {
                        show: true,
                        feature: {
                            dataZoom: {},
                            dataView: {readOnly: false},
                            magicType: {type: ['line', 'bar']},
                            restore: {},

                        }
                    },
                    xAxis:  {
                        type: 'category',
                        boundaryGap: false,
                        data: my_date,
                    },
                    yAxis: {
                        min:'dataMin',
                        type: 'value',

                    },
                    series: [
                        {
                            type:'line',
                            data:my_value,
                            markLine: {
                                data: [
                                    {type: 'average', name: '平均值'}
                                ]
                            }
                        },

                    ]
                };
                myChart.setOption(option);
            });
        });

    </script>
    <div style="position: relative ;height: 70%;width: 50%;float: left" id="line2">
    </div>
    <script type="text/javascript">
        $(document).ready(function(){
            jQuery.getJSON("stockPre",{isRecommend:false},function(data){

                console.log(data);

                var myChart = echarts.init(document.getElementById('line2'));
                var my_date = [];
                var my_value = [];
                for (var i = 0; i < data.length; i++) {
                    my_date.push(data[i].date);
                    my_value.push(data[i].close);
                }
                option = {
                    title: {
                        text: '最近趋势',

                    },
                    tooltip: {
                        trigger: 'axis'
                    },

                    toolbox: {
                        show: true,
                        feature: {
                            dataZoom: {},
                            dataView: {readOnly: false},
                            magicType: {type: ['line', 'bar']},
                            restore: {},
                        }
                    },
                    xAxis:  {
                        type: 'category',
                        boundaryGap: false,
                        data: my_date,
                    },
                    yAxis: {
                        min:'dataMin',
                        type: 'value',

                    },
                    series: [
                        {
                            type:'line',
                            data:my_value,
                            markLine: {
                                data: [
                                    {type: 'average', name: '平均值'}
                                ]
                            }
                        },

                    ]
                };
                myChart.setOption(option);
            });
        });
    </script>

</div>
<div style="position: absolute;right:10%;left: 10%;top:1600px;margin-top:100px;height: 600px;width: 80%;">
        <div style="position: relative;height: 220px;background-color: #efefef">
            <div id="team"><!--team-->
                <div class="wrapper">
                    <section>
                        <h2>明日趋势预测</h2>
                        <span>ARIMA模型历史准确率：</span><span id="history-percent"></span><br/>
                        <span>昨日预测：</span><span id="yesterday-pre"></span>
                    </section>
                </div>
            </div>

        </div>
    <div style="position: relative;height: 350px;top:0" id="line3">
    </div>
    <%--<div style="height: 100px;width: 100%;left: 10%">--%>
        <%--<br/>--%>
        <%----%>
    <%--</div>--%>
    <script type="text/javascript">
        $(document).ready(function(){
            jQuery.getJSON("yesterdayarima",function(data){
                $("#yesterday-pre").text(data.yesterdayP);
                $("#history-percent").text(data.percent);
            });

            jQuery.getJSON("arima",function(data){
                var myChart = echarts.init(document.getElementById('line3'));
                var my_date=[];
                var my_value=[];
               for(var i=0;i<data.length;i++){
                   my_date.push(data[i].date);
                   my_value.push(data[i].close);
               }
                option = {
                    title: {
                        text: 'ARIMI预测模型',

                    },
                    tooltip: {
                        trigger: 'axis'
                    },

                    toolbox: {
                        show: true,
                        feature: {
                            dataZoom: {},
                            dataView: {readOnly: false},
                            magicType: {type: ['line', 'bar']},
                            restore: {},

                        }
                    },
                    xAxis:  {
                        type: 'category',
                        boundaryGap: false,
                        data: my_date,
                    },
                    yAxis: {
                        min:'dataMin',
                        type: 'value',

                    },
                    series: [
                        {
                            type:'line',
                            data:my_value,
                            markPoint: {
                                data: [
                                    {type: 'max',valueIndex:'0'},

                                ]
                            },
                            markLine: {
                                data: [
                                    {type: 'average', name: '平均值'}
                                ]
                            }
                        },

                    ]
                };
                myChart.setOption(option);
                myChart.hideLoading();

            });

        });

    </script>


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
                        <li><a href="download">下载</a></li>
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


</body>
</html>
