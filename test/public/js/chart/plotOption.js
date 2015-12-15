var List = BUI.List
var items2 = []

var list1 = new List.Listbox({
    elCls:'bui-select-list',
    render: '#udp',
    items: items1,
    height: 150
})

var list2 = new List.Listbox({
    elCls: 'bui-select-list',
    render: '#sdp',
    item: items2,
    height: 150
})

list1.render()
list2.render()
$('#J_BtnRight1').on('click',function(){
    var selections1 = list1.getSelection()
    list1.removeItems(selections1)
    list2.addItems(selections1)
})

$('#J_BtnLeft1').on('click',function(){
    var selections = list2.getSelection()
    list1.addItems(selections)
    list2.removeItems(selections)
})


var columnItems = [
    {text:'考试层次', value: 0},
    {text:'考试范畴', value: 1},
    {text:'考点', value: 2},
    {text:"题型", value: 3}
]

var columnList = new List.Listbox({
    elCls: 'bui-select-list',
    render: '#columns',
    items: columnItems,
    height: 150,
    multipleSelect: false
})


var items = [
levelList,
categories,
checkpoints,
questionTypes
]

var ucList = new List.Listbox({
    elCls: 'bui-select-list',
    render: '#uc',
    items: [],
    height: 150
})

var scList = new List.Listbox({
    elCls: 'bui-select-list',
    render: '#sc',
    items: [],
    height: 150
})


ucList.render()
scList.render()

function renderClick(id){
    ucList.clearItems()
    scList.clearItems()
    ucList.addItems(items[id])
}

columnList.on("itemclick",function(ev){
    renderClick(ev.item.value)
})

columnList.render()

$("#J_BtnLeft2").on("click",function(){
    var sel = scList.getSelection()
    ucList.addItems(sel)
    scList.removeItems(sel)
})

$("#J_BtnRight2").on("click",function(){
    var sel = ucList.getSelection()
    ucList.removeItems(sel)
    scList.addItems(sel)
})

function packArgs(){
    var items = list2.getItems()
    var orgs = []
    for(var i=0;i<items.length;++i){
        orgs[i] = items[i].value
    }
    $("#orgs").val(JSON.stringify(orgs))
    orgs = []
    $("#columnType").val(columnList.getSelection()[0].value)
    items = scList.getItems()
    for(i=0;i<items.length;++i){
        orgs[i] = items[i].value
    }
    //console.log(items)
    $("#columnsId").val(JSON.stringify(orgs))
}

$("#submitBtn").on("click",function(){
    packArgs()
    $("#useOrgsAsX").val("true")
    return true
})

$("#submitBtn2").on("click",function(){
    packArgs()
    $("#useOrgsAsX").val("false")
    return true
})