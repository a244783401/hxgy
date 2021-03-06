$(function() {	
	loadCategList();
    var table = $('#example').DataTable({
    	"destroy": true,
        "bAutoWidth": true, //自适应宽度 
        "ajax": '/admin/courselist',
        "aoColumns": 
                [
                   { 'data': 'id', "width":15 },
                   { 'data': 'coursename',"width":100 },
                   { 'data': 'coursedesc',"width":200 },
                   { 'data': 'courseprice' },
                   { 'data': 'forcrowd' },
                   { 'data': 'learnnum' },
                   { 'data': 'isrecommend' },
                   { 'data': 'score' },
                   { 'data': 'enable' },
                   { 'data': 'categ' },
                   { 'data': 'createTime' },
                   { 'data': 'authorname' }, 
                   { 'data': 'authordeptname' }, 
                   { 'data': 'authorhospname' }, 
                   { "data": 'coursecategid',"visible": false,"searchable": false},
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
        	excuteAjax("deleteCourse",{"ids":id},function(jsonObj){
            	if (jsonObj.status==0) {
    				toastSucceed(jsonObj.message);
    				table.ajax.reload();
    			} else {
    				alert(jsonObj.message);
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
    			$('#titleDiv').text("修改课程");
    			$('#editorDiv').css("display","block");
    	    	$('#addOrUpdate').val("update");
    			$('#courseName').val(data.coursename);
    			$('#courseDesc').val(data.coursedesc);
    			$('#coursePrice').val(data.courseprice);
    			$('#forcrow').val(data.forcrowd);
    			$('#courseAuthor').val(data.authorname);
    			$('#courseDept').val(data.authordeptname);
    			$('#courseHospital').val(data.authorhospname);    			
    			$('#isrecommend').val(data.isrecommend=="是"?"1":"0");
    			$('#enable').val(data.enable=="是"?"0":"1");
    			$('#courseCateg').val(data.coursecategid);
    			$('#courseCode').val(data.courseCode);
    			$('#courseCoverUrl').val(data.courseCoverUrl);
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
			$('#titleDiv').text("添加课程");			
	    	$('#editorDiv').css("display","block");
	    	$('#addOrUpdate').val("add");
	    	$('#courseName,#courseDesc,#coursePrice,#forcrow,#courseAuthor,#courseDept,#courseHospital').val("");
		}
    });
    //处理新增或处理ajax
    $('#cancel').click(function(){
    	$('#editorDiv').css("display","none");
    });
    $('#confirm').click(function(){
    	var courseName = $('#courseName').val().replace(/[ ]/g,"");
    	if(courseName==""){
    		$('#courseName').css("border","1px solid red");
    		$('#nameCheck').css("display","block");
    		setTimeout(function() {
    			$('#courseName').css("border","1px solid #AAAAAA");
    			$('#nameCheck').css("display","none");
    		}, 2000);
    		return;  		
    	}
    	var reg =/^(([0-9]+.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*.[0-9]+)|([0-9]*[1-9][0-9]*))$/;
    	var price = $('#coursePrice').val().replace(/[ ]/g,"");
    	if (!reg.test(price)) {
    		$('#coursePrice').css("border","1px solid red");
    		$('#priceCheck').css("display","block");
    		setTimeout(function() {
    			$('#coursePrice').css("border","1px solid #AAAAAA");
    			$('#priceCheck').css("display","none");
    		}, 2000);
    		return; 
		}
    	var addOrUpdate = $('#addOrUpdate').val();
    	var url = addOrUpdate == "add" ? "addCourse" : "updateCourse";
    	var id =  addOrUpdate == "add" ? null : table.row('.selected').data().id;
    	var data = {"id":id,
    				"courseName":courseName,
    				"courseDesc":$('#courseDesc').val().replace(/[ ]/g,""),
    				"coursePrice":$('#coursePrice').val().replace(/[ ]/g,""),
    				"forCrowd":$('#forcrow').val().replace(/[ ]/g,""),
    				"recommend":$('#isrecommend').val(),
    				"enable":$('#enable').val(),
    				"createDate":addOrUpdate =="add"? null :table.row('.selected').data().createTime.substring(0,10)+"T"+table.row('.selected').data().createTime.substring(11,19)+".000+0800",
    				"courseCategoryId":$('#courseCateg').val(),
    				"authorName":$('#courseAuthor').val().replace(/[ ]/g,""),
    				"authorDeptName":$('#courseDept').val().replace(/[ ]/g,""),
    				"authorHospName":$('#courseHospital').val().replace(/[ ]/g,""),
    				"learnNum" : addOrUpdate =="add"? null :table.row('.selected').data().learnnum,
    				"score" :addOrUpdate =="add"? null: table.row('.selected').data().score,
			        "courseCode":$('#courseCode').val().replace(/[ ]/g,""),
			        "courseCoverUrl":$('#courseCoverUrl').val().replace(/[ ]/g,""),
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

function loadCategList(){
	excuteAjax("/admin/categlist", null, function(jsonObj) {
		var categData = jsonObj.data;
		$('#courseCateg').html('');
		for (var i = 0; i < categData.length; i++) {
			$('#courseCateg').append('<option value='+categData[i].id+'>'+categData[i].categdesc+'</option>')
		}
	});
}