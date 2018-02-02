function validate(formData, jqForm, options) { 
	    var form = jqForm[0]; 
	    if (!form.username.value || !form.idCard.value || !form.phoneno) { 
	    	showSingleDialogWithContent("姓名、身份证号、联系方式不能为空", null);
	        return false; 
	    } 
	}

function getUrlParam (name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]); return null;
   }


function loadUserDetail() {
	$.ajax({
		url: "getUserDetail",
		dataType: 'json',
		method: 'get',
		success : function(jsonObj) {
			if (jsonObj.success) {
				var record = jsonObj.data;
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
	loadUserDetail();
	$('#courseid').val(getUrlParam('courseid'));
	$('#coursecategid').val(getUrlParam('coursecategid'));
	$('#job,#education').click(function() {
		$(this).css('color', '#444');
	});

	//表单提交(两张表单)

	$('.sigunupInfo_submit').click(function() {
		$('#detailForm').ajaxSubmit({
			dataType : 'json',
			beforeSubmit: validate,
			error: function(){
				showSingleDialogWithContent("出错了 请稍后重试");
			},
			success : function(jsonObj) {
				if (jsonObj.success) {
					toastSucceed(jsonObj.msg);
					setTimeout(function() {
						location.href='getOrder';
					}, 1000);
				} else {
					showSingleDialogWithContent(jsonObj.msg, null);
				}
			}
		});

		var options = getImgOptions();

        $("#user_form").ajaxSubmit(options);
	});
});


function getImgOptions() {
    $('#file').on('change', function() {
        var size = $(this)[0].files[0].size;
        if (size >= 1024 * 1024) {
            $('.loadImg_notice').css({
                'color' : 'red',
                'display' : 'block'
            }).text('请上传小于1M的图片!');
            setTimeout(function() {
                $('.loadImg_notice').css('display', 'none');
            }, 3000);
            return;
        }
        $('.weui-progress__bar').css('display', 'block');
        $('.js_progress').animate({
            'width' : '80%'
        }, 3000);
        var options = {
            url : "loadHeadPortrait",
            type : "post",
            dataType : "json",
            success : function(jsonObj) {
                if (jsonObj.code == '1') {
                    $('.js_progress').animate({
                        'width' : '100%'
                    });
                    setTimeout(function() {
                        $('.js_progress').animate({
                            'width' : '0'
                        });
                        $('.weui-progress__bar').css('display', 'none');
                        $('.loadImg_notice').css({
                            'color' : 'green',
                            'display' : 'block'
                        }).text('上传成功!');
                    }, 1000);
                    setTimeout(function() {
                        $('.loadImg_notice').css('display', 'none');
                    }, 3000);
                    $('#imgUrl').val(jsonObj.imgUrl);
                } else {
                    $('.js_progress').animate({
                        'width' : '0'
                    });
                    $('.weui-progress__bar').css('display', 'none');
                    $('.loadImg_notice').css({
                        'color' : 'red',
                        'display' : 'block'
                    }).text('上传失败!');
                    setTimeout(function() {
                        $('.loadImg_notice').css('display', 'none');
                    }, 3000);
                }
            }
        };
        return  options ;
    });
}




