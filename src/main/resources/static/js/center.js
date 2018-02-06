$(function(){
    var userInfo=null;
	var marginTop = $(window).height()>560?$(window).height()-560:0;
	$('.center_footImg').css("margin-top",marginTop);
	//观看历史
	$('#visitIndex,#history,#msg,#setting,#loginIndex').click(function(){
		location.href=''+$(this).attr('id')+'';
	});
    //修改资料
    $('.center_header').click(function () {
        if(userInfo!=null){
            location.href='modifyInfo';
        }else {
            location.href='loginIndex';
        }
    });
	//初始化加载
    	$.ajax({
			url:"/user/login/getuserInfo",
            type : "post",
            dataType : 'json',
			success:function (jsonObj) {
                userInfo=jsonObj.data;
                if (userInfo!=null) {
                    $('#loginIndex').unbind('click').css({
                        'background': 'none',
                        'color': 'white'
                    }).text(userInfo.name);
                    if (userInfo.headPortrait != null) {
                        $('.center_header').attr('src', userInfo.headPortrait);
                    }
                    $('.center_fish').css('display', 'block');
                    }
            }
		})

});
