var vm = new Vue({
	el:'#page',
	data:{
	    showList: true,
		account :{},
		cny: null,
		btcTrue: null,
		btcMsg: '',
		cnyMsg: '',
		addr: null,
		btcAddrId: null,
	},
	watch: {
        btcTrue: function(newBtcTrue, oldBtcTrue){
            this.debouncedGetChangeBtc();
        },
        cny: function(newCny, oldCny){
            this.debouncedGetChangeCny();
        }
    },
	created: function(){
	    var token = window.localStorage.getItem('token');
        if(token == null){
             swal("请先登录", {
                  buttons: false,
                  timer: 2000,
                }).then((value) => {
                   window.location.href='login.html'
               });
               return;
        }
	    $.ajax({
            url: api + 'user/my',
            type:'POST',
            dataType:'json',
            data:{
                token:token
            },
            success:function(res){
                if(res.code==0){
                    vm.account = res.account;
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
        $.ajax({
           url: api + 'btcAddr/find',
           type:'POST',
           dataType:'json',
           data:{
               token:token
           },
           success:function(res){
               if(res.code==0){
                   if(res.btcAddr != null){
                      vm.showList = false;
                      vm.addr = res.btcAddr.addr;
                      vm.btcAddrId = res.btcAddr.btcAddrId;
                   }
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
        this.debouncedGetChangeBtc = _.debounce(this.getChangeBtc, 500);
        this.debouncedGetChangeCny = _.debounce(this.getChangeCny, 500);
    },
    methods:{
        allBtc: function(res){
            var btc = this.account.btcBalance;
            var btcTrueNo = btc/1.005;
            this.btcTrue = btcTrueNo.toFixed(10);
        },
        allCny: function(res){
            this.cny = this.account.cnyBalance;
        },
        getChangeBtc: function(){
           var btc = vm.account.btcBalance;
           if(!isDouble(this.btcTrue)) {
                this.btcMsg = '请输入正确的数字';
           }else if(this.btcTrue <= 0.001) {
               this.btcMsg = '提现金额不能小于0.001BTC';
           }else if(this.btcTrue > btc/1.005) {
               this.btcMsg = '输入金额超过可提现余额';
           }else {
               var oldBtc = this.btcTrue*1.005;
               var newBtc = oldBtc.toFixed(10);
               this.btcMsg = '实际扣除BTC:'+newBtc+'BTC';
           }
        },
        getChangeCny: function(){
            if(this.cny <= 0){
               this.cnyMsg = '提现金额不能小于0';
            }else if(this.cny > this.account.cnyBalance){
              this.cnyMsg = '输入金额超过可提现余额';
            }else{
               this.cnyMsg = '';
            }
        },
        addBtcPutForward: function(){
            var token = window.localStorage.getItem('token');
            var btc = vm.account.btcBalance;
            if(!isString(this.addr)){
                swal({
                   text: "是否前往设置btc地址？",
                   icon: "warning",
                   buttons: true,
                   dangerMode: true,
                }).then((willDelete) => {
                    if (willDelete) {
                         window.location.href='add_btc_attr_2.html'
                    } else {

                    }
                });
　　　           return;
            }
            if(!isDouble(this.btcTrue)) {
                 swal({
                   text: "请填入正确的数字",
                   icon: "warning",
                   button: "返回",
                   });
    　　　        return;
            }
            if(this.btcTrue <= 0.001){
                swal({
                   text: "提现金额不能小于0.001BTC",
                   icon: "warning",
                   button: "返回",
                   });
　　　           return;
            }
            if(this.btcTrue > btc/1.005){
               swal({
                    text: "输入金额超过可提现余额",
                    icon: "warning",
                    button: "返回",
                    });
　　　          return;
            }
             $.ajax({
                url: api + 'putForward/add',
                type:'POST',
                dataType:'json',
                data:{
                    token: token,
                    forwardType: 0,
                    btcTrue: this.btcTrue
                },
                success:function(res){
                    if(res.code==0){
                        swal(res.msg, {
                           buttons: false,
                           timer: 2000,
                         }).then((value) => {
                            window.location.reload();
                        });
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
        addCnyPutForward: function(){
            var token = window.localStorage.getItem('token');
            if(this.cny <= 0){
                swal({
                   text: "提现金额不能小于0",
                   icon: "warning",
                   button: "返回",
                   });
　　　           return;
            }
            if(this.cny > this.account.cnyBalance){
               swal({
                    text: "输入金额超过可提现余额",
                    icon: "warning",
                    button: "返回",
                    });
　　　          return;
            }
             $.ajax({
                url: api + 'putForward/add',
                type:'POST',
                dataType:'json',
                data:{
                    token: token,
                    forwardType: 1,
                    cny: this.cny
                },
                success:function(res){
                    if(res.code==0){
                        swal(res.msg, {
                           buttons: false,
                           timer: 2000,
                         }).then((value) => {
                            window.location.reload();
                        });
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
    }
});