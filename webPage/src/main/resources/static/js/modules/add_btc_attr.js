var vm = new Vue({
	el:'#page',
	data:{
	    addr: null,
	    btcAddrId: null
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
           url: api + 'btcAddr/find',
           type:'POST',
           dataType:'json',
           data:{
               token:token
           },
           success:function(res){
               if(res.code==0){
                   if(res.btcAddr != null){
                      vm.btcAddrId = res.btcAddr.btcAddrId;
                      vm.addr = res.btcAddr.addr;
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
    },
    methods:{
        save: function(){
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
            if(!isString(this.addr)){
                swal({
                       text: "请输入您的钱包地址",
                       icon: "warning",
                       button: "返回",
                  });
                 return;
            }
            if(this.btcAddrId == null){
                $.ajax({
                   url: api + 'btcAddr/add',
                   type:'POST',
                   dataType:'json',
                   data:{
                       token: token,
                       addr: this.addr
                   },
                   success:function(res){
                       if(res.code==0){
                           swal(res.msg, {
                                buttons: false,
                                timer: 2000,
                              }).then((value) => {
                                 window.location.href='account.html';
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
            }else{
                $.ajax({
                   url: api + 'btcAddr/update',
                   type:'POST',
                   dataType:'json',
                   data:{
                       token: token,
                       addr: this.addr,
                       btcAddrId: this.btcAddrId
                   },
                   success:function(res){
                       if(res.code==0){
                           swal(res.msg, {
                                buttons: false,
                                timer: 2000,
                              }).then((value) => {
                                 window.location.href='account.html';
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
    }

});