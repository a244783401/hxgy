$(function(){  
	$('.weui-tabbar__item').click(function(){
		if ($(this).hasClass('item_act')) {
			return;
		}else{
			location.href=''+$(this).attr('type')+'';
		}
	});
});


