<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 2016/6/2
  Time: 15:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

    <link rel="stylesheet" href="styles-sxc.css" />
    <title>AnyQuant | Plate</title>
    <script src="js/echarts.min.js"></script>
    <script src="js/jquery.js"></script>
    <link rel="stylesheet" href="table/css/style.css" type="text/css">
    <link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon" />

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
        })
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
            <h1>大盘信息</h1>
        </div>
    </div><!-- /featured -->
    <div class="clearfix"></div>

    <div style="float: left;height:900px;width: 100%;background-color: snow ;">
        <div style="margin-left:10%;float: left;height:850px;width: 50%;">
            <div style="position:relative;height:50%;width: 100%;">

                <h2>大盘数据</h2>
                <div style="height: 20%;width: 100%;margin-top: 30px" >
                    <span style="font-size: 20px; margin-right:50px;color: inherit" id="time"> 2016-01-01 18:00:00 </span><br/>
                    <span style="font-size: 20px; margin-right:50px;color: inherit"> 上证指数[000001] </span>
                    <span style="font-size: 12px; margin-right:50px;color: inherit"> 最高 </span>
                    <span style="font-size: 12px; margin-right:50px;color: inherit"> 最低 </span>
                    <span style="font-size: 12px; margin-right:50px;color: inherit"> 今开</span>
                    <span style="font-size: 12px; margin-right:50px;color: inherit"> 昨收 </span>
                    <span style="font-size: 12px; margin-right:50px;color: inherit"> 成交量</span>
                    <span style="font-size: 12px; margin-right:50px;color: inherit"> 成交额 </span>
                    <br/>
                    <span style="font-size: 20px; margin-right:100px;color: inherit" id="t-close"> 2888 </span>
                    <span style="font-size: 12px; margin-right:10px;color: inherit" id="max"> 2888 </span>
                    <span style="font-size: 12px; margin-right:10px;color: inherit" id="min">2888 </span>
                    <span style="font-size: 12px; margin-right:10px;color: inherit" id="open"> 2888</span>
                    <span style="font-size: 12px; margin-right:10px;color: inherit" id="close"> 2888 </span>
                    <span style="font-size: 12px; margin-right:10px;color: inherit" id="volume"> 2888</span>
                    <span style="font-size: 12px; margin-right:10px;color: inherit" id="deal"> 2888 </span>
                </div>
                <br/>
                <div style="height: 35%;width: 100%; ">
                    <span style="position:absolute;font-size: 20px; margin-right:30px;color: inherit;">今日预测 </span>
                    <span style="position:absolute;font-size: 12px; left: 420px;top: 160px;color: inherit"id="span1">5555 </span>
                    <span style="position:absolute;font-size: 12px; left: 210px;top: 220px;color: inherit"id="span2">5555 </span>
                    <span style="position:absolute;font-size: 12px; left: 520px;top: 220px;color: inherit"id="span3">5555 </span>
                    <img src="images/predict-result-bg.png" style="left: 16%;position: absolute" >


                </div>

                <div style="height: 20%;width: 100%;">
                    <div style="float: left;width: 15%;height: 90%;">
                        <span style="position:absolute;font-size: 20px; margin-right:30px;color: inherit;">昨日预测 </span>
                    </div>
                    <div style=";float: left;width: 25%;height: 90%;color: #1ed4b0;">
                        <span style="margin-right: 30px">预测</span>
                        <span style="margin-right: 30px">实际</span>
                        <%--<span style="margin-right: 15px">偏离度</span>--%>
                        <br/>
                        <span style="margin-right: 15px" id="span4">2888</span>
                        <span style="margin-right: 15px" id="span5">2888</span>
                        <%--<span style="margin-right: 15px"id="span6">2888</span>--%>
                    </div>
                    <div style="float: left;width: 25%;height: 90%;color: #ac2925;">
                        <span style="margin-right: 30px">预测</span>
                        <span style="margin-right: 30px">实际</span>
                        <%--<span style="margin-right: 15px">偏离度</span>--%>
                        <br/>
                        <span style="margin-right: 15px"id="span7">2888</span>
                        <span style="margin-right: 15px"id="span8">2888</span>
                        <%--<span style="margin-right: 15px"id="span9">2888</span>--%>
                    </div>
                    <div style="float: left;width: 25%;height: 90%;color: #d58512">
                        <span style="margin-right: 30px">预测</span>
                        <span style="margin-right: 30px">实际</span>
                        <%--<span style="margin-right: 15px">偏离度</span>--%>
                        <br/>
                        <span style="margin-right: 15px"id="span10">2888</span>
                        <span style="margin-right: 15px"id="span11">2888</span>
                        <%--<span style="margin-right: 15px"id="span12">2888</span>--%>
                    </div>
                </div>
            </div>

            <script type="text/javascript">

                $(document).ready(function(){

                    jQuery.getJSON("markHome",function(data){
                        $("#span1").text(data.highPre);
                        $("#span2").text(data.lowPre);
                        $("#span3").text(data.closePre);
                        $("#span4").text(data.yestDayLowPre);
                        $("#span5").text(data.todayMin);
                        $("#span6").text(data.distanceLow+"%");
                        $("#span7").text(data.yestDayHigPre);
                        $("#span8").text(data.todayMax);
                        $("#span9").text(data.distanceHigh+"%");
                        $("#span10").text(data.yestDayClosePre);
                        $("#span11").text(data.todayClose);
                        $("#span12").text(data.distanceClose+"%");
                    })

                    jQuery.getJSON("MarketNow",function(data){
                        $("#t-close").text(data.nowpri);
                        $("#max").text(data.highPri);
                        $("#min").text(data.lowpri);
                        $("#open").text(data.openPri);
                        $("#close").text(data.yesPri);
                        $("#volume").text(data.dealNum);
                        $("#deal").text(data.dealPri);
                        $("#time").text(data.time);

                    })

                })
            </script>

            <h2>K线图</h2>
            <div style="position:relative;height:50%;width: 100%;margin-top: 0" id="market-kline">

            </div>
            <script type="text/javascript">

                $(document).ready(function(){

                    // 基于准备好的dom，初始化echarts实例

                    jQuery.getJSON("marketKLine",function(data){
                        console.log(data);
                        var myChart = echarts.init(document.getElementById('market-kline'));
                        var data0;
                        data0 = splitData(data);
                        option = {
                            title: {
                                text: '涨跌情况',
                                left: 0
                            },
                            tooltip: {
                                trigger: 'axis',
                                axisPointer: {
                                    type: 'line'
                                }
                            },
                            legend: {
                                data: ['日K', 'MA5', 'MA10', 'MA20', 'MA30']
                            },
                            grid: {
                                left: '0',
                                right: '10%',
                                bottom: '15%'
                            },
                            xAxis: {
                                type: 'category',
                                data: data0.categoryData,
                                scale: true,
                                boundaryGap : false,
                                axisLine: {onZero: false},
                                splitLine: {show: false},
                                splitNumber: 20,
                                min: 'dataMin',
                                max: 'dataMax'
                            },
                            yAxis: {
                                scale: true,
                                splitArea: {
                                    show: true
                                }
                            },
                            dataZoom: [
                                {
                                    type: 'inside',
                                    start: 50,
                                    end: 100
                                },
                                {
                                    show: true,
                                    type: 'slider',
                                    y: '90%',
                                    start: 50,
                                    end: 100
                                }
                            ],
                            series: [
                                {
                                    name: '日K',
                                    type: 'candlestick',
                                    data: data0.values,
                                    markPoint: {
                                        label: {
                                            normal: {
                                                formatter: function (param) {
                                                    return param != null ? Math.round(param.value) : '';
                                                }
                                            }
                                        },
                                        data: [
                                            {
                                                name: 'XX标点',
                                                coord: ['2013/5/31', 2300],
                                                value: 2300,
                                                itemStyle: {
                                                    normal: {color: 'rgb(41,60,85)'}
                                                }
                                            },
                                            {
                                                name: 'highest value',
                                                type: 'max',
                                                valueDim: 'highest'
                                            },
                                            {
                                                name: 'lowest value',
                                                type: 'min',
                                                valueDim: 'lowest'
                                            },
                                            {
                                                name: 'average value on close',
                                                type: 'average',
                                                valueDim: 'close'
                                            }
                                        ],
                                        tooltip: {
                                            formatter: function (param) {
                                                return param.name + '<br>' + (param.data.coord || '');
                                            }
                                        }
                                    },
                                    markLine: {
                                        symbol: ['none', 'none'],
                                        data: [
                                            [
                                                {
                                                    name: 'from lowest to highest',
                                                    type: 'min',
                                                    valueDim: 'lowest',
                                                    symbol: 'circle',
                                                    symbolSize: 10,
                                                    label: {
                                                        normal: {show: false},
                                                        emphasis: {show: false}
                                                    }
                                                },
                                                {
                                                    type: 'max',
                                                    valueDim: 'highest',
                                                    symbol: 'circle',
                                                    symbolSize: 10,
                                                    label: {
                                                        normal: {show: false},
                                                        emphasis: {show: false}
                                                    }
                                                }
                                            ],
                                            {
                                                name: 'min line on close',
                                                type: 'min',
                                                valueDim: 'close'
                                            },
                                            {
                                                name: 'max line on close',
                                                type: 'max',
                                                valueDim: 'close'
                                            }
                                        ]
                                    }
                                },
                                {
                                    name: 'MA5',
                                    type: 'line',
                                    data: calculateMA(5),
                                    smooth: true,
                                    lineStyle: {
                                        normal: {opacity: 0.5}
                                    }
                                },
                                {
                                    name: 'MA10',
                                    type: 'line',
                                    data: calculateMA(10),
                                    smooth: true,
                                    lineStyle: {
                                        normal: {opacity: 0.5}
                                    }
                                },
                                {
                                    name: 'MA20',
                                    type: 'line',
                                    data: calculateMA(20),
                                    smooth: true,
                                    lineStyle: {
                                        normal: {opacity: 0.5}
                                    }
                                },
                                {
                                    name: 'MA30',
                                    type: 'line',
                                    data: calculateMA(30),
                                    smooth: true,
                                    lineStyle: {
                                        normal: {opacity: 0.5}
                                    }
                                },

                            ]
                        };

                        // 使用刚指定的配置项和数据显示图表。
                        myChart.setOption(option);


                        function splitData(rawData) {
                            var categoryData = [];
                            var values = []
                            for (var i = 0; i < rawData.length; i++) {

                                categoryData.push(rawData[i].date);
                                values.push(rawData[i].data)
                            }
                            return {
                                categoryData: categoryData,
                                values: values
                            };
                        }

                        function calculateMA(dayCount) {
                            var result = [];
                            for (var i = 0, len = data0.values.length; i < len; i++) {
                                if (i < dayCount) {
                                    result.push('-');
                                    continue;
                                }
                                var sum = 0;
                                for (var j = 0; j < dayCount; j++) {
                                    sum += data0.values[i - j][1];
                                }
                                result.push(sum / dayCount);
                            }
                            return result;
                        }


                    });
                })
            </script>

        </div>


        <div style="float: left;height:850px;width: 30%;;">
            <h2>历史数据</h2>
            <br/>
            <table class="zebra">
                <!--<caption>机构评级</caption>-->
                <thead>
                <tr>
                    <th>日期</th>
                    <th>成交量</th>
                    <th>成交价</th>
                    <th>金额</th>
                </tr>
                </thead>
                <tbody id="tb-history-data">
                <tr>
                    <td></td>
                    <td ></td>
                    <td ></td>
                    <td ></td>
                </tr>
                <tr>
                    <td></td>
                    <td ></td>
                    <td ></td>
                    <td ></td>
                </tr>
                <tr>
                    <td></td>
                    <td ></td>
                    <td ></td>
                    <td ></td>
                </tr>
                <tr>
                    <td></td>
                    <td ></td>
                    <td ></td>
                    <td ></td>
                </tr>
                <tr>
                    <td></td>
                    <td ></td>
                    <td ></td>
                    <td ></td>
                </tr>
                <tr>
                    <td></td>
                    <td ></td>
                    <td ></td>
                    <td ></td>
                </tr>
                <tr>
                    <td></td>
                    <td ></td>
                    <td ></td>
                    <td ></td>
                </tr>
                <tr>
                    <td></td>
                    <td ></td>
                    <td ></td>
                    <td ></td>
                </tr>
                <tr>
                    <td></td>
                    <td ></td>
                    <td ></td>
                    <td ></td>
                </tr>
                <tr>
                    <td></td>
                    <td ></td>
                    <td ></td>
                    <td ></td>
                </tr>
                <tr>
                    <td></td>
                    <td ></td>
                    <td ></td>
                    <td ></td>
                </tr>


                </tbody>
            </table>

        </div>
    </div>
    <script type="text/javascript">

        $(document).ready(
                function(){
                    jQuery.getJSON("historyMarketDate",function(data){
                        var l=data.length-1;
                        $("#tb-history-data").children().each(function(i){
                                $(this).children().each(function(j){
//
                                    if(j==0){$(this).text(data[l-i].date);}
                                    if(j==1){$(this).text(data[l-i].volume);}
                                    if(j==2){$(this).text(data[l-i].adj_price);}
                                    if(j==3){$(this).text(data[l-i].volume*data[l-i].adj_price);}

                                });

                            });
//                            $("#tb-history-data").children().eq(i).eq(3).text(data[i].close);

                    })
                }
        )
    </script>


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

</body>
</html>


