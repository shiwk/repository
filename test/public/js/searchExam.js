var initSet = {}
document.InitCallback = function(id){
    if(id in initSet){
    } else {
        initSet[id] = true
        setTimeout(function(){  //! TODO Fix Use Timeout when data complete.
            $("#area-"+id).click(function(){
                var url = BASE_URL+"?examId="+id;
                url += "#total-menu"
                window.parent.window.location = url
            })
            $("#school-"+id).click(function(){
                var url = BASE_URL+"?examId="+id;
                url += "#school-menu"
                window.parent.window.location = url
            })
        },800)
    }
}

document.InitProgressbar = function(id, url){
    setTimeout(function(){
        var ProgressBar = BUI.ProgressBar
        var Progressbar = ProgressBar.Base;
        var progressbar = new Progressbar({
                  elCls : 'progress progress-striped active',
                  render : '#progressbar_'+id,
                  tpl : '<div class="bar"></div>',
                  percent:0
        });
        progressbar.render();
        setInterval(function(){
            $.getJSON(url+"?examId="+id, function(result){
                var total = result[0]
                var left = result[1];
                percent = (total - left) / total;
                percent *= 100
                $("#progresslabel_"+id).text("缓存已完成:"+percent.toFixed(2)+"%")
                progressbar.set('percent',percent)
            })
        }, 800)
    }, 800);
}