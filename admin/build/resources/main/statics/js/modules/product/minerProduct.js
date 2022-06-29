function picImg(goodId){
	$('#input-702').fileinput('destroy');

 	//$('#input-702').fileinput('refresh')
	 getImage(goodId);
	 layer.open({
         type: 1,
         offset: '50px',
         skin: 'layui-layer-molv',
         title: "图片预览",
         area: ['800px', '550px'],
         shade: 0,
         shadeClose: false,
         content: jQuery("#imgLayer"),
         btn: ['规格删除','重新生成规格', '取消'],
         btn1: function (index) {
        	 
             layer.close(index);
         },
         zIndex : 100
     });
}

function getInitialPreviewConfig(){
 	var goodImage = vm.goodImage;
	var goodImageIds = vm.goodImageIds;
	vm.initialPreviewConfig=[];
	for(var index in goodImage){
		 var jsonStr = '{"filePath[]":"['+goodImage[index]+']","picImgIds":['+goodImageIds[index]+']}';
		 $.parseJSON(jsonStr);
		vm.initialPreviewConfig.push(new Object());
		vm.initialPreviewConfig[index].url=baseURL +"good/goodimage/delete";
		vm.initialPreviewConfig[index].width="120px";
		vm.initialPreviewConfig[index].extra=$.parseJSON(jsonStr);
		vm.initialPreviewConfig[index].key=goodImage[index];
	}
}


function getImage(goodId){
	//$('#input-id').fileinput('refresh');

 	 vm.goodImage=[];
	 vm.goodImageIds=[];
	$.get(baseURL + "good/goodimage/goodImageList",{goodId:goodId}, function(r){
		$(r.goodImage).each(function(i,n){
			
			 vm.goodImage.push(n.picImg);
			 vm.goodImageIds.push(n.picImgId);
	 	})
 		getInitialPreviewConfig();
	 	  // 图片上传star
	    $("#input-702").fileinput({
	        theme: 'fa',
	        uploadUrl: baseURL + "good/goodimage/saveGoodImage",
	        uploadAsync: false,
	        minFileCount: 1,
	        maxFileCount: 5,
	        overwriteInitial: false,
	        showRemove:false,
	        showUpload: false, 
	        language : 'zh',
	        initialPreview: vm.goodImage,
	        initialPreviewAsData: true, // identify if you are sending preview data only and not the raw markup
	        initialPreviewFileType: 'image', // image is the default and can be overridden in config below
	        initialPreviewConfig: vm.initialPreviewConfig,
	        uploadExtraData: {
	            goodId: goodId
 	        }
	    }).on('filesorted', function(e, params) {
	        console.log('file sorted', e, params);
	    }).on('fileuploaded', function(e, params) {
	     
	        console.log('file uploaded', e, params);
	    }).on('filebatchuploadsuccess', function(event, data, previewId, index) {
	   	 
	    	  getImage();
	    	   console.log('filebatchuploadsuccess', event, data, previewId, index);
	    }).on('filesuccessremove', function(event, data, index,e) {
	    	console.log(event, data, index,e);
	    	console.log("filesuccessremove");
	    	 
	    		index = jQuery.inArray(data, vm.goodImageIds)
	    		
	     
	    		var filePath = [];
	    		filePath.push(vm.goodImage[index]);
	    		deleteFiles(filePath);
	    		vm.goodImageIds.splice(index,1); 
	    		vm.goodImage.splice(index,1); 

	    	//return false;
	    }).on('filecleared', function(event, data, previewId, index) {
	    	console.log(event, data, previewId,index);
	    	console.log("filecleared");
	    	deleteFiles(vm.goodImage);
	    	vm.goodImage=[];
	    	vm.goodImageIds=[];
	    }).on('filedeleted', function(event, data, previewId, index) {
	    	console.log(event, data, previewId,index);
	    	console.log("filedeleted");
	    }).on('filepreremove', function(event, data, previewId, index) {
	    	console.log(event, data, previewId,index);
	    	console.log("filepreremove");
	    }).on('fileclear', function(event, data, previewId, index) {
	    	console.log(event, data);
	    	console.log("fileclear");
	    }).on('filepreupload', function(event, data, previewId, index) {
	    	console.log(event, data);
	    	console.log("filepreupload");
	    }).on('filebatchuploadcomplete', function(event, data, previewId, index) {
	    	getImage();
	    	console.log(event, data);
	    	console.log("filebatchuploadcomplete");
	    }).on('beforeSend', function(event, data, previewId, index) {
	    	console.log(event, data);
	    	console.log("beforeSend");
	    }).on('filebatchselected', function(event, data, previewId, index) {
	    	console.log("filebatchselected");
	     
	    }).on('fileselect', function(event, data, previewId, index) {
	    }).on('fileimagesloaded', function(event) {
	        console.log("fileimagesloaded");
	    }).on('fileloaded', function(event, file, previewId, index, reader) {
	        console.log("fileloaded");
	    }).on('fileimageloaded', function(event, file, previewId, index, reader) {
	        console.log("fileimageloaded");
	    });
	    // 图片上传end
      });
	//$('#input-702').fileinput('refresh');

}
$(function () {

	//getImage();
    $("#jqGrid").jqGrid({
        url: baseURL + 'miner/list',
        datatype: "json",
        colModel: [			
			{ label: '产品id', name: 'productId', index: 'product_id', width: 80, key: true },
			{ label: '产品名称', name: 'productName', index: 'product_name', width: 150 },
			{ label: '分类', name: 'categoryName', index: 'category_name', width: 120 ,formatter: function(cellvalue, options, rowObject){
 	            if(cellvalue == null || cellvalue == ''){
 	           	return   "它的分类被删除了";
 	            }
 	            
				return   rowObject.categoryName;
	           
	        }},
	        //{ label: '产品介绍', name: 'introduction', index: 'introduction', width: 150 },
	        { label: '单价', name: 'price', index: 'price', width: 120 },
	        { label: '额定算力', name: 'stock', index: 'stock', width: 120 },
	        { label: '机型', name: 'model', index: 'model', width: 120 },
	        { label: '电费', name: 'electricityFees', index: 'electricity_fees', width: 120 },
            { label: '上架', name: 'showInShelve', index: 'show_in_shelve', width: 120 ,formatter: function(cellvalue, options, rowObject){
				if(cellvalue == 0){
	                return '<span class="label label-primary">下架</span>';
	            }
	            if(cellvalue == 1){
	                return '<span class="label label-success">上架</span>';
	            }  
	            return '<span class="label label-primary">上架</span>';
	           
	        }}, 	
	    	{ label: '创建时间', name: 'createAt', index: 'create_at', width: 120 },
            //{ label: '创建者', name: 'createUser', index: 'create_user', width: 120},
			/*
			{ label: '更新者', name: 'updateBy', index: 'update_by', width: 80 }, 			
			{ label: '更新时间', name: 'updateTime', index: 'update_time', width: 80 }, */
			/*{ label: '', name: 'delFlag', index: 'del_flag', width: 80 }	*/		
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
		minerProduct: {},
		introduction:true,
		search:{},
		goodImage:[],
		goodImageIds:[],
		initialPreviewConfig:[],
	},
	created:function() {
      	   Vue.nextTick(function () {
      		   var E = window.wangEditor;
      	        editor = new E('#editor');
      	        // 配置服务器端地址
      	      /* editor.customConfig.uploadFileName = 'file';
      	       editor.customConfig.uploadImgServer = baseURL +'sys/oss/wangEditorupload';*/
      	       editor.create();
      	        if(vm.minerProduct){
                    editor.txt.html(vm.minerProduct.introduction);
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
            vm.minerProduct = {};
            vm.minerProduct = {salesStatus:0};
            if(editor){
                editor.txt.html('');
            }
		},

		update: function (event) {
		    var productId = getSelectedRow();
                if(productId == null){
                    return ;
                }
                vm.showList = false;
                vm.title = "修改";

                vm.getInfo(productId);
                if(editor){
                   editor.txt.html(vm.minerProduct.introduction);
                }
		},
		 
		shelve1: function (event) {
			var productId = getSelectedRow();
			if(productId == null){
				return ;
			}
			confirm('确定要上架吗', function(){
			        	$.ajax({
							type: "POST",
						    url: baseURL + "miner/updateShelve",
 						    data: {showInShelve:1,productId:productId},
						    success: function(r){
						    	if(r.code === 0){
									alert('操作成功', function(data){
										vm.reload();
									});
								}else{
									alert(r.msg, function(data){
										vm.reload();
 									});
								} 
							}
						});
			});  
			 
		},
		 	 
		shelve0: function (event) {
			var productId = getSelectedRow();
			if(productId == null){
				return ;
			}
			confirm('确定要下架吗', function(){
			        	$.ajax({
							type: "POST",
						    url: baseURL + "miner/updateShelve",
 						    data: {showInShelve:0,productId:productId},
						    success: function(r){
						    	if(r.code === 0){
									alert('操作成功', function(data){
										vm.reload();
									});
								}else{
									alert(r.msg, function(data){
										vm.reload();
 									});
								} 
							}
						});
			});  
			 
		},

		saveOrUpdate: function (event) {
		    if(editor){
            	vm.minerProduct.introduction =editor.txt.html();
            }
			var url = vm.minerProduct.productId == null ? "miner/save" : "miner/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.minerProduct),
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
			var productIds = getSelectedRows();
			if(productIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "miner/delete",
                    contentType: "application/json",
				    data: JSON.stringify(productIds),
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
		getInfo: function(productId){
			$.get(baseURL + "miner/info/"+productId, function(r){
                vm.minerProduct = r.minerProduct;
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