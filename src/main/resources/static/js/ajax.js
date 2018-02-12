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

function judgeStatus(status,data){
	if (status == 10){
		console.log("judge")
		showConfirmDialog("还未登陆","前往登陆？","返回","登陆",function(){
			location.href='/loginIndex';
		})
	}
	if (status == 13){
		showConfirmDialog("温馨提示!","已经购买过该课程，前往观看？","返回","观看",function(){
			location.href='/visitIndex';
		})
	}
	if (status == 11){
		showConfirmDialog('需要购买','前往购买？？','再想想','前往购买',function(){
			location.href='/user/course/sign_up';
		})
	}
	if (status == 2){
		showSingleDialogWithContent("参数错误!!",null);
	}
	if (status == 12){
		showConfirmDialog("温馨提示!","已经报名该课程，前往支付？","返回","支付",function(){
			location.href="/video/order?orderId="+data.id
		})
	}
}
function timestampToTime(timestamp) {
	var date = new Date(timestamp);
	Y = date.getFullYear() + '-';
	M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
	D = date.getDate() + ' ';
	h = date.getHours() + ':';
	m = date.getMinutes() + ':';
	s = date.getSeconds();
	return Y+M+D+h+m+s
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