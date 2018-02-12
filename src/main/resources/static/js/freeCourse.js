$(function () {
    excuteAjax("/video/get_freeCourse",null, function (res) {
        var historyStr = ""
        if (res.status != 0){
            showSingleDialogWithContent(res.message,null);
        }else {
            var freeVideo = res.data
            for (var i = 0;i < freeVideo.length;i++){
             historyStr+='<div class="history_list_item" videoId="'+freeVideo[i].id+'" courseId="'+freeVideo[i].courseId+'">'+
                    '<div class="history_list_circle" action="0" id="'+i+'">'+
                    '<img src="images/history/circle.png"/>'+
                    '</div>'+
                    '<div class="history_list_cover">'+
                    '<div class="history_list_coverImg" >'+'<img src="'+freeVideo[i].coverurl+'"/>'+
                    '</div>'+
                    '</div>'+
                    '<div class="history_list_content">'+
                    '<div class="history_list_title">'+freeVideo[i].name+'</div>'+
                    '<div class="history_list_author">'+freeVideo[i].authorName+'</div>'+
                    '</div>'+
                    '</div>';
            }
        }
        $('.history_list').html(historyStr);

        $('.history_list_item').click(function(){
            var videoId = $(this).attr('videoId');
            excuteAjax("/video/video_play", {"videoId":videoId,"courseId":$(this).attr('courseId')},function(re){
                if (re.status != 0){
                    judgeStatus(re.status,re.data);
                }else location.href = "/videoPlay?videoId="+videoId
            })
        });
    })
})