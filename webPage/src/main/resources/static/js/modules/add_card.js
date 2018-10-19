var vm = new Vue({
	el:'#page',
	data:{
		trueName : '',
		openingBank: '',
		cardNumber: '',
		bankId: null
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
           url: api + 'bank/find',
           type:'POST',
           dataType:'json',
           data:{
               token:token
           },
           success:function(res){
               if(res.code==0){
                   if(res.bank != null){
                     vm.trueName = res.bank.trueName;
                     vm.openingBank = res.bank.openingBank;
                     vm.cardNumber = res.bank.cardNumber;
                     vm.bankId = res.bank.bankId;
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
            if(!isString(this.cardNumber)){
                swal({
                       text: "请输入您的银行卡号",
                       icon: "warning",
                       button: "返回",
                  });
                 return;
            }
            if(this.bankId == null){
                $.ajax({
                   url: api + 'bank/add',
                   type:'POST',
                   dataType:'json',
                   data:{
                       token: token,
                       cardNumber: this.cardNumber,
                       openingBank: this.openingBank,
                       trueName: this.trueName
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
                   url: api + 'bank/update',
                   type:'POST',
                   dataType:'json',
                   data:{
                       token: token,
                       cardNumber: this.cardNumber,
                       openingBank: this.openingBank,
                       trueName: this.trueName,
                       bankId: this.bankId
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