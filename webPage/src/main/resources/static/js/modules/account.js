var vm = new Vue({
	el:'#page',
	data:{
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
    },
});