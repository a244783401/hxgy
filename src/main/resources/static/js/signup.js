$(function(){
	//加载课程信息
	start();
	var marginTop = $(window).height()>560?$(window).height()-560:20;
	$('.center_footImg').css("margin-top",marginTop);
	
	$('#signup').click(function(){
		location.href="signupIndex";
	});	
	
//	$('.sign_allCourse_btn_sign,.sign_singleCourse_btn_sign').click(function(){
//		location.href="signupInfo";
//	});

	function start(){
		excuteAjax('/user/course/sign_list', {'periodNum':'44'}, function(jsonObj) {
			if (jsonObj.status!=0) {
				showSingleDialogWithContent(jsonObj.message, null);
			} else {

				var singleStr = '',totalStr = '';
				var courseList =  jsonObj.data;
				console.log(courseList);
				if(courseList!=null&&courseList.length>0){
					for (var i = 0; i < courseList.length; i++) {
						if (courseList[i].recommend==1) {
							$('#allCourse_price').text(courseList[i].coursePrice);
							$('#aEnroll').attr("index",courseList[i].id);
						}else{
							totalStr+= '<span>'+courseList[i].courseName+'</span>'+
								'<img src="images/signup/add.png"/>';
							singleStr += '<div class="signup_singleCourse" style="margin-top:10px;">'+
								'<div class="sign_singleCourse_price">'+
								'<span style="font-size:17px;color:#444444;">'+courseList[i].courseName+'</span>'+
								'<span style="font-size:16px;">￥</span>'+courseList[i].coursePrice+''+
								'</div>'+
								'<div class="sign_singleCourse_button">'+
								'<a id="sign_up" index=' + courseList[i].id + ' class="sign_singleCourse_btn_sign">报名</a>'+
								'<a class="sign_singleCourse_btn_desc">详情</a>'+
								'</div>'+
								'</div>';
						}
					}
					var imgStr = '<img src="images/signup/add.png"/>';
					$('.sign_allCourseDvi_title').html(totalStr.substring(0,totalStr.length-imgStr.length));
					$('.signup_singleCourseContent').html(singleStr);
				}
				clickThing();
			}
		});
	}

	/**
	 * 点击报名
	 */
	function clickThing(){
	$('.sign_singleCourse_btn_sign').click(console.log($(this).attr("index")));
	$('sign_allCourse_btn_sign').click(console.log($(this).index));
		function userSignup(){
			excuteAjax("/user/course/signup_info", {'id':this.index})
		}
	}
});


