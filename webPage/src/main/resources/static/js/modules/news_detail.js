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
		totices :{}
	},
	created: function(){
	   var _this = this;
       var productId = getUrlParam('toticesId');
        $.get(api + 'totices/findOne/'+productId, function(r){
            console.log(r);
            _this.totices = r.totices;
            $('#contentInfo').html(_this.totices.content);//通过html()方法将数据输出到div中
        });
    }
});