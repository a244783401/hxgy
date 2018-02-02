$(function(){  
	$('#signupIndex').click(function(){
		location.href = "user/course/sign_up"
	});
	$('#visitIndex').click(function () {
		location.href="video/allcourse"
	})
	$('#share,#free_course').click(function(){
		showSingleDialogWithContent('暂未开放',null);
	});
	$('#center_intro').click(function(){
		location.href="centerIntro";
	});

});


