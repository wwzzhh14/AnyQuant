<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 2016/6/2
  Time: 20:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

    <link rel="stylesheet" href="styles-sxc.css" />
    <title>AnyQuant | Recommend</title>
    <script src="js/jquery.js"></script>
    <script src="js/echarts.min.js"></script>
    <link rel="stylesheet" href="table/css/style.css" type="text/css">
    <link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon" />
    <style>

        #gragha {
            width: 700px;
            height: 500px;
            float: left;
            margin-left: 400px;
            margin-right: 50px;
            margin-bottom: 100px;;
        }
        #gragha li {
            float: left;
            width: 100px;
            margin-top: 15px;
            margin-left: 55px;
            margin-bottom: 50px;
            background-color: #CCC;
            text-align: center;
        }
        #gragha a {
            font-size: 18px;
            font-family: Verdana, Geneva, sans-serif;
            color: #363636;
        }
        #gragha a:hover {
            color: green;
        }
        #recom {
            width: 300px;
            height: 400px;
            float: left;
        }
        .title {
            margin-top: 20px;
            height: 50px;
            font-size: 24px;
        }
        #graghb {
            width: 700px;
            height: 500px;
            clear: left;
            margin-left: 400px;
        }
        #graghb li {
            float: left;
            width: 100px;
            margin-top: 15px;
            margin-left: 60px;
            margin-bottom:100px ;
            background-color: #CCC;
            text-align: center;
        }
        #graghb a {
            font-size: 18px;
            font-family: Verdana, Geneva, sans-serif;
            color: #363636;
        }
        #graghb a:hover {
            color: green;
        }

    </style>
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
            <h1>推荐</h1>
        </div>
    </div><!-- /featured -->
    <div class="clearfix"></div>
    <h2 style="margin-left: 10%">行业推荐</h2>
    <div style="width: 80%;height: 700px;margin-left: 10%;" >
        <div style="width: 100%;height: 75%;" id="point-chart"></div>
        <div style="width: 100%;height: 25%;">
            <div style="width: 100%;height: 15%;background-color: #5f5b5b ;text-align: center">
                <ul>
                    <li style="float: left ;color: #ce2700 ;margin-left:60px">保险业</li>
                    <li style="float: left ;color: #fbcbc1 ;margin-left:60px">有色金属业</li>
                    <li style="float: left ;color: #6fffbc ;margin-left:60px">多元金融业</li>
                    <li style="float: left ;color: #9d9d9d ;margin-left:60px">银行业</li>
                    <li style="float: left ;color: darkkhaki ;margin-left:60px">贸易业</li>
                    <li style="float: left ;color: #3ec9c3 ;margin-left:60px">电信业</li>
                </ul>
            </div>
            <div style="width: 100%;height: 80%;background-color: #1f2838">
                <br/>
                <span style="color: #EEEEFF;margin-left: 5%">处于“市盈率提高、成交量增大”象限的包括：
持股成本提高，投资回收期变长，行业整体盈利能力改善，交投趋于活跃，建议持续投资。
</span><br/>
                <span style="color: #EEEEFF;margin-left: 5%">处于“市盈率提高、成交量减小”象限的包括：
持股成本提高，投资回收期变长，行业整体盈利能力改善，交投趋于低迷，建议观望。
</span><br/>
                <span style="color: #EEEEFF;margin-left: 5%">处于“市盈率降低、成交量增大”象限的包括：
持股成本降低，投资回收期变短，行业整体盈利能力恶化，交投趋于活跃，注意做空。
</span><br/>
                <span style="color: #EEEEFF;margin-left: 5%">处于“市盈率降低、成交量减小”象限的包括：
持股成本降低，投资回收期变短，行业整体盈利能力恶化，交投趋于低迷，不建议投资。
</span><br/>
            </div>
        </div>

    </div>
    <script type="text/javascript">

        $(document).ready(
                function() {
                    var myChart = echarts.init(document.getElementById('point-chart'));
                    myChart.showLoading();
                    jQuery.getJSON("recommendIndustry", function (data) {

                        console.log(data);
                        var my_plate=["Multiple Finance","Insurance","Nobel Metal","Telicommunications","Commerce","Banking"];
                        var my_timeline=[];
                        for(var i=0;i<(data.length/6);i++){
                            my_timeline.push(data[i*6].date);
                        }
                        var my_series=[];
                        for(var i=0;i<(data.length/6);i++){
                            var arr1=[];
                            for(var j=0;j<6;j++){
                                var arr2=[];
                                arr2.push(data[i*6+j].x*100);
                                arr2.push(data[i*6+j].y*100);
                                arr2.push(500000000);
                                arr2.push(data[i*6+j].name);
                                arr2.push(data[i*6+j].date);
                                arr1.push(arr2);
                            }
                            my_series.push(arr1);
                        }
                        var my_data={"plate":my_plate,"timeline":my_timeline,"series":my_series};
                        console.log(my_data);
                        var itemStyle = {
                            normal: {
                                opacity: 0.8,
                                shadowBlur: 10,
                                shadowOffsetX: 0,
                                shadowOffsetY: 0,
                                shadowColor: 'rgba(0, 0, 0, 0.5)'
                            }
                        };


                        var sizeFunction = function (x) {
                            var y = Math.sqrt(x / 5e8) + 0.1;
                            return y * 40;
                        };
                        Schema:
                                var schema = [
                                    {name: 'Income', index: 0, text: '市盈率增长率', unit: '%'},
                                    {name: 'LifeExpectancy', index: 1, text: '成交量增长率', unit: '%'},
                                    {name: 'Population', index: 2, text: '总人口', unit: ''},
                                    {name: 'plate', index: 3, text: '板块', unit: ''}
                                ];

                        option = {
                            baseOption: {
                                timeline: {
                                    axisType: 'category',
                                    orient: 'vertical',
                                    autoPlay: true,
                                    inverse: true,
                                    playInterval: 3000,
                                    left: null,
                                    right: 0,
                                    top: 20,
                                    bottom: 20,
                                    width: 80,
                                    height: null,
                                    label: {
                                        normal: {
                                            textStyle: {
                                                color: '#999'
                                            }
                                        },
                                        emphasis: {
                                            textStyle: {
                                                color: '#fff'
                                            }
                                        }
                                    },
                                    symbol: 'none',
                                    lineStyle: {
                                        color: '#555'
                                    },
                                    checkpointStyle: {
                                        color: '#bbb',
                                        borderColor: '#777',
                                        borderWidth: 2
                                    },
                                    controlStyle: {
                                        showNextBtn: false,
                                        showPrevBtn: false,
                                        normal: {
                                            color: '#666',
                                            borderColor: '#666'
                                        },
                                        emphasis: {
                                            color: '#aaa',
                                            borderColor: '#aaa'
                                        }
                                    },
                                    data: []
                                },
                                backgroundColor: '#333',
                                title: {
                                    'text': my_data.timeline[0],
                                    textAlign: 'center',
                                    left: '80%',
                                    top: '75%',
                                    textStyle: {
                                        fontSize: 50,
                                        color: 'rgba(255, 255, 255, 0.7)'
                                    }
                                },
                                tooltip: {
                                    padding: 5,
                                    backgroundColor: '#222',
                                    borderColor: '#777',
                                    borderWidth: 1,
                                    formatter: function (obj) {
                                        var value = obj.value;
                                        return schema[3].text + '：' + value[3] + '<br>'
                                                + schema[1].text + '：' + value[1] + schema[1].unit + '<br>'
                                                + schema[0].text + '：' + value[0] + schema[0].unit + '<br>';
//                                + schema[2].text + '：' + value[2] + '<br>';
                                    }
                                },
                                grid: {
                                    left: '8%',
                                    right: '110'
                                },
                                xAxis: {
                                    type: 'value',
                                    name: 'volume',
                                    min: -100,
                                    max: 100,
                                    nameTextStyle: {
                                        color: '#ccc',
                                        fontSize: 18
                                    },
                                    axisLine: {
                                        lineStyle: {
                                            color: '#ccc'
                                        }
                                    },
                                    axisTick: {
                                        lineStyle: {
                                            color: '#ccc'
                                        }
                                    },
                                    splitLine: {
                                        show: false
                                    },
                                    axisLabel: {
                                        formatter: '{value} %',
                                        textStyle: {
                                            color: '#ccc'
                                        }
                                    }
                                },
                                yAxis: {
                                    type: 'value',
                                    name: 'pe',
                                    min: -20,
                                    max: 20,
                                    nameTextStyle: {
                                        color: '#ccc',
                                        fontSize: 18
                                    },
                                    axisLine: {
                                        lineStyle: {
                                            color: '#ccc'
                                        }
                                    },
                                    axisTick: {
                                        lineStyle: {
                                            color: '#ccc'
                                        }
                                    },
                                    splitLine: {
                                        show: false
                                    },
                                    axisLabel: {
                                        formatter: '{value} %',
                                        textStyle: {
                                            color: '#ccc'
                                        }
                                    }
                                },
                                visualMap: [
                                    {
                                        show: false,
                                        dimension: 3,
                                        categories: my_data.plate,
                                        calculable: true,
                                        precision: 0.1,
                                        textGap: 30,
                                        textStyle: {
                                            color: '#ccc'
                                        },
                                        inRange: {
                                            color: ['#bcd3bb', '#e88f70', '#edc1a5', '#9dc5c8', '#e1e8c8', '#7b7c68', '#e5b5b5', '#f0b489', '#928ea8', '#bda29a']
                                        }
                                    }
                                ],
                                series: [
                                    {
                                        type: 'scatter',
                                        itemStyle: itemStyle,
                                        data: my_data.series[0],
                                        symbolSize: function(val) {
                                            return sizeFunction(val[2]);
                                        }
                                    }
                                ],
                                animationDurationUpdate: 3000,
                                animationEasingUpdate: 'quinticInOut'
                            },
                            options: []
                        };

                        for (var n = 0; n <my_data.timeline.length; n++) {
                            option.baseOption.timeline.data.push(my_data.timeline[n]);
                            option.options.push({
                                title: {
                                    show: true,
                                    'text': my_data.timeline[n] + ''
                                },
                                series: {
                                    name: my_data.timeline[n],
                                    type: 'scatter',
                                    itemStyle: itemStyle,
                                    data: my_data.series[n],
                                    symbolSize: function(val) {
                                        return sizeFunction(val[2]);
                                    }
                                }
                            });
                        }

                        myChart.hideLoading();
                        myChart.setOption(option);
                    })
                })


    </script>
    <br/>
    <div style="width: 80%;height: 500px;margin-left: 10%;">
        <h2>热门股票</h2>
        <br/>
        <div style="height: 80%;width: 30%;float: left;margin-top: 8%">
            <table class="zebra">
                <!--<caption>机构评级</caption>-->
                <thead>
                <tr>
                    <th>股票名称</th>
                    <th>涨幅</th>
                </tr>
                </thead>
                <tbody id="tb-hotstock">


                </tbody>
            </table>

        </div>
        <div style="height: 80%;width: 60%;background-color: #5f5b5b;float: right;margin-left: 10%" id="market-kline"></div>

    </div>
    <script type="text/javascript"  charset="UTF-8">

        var my_data;
        function  getKline(index) {
            jQuery.getJSON("hotSearch",{"stockName":my_data[index].code},function(data){

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
        }

        $(document).ready(
                function(){
                    jQuery.getJSON("hotstock",function(data){
                        var stock_name=["中国银行","农业银行","中信证券","上汽集团","苏宁云商","工商银行"];
                        my_data=data;
                        var l=data.length-1;
                        for(var i=0;i<data.length;i++){
                            $("#tb-hotstock").append(
                                    "<tr onclick=\'getKline("+i+")\'>" +
                                    " <td>"+stock_name[i]+"</td>" +
                                    " <td>"+data[l-i].increase+"</td>" +
                                    "</tr>"
                            )
                        }

                        jQuery.getJSON("hotSearch",{"stockName":data[0].code},function(data){

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

                }
        )




    </script>

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

</body>
</html>
