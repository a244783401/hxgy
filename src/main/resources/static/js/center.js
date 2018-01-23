$(function(){  
	var marginTop = $(window).height()>560?$(window).height()-560:0;
	$('.center_footImg').css("margin-top",marginTop);
	
	//观看历史
	$('#visitIndex,#history,#msg,#setting,#loginIndex').click(function(){
		location.href=''+$(this).attr('id')+'';
	});
		
	//初始化加载
	if (userInfo!=null) {
		$('#loginIndex').unbind('click').css({'background':'none','color':'white'}).text(userInfo.name);
		if (userInfo.headPortrait!=null) {
			$('.center_header').attr('src',userInfo.headPortrait);
		}
		$('.center_fish').css('display','block');
	}
});
