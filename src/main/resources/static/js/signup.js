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
		console.info("ajax start")
		excuteAjax('/user/course/sign_list', {'periodNum':'44'}, function(jsonObj) {
			if (jsonObj.code!=1) {
				showSingleDialogWithContent(jsonObj.message, null);
			} else {
				var singleStr = '',totalStr = '';
				var courseList =  jsonObj.data;
				if(courseList!=null&&courseList.length>0){
					for (var i = 0; i < courseList.length; i++) {
						if (courseList[i].isrecommend==1) {
							$('#allCourse_price').text(courseList[i].courseprice);
							$('#aEnroll').attr("href",'signupInfo?coursecategid='+courseList[i].coursecategid
								+'&courseid='+ courseList[i].id);
						}else{
							totalStr+= '<span>'+courseList[i].coursename+'</span>'+
								'<img src="images/signup/add.png"/>';
							singleStr += '<div class="signup_singleCourse" style="margin-top:10px;">'+
								'<div class="sign_singleCourse_price">'+
								'<span style="font-size:17px;color:#444444;">'+courseList[i].coursename+'</span>'+
								'<span style="font-size:16px;">￥</span>'+courseList[i].courseprice+''+
								'</div>'+
								'<div class="sign_singleCourse_button">'+
								'<a id="sign_up" href="signupInfo?coursecategid='+courseList[i].coursecategid
								+'&courseid='+ courseList[i].id +'" class="sign_singleCourse_btn_sign">报名</a>'+
								'<a class="sign_singleCourse_btn_desc">详情</a>'+
								'</div>'+
								'</div>';
						}
					}
					var imgStr = '<img src="images/signup/add.png"/>';
					$('.sign_allCourseDvi_title').html(totalStr.substring(0,totalStr.length-imgStr.length));
					$('.signup_singleCourseContent').html(singleStr);
				}


			}
		});
	}


});


