var PREPATH = "/lamport/api/admin";
var PRESTAPATH = "/lamport/admin/main";
var URL = "http://localhost:8083";
var IMGPATH = "/lamport/upload_files";
var IMG_NUM = 4;

//设置ajax请求完成后运行的函数,
var jqxhr;
$.ajaxSetup({
	complete: function() {
		redirectedFunc(jqxhr);
	}
});


//将顶端导航栏弄消失  往上滚动出现  往下滚动消失
var scrolltop = new Array();
var i_sc = 0;
scrolltop[0] = 0;
$("#content_div").scroll(function() {
	console.log("fade~");
	i++;
	scrolltop[i_sc] = $(document).scrollTop();
	if(scrolltop[i_sc] > scrolltop[i_sc - 1]) {
		$("header_").fadeOut()
	} else {
		$("header_").fadeIn()
	};
})


function redirectedFunc(res) {
	if((res != null) && ("REDIRECT" == res.getResponseHeader("REDIRECT"))) { //若HEADER中含有REDIRECT说明后端想重定向，
		var win = window;
		while(win != win.top) {
			win = win.top;
		}
		win.location.href = res.getResponseHeader("CONTENTPATH"); //将后端重定向的地址取出来,使用win.location.href去实现重定向的要求
	}
}

String.format = function(str) {
	var args = arguments,
		re = new RegExp("%([1-" + args.length + "])", "g");
	return String(str).replace(
		re,
		function($1, $2) {
			return args[$2];
		}
	);
};

var numperrow = function(i) {
	if(i == 1) {
		return 1;
	} else if(i == 2 | i == 3 | i == 4) {
		return 2;
	} else {
		return 3;
	}
};

function layerphotos(div_id) {
	console.log("layer.photos() start.");
	layui.use('layer', function() {
		var layer = layui.layer;
		// 				layer.msg("hhh");
		layer.photos({
			photos: '#' + div_id,
			anim: 5 //0-6的选择，指定弹出图片动画类型，默认随机（请注意，3.0之前的版本用shift参数）
		});
	});
	console.log("layer.photos() end.");
};

function layerphotos_dynamic(div_id) {
	console.log("layer.layerphotos_dynamic() start.");
	layui.use('layer', function() {
		var layer = layui.layer;
		// 				layer.msg("hhh");
		layer.photos_dynamic({
			photos: '#' + div_id,
			anim: 5 //0-6的选择，指定弹出图片动画类型，默认随机（请注意，3.0之前的版本用shift参数）
		});
	});
	console.log("layer.layerphotos_dynamic() end.");
};

//回到页面顶部
function scroll_top() {
	console.log("scroll");
	$('.layui-body').scrollTop(0);
}

//表单验证弹出框的方法
function layer_out(content) {
	layui.use('layer', function() {
		layer.open({
			title: '',
			content: content,
			icon: 2,
			closeBtn: false,
			anim: 6,
			btn: false,
			time: 1200
		});
	});
}
//表单验证弹出框：必填项
function required_verify(posi, value) {
	if(value == "" || value == null) {
		layer_out(posi + "是必填项哦！");
		return 0;
	}
	return 1;
}

//从服务器请求category的方法，传入的参数是要append下拉框的select标签id
function getCategories(id_) {
	jqxhr = $.ajax({
		type:"post",
		url:"/admin/getCategories",
		async:true,
		dataType: "json",
		success: function(data) {
			var values = data.values;
			$.each(values, function(index) {
				$("#" + id_).append('<option value="' + values[index] + '">' + values[index] + '</option>');
			});
		}
	});	
}

function getCategories(id_, type) {
	jqxhr = $.ajax({
		type:"post",
		url:"/admin/getCategories",
		async:true,
		dataType: "json",
		success: function(data) {
			var values = data.values;
			$.each(values, function(index) {
				if(type == 1) {
					$("#" + id_).append('<option value="' + values[index] + '">' + values[index] + '</option>');
				} else if (type == 2) {
					$("#" + id_).append('<input type="checkbox" name="' + values[index] + '" title="' + values[index] + 'C++">"');			
				}
			});
		}
	});	
}

