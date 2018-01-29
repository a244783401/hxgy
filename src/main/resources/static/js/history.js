$(function(){ 
	//加载视频列表
	excuteAjax('/video/history','', function(result) {
	var historyStr = '';
	if (jsonObj.status != 0) {
		showSingleDialogWithContent(jsonObj.msg, null);
	}else {			
		var historyList = jsonObj.historyList;
	for (var i = 0; i < jsonObj.data.length; i++) {
		var time=timeFor(jsonObj.data[i].videocurrenttime);
		historyStr+='<div class="history_list_item">'+
            		'<div class="history_list_circle" action="0" id="'+i+'">'+
            			'<img src="images/history/circle.png"/>'+
            		'</div>'+
            		'<div class="history_list_cover">'+
            			'<div class="history_list_coverImg" >'+'<img src="'+jsonObj.data[i].coverurl+'"/>'+
            				'<div class="history_list_coverTime">'+jsonObj.data[i].videotime+'</div>'+
            			'</div>'+              
            		'</div>'+
            		'<div class="history_list_content">'+
            			'<div class="history_list_title">'+jsonObj.data[i].name+'</div>'+
            			'<div class="history_list_author">'+jsonObj.data[i].authorname+'</div>'+
            			'<div class="history_list_time">'+
            				'<img src="images/history/clock.png"/><span>已观看时间：'+time+'</span>'+
            			'</div>'+
            		'</div>'+
            	'</div>';
	}
	}
	$('.history_list').html(historyStr);	
	
	$('.history_list_item').click(function(){
		location.href='videoPlay';
	});
	});
	
	//点击删除图标
	$('.history_delete').click(function(){
		var action = $(this).attr('action');
		if (action=='0') {//点击删除图标
			$('.history_list_circle').css('display','block');
			$('.history_list_content').css('width','52%');
			$('.history_delete').html('取消');
			$(this).attr('action','1');
			$('.history_last').css('margin-bottom','60px');
			$(".history_footer").css('display','block').animate({bottom:0});
			//单项选择
			$('.history_list_item').unbind().click(function(){				
				var circle = $(this).find('.history_list_circle');
				if (circle.attr('action')=='0') {
					circle.attr('action','1');
					circle.html('<img src="images/history/circle_selected.png"/>')
				}else if(circle.attr('action')=='1'){
					circle.attr('action','0');
					circle.html('<img src="images/history/circle.png"/>');
				}
				var isHas = 0;
				var isNo = 0;
				$('.history_list_item').find('.history_list_circle').each(function(){
					if ($(this).attr('action')=='1') {
						isHas++;
					}else{
						isNo++;
					}					
				});
				$('.history_footer_delete').css('color',(isHas>0?'#ff8580':'#999')).attr('action',(isHas>0?'1':'0'));
				//如果全被选中
				if (isNo==0) {
					$('.history_footer_selectAll').attr('action','1');
					$('.history_footer_all_img').attr('src','images/history/circle_selected.png');
					$('.history_footer_all_text').text('全不选');
				}else{
					$('.history_footer_selectAll').attr('action','0');
					$('.history_footer_all_img').attr('src','images/history/circle.png');
					$('.history_footer_all_text').text('全选');
				}
			});			
		}else if(action=='1'){//点击取消
			//清空选择项状态
			$('.history_list_item').find('.history_list_circle').each(function(){
				$(this).attr('action','0');
				$(this).html('<img src="images/history/circle.png"/>');
			});
			$('.history_footer_selectAll').attr('action','0');
			$('.history_footer_all_img').attr('src','images/history/circle.png');
			$('.history_footer_all_text').text('全选');
			$('.history_footer_delete').css('color','#999').attr('action','0');
			
			$('.history_list_item').unbind().click(function(){
				location.href='videoPlay';
			});			
			$('.history_list_circle').css('display','none');
			$('.history_list_content').css('width','60%');
			$('.history_delete').html('<img src="images/history/delete.png" />');
			$(this).attr('action','0');
			$('.history_last').css('margin-bottom','10px');
			$(".history_footer").css('display','block').animate({bottom:-50});  
		}
	});
	//全选按钮
	$('.history_footer_selectAll').click(function(){
		var action = $(this).attr('action');
		if (action=='0') {
			$(this).attr('action','1');
			$('.history_footer_all_img').attr('src','images/history/circle_selected.png');
			$('.history_list_item').find('.history_list_circle').each(function(){
				$(this).attr('action','1');
				$(this).html('<img src="images/history/circle_selected.png"/>')
			});
			$('.history_footer_all_text').text('全不选');
			$('.history_footer_delete').css('color','#ff8580').attr('action','1');
		}else if(action=='1'){
			$(this).attr('action','0');
			$('.history_footer_all_img').attr('src','images/history/circle.png');
			$('.history_list_item').find('.history_list_circle').each(function(){
				$(this).attr('action','0');
				$(this).html('<img src="images/history/circle.png"/>')
			});
			$('.history_footer_all_text').text('全选');
			$('.history_footer_delete').css('color','#999').attr('action','0');
		}
	});
	//删除
	$('.history_footer_delete').click(function(){
		var action = $(this).attr('action');
		var ids = '';
		var num = 0;
		if (action=='1') {
			$('.history_list_item').find('.history_list_circle').each(function(){
				if ($(this).attr('action')=='1') {
					ids+=$(this).attr('id')+',';
					num++;
				}
			});
			showConfirmDialog('确认删除','选中'+num+'条历史记录，确认删除？','再想想','确定删除',function(){
				excuteAjax('',null,function(jsonObj){
					
				});
			},null);
		}
		
	});
	
});
		function timeFor(s) {
		    var t = "";
		    if (s > -1) {
		        hour = Math.floor(s / 3600);
		        min = Math.floor(s / 60) % 60;
		        sec = s % 60;
		        day = parseInt(hour / 24);
		        if (day > 0) {
		            hour = hour - 24 * day;
		            t += day + ":" + hour + ":";
		        } else {
		            t += hour + ":";
		        }
		        if (min < 10) {
		            t += "0";
		        }
		        t += min + ":";
		        if (sec < 10) {
		            t += "0";
		        }
		        t += sec;
		    }
		    return t;
		}
