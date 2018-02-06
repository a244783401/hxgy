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
				if(courseList!=null&&courseList.length>0){
					for (var i = 0; i < courseList.length; i++) {
						if (courseList[i].recommend==1) {
							$('#allCourse_price').text(courseList[i].coursePrice);
							$('#aEnroll').attr("index",courseList[i].id)
							$('#aDetil').attr("index",courseList[i].id)
						}else{
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
								'<a id="sign_up" onclick="clickThing(' + courseList[i].id  + ')" class="sign_singleCourse_btn_sign">报名</a>'+
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
	clickThing($(this).attr("index"))
})

function clickThing(id){
	excuteAjax("/user/course/signup_info",{'id':id},function(res){
		if (res.status != 0){
			if (res.status == 10){
				showConfirmDialog("还未登陆","前往登陆？","返回","登陆",function(){
					location.href='/myCenterIndex';
				})
			}
			if (res.status == 13){
				showConfirmDialog("温馨提示!","已经购买过该课程，前往观看？","返回","观看",function(){
					location.href='/visitIndex';
				})
			}
			showSingleDialogWithContent(res.message, null);
		}else {
			if (res.data == null){
			location.href="/user/course/signup_info_index?id="+id;
			}else{
				showConfirmDialog("温馨提示!","已经报名该课程，前往支付？","返回","支付",function(){
					location.href="/video/order?orderId="+res.data.id
				})
			}
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