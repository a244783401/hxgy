$(function() {
    var table = $('#example').DataTable({
    	"destroy": true,
        "bAutoWidth": true, //自适应宽度 
        "ajax": 'categList',
        "aoColumns": 
                [
                   { 'data': 'id' },
                   { 'data': 'code' },
                   { 'data': 'categdesc' },
                ]
    });          
    $('#example tbody').on( 'click', 'tr', function () {
    	$(this).toggleClass('selected');
    });
    //删除
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
        	excuteAjax("deleteCateg",{"ids":id},function(jsonObj){
            	if (jsonObj.status==1) {
    				toastSucceed(jsonObj.msg);
    				table.ajax.reload();
    			} else {
    				alert("删除失败");
    			}
            });
        },null);
    });
    //修改
    $('#update').click(function(){
    	var len = table.rows('.selected').data().length;
    	var data = table.row('.selected').data();
    	if (len==1) {
    		if ($('#editorDiv').css("display")=="block") {
    			return;
    		} else {
    			$('#titleDiv').text("修改分类");
    			$('#categCode').val(data.code);
    			$('#categDesc').val(data.categdesc);
    	    	$('#editorDiv').css("display","block");
    	    	$('#addOrUpdate').val("update");
    		}
		} else if(len>1){
			alert("选中了"+len+"条数据,无法编辑!");
			return;
		}   	
    });   
    //新增
    $('#add').click(function(){
    	if ($('#editorDiv').css("display")=="block") {
			return;
		} else {
			$('#titleDiv').text("添加分类");
			$('#categCode,#categDesc').val("");
	    	$('#editorDiv').css("display","block");
	    	$('#addOrUpdate').val("add");
		}
    });
    //处理新增或处理ajax
    $('#cancel').click(function(){
    	$('#editorDiv').css("display","none");
    });
    $('#confirm').click(function(){  
    	var code = $('#categCode').val().replace(/[ ]/g,"");
    	var categDesc = $('#categDesc').val().replace(/[ ]/g,"");
    	if(code==""||categDesc==""){
    		alert("编号或名称不能为空");
    		return;
    	}
    	var addOrUpdate = $('#addOrUpdate').val();
    	var url = addOrUpdate == "add" ? "addCateg" : "updateCateg";
    	var id =  addOrUpdate == "add" ? null : table.row('.selected').data().id;
    	excuteAjax(url,{"id":id,"code":code,"categdesc":categDesc},function(jsonObj){
        	if (jsonObj.status==1) {
				toastSucceed(jsonObj.msg);
				$('#editorDiv').css("display","none");
				table.ajax.reload();
			} else {
				alert(""+addOrUpdate=="add"?"添加":"修改"+"失败");
			}
        });
    });
});