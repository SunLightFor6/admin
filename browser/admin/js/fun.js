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