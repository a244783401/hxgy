$(function(){
	$('.setting_login_out').click(function(){
		showConfirmDialog('确认退出登录？', '确认退出本次登录吗?', '取消', '确认', function() {
            excuteAjax('/user/login/logout',null,function (jsonObj) {
            	if(jsonObj.status==1) {
                    showSingleDialog("温馨提示",jsonObj.message,"");
                    setTimeout(function() {
                        location.href= 'loginIndex';
                    }, 1500)
                }else {
                    toastSucceed(jsonObj.message);
                    setTimeout(function() {
                        location.href= 'myCenterIndex';
                    }, 1500)
                }
            })
		}, null);
	});
	$('#modifyInfo,#modifyPwd').click(function(){
		location.href=''+$(this).attr('id')+'';
	});

});