function validate(formData, jqForm, options) {
	var form = jqForm[1];
	if ($("#phoneno").val() == "" || $("#username").val() == "" || $("#birthday").val() == "") {
		showSingleDialogWithContent("姓名,电话号码,生日不能为空！！", null);
		return false;
	}
}
var options;
var isOK;
$(function(){
	if (userInfo!=null||userInfo!=undefined) {
		if(userInfo.headPortrait!=null){
			$('#headUrl').val(userInfo.headPortrait);
			$('#image-hide').attr('src',userInfo.headPortrait);
		}
		$('#phoneno').text(userInfo.phoneno);
		$('#username').val(userInfo.name);
		if(userInfo.sex!=null){	
			$('.span-sex').removeClass('sex-active');
			if (userInfo.sex=='男') {
				$('.male').addClass('sex-active');
			} else {
				$('.female').addClass('sex-active');
			}
			$('#sex').val(userInfo.sex);			
		}else{
			$('#sex').val('男');	
		}
		$('#birthday').val(userInfo.birthday);
	}
	
    $('.sex-group').children().click(function(){
        if ($(this).hasClass('sex-active')) return;
        $(this).addClass('sex-active');
        $(this).siblings().removeClass('sex-active');
        $("#sex").val($(this).text());
    });
    
    $('#file').on('change',function(){
    	var size = $(this)[0].files[0].size;
    	if(size>=1024*1024){
    		$('.loadImg_notice').css({'color':'red','display':'block'}).text('请上传小于1M的图片!');
    		setTimeout(function() {
    			$('.loadImg_notice').css('display','none');
    		}, 3000);
    	return;
    	}
    	$('.weui-progress__bar').css('display','block');
    	$('.js_progress').animate({'width':'80%'},3000);	
    	options = {
    		url:"/user/course/updateHeadImg",
    		type:"post",
    		dataType:"json",
    		success:function(res){
    			if (res.status==0) {
    				$('.js_progress').animate({'width':'100%'});
    				setTimeout(function() {
    				$('.js_progress').animate({'width':'0'});
					$('.weui-progress__bar').css('display','none');
					$('.loadImg_notice').css({'color':'green','display':'block'}).text('上传成功!');
    				},1000);
    				setTimeout(function() {
    					$('.loadImg_notice').css('display','none');
    				}, 3000);
    				//$('#headUrl').val(res.imgUrl);
    			}else{
					judgeStatus(res.status,res.data);
   			 	}
    		}
    	};
    });
    
    $('#modifySubmit').click(function(){
		$("#detailForm").ajaxSubmit({
			dataType : 'json',
			beforeSubmit: validate,
			success:function(res){
				if (res.status == 0){
						location.href="myCenterIndex"
				}else {
					showSingleDialogWithContent(res.message,null);
				}
			}
		})

    });
});
