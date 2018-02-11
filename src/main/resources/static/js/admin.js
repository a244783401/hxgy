/**
 * Created by 10104 on 2018/1/24.
 */
/**
 * ajax登录
 */
$(".btn-info").click(function () {
    excuteAjax("login", {name: $('#input_username').val(), password: $('#input_password').val()}, function (result) {
        if(result == 0)
            window.history.go(0);
        else{
            $('.tips').text("请输入正确的用户名和密码");
        }
    })
})
function hideLoding(){;}
function loading(){;}
function hideLoding(){;}