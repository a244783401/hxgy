function excuteAjax(url, data, fun) {
	$.ajax({
		url : url,
		type : "post",
		data : data,
		dataType : 'json',
        success : function(jsonObj) {
			fun(jsonObj);
		} ,
		error : function(error){
			hideLoding();
		}
		, beforeSend: function () {
			loading("加载中...");
		},
		complete:function(){
			 hideLoding();
		}
	});
}