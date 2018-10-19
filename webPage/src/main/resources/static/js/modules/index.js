function getUrlParam(name){
    var obj = {};
    var url_0=window.location.href;
    var url;
    if(url_0.indexOf('?')!=-1){
        url = url_0.split('?')[1].split('&');
    }else{
        return false
    }
    var nameList = [];
    url.forEach(function(item) {
        let tempArr = item.split('=');
        let key = decodeURIComponent(tempArr[0]);
        let val = decodeURIComponent(tempArr[1]);
        obj[key] = val;
        nameList.push(key);
    })
    if (nameList.indexOf(name) == -1) {
        return false
    } else {
        return obj[name]
    }
}
var vm = new Vue({
	el:'#cloudPage',
	data:{
		cloudProduct :{}
	},
	created: function(){
	    $.ajax({
            url: api + 'cloud/findFirst',
            type:'POST',
            dataType:'json',
            success:function(res){
                if(res.code==0){
                    vm.cloudProduct = res.cloudProduct;
                    if(vm.cloudProduct == null){
                        $('#cloudList').hide();
                    }
                }else{
                    console.log(res);
                }
            },
            error: function(res) {
                console.log(res);
            }
        });
    },
    methods:{
        clickBuy: function(){
            var productId = vm.cloudProduct.productId;
            window.location.href='mall_detail.html?productId='+productId;
        }
    }
});