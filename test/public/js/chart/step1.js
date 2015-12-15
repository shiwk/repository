var List = BUI.List;
var Data = BUI.Data;
var Toolbar = BUI.Toolbar;

var tpl = [
          '<li class="bui-list-item">',
            '<div class="demo-list-content">',
              '<a class="demo-list-link" href="{href}">',
                '<img src="{src}"/>',
              '</a>',
              '<div class="demo-list-des">',
                '<h3>{title}</h3>',
                '{desc}',
              '</div>',
            '</div>',
            '<div class="demo-list-border">',
            '</div>',
          '</li>'
        ]


store = new Data.Store({
    url: '1/data.json',
    pageSize: 10
})

list = new List.SimpleList({
    render: '#list1',
    elCls: 'demo-list clearfix',
    store: store,
    itemTpl: tpl.join('')
})

///console.log(tpl.join(''))
paging = new Toolbar.NumberPagingBar({
    render: '#paging',
    elCls: 'pagination pull-right',
    store: store
});

list.render()
paging.render()
store.load()