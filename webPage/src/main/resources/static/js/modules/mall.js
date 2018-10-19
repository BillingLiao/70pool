var vm = new Vue({
	el:'#page',
	data:{
		cloudProductList :{}
	},
	created: function(){
	    $.ajax({
            url: api + 'cloud/findList',
            type:'GET',
            dataType:'json',
            success:function(res){
                if(res.code==0){
                    vm.cloudProductList = res.cloudProductList;
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
        clickBuy: function(productId){
            //var productId = vm.cloudProduct.productId;
            window.location.href='mall_detail.html?productId='+productId;
        }
    }
});