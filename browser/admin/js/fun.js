var PREPATH = "/admin";
var PRESTAPATH = "main";
var URL = "http://localhost:8080";
var IMGPATH = "";
var IMG_NUM = 4;

var noMore = 0;

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
		var layer = layui.layer;
		layer.open({
			title: '',
			content: content,
			icon: 2,
			closeBtn: true,
			anim: 6,
			btn: false
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
//验证
/**
 * 
 * @param {Object} posi		在哪个输入框
 * @param {Object} value	输入框的值
 * @param {Object} expression	正则表达式
 * @param {Object} info 提示信息
 */
function verify_(posi, value, expression, info) {
	console.log(posi);
	console.log(value);
	console.log(expression);
	console.log(info);
	console.log(expression.test(value));
	if(!expression.test(value)) {
		layer_out(posi + ": " + info);
		console.log("----------------------------------验证失败" + value + " " + expression);
		return 0;
	} else {
		console.log("----------------------------------验证成功" + value + " " + expression);
	}
	return 1;
}
/**
 * 验证密码
 * @param {Object} posi		哪个输入框
 * @param {Object} value	输入框的值
 */
function verify_pass(posi, value) {
	return verify_(posi, value, /^[\S]{8,12}$/, '密码至少8到16位，且不能出现空格');
}

/**
 * 验证手机号
 * @param {Object} posi		哪个输入框
 * @param {Object} value	输入框的值
 */
function verify_tele(posi, value) {
	return verify_(posi, value, /(\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$/, '联系电话不符合格式,请检查');
}

//从服务器请求category的方法，传入的参数是要append下拉框的select标签id
//function getCategories(id_) {
//	getCategories(id_, 1);
//}

function getCategories(id_, type) {
	if(type==null){
		type = 1;
	}
	layui.use("form", function() {
		var form = layui.form;

		jqxhr = $.ajax({
			type: "post",
			url: PREPATH + "/admin/getCategories",
			async: true,
			dataType: "json",
			success: function(data) {
				var values = data.values;
				console.log("getCategories values:" + values);
				$.each(values, function(index) {
					if(type == 1) {
						console.log(type);
						$("#" + id_).append('<option value="' + values[index] + '">' + values[index] + '</option>');
					} else if(type == 2) {
						console.log(type);
						$("#" + id_).append('<input type="checkbox" name="' + values[index] + '" title="' + values[index] + '">');
					} else if(type == 3) {
						if(values[index] == "ALL") {
							values[index] = "通用";
						}
						$("#" + id_).append('<option value="' + values[index] + '">' + values[index] + '</option>');
					}
					form.render();
				});
			},
			error: function() {
				$("#" + id_).append('<option value="课程类别信息获取失败，请重试或联系系统管理员" disabled>课程类别信息获取失败，请重试或联系系统管理员</option>');
				form.render();
			}
		});
	});
}
//获取分部信息，填充到下拉框的地方
//function getBranchesName(id_) {
//	getBranchesName(id_, 1);
//}
//js 的重载  因为js函数参数长度可变 所以其实是没必要用重载的

function getBranchesName(id_, type) {
	if(type==null) {
		type = 1;
	}
	layui.use("form", function() {
		var form = layui.form;
		console.log(type);
		jqxhr = $.ajax({
			type: "post",
			url: PREPATH + "/admin/getBranchesName",
			async: true,
			dataType: "json",
			success: function(data) {
				var values = data.values;
				console.log("getCategories values:" + values);
				$.each(values, function(index) {
					console.log(type);
					if(type == 1) {
						$("#" + id_).append('<option value="' + values[index] + '">' + values[index] + '</option>');
					} else if(type == 2) {
						$("#" + id_).append('<input type="checkbox" name="' + values[index] + '" title="' + values[index] + '">');
					} else if(type == 3) {
						$("#" + id_).append('<input disabled="disabled" type="checkbox" name="' + values[index] + '" id="' + values[index] + '" title="' + values[index] + '">');
					}
					form.render();
				});
			},
			error: function() {
				$("#" + id_).append('<option value="分部信息获取失败，请重试或联系系统管理员" disabled>分部信息获取失败，请重试或联系系统管理员</option>');
				form.render();
			}
		});
	});
}

//图表函数
function renderLayer04Left(data1) {
	var myChart = echarts.init(document.getElementById("layer04_left_chart"));
	console.log("1222");
	myChart.setOption({
			title: {
				text: ''
			},
			tooltip: {
				trigger: 'axis'
			},
			legend: {
				data: []
			},
			grid: {
				left: '3%',
				right: '4%',
				bottom: '5%',
				top: '4%',
				containLabel: true
			},
			xAxis: {
				type: 'category',
				boundaryGap: false,
				data: getLatestDays(30),
				axisLabel: {
					textStyle: {
						color: "white", //刻度颜色
						fontSize: 8 //刻度大小
					},
					rotate: 45,
					interval: 0
				},
				axisTick: {
					show: false
				},
				axisLine: {
					show: true,
					lineStyle: {
						color: '#0B3148',
						width: 1,
						type: 'solid'
					}
				}
			},
			yAxis: {
				type: 'value',
				axisTick: {
					show: false
				},
				axisLabel: {
					textStyle: {
						color: "white", //刻度颜色
						fontSize: 8 //刻度大小
					}
				},
				axisLine: {
					show: true,
					lineStyle: {
						color: '#0B3148',
						width: 1,
						type: 'solid'
					}
				},
				splitLine: {
					show: false
				}
			},
			tooltip: {
				formatter: '{c}',
				backgroundColor: '#FE8501'
			},
			series: [{
				name: '',
				type: 'line',
				smooth: true,
				areaStyle: {
					normal: {
						color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
							offset: 0,
							color: '#026B6F'
						}, {
							offset: 1,
							color: '#012138'
						}], false),
						opacity: 0.2
					}
				},
				itemStyle: {
					normal: {
						color: '#009991'
					},
					lineStyle: {
						normal: {
							color: '#009895',
							opacity: 1
						}
					}
				},
				symbol: 'none',
				data: data1
			}]
		}

	);
}

function renderLayer04Right(data2, data3) {
	var myChart = echarts.init(document.getElementById("layer04_right_chart"));
	myChart.setOption({
		title: {
			text: ''
		},
		tooltip: {
			trigger: 'axis'
		},
		legend: {
			top: 20,
			right: 5,
			textStyle: {
				color: 'white'
			},
			orient: 'vertical',
			data: [{
					name: '订单数量',
					icon: 'circle'
				},
				{
					name: '预约数量',
					icon: 'circle'
				}
			]
		},
		grid: {
			left: '3%',
			right: '16%',
			bottom: '3%',
			top: '3%',
			containLabel: true
		},
		xAxis: {
			type: 'category',
			boundaryGap: false,
			axisTick: {
				show: false
			},
			axisLabel: {
				textStyle: {
					color: "white", //刻度颜色
					fontSize: 8 //刻度大小
				}
			},
			axisLine: {
				show: true,
				lineStyle: {
					color: '#0B3148',
					width: 1,
					type: 'solid'
				}
			},
			data: get6Month()
		},
		yAxis: {
			type: 'value',
			axisTick: {
				show: false
			},
			axisLabel: {
				textStyle: {
					color: "white", //刻度颜色
					fontSize: 8 //刻度大小
				}
			},
			axisLine: {
				show: true,
				lineStyle: {
					color: '#0B3148',
					width: 1,
					type: 'solid'
				}
			},
			splitLine: {
				show: false
			}
		},
		series: [{
				name: '订单数量',
				type: 'line',
				itemStyle: {
					normal: {
						color: '#F3891B'
					},
					lineStyle: {
						normal: {
							color: '#F3891B',
							opacity: 1
						}
					}
				},
				data: data2
			},
			{
				name: '预约数量',
				type: 'line',
				itemStyle: {
					normal: {
						color: '#006AD4'
					},
					lineStyle: {
						normal: {
							color: '#F3891B',
							opacity: 1
						}
					}
				},
				data: data3
			}
		]
	});
}

function get6Month() {
	var last6Month = new Array();
	var data = new Date();
	//获取年  
	var year = data.getFullYear();
	//获取月  
	var mon = data.getMonth() + 1;
	if(mon <= 0) {
		year = year - 1;
		mon = mon + 12;
	}
	if(mon < 10) {
		mon = "0" + mon;
	}
	last6Month[0] = year + "/" + mon;
	for(var i = 1; i < 6; i++) {
		mon = mon - 1;
		if(mon <= 0) {
			year = year - 1;
			mon = mon + 12;
		}
		if(mon < 10) {
			mon = "0" + mon;
		}

		last6Month[i] = year + "/" + mon;
	}

	return last6Month.reverse();

	return last6Month;

}

function get10MinutesScale() {
	var currDate = new Date();
	var odd = currDate.getMinutes() % 10;
	var returnArr = new Array();
	currDate.setMinutes(currDate.getMinutes() - odd);
	for(var i = 0; i < 7; i++) {
		returnArr.push(currDate.getHours() + ":" + (currDate.getMinutes() < 10 ? ("0" + currDate.getMinutes()) : currDate.getMinutes()));
		currDate.setMinutes(currDate.getMinutes() - 10);
	}
	return returnArr;
}

function getLatestDays(num) {
	var currentDay = new Date();
	var returnDays = [];
	currentDay.setDate(currentDay.getDate());
	returnDays.push((currentDay.getMonth() + 1) + "/" + currentDay.getDate());
	for(var i = 0; i < num - 1; i++) {
		currentDay.setDate(currentDay.getDate() - 1);
		returnDays.push((currentDay.getMonth() + 1) + "/" + currentDay.getDate());
	}
	return returnDays.reverse();
}

function drawLayer02Label(canvasObj, text, textBeginX, lineEndX) {
	var colorValue = '#04918B';

	var ctx = canvasObj.getContext("2d");

	ctx.beginPath();
	ctx.arc(35, 55, 2, 0, 2 * Math.PI);
	ctx.closePath();
	ctx.fillStyle = colorValue;
	ctx.fill();

	ctx.moveTo(35, 55);
	ctx.lineTo(60, 80);
	ctx.lineTo(lineEndX, 80);
	ctx.lineWidth = 1;
	ctx.strokeStyle = colorValue;
	ctx.stroke();

	ctx.font = '12px Georgia';
	ctx.fillStyle = colorValue;
	ctx.fillText(text, textBeginX, 92);
}