var vm = new Vue({
	el:'#page',
	data:{
		phone: null,
		password: null,
		verificationCode: null,
		verification: null,
		invitationCode: null,
		loading: false,
		sendMsgText: '获取验证码',
		sendMsgStatus: false
	},
	methods:{
	    getSMSCode: function(){
	        var _this = this;
	        if(isString(this.phone)){
	            if (!isPhone(this.phone)) {
                    swal({
                     text: "请输入正确的手机号码",
                     icon: "warning",
                     button: "返回",
                    });
                    return;
                }
                if (this.sendMsgStatus) {
                    return;
                }

                $.ajax({
                    url: api + 'login/sendCode',
                    type:'POST',
                    dataType:'json',
                    data:{
                        phone: this.phone,
                        msgType: 0
                    },
                    success:function(res){
                        if(res.code==0){
                            console.log(res);
                            _this.verificationCode = res.verificationCode;

                            var timer = null, num = 60;

                            this.sendMsgStatus = true;

                            swal("验证码已发送至你的手机，请注意查收", {
                              buttons: false,
                              timer: 3000,
                            });

                            timer = setInterval(function() {
                                if (!num) {
                                    clearInterval(timer);
                                    num = 60;
                                    _this.sendMsgText = '重新发送';
                                    _this.sendMsgStatus = false;
                                    return;
                                }
                                num--;
                                _this.sendMsgText = '重新发送' + '(' + num + ')';
                            }, 1000);
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
               swal({
                 text: "请输入您的手机号码",
                 icon: "warning",
                 button: "返回",
               });
	        }
	    },
        regAndLogin: function(){
           var _this = this;
           if(!isString(this.phone)){
                swal({
                 text: "请输入您的手机号码",
                 icon: "warning",
                 button: "返回",
                });
               return;
           }
           if (!isPhone(this.phone)) {
               swal({
                text: "请输入正确的手机号码",
                icon: "warning",
                button: "返回",
               });
               return;
           }
           if(!isString(this.verification)){
               swal({
                text: "请输入验证码",
                icon: "warning",
                button: "返回",
               });
              return;
          }
           if(!isString(this.password)){
              swal({
                   text: "请输入您的密码",
                   icon: "warning",
                   button: "返回",
              });
             return;
           }
           if(this.password.length < 6 || this.password.length > 16){
                 swal({
                      text: "请输入6-16位的密码",
                      icon: "warning",
                      button: "返回",
                 });
            return;
          }
          _this.loading = true;

          $.ajax({
            url: api + 'login/register',
            type:'POST',
            dataType:'json',
            data:{
                phone: this.phone,
                password: this.password,
                verification: this.verification,
                invitationCode: this.invitationCode
            },
            success:function(res){
                _this.loading = false;
                if(res.code==0){
                    swal(res.msg, {
                       buttons: false,
                       timer: 2000,
                     }).then((value) => {
                        window.localStorage.setItem('token',res.result);
                        window.location.href='index.html'
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
                _this.loading = false;
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