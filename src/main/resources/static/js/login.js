$(function(){  
	$('.login_button').click(function(){
		var data = {'phoneno':$('#phoneno').val(),
					'password':$('#password').val(),
					"channel" : "2","deviceId" : ""};
		excuteAjax('/user/login/userLogin_psy', data, function(jsonObj) {
			if (jsonObj.status==1) {
				showSingleDialogWithContent(jsonObj.message,null);
			} else {
				toastSucceed(jsonObj.message);
				setTimeout(function() {
					location.href= 'myCenterIndex';
				}, 1500)
			}
		});
	});
	$('.login_thirdImg_wechat').click(function () {
		location.href='/wechat/getcode';

    })
});




