var editor;
var vm = new Vue({
    el:'#rrapp',
    data:{
        showList: true,
        title: '添加产品',
        cloudProduct:{}
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
       							vm.next();
       						});
       					}else{
       						alert(r.msg);
       					}
       				}
       			});
       		},
		reload: function (event) {
			location.href=baseURL+'modules/product/cloudProduct.html';
		},
		next: function (event) {
            location.href="modules/product/cloudProduct.html";
        }
     } 
 })
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
 		  var fileName = data.response.fileName;
          var url = baseURL + "upload/" + fileName;
          //var url = "D:/upload/" + fileName;
          vm.cloudProduct.picImg=url;
	 	
	 }).on('filesuccessremove', function(event, data, index,e) {
			var filePath = [];
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
