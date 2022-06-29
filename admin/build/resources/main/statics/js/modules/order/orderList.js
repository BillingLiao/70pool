function getOrderStatus(orderStatus){
	  switch(orderStatus){
      case 0:
         return "待支付";
          break;
      case 1:
    	  return("待支付关闭");
          break;
      case 2:
    	  return("已付款");
          break;
      case 3:
    	  return("待收货");
          break;
      case 4:
    	  return("已收货");
          break;
      case 5:
    	  return("已完成订单");
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
        url: baseURL + 'order/list',
        datatype: "json",
        colModel: [			
			{ label: '订单编号', name: 'orderId', index: 'order_id', width: 80, key: true },
			{ label: '订单号', name: 'orderNo', index: 'order_no', width: 150 },
			{ label: '订单类型', name: 'categoryName', index: 'category_name', width: 100 },
	        { label: '收款(元)', name: 'actualReceipts', index: 'actual_receipts', width: 120 },
	        { label: '订单提交时间', name: 'createTime', width: 120 },
            { label: '订单状态', name: 'orderStatus', width: 100, formatter: function (cellvalue, options, rowObject) {
                      return getOrderStatus(cellvalue);
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
		categoryName: null,
		order: {},
		search:{}
	},

	methods: {
		query: function (orderStatus) {
              vm.reload(orderStatus);
        },
		add: function(){
		    vm.showList = false;
            vm.title = "新增";
            vm.order = {};
		},

		update: function (event) {
		    var orderId = getSelectedRow();
                if(orderId == null){
                    return ;
                }
                vm.showList = false;
                vm.title = "修改";

                vm.getInfo(orderId);

		},
        status: function(){
            var orderId = getSelectedRow();
            if(orderId == null){
                return ;
            }
            vm.getInfo(orderId);

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
                        url: baseURL + "order/status",
                        contentType: "application/json",
                        data: JSON.stringify(vm.order),
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

			var url = vm.order.orderId == null ? "order/save" : "order/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.order),
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
			var orderIds = getSelectedRows();
			if(orderIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "order/delete",
                    contentType: "application/json",
				    data: JSON.stringify(orderIds),
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
		getInfo: function(orderId){
			$.get(baseURL + "order/info/"+orderId, function(r){
                vm.order = r.order;
            });
		},
		reload: function (orderStatus) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{
                postData:{"categoryName":vm.categoryName ,"orderStatus":orderStatus},
                page:page
            }).trigger("reloadGrid");
		},
	}
});