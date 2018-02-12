$(function(){
	$('.order_submit').click(function(){
        showSingleDialogWithContent("暂不支持！！！",null);
        setTimeout(function () {
            location.href="/index"
        },2000)
    })
});
