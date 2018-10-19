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
	el:'#page',
	data:{
		order :{},
		financialProduct: {},
		cloudProduct: {},
	},
	created: function(){
	   var token = window.localStorage.getItem('token');
       var orderId = getUrlParam('orderId');
       if(token == null){
           swal('请先登录', {
              buttons: false,
              timer: 2000,
            }).then((value) => {
               window.location.href='login.html'
           });
           return;
       }
       $.ajax({
           url: api + 'order/findOne',
           type:'POST',
           dataType:'json',
           data:{
               token: token,
               orderId: orderId
           },
           success:function(res){
               if(res.code==0){
                   vm.order = res.result.order;
                   vm.cloudProduct = res.result.cloudProduct;
                   vm.financialProduct = res.result.financialProduct;
               }else{
                   swal({
                      text: res.msg,
                      icon: "error",
                      button: "返回",
                     });
               }
           },
           error: function(res) {
               swal({
                 text: res.msg,
                 icon: "error",
                 button: "返回",
                });
           }
       });
    }
});