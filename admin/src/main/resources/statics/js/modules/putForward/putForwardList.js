function getPutForwardStatus(forwardStatus){
	  switch(forwardStatus){
      case 0:
         return "已提交";
          break;
      case 1:
    	  return("提现关闭");
          break;
      case 2:
    	  return("提现失败");
          break;
      case 3:
    	  return("已完成");
          break;
      default:
          return("-");
          break;
  }
}

function getPutForwardType(forwardType){
	  switch(forwardType){
      case 1:
         return "矿机";
          break;
      case 2:
    	  return("btc");
          break;
      case 3:
    	  return("cny");
          break;
      default:
          return("-");
          break;
  }
}

$(function () {

    $('.buttoonnamber input').removeClass('active')
    $('.buttoonnamber input').eq(0).addClass('active');
    $('.buttoonnamber input').click(function(){

        $('.buttoonnamber input').removeClass('active');
        $(this).addClass('active');

    })

    $("#jqGrid").jqGrid({
        url: baseURL + 'putForward/list',
        datatype: "json",
        colModel: [
			{ label: '提现编号', name: 'putForwardId', index: 'put_orward_id', width: 80, key: true },
			{ label: '提现单号', name: 'forwardNo', index: 'forward_no', width: 150 },
	        { label: '金额(btc)', name: 'btc', index: 'btc', width: 120 },
	        { label: '金额(cny)', name: 'cny', index: 'cny', width: 120 },
	        { label: '提交时间', name: 'createTime', width: 120 },
	        { label: '提现类型', name: 'forwardType', width: 100, formatter: function (cellvalue, options, rowObject) {
                                  return getPutForwardType(cellvalue);
                        }},
            { label: '提现状态', name: 'forwardStatus', width: 100, formatter: function (cellvalue, options, rowObject) {
                      return getPutForwardStatus(cellvalue);
            }},
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
            putForward: "putForward"
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
		q:{
            forwardNo: null
        },
		title: null,
		forwardNo: null,
		putForward: {},
		search:{}
	},

	methods: {
		query: function (forwardStatus) {
              vm.reload(forwardStatus);
        },
		add: function(){
		    vm.showList = false;
            vm.title = "新增";
            vm.putForward = {};
		},

		update: function (event) {
		    var putForwardId = getSelectedRow();
                if(putForwardId == null){
                    return ;
                }
                vm.showList = false;
                vm.title = "修改";

                vm.getInfo(putForwardId);

		},
        status: function(){
            var putForwardId = getSelectedRow();
            if(putForwardId == null){
                return ;
            }
            vm.getInfo(putForwardId)
            layer.open({
                type: 1,
                offset: '50px',
                skin: 'layui-layer-molv',
                title: "设置状态",
                area: ['300px', '300px'],
                shade: 0,
                shadeClose: false,
                content: jQuery("#statusLayer"),
                btn: ['确定', '取消'],
                btn1: function (index) {
                    $.ajax({
                        type: "POST",
                        url: baseURL + "putForward/status",
                        contentType: "application/json",
                        data: JSON.stringify(vm.putForward),
                        success: function(r){
                            if(r.code === 0){
                                alert('操作成功', function(data){
                                     layer.close(index);
                                });
                            }else{
                                alert(r.msg, function(data){
                                 layer.close(index);
                                });
                            }
                            vm.reload();
                        }
                    });

                }

            });
        },

		saveOrUpdate: function (event) {

			var url = vm.putForward.putForwardId == null ? "putForward/save" : "putForward/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.putForward),
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
			var putForwardIds = getSelectedRows();
			if(putForwardIds == null){
				return ;
			}

			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "putForward/delete",
                    contentType: "application/json",
				    data: JSON.stringify(putForwardIds),
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
		getInfo: function(putForwardId){
			$.get(baseURL + "putForward/info/"+putForwardId, function(r){
                vm.putForward = r.putForward;
            });
		},
		reload: function (forwardStatus) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{
                postData:{"forwardNo":vm.q.forwardNo, "forwardStatus":forwardStatus},
                page:page
            }).trigger("reloadGrid");
		},
	}
});