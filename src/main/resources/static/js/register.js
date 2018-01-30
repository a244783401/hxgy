$(function(){  
	//获取验证码
	$('.register_code').click(function(){
		random($(this));
	});
	//显示密码
	$('.register_eye').click(function(){
		var act = $(this).attr('act');
		var inputId = $(this).attr('for');
		if (act=='0') {
			$(this).attr('act','1').html('<img src="images/register/eye_act.png"/>');
			$('#'+inputId).attr('type','text');
		} else {
			$(this).attr('act','0').html('<img src="images/register/eye.png"/>');
			$('#'+inputId).attr('type','password');
		}
	});
	//注册
	$('.register_submit').click(function(){
	    var phoneno = $('#phoneno').val();
	    var code = $('#code').val();
	    var username = $('#username').val();
	    var pwd = $('#firstPass').val();
	    var repPwd = $('#seccondPass').val();  	    
		if (check(phoneno,code,pwd,repPwd)) {
			//验证验证码
			excuteAjax('/validate/validateRandomCode',{'phoneno':phoneno,'inputCode':code,'codeType':'00'},function(jsonObj){
				if (jsonObj.status == 1) {
					showSingleDialogWithContent(jsonObj.message,null);
				}else{
					excuteAjax('/user/register/registerUser',{'phoneno' : phoneno.trim(),
											   'channel' : 'WECHAT',
											   'randomCode' : code,
											   'password' : pwd,
											   'username' : username},function(json){
							if (json.status == 1) {
								showSingleDialogWithContent(json.message,null);
							}else{
								toastSucceed(json.message);
								setTimeout(function () {
									location.href= 'myCenterIndex';
               				 	},1500);
							}				
					});
				}
			});
		}
	});
});



/**
 * 短信验证码
 */
function random(obj){
	var phoneno = $('#phoneno').val();
	if (checkPhone(phoneno)) {
		var data = {'phoneno': phoneno.trim(),'channel': '2','isRegister':'00','type': '0'};
        excuteAjax('/validate/generateRandomCode',data, function (jsonObj) {
            if (jsonObj.status == 1) {
                showSingleDialogWithContent(jsonObj.message,null);
            } else {
                toastSucceed(jsonObj.message);
                var count = 60;
                $(obj).css({"background-color": "#dcdcdc", "color": "white"}).text("重新获取("+count+")");                
                var countFun = window.setInterval(function changeTime() {
                    count--;
                    $(obj).text("重新获取("+count+")");
                    if (count == 0) {
                        window.clearInterval(countFun);
                        $(obj).text("重新获取").css({"color": "white", "background-color": "#75bde9"});
                        count = 60;
                        $(obj).bind('click', random);
                    }
                }, 1000);
                $(obj).unbind('click');
            }
        });
	}
}


/**
 * 验证验证码
 * @param randomCode
 * @returns {Boolean}
 */
function checkRandomCode(randomCode) {
    if ((randomCode != null && randomCode.trim() == "") || randomCode == "验证码"||randomCode==null) {
        showSingleDialog("温馨提示", "验证码不能为空", "确定");
        return false;
    }
    var reg = /^\d{6}$/;
    if (reg.test(randomCode) == false) {
        showSingleDialog("温馨提示", "验证码不正确", "确定");
        return false;
    }
    return true;
}

/**
 * 验证密码
 * @param pwd
 * @param repPwd
 * @returns {Boolean}
 */
function checkPwd(pwd, repPwd) {
    if((pwd != null && pwd.trim() == "") || pwd == "密码"||pwd==null||
    		(repPwd != null && repPwd.trim() == "") || repPwd == "密码"||pwd==null){
    	 showSingleDialog("温馨提示", "密码不能为空", "确定");
         return false;
    }
    if (pwd !== repPwd) {
        showSingleDialog("温馨提示", "两次密码不一致", "确定");
        return false;
    }
    return true;
}

/**
 * 验证手机号
 * @param phone
 * @returns {Boolean}
 */
function checkPhone(phone){
    if ((phone != null && phone.trim() == "") || phone == "手机号"||phone==null) {
        showSingleDialog("温馨提示", "手机号不能为空", "确定");
        return false;
    }
    var reg = /^(13[0-9]|14[0-9]|15[0-9]|17[0-9]|18[0-9])\d{8}$/i;
    if (reg.test(phone) == false) {
        showSingleDialog("温馨提示", "手机号格式不正确", "确定");
        return false;
    }
    return true;
}

function check(phoneno,code,pwd,repPwd) { 
    return checkPhone(phoneno)&&checkRandomCode(code)&&checkPwd(pwd,repPwd);
}
