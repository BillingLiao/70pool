var vm = new Vue({
	el:'#page',
	data:{
		phone: null,
		password: null,
		loading: false,
	},
	methods:{
        login: function(){
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
            url: api + 'login/login',
            type:'POST',
            dataType:'json',
            data:{
                telphone: this.phone,
                password: this.password
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