/**
 * Created by 10104 on 2018/2/1.
 */
$(function () {
    var table = $('#example').DataTable({
        "destroy": true,
        "bAutoWidth": true, //自适应宽度
        "ajax": '/admin/commentList',
        "aoColumns":
            [
                { 'data': 'id', "width":15 },
                { 'data': 'userName',"width":100 },
                { 'data': 'commentDesc',"width":200 },
                { 'data': 'score' },
                { 'data': 'courseName' },
                { 'data': 'courseItemName' },
                { 'data': 'commentDate' },
            ]
    });
    $('#example tbody').on( 'click', 'tr', function () {
        $(this).toggleClass('selected');
    });
    $('#delete').click(function(){
        var id = "";
        var len = table.rows('.selected').data().length;
        if (len!=0) {
            for (var i = 0; i < len; i++) {
                id+=table.rows('.selected').data()[i].id+",";
            }
        }else{
            return;
        }
        showConfirmDialog("温馨提示","选中"+len+"条数据,确认删除？","取消","确认",function(){
            excuteAjax("deleteComment",{"ids":id},function(jsonObj){
                if (jsonObj.status==0) {
                    toastSucceed(jsonObj.message);
                    table.ajax.reload();
                } else {
                    alert("删除评论失败");
                }
            });
        },null);
    });
})
