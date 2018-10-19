$(function () {

    $("#jqGrid").jqGrid({
        url: baseURL + 'cloud/list',
        datatype: "json",
        colModel: [			
			{ label: '产品id', name: 'productId', index: 'product_id', width: 80, key: true },
			{ label: '产品名称', name: 'productName', index: 'product_name', width: 150 },
			{ label: '缩略图', name: 'picImg', index: 'pic_img', width: 150,formatter: function(cellvalue, options, rowObject){
                 if(cellvalue){
                     return       '<a class="btn btn-sm btn-success" href="'+cellvalue+'"  target="_blank"><i class="fa fa-file-photo-o"></i>&nbsp;预览</a>';
                 }else{
                     return       '<a class="btn btn-sm btn-warning" ><i class="fa fa-file-photo-o"></i>&nbsp;暂无图片</a>';

                 }
            }},
			{ label: '分类', name: 'categoryName', index: 'category_name', width: 120 ,formatter: function(cellvalue, options, rowObject){
                if(cellvalue == null || cellvalue == ''){
                return   "它的分类被删除了";
                }

                return   rowObject.categoryName;

            }},
	        //{ label: '产品介绍', name: 'introduction', index: 'introduction', width: 150 },
	        { label: '库存(T)', name: 'stock', index: 'stock', width: 120 },
	        { label: '单价(元)', name: 'price', index: 'price', width: 120 },
	        { label: '售价(元)', name: 'retailPrice', index: 'retail_price', width: 120 },
	        { label: '服务费', name: 'managementExpense', index: 'management_expense', width: 120 },
	        { label: '本期开售时间', name: 'saleTime', index: 'sale_time', width: 120 },
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
		cloudProduct: {},
		introduction:true,
		search:{}
	},
	created:function() {
      	   Vue.nextTick(function () {
      		   var E = window.wangEditor;
      	        editor = new E('#editor');
      	        // 配置服务器端地址
      	      /* editor.customConfig.uploadFileName = 'file';
      	       editor.customConfig.uploadImgServer = baseURL +'sys/oss/wangEditorupload';*/
      	       editor.create();
      	        if(vm.cloudProduct){
                    editor.txt.html(vm.cloudProduct.introduction);
                 }
      	         });

    },
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){

        			location.href=baseURL + 'modules/product/sort.html';
        		},
        update: function (event) {
            var productId = getSelectedRow();
            if(productId == null){
                return ;
            }

            location.href=baseURL + 'modules/product/type.html?productId='+productId;
        },
/*		add: function(){
		    vm.showList = false;
            vm.title = "新增";
            vm.cloudProduct = {};
            vm.cloudProduct = {salesStatus:0};
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
                   editor.txt.html(vm.cloudProduct.introduction);
                }
		},*/
		 
		shelve1: function (event) {
			var productId = getSelectedRow();
			if(productId == null){
				return ;
			}
			confirm('确定要上架吗', function(){
			        	$.ajax({
							type: "POST",
						    url: baseURL + "cloud/updateShelve",
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
						    url: baseURL + "cloud/updateShelve",
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
            	vm.cloudProduct.introduction =editor.txt.html();
            }
			var url = vm.cloudProduct.productId == null ? "cloud/save" : "cloud/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.cloudProduct),
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
				    url: baseURL + "cloud/delete",
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
			$.get(baseURL + "cloud/info/"+productId, function(r){
                vm.cloudProduct = r.cloudProduct;
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

$(document).on("ready", function() {
	$("#input-7021").fileinput({
	     theme: 'fa',
	    uploadUrl: baseURL + "cloud/mulUpload",
	    uploadAsync: false,
	    minFileCount: 1,
	    maxFileCount: 1,
	    overwriteInitial: false,
        showRemove:false,
        showUpload: false,
	    language : 'zh',
	    initialPreview: [
	        // "http://lorempixel.com/800/460/people/1",
	        // "http://lorempixel.com/800/460/people/2"
	    ],
	    initialPreviewAsData: true, // identify if you are sending preview data only and not the raw markup
	    initialPreviewFileType: 'image', // image is the default and can be overridden in config below
	    initialPreviewConfig: [
	        // {caption: "People-1.jpg", size: 576237, width: "120px", url: "/site/file-delete", key: 1},
	        // {caption: "People-2.jpg", size: 932882, width: "120px", url: "/site/file-delete", key: 2},
	    ]
	}).on('filesorted', function(e, params) {
	    console.log('file sorted', e, params);
	}).on('fileuploaded', function(event, data, previewId, index) {
	     console.log('fileuploaded', data);
 		 var fileName = data.response.fileName;
 		 var url = baseURL + "upload/" + fileName;
	     vm.cloudProduct.picImg=url;

	 }).on('filesuccessremove', function(event, data, index,e) {
			var filePath = [];
			console.log("filesuccessremove");
			console.log(filePath);
			filePath.push(vm.cloudProduct.picImg);
			deleteFiles(filePath);
			vm.cloudProduct.picImg="";
		//return false;
	}).on('filecleared', function(event, data, previewId, index) {
		console.log(event, data, previewId,index);
		console.log("filecleared");
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

		console.log(event, data);
		console.log("filebatchuploadcomplete");
	}).on('beforeSend', function(event, data, previewId, index) {
		console.log(event, data);
		console.log("beforeSend");
	}) .on('fileselect', function(event, data, previewId, index) {

		console.log(event, data,previewId,index);
		console.log("fileselect");

	}).on('filebatchpreupload', function(event, data, previewId, index) {


	}).on('filepreajax', function(event, data, previewId, index) {
		 var test = $("#picimg .file-preview-thumbnails .kv-preview-thumb").length;
		 if(test >1){alert("只能上传一个文件");
			 var container = $(".file-input");
			var processDiv = container.find('.kv-upload-progress');
			processDiv.hide();
			console.log(event, data,previewId,index);
			console.log("filepreajax");
			 return false;
		 }
	});
})