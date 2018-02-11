$(function() {	
	loadCourseList();
    var table = $('#example').DataTable({
    	"destroy": true,
        "bAutoWidth": true, //自适应宽度 
        "ajax": '/admin/videoList',
        "aoColumns": 
                [
                    { 'data': 'id', 'width':15 },
                    { 'data': 'name','width':100 },
                    { 'data': 'videoDesc',"visible": false,"searchable": false},
                    { 'data': 'url','width':250 },
                    { 'data': 'coverurl','width':250 },
                    { 'data': 'viewnum' },
                    { 'data': 'enable' },
                    { 'data': 'courseName' },
                    { 'data': 'createTime' },
                    { 'data': 'authorname' },
                    { 'data': 'authordeptname' },
                    { 'data': 'authorhospname' },
                    { "data": 'courseid',"visible": false,"searchable": false},
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
        showConfirmDialog("温馨提示","选中"+len+"条数据,该操作会删除视频及所有用户评论，此过程无法逆转，确认删除？","取消","确认",function(){
        	excuteAjax("/admin/deleteVideo",{"ids":id},function(jsonObj){
            	if (jsonObj.status==0) {
    				toastSucceed(jsonObj.message);
    				table.ajax.reload();
    			} else {
                    toastSucceed(jsonObj.message);
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
    			$('#titleDiv').text("修改视频");
    			$('#editorDiv').css("display","block");
    	    	$('#addOrUpdate').val("update");
    			$('#videoName').val(data.name);
    			$('#videoUrl').val(data.url);
    			$('#coverurl').val(data.coverurl);
    			$('#course').val(data.courseid);
    			$('#videoAuthor').val(data.authorname);
    			$('#videoDept').val(data.authordeptname);
    			$('#videoHospital').val(data.authorhospname);
    			$('#enable').val(data.enable=="是"?"1":"0");
    			$('#videoDesc').val(data.videoDesc);
    			$('#courseCoverUrl').val(data.courseCoverUrl);
    			$('#courseCode').val(data.courseCode);
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
			$('#titleDiv').text("添加视频");			
	    	$('#editorDiv').css("display","block");
	    	$('#addOrUpdate').val("add");
	    	$('#videoName,#videoUrl,#coverurl,#videoAuthor,#videoDept,#videoHospital').val("");
		}
    });
    //处理新增或处理ajax
    $('#cancel').click(function(){
    	$('#editorDiv').css("display","none");
    });
    $('#confirm').click(function(){
    	var videoName = $('#videoName').val().replace(/[ ]/g,"");
    	if(videoName==""){
    		$('#videoName').css("border","1px solid red");
    		$('#nameCheck').css("display","block");
    		setTimeout(function() {
    			$('#videoName').css("border","1px solid #AAAAAA");
    			$('#nameCheck').css("display","none");
    		}, 2000);
    		return;  		
    	}
    	var addOrUpdate = $('#addOrUpdate').val();
    	var url = addOrUpdate == "add" ? "addVideo" : "updateVideo";
    	var id =  addOrUpdate == "add" ? null : table.row('.selected').data().id;
    	var data = {"id":id,
    				"name":videoName,
			        "videoDesc":$('#videoDesc').val(),
    				"url":$('#videoUrl').val(),
    				"coverUrl":$('#coverurl').val(),
    				"courseId":$('#course').val(),
    				"enable":$('#enable').val(),   				
    				"authorName":$('#videoAuthor').val().replace(/[ ]/g,""),
    				"authorDeptName":$('#videoDept').val().replace(/[ ]/g,""),
    				"authorHospName":$('#videoHospital').val().replace(/[ ]/g,""),
    				"createDate":addOrUpdate =="add"? null :table.row('.selected').data().createTime.substring(0,10)+"T"+table.row('.selected').data().createTime.substring(11,19)+".000+0800",
    				"viewNum" : addOrUpdate =="add"? null :table.row('.selected').data().viewnum,
    				}
    	excuteAjax(url,data,function(jsonObj){
        	if (jsonObj.status==0) {
				toastSucceed(jsonObj.message);
				$('#editorDiv').css("display","none");
				table.ajax.reload();
			} else {
				alert(""+addOrUpdate=="add"?"添加":"修改"+"失败");
			}
        });
    });   
});

function loadCourseList(){
	excuteAjax("/admin/courselist", null, function(jsonObj) {
		var courseData = jsonObj.data;
		$('#course').html('');
		for (var i = 0; i < courseData.length; i++) {
			$('#course').append('<option value='+courseData[i].id+'>'+courseData[i].coursename+'</option>')
		}
	});
}