$(function() {
	getHistory(videoId);
	Media = document.getElementById("videoPlayer");
	// 加载视频详情	
	excuteAjax('getVideoById', {
		'videoId' : videoId
	}, function(jsonObj) {
		if (jsonObj.code != 1) {
			showSingleDialogWithContent(jsonObj.msg, null);
		} else {
			var video = jsonObj.video;
			var user = jsonObj.userInfo;
			$('#videoPlayer').attr({
				'src' : video.url,
				'poster' : video.coverurl
			});
			$('.video_lookNum').text(video.viewnum);
			$('.video_date').text(video.createdatestr);
			$('.video_collection').attr(jsonObj.isPraise == '1' ? {
				'act' : 'yes',
				'src' : 'images/video/collection_selected.png'
			} : {
				'act' : 'no',
				'src' : 'images/video/collection.png'
			});
			$('.video_info_title').text(video.name);
			$('.video_info_intro').text(
					'简介：'
							+ ((video.videodesc == null) ? '暂无'
									: video.videodesc));
			$('.video_author').text('主讲人：' + video.authorname);
			// 设置评论按钮属性
			$('.video_comment_publish').attr({
				'videoId' : video.id,
				'courseId' : video.courseid,
				'userId' : user.userId,
				'userHead' : user.headPortrait,
				'userName' : user.name
			})
		}
	});

	
	// 加载评论列表
	excuteAjax(
			'getCommentList',
			{
				'videoId' : videoId
			},
			function(jsonObj) {
				var commentStr = '';
				if (jsonObj.code != 1) {
					showSingleDialogWithContent(jsonObj.msg, null);
				} else {
					var commentList = jsonObj.commentList;
					$('.video_commentNum').text(commentList.length);
					if (commentList.length == 0) {
						commentStr = '<div class="video_nocomment">'
								+ '<img src="/psychologyWeb/images/video/nocomment.png" />'
								+ '<div class="video_nocomment_text">还没有评论，赶紧抢沙发吧~</div>'
								+ '</div>';
					} else {
						for (var i = 0; i < commentList.length; i++) {
							commentStr += '<div class="video_comment_item">'
									+ '<img src="'
									+ (commentList[i].userhead == null ? ''
											: commentList[i].userhead)
									+ '"  onerror="this.src=\'\/psychologyWeb\/images\/video\/defaulthead.png\'"/>'
									+ '<div class="video_comment_content">'
									+ '<div class="video_comment_account">'
									+ (commentList[i].username == null ? '匿名用户'
											: commentList[i].username)
									+ '</div>'
									+ '<div class="video_comment_date">2017-06-02 15:50</div>'
									+ '<div class="video_comment_text">'
									+ commentList[i].commentdesc + '</div>'
									+ '</div>' + '</div>'
						}
					}
					$('.video_commentList').html(commentStr);
					$('.video_comment_content').width($(window).width() - 95);
				}
			});

	// 滑动固定评论栏
	var navH = $(".video_commentDiv").offset().top - 200;
	$(window).scroll(function() {
		var scroH = $(this).scrollTop();
		if (scroH >= navH) {
			$(".video_commentDiv").css({
				"position" : "fixed",
				"top" : 200
			});
		} else if (scroH < navH) {
			$(".video_commentDiv").css({
				"position" : "static"
			});
		}
	});
	// 展开、收起
	$('.video_info_handler').click(function() {
		var action = $(this).attr('act');
		if (action == 'down') {
			$('.video_info_intro').removeClass('down');
			$(this).attr({
				'src' : '/psychologyWeb/images/video/up.png',
				'act' : 'up'
			});
		} else if (action == 'up') {
			$('.video_info_intro').addClass('down');
			$(this).attr({
				'src' : '/psychologyWeb/images/video/down.png',
				'act' : 'down'
			});
		}
	});

	// 收藏
	$('.video_collection').click(function() {
		var action = $(this).attr('act');
		if (action == 'no') {
			$(this).attr({
				'src' : '/psychologyWeb/images/video/collection_selected.png',
				'act' : 'yes'
			});
		} else if (action == 'yes') {
			$(this).attr({
				'src' : '/psychologyWeb/images/video/collection.png',
				'act' : 'no'
			});
		}
	});
	// 展开评论
	$('.video_comment_do').click(function() {
		$('.video_content').css('display', 'none');
		$(".video_comment_editor").css('display', 'block').animate({
			left : 0
		});
	});
	// 快速评论
	$('.video_quick_content').click(function() {
		$('.video_comment_textarea').text($(this).text());
	});
	// 评论
	$('.video_comment_publish').click(function() {
		var data = {
			'videoId' : $(this).attr('videoId'),
			'courseId' : $(this).attr('courseId'),
			'commentDesc' : $('.video_comment_textarea').text(),
			'userHead' : $(this).attr('userHead'),
			'userName' : $(this).attr('userName')
		}
		excuteAjax('addComment', data, function(jsonObj) {

		});
	});
	// 取消评论
	$('.video_comment_cancel').click(function() {
		$(".video_comment_editor").animate({
			left : '100%'
		}).css('display', 'none');
		$('.video_content').css('display', 'block');
	});
});

//添加历史记录
function addWatchHistory() {
	var time = Math.round(Media.currentTime);
	var data = {
		'videoid' : videoId,
//		'courseId' : video.courseid,
		'videocurrenttime' : time
	}
	excuteAjax('addHistory',data, function(jsonObj) {

	});
}

function addHistory() {
//	event.returnValue="确定离开当前页面吗？";
	if (Media.currentTime != 0 && Media.currentTime != videoCurrentTime) {
		addWatchHistory();
	} else {
		console.log("未观看,无需记录");
	}
}



function getHistory(videoId) {
    var data = {
        videoId: videoId
    }
    excuteAjax('getHistory', data , function(res) {
            if (res.code == '1') {
                var history = res.data;
                //alert(JSON.stringify(history))
                if (history) {
                    videoCurrentTime = history.videocurrenttime;
                    Media.currentTime = videoCurrentTime;
                }
            } else {
                console.log(res.msg);
            }
    });
}

