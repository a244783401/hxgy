$(function () {
    //短信验证码验证
    $('#code').blur(function () {
        var phoneno = $('#phoneno').val();
        var code = $('#code').val();
        if(code!=null&&code!="") {
            excuteAjax('/validate/validateRandomCode', {
                'phoneno': phoneno,
                'inputCode': code,
                'codeType': '00'
            }, function (jsonObj) {
                if (jsonObj.status == 1) {
                    showSingleDialogWithContent(jsonObj.message, null);
                }
            });
        }
    });

});