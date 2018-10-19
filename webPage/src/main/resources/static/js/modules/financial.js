if(window.localStorage.getItem('token')==null){
     window.location.href='login.html'
}

var vm = new Vue({
	el:'#page',
	data:{
		account :{},
		userFinancialList :{}
	},
	created: function(){
	    var token = window.localStorage.getItem('token');
	    $.ajax({
            url: api + 'user/detailed',
            type:'POST',
            dataType:'json',
            data:{
                token:token
            },
            success:function(res){
                console.log(res);
                if(res.code==0){
                    vm.account = res.result.account;
                    vm.userFinancialList = res.result.userFinancialList;
                }else{
                    console.log(res);
                }
            },
            error: function(res) {
                console.log(res);
            }
        });
    }
});