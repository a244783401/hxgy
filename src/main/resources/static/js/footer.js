$(function(){  
	$('.weui-tabbar__item').click(function(){
		if ($(this).hasClass('item_act')) {
			return;
		}else{
			console.log($(this).attr("type"))
			location.href='/'+$(this).attr("type");
		}
	});
});


