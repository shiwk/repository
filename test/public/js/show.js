BUI.use(['bui/tab','bui/mask'],function(Tab){
        function InitTab(tab){
            tab.clicked = false
            tab.on("click", function(ev){
                if(tab.clicked){
                    tab.setSelected(0)
                }
                tab.clicked = !tab.clicked
            })
        }
        function InitListTab(list){
            for(var i=0;i<list.length;++i){
             InitTab(
                    new Tab.TabPanel({
                        render: '#'+list[i].tabId,
                        elCls: 'nav-tabs',
                        panelContainer: '#'+list[i].panelId,
                        autoRender: true,
                        children: [{
                            text: list[i].data[0].text,
                            value: "1",
                            loader: {
                                url: BASE_URL,
                                dataType: "html",
                                params: {
                                    json: JSON.stringify(list[i].data)
                                },
                                ajaxOptions: {
                                    type: "post"
                                }
                            }
                        }]
                    })
                )
            }
        }
        data = [
            {text:'全区考核范畴分析表',value:'1', loader:{url:list[0],dataType:'html'}},
            {text:'考核范畴平均分图',value:'2', loader:{url:list[1],dataType:'html'}},
            {text:'考核范畴得分率图',value:'3', loader:{url:list[2],dataType:'html'}},
            {text:'考核范畴标准差图', value:'4', loader:{url:list[3],dataType:'html'}},
            {text:'考点平均分图', value:'4',loader:{url:list[4],dataType:'html'}},
            {text:'考点得分率',value:'5',loader:{url:list[5],dataType:'html'}},
            {text:'考点标准差图', value:'4',loader:{url:list[6],dataType:'html'}}
        ];
        tab = new Tab.TabPanel({
          render : '#all-category-tab',
          elCls : 'nav-tabs',
          panelContainer : '#all-category-panel',
          autoRender: true,
          children: [
            { text: data[0].text,
              value: '1',
              loader:{ url:BASE_URL,
                dataType:'html',
                params: {
                    json: JSON.stringify(data)
                },
                ajaxOptions: {
                    type: 'post'
                }
              }}
          ]
        });
//        tab.setSelected(tab.getItemAt(0));
        InitTab(tab)
        InitListTab(scList)
//        var tmp= new Tab.TabPanel({
//                  render : '#school-category-tab',
//                  elCls : 'nav-tabs',
//                  panelContainer : '#school-category-panel',
//                  autoRender: true,
//                  children:scList
//         });
//        InitTab(tmp)
        InitListTab(cpList)
        InitTab(new Tab.TabPanel({
            render: '#all-type-tab',
            elCls: 'nav-tabs',
            panelContainer: '#all-type-panel',
            autoRender: true,
            children: atList
        }))
        InitListTab(stList)
        InitListTab(alList)
        InitListTab(slList)
        InitListTab(rList)
        InitListTab(rangeList)
      });