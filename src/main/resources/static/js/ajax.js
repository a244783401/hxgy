function excuteAjax(url, data, fun) {
	$.ajax({
		url : url,
		type : "post",
		data : JSON.stringify(data),
		dataType : "json",
		contentType : "application/json",
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