$(function(){
	$('.setting_login_out').click(function(){
		showConfirmDialog('确认退出登录？', '确认退出本次登录吗?', '取消', '确认', function() {
			location.href = 'loginout';
		}, null);
	});
	$('#modifyInfo,#modifyPwd').click(function(){
		location.href=''+$(this).attr('id')+'';
	});
});