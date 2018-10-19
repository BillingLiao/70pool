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
		cloudProduct :{},
		tstimateOne: {},
		amount: 1,
		actualReceipts: null,
		consentClause: '同意签约云算力服务协议'
	},
	watch: {
        amount: function(newAmount, oldAmount){
            this.debouncedGetActualReceipts();
        }
     },
	created: function(){
       var productId = getUrlParam('productId');
        $.get(api + 'cloud/findOne/'+productId, function(r){
            vm.cloudProduct = r.cloudProduct;
            vm.actualReceipts = vm.cloudProduct.price*vm.amount;
        });
        $.ajax({
            url: api + 'tstimate/first',
            type:'GET',
            dataType:'json',
            success:function(res){
                if(res.code==0){
                    vm.tstimateOne = res.tstimateOne;
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
        this.debouncedGetActualReceipts = _.debounce(this.getActualReceipts, 500)
    },
    methods:{
        getActualReceipts: function(){
            if (!isPositiveInteger(this.amount)) {
　　　　　　      swal({
                    text: "购买数量须填入正整数",
                    icon: "warning",
                    button: "返回",
                    });
                 vm.actualReceipts = 0;
　　　           return false;
            }
            if(this.amount > this.cloudProduct.stock){
                swal({
                 text: "购买数量不能大于库存",
                 icon: "warning",
                 button: "返回",
                });
                vm.actualReceipts = 0;
                return;
            }
            $.ajax({
                url: api + 'order/actualReceipts',
                type:'POST',
                dataType:'json',
                data:{
                    amount: vm.amount,
                    price: vm.cloudProduct.price
                },
                success:function(res){
                    if(res.code==0){
                        vm.actualReceipts = res.actualReceipts;
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
        },
        goToPay: function(){
            var _this = this;
            var token = window.localStorage.getItem('token');
            var productId = getUrlParam('productId');
            if(token == null){
                swal('请先登录', {
                   buttons: false,
                   timer: 2000,
                 }).then((value) => {
                    window.location.href='login.html'
                });
            }else{
                if (!isPositiveInteger(this.amount)) {
    　　　　　　      swal({
                        text: "购买数量须填入正整数",
                        icon: "warning",
                        button: "返回",
                        });
    　　　           return;
                }
                if(this.amount > this.cloudProduct.stock){
                    swal({
                     text: "购买数量不能大于库存",
                     icon: "warning",
                     button: "返回",
                    });
                    return;
                }
                if (!$(".checkbox").attr("checked") == true){
                    _this.consentClause = '请先同意签约云算力服务协议';
                    return;
                }
                $.ajax({
                    url: api + 'order/add',
                    type:'POST',
                    dataType:'json',
                    data:{
                        token: token,
                        productId:productId,
                        actualReceipts:this.actualReceipts,
                        amount:this.amount
                    },
                    success:function(res){
                        if(res.code==0){
                            var orderId = res.order.orderId;
                            window.location.href='pay.html?orderId='+orderId;
                        }else if(res.code==3){
                             swal(res.msg, {
                                buttons: false,
                                timer: 2000,
                              }).then((value) => {
                                 window.location.href='login.html'
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

        }
    }

});