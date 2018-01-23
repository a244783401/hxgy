(function($) {       
	$.imageFileVisible = function(options) {     
		// 默认选项
		var defaults = {    
				//包裹图片的元素
				wrapSelector: null,    
				fileSelector:  null ,
				width : '100%',
				height: 'auto',
				errorMessage: "不是图片"
		};    
		var opts = $.extend(defaults, options);     
		$(opts.fileSelector).on("change",function(){
			var file = this.files[0];
			var imageType = /image.*/;
			if (file.type.match(imageType)) {
				var reader = new FileReader();
				reader.onload = function(){
					var img = new Image();
					img.src = reader.result;
					$(img).width( opts.width);
					$(img).height( opts.height);
					$( opts.wrapSelector ).html("");
					$( opts.wrapSelector ).append(img);
				};
				reader.readAsDataURL(file);
				$("#image-hide").css("display","none");
			}else{
				showSingleDialogWithContent(opts.errorMessage);
				return;
			}
		});
	};     
}	)(jQuery); 