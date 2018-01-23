$(function(){ 
	//初始化页面
	var htmlStr = '';
	for (var i = 0; i < 5; i++) {
		htmlStr+='<div class="msg_item">'+
            		'<div class="msg_item_header">'+
            			'<div class="msg_item_title">系统维护通知</div>'+
            			'<div class="msg_item_date" style="">2017-05-03</div>'+
            		'</div>'+
            		'<div class="msg_item_content">系统维护通知系统维护通知系统维护通知系统维护通知系统维护通知系统维护通知系统维护通知</div>'+
            	 '</div>';
	}
	$('.msg_items').html(htmlStr);	
	$('.msg_item').click(function(){
		alert()
	});
});