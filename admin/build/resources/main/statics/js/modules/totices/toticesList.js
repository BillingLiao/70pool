
$(function () {

    $("#jqGrid").jqGrid({
        url: baseURL + 'totices/list',
        datatype: "json",
        colModel: [			
			{ label: '公告编号', name: 'toticesId', index: 'totices_id', width: 80, key: true },
			{ label: '标题', name: 'toticesTitle', index: 'totices_title', width: 300 },
            //{ label: '内容', name: 'content', index: 'content', width: 360 },
	        { label: '发布日期', name: 'publishDate', index: 'publish_date', width: 120 },
	    ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
        //rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader : {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames : {
            page:"page", 
            rows:"limit", 
            order: "order"
        },
        gridComplete:function(){
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
});

var editor;
var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		totices: {},
		search:{}
	},
	created:function() {
        Vue.nextTick(function () {
           var E = window.wangEditor;
            editor = new E('#editor');
            editor.create();
            if(vm.totices){
                editor.txt.html(vm.totices.content);
            }
            });

    },
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
		    vm.showList = false;
            vm.title = "新增";
            vm.totices = {};
            if(editor){
                  editor.txt.html('');
            }
		},

		update: function (event) {
		    var toticesId = getSelectedRow();
                if(toticesId == null){
                    return ;
                }
                vm.showList = false;
                vm.title = "修改";

                vm.getInfo(toticesId);
               /* if(editor){
                    editor.txt.html('');
                    console.log(vm.totices.content);
                    editor.txt.html(vm.totices.content);
                }*/
		},

		saveOrUpdate: function (event) {
		    if(editor){
                vm.totices.content =editor.txt.html();
            }
			var url = vm.totices.toticesId == null ? "totices/save" : "totices/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.totices),
			    success: function(r){
			    	if(r.code === 0){
						alert('操作成功', function(index){
							vm.reload();
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		del: function (event) {
			var toticesIds = getSelectedRows();
			if(toticesIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "totices/delete",
                    contentType: "application/json",
				    data: JSON.stringify(toticesIds),
				    success: function(r){
						if(r.code == 0){
							alert('操作成功', function(index){
								$("#jqGrid").trigger("reloadGrid");
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},
		getInfo: function(toticesId){
			$.get(baseURL + "totices/info/"+toticesId, function(r){
                vm.totices = r.totices;
                if(editor){
                    editor.txt.html('');
                    console.log(vm.totices.content);
                    editor.txt.html(vm.totices.content);
                }
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{
                postData:vm.search,
                page:page
            }).trigger("reloadGrid");
		},
	}
});