$(function(){  
	$('.login_button').click(function(){
		var data = {'phoneno':$('#phoneno').val(),
					'password':$('#password').val(),
					"channel" : "2","deviceId" : ""}
		excuteAjax('login', data, function(jsonObj) {
			if (jsonObj.code!=1) {
				showSingleDialogWithContent(jsonObj.msg,null);
			} else {
				toastSucceed(jsonObj.msg);
				setTimeout(function() {
					location.href= 'myCenterIndex';
				}, 1500)
			}
		});
	});
});


