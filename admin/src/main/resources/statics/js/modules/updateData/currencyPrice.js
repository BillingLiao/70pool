$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'currencyPrice/list',
        datatype: "json",
        colModel: [
			{ label: '编号', name: 'priceId', index: 'price_id', width: 50, key: true },
			{ label: '比特币价格(元)', name: 'btcCny', index: 'btc_cny', width: 80 },
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
		currencyPrice: {}
	},
	methods: {

		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.currencyPrice = {};
		},
		update: function (event) {
			var priceId = getSelectedRow();
			if(priceId == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";

            vm.getInfo(priceId)
		},
		saveOrUpdate: function (event) {
			var url = vm.currencyPrice.priceId == null ? "currencyPrice/save" : "currencyPrice/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.currencyPrice),
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
			var priceIds = getSelectedRows();
			if(priceIds == null){
				return ;
			}

			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "currencyPrice/delete",
                    contentType: "application/json",
				    data: JSON.stringify(priceIds),
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
		getInfo: function(priceId){
			$.get(baseURL + "currencyPrice/info/"+priceId, function(r){
                vm.currencyPrice = r.currencyPrice;
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