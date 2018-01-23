/**
 * 弹出框
 * 
 */
function loading(str) {
    $(document.body).append('<div id="loadingToast" style="display:block;">'
        + '<div class="weui-mask_transparent">' + '</div>'
        + '<div class="weui-toast">'
        + '<i class="weui-loading weui-icon_toast">' + '</i>'
        + '<p class="weui-toast__content">' + str + '</p>'
        + '</div>' + '</div>');
}
function hideLoding() {
    if ($('#loadingToast').length > 0) {
        $('#loadingToast').remove();
    }
}

function toastSucceed(str){
    $(document.body).append(
    '<div id="toast" style="display: none;">'
        +'<div class="weui-mask_transparent">'+'</div>'
        +'<div class="weui-toast">'
        +'<i class="weui-icon-success-no-circle weui-icon_toast">'+'</i>'
        +'<p class="weui-toast__content">'+str+'</p>'
        +'</div>'
        +'</div>');
    var $toast = $('#toast');
    $toast.fadeIn(100);
    setTimeout(function () {
        $toast.fadeOut(100,function(){
            $toast.remove();
        });
    }, 2000);
}

function showConfirmDialog(title,content,noBtn,yesBtn,fun,css){
//	var style=''
//	if(css){
//		var textAlign = css.textAlign,
//		margin = css.margin,
//		lineHeight = css.lineHeight;
//	 textAlign = textAlign?'text-align:'+textAlign:'',
//	 margin = margin?"margin:"+margin:'',
//	 lineHeight = lineHeight?'line-height:'+lineHeight:'';
//	 style = [textAlign,margin,lineHeight].join(';');
//	}
	
	$(document.body).append(
			$('<div id="dialog" style="display: none;"><div class="weui-mask"></div></div>')
			.append(
				$('<div class="weui-dialog">'
			        + '<div class="weui-dialog__hd">' + '<strong class="weui-dialog__title">' + title + '</strong>' + '</div>'
			        + '</div>'
		        )
		        .append(
		        	$('<div class="weui-dialog__bd">' + content + '</div>').css(css||{})
		        )
		        .append(
		        	$('<div class="weui-dialog__ft">'
				        + '<a href="javascript:;" class="weui-dialog__btn weui-dialog__btn_default" style="color:#666666;">'+noBtn+'</a>'
				        + '<a href="javascript:;" class="weui-dialog__btn weui-dialog__btn_primary">'+yesBtn+'</a>'
				        + '</div>'
			        )
		        )
			)
	);
	$('#dialog').fadeIn(200);
	$('.weui-dialog__btn').click(function () {
		$('#dialog').fadeOut(200, function () {
			$('#dialog').remove();
		});
	});
	$('.weui-dialog__btn_primary').click(function () {
		fun();
	});
}
//特殊弹框、医生详情预约按钮
function showSpecialDialog(title,content,noBtn,yesBtn,fun,css){
	$(document.body).append(
		$('<div id="dialog" style="display: none;"><div class="weui-mask"></div></div>')
		.append(
			$('<div class="weui-dialog"><div class="weui-dialog__hd" style="border-bottom:1px solid #D5D5D6;">' + '<strong class="weui-dialog__title">' + title + '</strong></div></div>')
			.append(
				$('<div class="weui-dialog__bd">' + content + '</div>')
				.css(css||{})
			)
			.append('<div class="weui-dialog__ft">'
			        + '<a href="javascript:;" class="weui-dialog__btn weui-dialog__btn_default" style="color:#666666;">'+noBtn+'</a>'
			        + '<a href="javascript:;" class="weui-dialog__btn weui-dialog__btn_primary">'+yesBtn+'</a>'
			        + '</div>')
		)
	);
	$('#dialog').fadeIn(200);
	$('.weui-dialog__btn').click(function () {
		$('#dialog').fadeOut(200, function () {
			$('#dialog').remove();
		});
	});
	$('.weui-dialog__btn_primary').click(function () {
		fun();
	});
}

function showSingleDialog(title, content, btn,css) {
    $(document.body).append(
    	$('<div id="dialog" style="display: none;"><div class="weui-mask"></div></div>')
    	.append(
    		$('<div class="weui-dialog"><div class="weui-dialog__hd"><strong class="weui-dialog__title">' + title + '</strong></div></div>')
    		.append(
    			$('<div class="weui-dialog__bd">' + content + '</div>').css(css||{})
    			
    		)
    		.append('<div class="weui-dialog__ft">'
        	        + '<a href="javascript:;" class="weui-dialog__btn weui-dialog__btn_primary">' + btn + '</a>'
        	        + '</div>')
    	).fadeIn(200)
    );
    $('.weui-mask').click(function () {
        $('#dialog').fadeOut(200, function () {
            $('#dialog').remove();
        });
    });
    $('.weui-dialog__btn').click(function () {
        $('#dialog').fadeOut(200, function () {
            $('#dialog').remove();
        });
    });
}
//确认框
function showSingleDialogWithContent(content,css) {
    $(document.body).append(
    	$('<div id="dialog" style="display: none;"><div class="weui-mask"></div></div>')
    	.append(
    		$('<div class="weui-dialog"><div class="weui-dialog__hd">' + '<strong class="weui-dialog__title">' + "温馨提示" + '</strong>' + '</div></div>')
    		.append(
    			$('<div class="weui-dialog__bd">' + content + '</div>')
    			.css(css||{})
    		)
	        .append('<div class="weui-dialog__ft"><a href="javascript:;" class="weui-dialog__btn weui-dialog__btn_primary">确定</a></div>')
    	)
    );
    $('#dialog').fadeIn(200);
    $('.weui-mask').click(function () {
        $('#dialog').fadeOut(200, function () {
            $('#dialog').remove();
        });
    });
    $('.weui-dialog__btn').click(function () {
        $('#dialog').fadeOut(200, function () {
            $('#dialog').remove();
        });
    });
}

function showActionSheet(title,arr,obj) {
	var temparr =[];
    for (var i = 0; i < arr.length; i++) {
    	temparr.push('<div code="'+arr[i].code+'" class="weui-actionsheet__cell">'+arr[i].name+'</div>');  
    }
    $(document.body).append(
        '<div class="weui-mask" id="iosMask" style="display: none;">' + '</div>'
        + '<div class="weui-actionsheet" id="iosActionsheet">'   
        + '<div class="weui-actionsheet__cell" id="iosActionsheetCancel">'+title+'</div>' 
        + '<div class="weui-actionsheet__menu" style="overflow-y:auto;"></div>'     
        + '</div>'
        );
    var $iosActionsheet = $('#iosActionsheet');
    var $iosMask = $('#iosMask');
    var $cell = $('.weui-actionsheet__cell');
    var $menu = $('.weui-actionsheet__menu');
    $menu.height($(window).height()-350); 
    function hideActionSheet() {
    	$menu.html("");
        $iosActionsheet.removeClass('weui-actionsheet_toggle');
        $iosMask.fadeOut(200, function () {
            $('#actionRoot').remove();
        });
    }
    for (var i = 0; i < temparr.length; i++) {
        $menu.append(temparr[i]);
        $cell = $('.weui-actionsheet__cell');
    }
    $iosMask.fadeIn(200);
    $iosActionsheet.addClass('weui-actionsheet_toggle');

    $('#iosActionsheetCancel').on('click', hideActionSheet);
    $iosMask.on('click', hideActionSheet);
    $cell.on('click',function(){
    	obj.val($(this).text()).attr("code",$(this).attr("code"));
    	hideActionSheet();
    });
}

function showSelectSheet(option){
	var dataList =option.list==undefined?[]:option.list;
	var htmlStr = '';
	htmlStr += '<div class="weui-skin_android" id="androidActionsheet" style="opacity: 1;">'+
					'<div class="weui-mask"></div>'+   	    
						'<div class="weui-actionsheet">'+ 
						'<div class="weui-actionsheet__menu">'+
						'<div class="weui-actionsheet__title">'+option.title+'</div>';
	htmlStr += '<div class="weui-actionsheet__content">';
	for (var i = 0; i < dataList.length; i++) {
		htmlStr += '<div class="weui-actionsheet__cell">'+dataList[i]+'</div>';
	}
	htmlStr += '</div></div></div></div>'
    $(document.body).append(htmlStr);
    function hideSelectSheet() {
    	$menu.html("");
        $iosActionsheet.removeClass('weui-actionsheet_toggle');
        $iosMask.fadeOut(200, function () {
            $('#actionRoot').remove();
        });
    }
}