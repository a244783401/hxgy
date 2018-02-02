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
function getMyDate(str) {
	var oDate = new Date(str),
		oYear = oDate.getFullYear(),
		oMonth = oDate.getMonth() + 1,
		oDay = oDate.getDate(),
		oHour = oDate.getHours(),
		oMin = oDate.getMinutes(),
		oSen = oDate.getSeconds(),
		oTime = oYear + '-' + getzf(oMonth) + '-' + getzf(oDay);//最后拼接时间
	return oTime;
	function getzf(num) {
		if (parseInt(num) < 10) {
			num = '0' + num;
		}
		return num;
	}
}
function MillisecondToDate(msd) {
	var time = parseFloat(msd) /1000;
	if (null!= time &&""!= time) {
		if (time >60&& time <60*60) {
			time = parseInt(time /60.0) +"分钟"+ parseInt((parseFloat(time /60.0) -
					parseInt(time /60.0)) *60) +"秒";
		}else if (time >=60*60&& time <60*60*24) {
			time = parseInt(time /3600.0) +"小时"+ parseInt((parseFloat(time /3600.0) -
					parseInt(time /3600.0)) *60) +"分钟"+
				parseInt((parseFloat((parseFloat(time /3600.0) - parseInt(time /3600.0)) *60) -
					parseInt((parseFloat(time /3600.0) - parseInt(time /3600.0)) *60)) *60) +"秒";
		}else {
			time = parseInt(time) +"秒";
		}
	}else{
		time = "0 时 0 分0 秒";
	}
	return time;

}