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
		account :{}
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
        }else{
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
});