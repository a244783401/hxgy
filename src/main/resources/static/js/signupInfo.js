function validate(formData, jqForm, options) { 
	    var form = jqForm[0]; 
	    if (!form.username.value || !form.idCard.value || !form.phoneno) { 
	    	showSingleDialogWithContent("姓名、身份证号、联系方式不能为空", null);
	        return false; 
	    } 
	}

function getUrlParam (name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)","i");
        var r = window.location.search.substr(1).match(reg);
        if (r!=null) return unescape(r[2]); return null;
   }


function loadUserDetail() {
	$.ajax({
		url: "getUserDetail",
		dataType: 'json',
		method: 'get',
		success : function(res) {
			if (res.status == 0) {
				var record = res.data;
				var formObj = document.forms[0].elements;
				formObj.cop.value = record.cop;
				formObj.username.value = record.name;
				formObj.idCard.value = record.idCard;
				formObj.profession.value = record.profession;
				formObj.education.value = record.education;
				formObj.phoneno.value = record.phoneno;
				formObj.email.value = record.email;
				formObj.headPortrait.value = record.headPortrait;
				$('#image-hide').attr('src', record.headPortrait);
			} else {
				showSingleDialogWithContent(jsonObj.msg, null);
			}
		}
	})
}

/**
 * 获取参数
 */
$(function() {
	var isOk;
	loadUserDetail();
	$('#courseid').val(getUrlParam('id'));
	$('#courseceducationategid').val(getUrlParam('coursecategid'));
	$('#job,#rtl').click(function() {
		$(this).css('color', '#444');
	});

	//表单提交(两张表单)
 $('.sigunupInfo_submit').click(function() {
		//图片上传
		if (isTrue){
			$('#user_form').ajaxSubmit({
				dataType : 'json',
				success:function(res){
					if (res.status != 0){
						showSingleDialogWithContent(res.message,null)
					}else {
						updateInfo();
					}
				}
			})
		} else{
			showSingleDialogWithContent("所选图片格式大小不对!!",null);
		}
//表单提交
function updateInfo(){
	$('#detailForm').ajaxSubmit({
		dataType : 'json',
		beforeSubmit: validate,
		success : function(res) {
			console.log(res)
			if (res.status == 0) {
				location.href="/video/order?orderId="+res.data.id
			} else {
				showSingleDialogWithContent(res.message, null);
			}
		}
	});
}

        //var options = getImgOptions();
        //$("#user_form").ajaxSubmit(options);
	});



});
/**
 * 判断图片
 * @type {boolean}
 */
    var isTrue = true;
    function fileChange() {
       var size = $('#file')[0].files[0].size;
        console.log(size)
        if (size >= 1024 * 1024){
            showSingleDialogWithContent("上传图片不能大于1M",null)
			isTrue = false;
        }else{
            isTrue = true;
        }
    }


//function getImgOptions() {
//    $('#file').on('change', function() {
//        var size = $(this)[0].files[0].size;
//        if (size >= 1024 * 1024) {
//            $('.loadImg_notice').css({
//                'color' : 'red',
//                'display' : 'block'
//            }).text('请上传小于1M的图片!');
//            setTimeout(function() {
//                $('.loadImg_notice').css('display', 'none');
//            }, 3000);
//            return;
//        }
//        $('.weui-progress__bar').css('display', 'block');
//        $('.js_progress').animate({
//            'width' : '80%'
//        }, 3000);
//        var options = {
//            url : "loadHeadPortrait",
//            type : "post",
//            dataType : "json",
//            success : function(jsonObj) {
//                if (jsonObj.code == '1') {
//                    $('.js_progress').animate({
//                        'width' : '100%'
//                    });
//                    setTimeout(function() {
//                        $('.js_progress').animate({
//                            'width' : '0'
//                        });
//                        $('.weui-progress__bar').css('display', 'none');
//                        $('.loadImg_notice').css({
//                            'color' : 'green',
//                            'display' : 'block'
//                        }).text('上传成功!');
//                    }, 1000);
//                    setTimeout(function() {
//                        $('.loadImg_notice').css('display', 'none');
//                    }, 3000);
//                    $('#imgUrl').val(jsonObj.imgUrl);
//                } else {
//                    $('.js_progress').animate({
//                        'width' : '0'
//                    });
//                    $('.weui-progress__bar').css('display', 'none');
//                    $('.loadImg_notice').css({
//                        'color' : 'red',
//                        'display' : 'block'
//                    }).text('上传失败!');
//                    setTimeout(function() {
//                        $('.loadImg_notice').css('display', 'none');
//                    }, 3000);
//                }
//            }
//        };
//        return  options ;
//    });
//}




