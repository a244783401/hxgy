$(function(){
	//加载当前推送期数信息
	//加载课程信息
	var periodNum = $("#periodNum").text()
	console.log(periodNum);
	start();
	var marginTop = $(window).height()>560?$(window).height()-560:20;
	$('.center_footImg').css("margin-top",marginTop);
	
	$('#signup').click(function(){
		location.href="signupIndex";
	});	
	
	function start(){
		excuteAjax('/user/course/sign_list', {'periodNum':periodNum}, function(jsonObj) {
			if (jsonObj.status!=0) {
				showSingleDialogWithContent(jsonObj.message, null);
			} else {

				var singleStr = '',totalStr = '';
				var courseList =  jsonObj.data;
				if(courseList!=null&&courseList.length>0){
					for (var i = 0; i < courseList.length; i++) {
						if (courseList[i].stand == 1 && courseList[i].enable == 1) {
							$('#allCourse_price').text(courseList[i].coursePrice);
							$('#aEnroll').attr("index",courseList[i].id)
							$('#aEnroll').attr("action",courseList[i].courseCategoryId)
							$('#aDetil').attr("index",courseList[i].id)
						}else if(courseList[i].enable == 1){
							totalStr+= '<span>'+courseList[i].courseName+'</span>'+
								'<img src="/static/images/signup/add.png"/>';
							singleStr += '<div class="signup_singleCourse" style="margin-top:10px;">'+
								'<div class="sign_singleCourse_price">'+
								'<span style="font-size:17px;color:#444444;">'+courseList[i].courseName+'</span>'+
									'<div>'+
								'<span style="font-size:16px;">￥</span>'+courseList[i].coursePrice+''+
									'</div>'+
								'</div>'+
								'<div class="sign_singleCourse_button">'+
								'<a id="sign_up" onclick="clickThing(' + courseList[i].id  +','+courseList[i].courseCategoryId+ ')" class="sign_singleCourse_btn_sign">报名</a>'+
								'<a onclick="getDetilInfo(' + courseList[i].id  + ')" class="sign_singleCourse_btn_desc">详情</a>'+
								'</div>'+
								'</div>';
						}
					}
					var imgStr = '<img src="/static/images/signup/add.png"/>';
					$('.sign_allCourseDvi_title').html(totalStr.substring(0,totalStr.length-imgStr.length));
					$('.signup_singleCourseContent').html(singleStr);
				}
			}
		});
	}

});
/**
 * 点击报名
 */
$("#aEnroll").on("click",function(){
	clickThing($(this).attr("index"),$(this).attr("action"))
})

function clickThing(courseId,courseCategoryId){
	excuteAjax("/user/course/signup_info",{'courseId':courseId},function(res){
		if (res.status != 0){
			judgeStatus(res.status,res.data);
		}else {
			location.href="/user/course/signup_info_index?courseId="+courseId+"&courseCategoryId="+courseCategoryId;
		}
	})
}
/**
 * 点击详情
 */
$('#aDetil').on("click", function () {
	getDetilInfo($(this).attr("index"))
})
function getDetilInfo(id){
	excuteAjax("/user/course/course_detil",{"courseId":id}, function (res) {
		if (res.status != 0){
			showSingleDialogWithContent(res.message,null);
		}else{
			showSingleDialogWithContent(
				'<div><label>课程名字：'+res.data.courseName+'</label><br>'+
				'<label>适宜人群：'+res.data.forCrowd+'</label><br>'+
				'<label>作者名字：'+res.data.authorName+'</label><br>'+
				'<label>课程价格：'+res.data.coursePrice+'</label><br></div>',null);
		}
	})
}