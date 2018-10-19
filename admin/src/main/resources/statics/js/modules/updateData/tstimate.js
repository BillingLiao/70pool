$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'tstimate/list',
        datatype: "json",
        colModel: [
			{ label: '编号', name: 'tstimateId', index: 'tstimate_id', width: 50, key: true },
			{ label: '预估收益(btc)', name: 'tstimate', index: 'tstimate', width: 80 },
			{ label: '更新时间', name: 'createTime', width: 120 },
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true,
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

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		tstimate: {}
	},
	methods: {

		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.tstimate = {};
		},
		update: function (event) {
			var tstimateId = getSelectedRow();
			if(tstimateId == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";

            vm.getInfo(tstimateId)
		},
		saveOrUpdate: function (event) {
			var url = vm.tstimate.tstimateId == null ? "tstimate/save" : "tstimate/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.tstimate),
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
			var tstimateIds = getSelectedRows();
			if(tstimateIds == null){
				return ;
			}

			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "tstimate/delete",
                    contentType: "application/json",
				    data: JSON.stringify(tstimateIds),
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
		getInfo: function(tstimateId){
			$.get(baseURL + "tstimate/info/"+tstimateId, function(r){
                vm.tstimate = r.tstimate;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page
            }).trigger("reloadGrid");
		}
	}
});