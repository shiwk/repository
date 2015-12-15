var Grid = BUI.Grid;
var Data = BUI.Data;
var Store = Data.Store;

columns = [
    {title: '用户名', dataIndex:'name', width:75},
    {title:'真实姓名', dataIndex:'realName',width:75},
    {title:'权限', dataIndex:'role', width:200},
    {title:'教授科目',dataIndex:'subjects',width:100},
    {title:'状态',dataIndex:'status',width:50},
    {title:'操作',dataIndex:'method', width: 300,renderer:function(v){
        tmp = ""
        if(v.status==0){
            tmp = "冻结"
        } else {
            tmp = "解冻"
        }
        var furl = FREEZE_URL+"?userId="+v.id
        furl = ' <a href="'+furl+'" class="button button-primary">'+ tmp+'</a>'

        var eurl = EDIT_URL+"?userId="+v.id
        eurl = '<a href="'+eurl+'" class="button button-primary">编辑</a>'
        if(SELF_ID==v.id){
            return eurl
        } else {
            return eurl +"&nbsp;"+furl
        }
    }}
]

var store = new Store({
    url: BASE_URL,
    autoLoad: true,
    pageSize:10
})
var grid = new Grid.Grid({
    render: '#grid',
    columns: columns,
    loadMask: true,
    store: store,
    bbar: {
         pagingBar:true
    }
})
grid.render()