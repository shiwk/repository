$(function(){
Highcharts.theme = {
   colors: ['#058DC7', '#50B432', '#ED561B', '#DDDF00', '#24CBE5', '#64E572', '#FF9655', '#FFF263', '#6AF9C4'],
   chart: {
      backgroundColor: {
         linearGradient: { x1: 0, y1: 0, x2: 1, y2: 1 },
         stops: [
            [0, 'rgb(255, 255, 255)'],
            [1, 'rgb(240, 240, 255)']
         ]
      },
      borderWidth: 2,
      plotBackgroundColor: 'rgba(255, 255, 255, .9)',
      plotShadow: true,
      plotBorderWidth: 1
   },
   title: {
      style: {
         color: '#000',
         font: 'bold 16px "Trebuchet MS", Verdana, sans-serif'
      }
   },
   subtitle: {
      style: {
         color: '#666666',
         font: 'bold 12px "Trebuchet MS", Verdana, sans-serif'
      }
   },
   xAxis: {
      gridLineWidth: 1,
      lineColor: '#000',
      tickColor: '#000',
      labels: {
         style: {
            color: '#000',
            font: '11px Trebuchet MS, Verdana, sans-serif'
         }
      },
      title: {
         style: {
            color: '#333',
            fontWeight: 'bold',
            fontSize: '12px',
            fontFamily: 'Trebuchet MS, Verdana, sans-serif'

         }
      }
   },
   yAxis: {
      minorTickInterval: 'auto',
      lineColor: '#000',
      lineWidth: 1,
      tickWidth: 1,
      tickColor: '#000',
      labels: {
         style: {
            color: '#000',
            font: '11px Trebuchet MS, Verdana, sans-serif'
         }
      },
      title: {
         style: {
            color: '#333',
            fontWeight: 'bold',
            fontSize: '12px',
            fontFamily: 'Trebuchet MS, Verdana, sans-serif'
         }
      }
   },
   legend: {
      itemStyle: {
         font: '9pt Trebuchet MS, Verdana, sans-serif',
         color: 'black'

      },
      itemHoverStyle: {
         color: '#039'
      },
      itemHiddenStyle: {
         color: 'gray'
      }
   },
   labels: {
      style: {
         color: '#99b'
      }
   },

   navigation: {
      buttonOptions: {
         theme: {
            stroke: '#CCCCCC'
         }
      }
   },

    lang:{
        printChart:'打印图表',
        contextButtonTitle:'菜单',
        downloadJPEG:'下载JPEG 格式图片',
        downloadPDF:'下载PDF  格式图片',
        downloadPNG:'下载PNG  格式图片',
        downloadSVG:'下载SVG  格式图片'
    },

    exporting:{
        buttons:{
            contextButton:{
                menuItems:[
                    {
                        text:'下载JPEG 格式图片',
                        onclick:function(){
                            this.exportChart({type:'application/jpeg'});
                        }
                    },
                    {
                        text:'下载PDF 格式图片',
                        onclick:function(){
                            this.exportChart({type:'application/pdf'});
                        }
                    }
                ]
            },
            customButton: {
                x: -32,
                onclick: function () {
                    var isShown = true
                    for(var i in document.chart.series){
                        if(!document.chart.series[i].visible){
                            isShown = false
                            break
                        }
                    }
                    var func = function(s){
                        s.show()
                    }
                    if(isShown) {
                        func = function(s){
                            s.hide()
                        }
                    }
                    for(var i in document.chart.series){
                        func(document.chart.series[i])
                    }
                },
                text: '全选/全不选'
            },
            customButton2 : {
                x: -118,
                text: '紧凑/舒展',
                onclick: function() {
                    var m = document.chart.xAxis[0].max - document.chart.xAxis[0].min
                    if(m < 5){
                        //紧凑
                        document.chart.xAxis[0].update({max:null, min:null})
                    } else {
                        document.chart.xAxis[0].update({max:4,min:0})
                    }
                }
            }
        }
    }


};


// Apply the theme
var highchartsOptions = Highcharts.setOptions(Highcharts.theme);

    //
    //$.ajax({
    //    url: FETCH_URL,
    //    success: function(result){
    //        $('#container').highcharts(result)
    //        console.log(result)
    //        document.chart = $('#container').highcharts();
    //    },
    //    scriptCharset: "utf-8",
    //    complete: function(jqXHR,textStatus ){
    //        console.log(textStatus)
    //    },
    //    error: function(jqXHR, textStatus, err){
    //        console.log(err)
    //    }
    //    })
    //
    //

    $.get(FETCH_URL,function(result){
        $('#container').highcharts(result)
        document.chart = $('#container').highcharts();
    })
})