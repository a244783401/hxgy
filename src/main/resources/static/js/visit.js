$(function(){
    //初始化页面
    initUI();
    // 切换 简介 目录  评价view  
});

function initUI(){
	excuteAjax('/video/allcourse_info', {'periodNum':44}, function(jsonObj) {
		console.log(jsonObj)
		if (jsonObj.status !=0 ) {
			showSingleDialogWithContent(jsonObj.message, null);
		} else {
			var itemStr = '',wrapStr =  '';
			var courseFlag = [];
			var descList = jsonObj.data
			//加载选择项
			if (descList!=null && descList.length>0) {
				var num = 0;
				for (var i = 0; i < descList.length; i++) {					
					if (descList[i].recommend!=1) {
						if (num==0) {
							itemStr += '<div class="itemView"  onclick="findAllVideoByCourseId(' + descList[i].id  + ')" num="'+num+'"><span class="itemFont fontSelect">'+descList[i].courseName+'</span></div>';
						}else{
							itemStr += '<div class="itemView"onclick="findAllVideoByCourseId(' + descList[i].id  + ')"><span class="itemFont">'+descList[i].courseName+'</span></div>';
						}
						num++;
						wrapStr += '<div class="swiper-slide" style="overflow: hidden;text-align:center;" id="'+descList[i].forcrowd+'"></div>';
						courseFlag.push({'code':descList[i].coursecode,'enable':descList[i].enable})
					}
				}
				$('#midItems').html(itemStr);
				$('.swiper-wrapper').html(wrapStr);
				// 初始化横向滑动
			    var mySwiper = new Swiper ('.swiper-container', {
			        direction: 'horizontal',
			        loop: false,
			        // 如果需要滚动条
			        onSlideChangeEnd: function(swiper){
			            console.log(swiper.activeIndex);
			            $(".itemFont").removeClass("fontSelect");
			            $(".itemView").each(function() {
			            	if ($(this).attr("num")==swiper.activeIndex) {
			            		$(this).find(".itemFont").first().addClass("fontSelect");
							}
			            });
			        }
			    });
			    $(".itemView").click(function(){
			        $(".itemFont").removeClass("fontSelect");
			        $(this).find(".itemFont").first().addClass("fontSelect");
			        mySwiper.slideTo($(this).attr("num"), 300, false);
			    });
			    findAllVideoByCourseId();
                ////查询视频
                //for (var i = 0; i < courseFlag.length; i++) {
			    	//excuteAjax('videoListByCode', {'code':courseFlag[i].code,'enable':courseFlag[i].enable}, function(jsonObj) {
				//		if (jsonObj.status!=1) {
				//			showSingleDialogWithContent(jsonObj.msg , null);
				//		}else{
				//		    //查询视频列表ajax
				//			var videoList= jsonObj.data;
				//			var tempStr = '';
				//			if (jsonObj.enable=='true'&&videoList!=null&&videoList.length>0) {
				//				for (var j = 0; j < videoList.length; j++) {
				//					tempStr+='<div class="visit_courseList" videoId="'+videoList[j].id+'">'+
				//			        '<img src="'+videoList[j].coverurl+'"  class="visit_courseList_img" />'+
				//			        '<div class="visit_courseList_text">'+
				//			            '<div class="visit_courseList_title" style="text-align:left;">'+videoList[j].name+'</div>'+
				//			            '<div class="visit_courseList_author" style="text-align:left;">主讲人：<span>'+videoList[j].authorName+'</span></div>'+
				//			            '<div class="visit_courseList_info">'+
				//			                '<img src="/psychologyWeb/images/visit/visit_eye.png"/><span class="visit_courseList_eye">'+videoList[j].viewNum+'</span>'+
				//			                '<img src="/psychologyWeb/images/visit/visit_heart.png" style="margin-left:10px;"/><span class="visit_courseList_heart" >'+videoList[j].praisenum+'</span>'+
				//			                '<div class="visit_courseList_date" style="">'+videoList[j].createdatestr+'</div>'+
				//			            '</div>'+
				//			        '</div>'+
				//			    '</div>';
				//				}
				//				tempStr+='<div class="visit_courseList_end">暂时只有这么多</div>';
				//				$('#'+jsonObj.courseCode).html(tempStr);
				//				$('.visit_courseList').click(function(){
				//					location.href='videoPlay?videoId='+$(this).attr('videoId');
				//				});
				//			    $('.visit_courseList_text').width($(window).width()-170);
				//			    $('.visit_courseList_date').width($(window).width()-170-100);
				//			    //enable 为 false 或 查询出的视频列表为空  则显示 未开课
				//			}else{
				//				tempStr+='<img src="/psychologyWeb/images/visit/visit_sun.png" class="visit_null_sun" />'+
				//			    '<div class="visit_null_text">课程还没有开始哦</div>'+
				//			    '<div class="visit_null_text">耐心等待下下~</div>'
				//			    $('#'+jsonObj.courseCode).html(tempStr);
				//				$('.visit_courseList_text').width($(window).width()-170);
				//			    $('.visit_courseList_date').width($(window).width()-170-100);
				//			}
				//		}
				//	});
				//}
			}
		}
	});
}
/**
 * 根据分类查询视频
 * @param data
 */
function findAllVideoByCourseId(id){
	if (!id){
		id = null;
	}
	excuteAjax("/video/find_all_video",{'courseId':id}, function (result) {
		if (result.status != 0){
			showSingleDialogWithContent(result.message, null);
	}else{
			var videoList= result.data;
			var tempStr = '';
			if (videoList!=null&&videoList.length>0) {
				for (var j = 0; j < videoList.length; j++) {
					tempStr+='<div class="visit_courseList" courseId = "'+videoList[j].courseId+'" videoId="'+videoList[j].id+'">'+
						'<img src="'+videoList[j].coverurl+'"  class="visit_courseList_img" />'+
						'<div class="visit_courseList_text">'+
						'<div class="visit_courseList_title" style="text-align:left;">'+videoList[j].name+'</div>'+
						'<div class="visit_courseList_author" style="text-align:left;">主讲人：<span>'+videoList[j].authorName+'</span></div>'+
						'<div class="visit_courseList_info">'+
						'<img src="/static/images/visit/visit_eye.png"/><span class="visit_courseList_eye">'+videoList[j].viewNum+'</span>'+
						'<img src="/static/images/visit/visit_heart.png" style="margin-left:10px;"/><span class="visit_courseList_heart" >'+videoList[j].praiseNum+'</span>'+
						'<div class="visit_courseList_date" style="">'+getMyDate(videoList[j].createdatestr)+'</div>'+
						'</div>'+
						'</div>'+
						'</div>';
				}
				tempStr+='<div class="visit_courseList_end">暂时只有这么多</div>';
				$('.swiper-wrappersssssss').html(tempStr);
				$('.visit_courseList').click(function(){
					excuteAjax("/video/video_play", {'videoId':$(this).attr('videoId'),'courseId':$(this).attr('courseId')},function(result){
							if (result.status != 0){
								if (result.status == 11){
									showConfirmDialog('需要购买','前往购买？？','再想想','前往购买',function(){
										location.href='/index';
									})
								}
								if (result.status == 10){
									showConfirmDialog('前往登陆','前往登陆？？','再想想','前往登陆',function(){
										location.href='/myCenterIndex';
									})
								}
								else showSingleDialogWithContent(result.message, null);
							}else{
								location.href=result.data
							}
					})
				});
				$('.visit_courseList_text').width($(window).width()-170);
				$('.visit_courseList_date').width($(window).width()-170-100);
				//enable 为 false 或 查询出的视频列表为空  则显示 未开课
			}else{
				tempStr+='<img src="/static/images/visit/visit_sun.png" class="visit_null_sun" />'+
					'<div class="visit_null_text">课程还没有开始哦</div>'+
					'<div class="visit_null_text">耐心等待下下~</div>'
				$('.swiper-wrapper').html(tempStr);
				$('.visit_courseList_text').width($(window).width()-170);
				$('.visit_courseList_date').width($(window).width()-170-100);
			}
		}
	})
}
function initZXKC(data){
	var htmlStr = "";
	for (var i = 0; i <8 ; i++) {
		htmlStr+='<div class="visit_courseList">'+
        '<img src="/static/images/visit/psy_banner.png"  class="visit_courseList_img" />'+
        '<div class="visit_courseList_text">'+
            '<div class="visit_courseList_title">人类对心理现象的认识过程和心理疾病的预防</div>'+
            '<div class="visit_courseList_author">主讲人：<span>周汝英</span></div>'+
            '<div class="visit_courseList_info">'+
                '<img src="/static/images/visit/visit_eye.png"/><span class="visit_courseList_eye">126</span>'+
                '<img src="/static/images/visit/visit_heart.png" style="margin-left:10px;"/><span class="visit_courseList_heart" >98</span>'+
                '<div class="visit_courseList_date" style="">2017-03-23</div>'+
            '</div>'+
        '</div>'+
    '</div>';
	}
	htmlStr+='<div class="visit_courseList_end">暂时只有这么多</div>';
	$("#zxkc").append($(htmlStr));

}


function initLWZD(data){
	//论文指导
    var htmlStr = '';
    if (data==undefined||data.length==0) {
		htmlStr+='<img src="/static/images/visit/visit_sun.png" class="visit_null_sun" />'+
	    '<div class="visit_null_text">课程还没有开始哦</div>'+
	    '<div class="visit_null_text">耐心等待下下~</div>'
	}else if(data.isPay=="0"){
		htmlStr+='<img src="/psychologyWeb/images/visit/visit_cloud.png" class="visit_null_sun" style="width:100px;" />'+
            '<div class="visit_null_text" style="margin-top:10px;">你都没有报名哎~</div>'+
            '<div class="visit_null_button">报名去</div>';
	}else{
		
	}
    $("#lwzd").append($(htmlStr));
}
