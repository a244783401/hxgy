$(function(){
	//加载课程信息
	start();
	var marginTop = $(window).height()>560?$(window).height()-560:20;
	$('.center_footImg').css("margin-top",marginTop);
	
	$('#signup').click(function(){
		location.href="signupIndex";
	});	
	
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
							$('#aEnroll').attr("index",courseList[i].id)
						}else{
							totalStr+= '<span>'+courseList[i].courseName+'</span>'+
								'<img src="images/signup/add.png"/>';
							singleStr += '<div class="signup_singleCourse" style="margin-top:10px;">'+
								'<div class="sign_singleCourse_price">'+
								'<span style="font-size:17px;color:#444444;">'+courseList[i].courseName+'</span>'+
								'<span style="font-size:16px;">￥</span>'+courseList[i].coursePrice+''+
								'</div>'+
								'<div class="sign_singleCourse_button">'+
								'<a id="sign_up" onclick="clickThing(' + courseList[i].id  + ')" class="sign_singleCourse_btn_sign">报名</a>'+
								'<a onclick="getDetilInfo(' + courseList[i].id  + ')" class="sign_singleCourse_btn_desc">详情</a>'+
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
/**
 * 点击报名
 */
$("#aEnroll").on("click",function(){
	clickThing($(this).attr("index"))
})
function clickThing(id){
	excuteAjax("/user/course/signup_info",{'id':id},function(jsonObj){
		if (jsonObj.status != 0){
			showSingleDialogWithContent(jsonObj.message, null);
		}else {
			location.href="/user/course/signup_info_index";
		}
	})
}
/**
 * 点击详情
 */
$('#aDetil').on("click", function () {
	getDetilInfo($("#aEnroll").attr("index"))
})
function getDetilInfo(id){

}