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
		url:PREPATH + "/admin/getCategories",
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
		url:PREPATH + "/admin/getCategories",
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
//图表函数
function renderLayer04Left(data1){
	var myChart = echarts.init(document.getElementById("layer04_left_chart"));
	myChart.setOption(
		{
			title: {
				text: ''
			},
			tooltip : {
				trigger: 'axis'
			},
			legend: {
				data:[]
			},
			grid: {
				left: '3%',
				right: '4%',
				bottom: '5%',
				top:'4%',
				containLabel: true
			},
			xAxis :
			{
				type : 'category',
				boundaryGap : false,
				data : getLatestDays(31),
				axisLabel:{
					textStyle:{
						color:"white", //刻度颜色
						fontSize:8  //刻度大小
					},
					rotate:45,
					interval:2
				},
				axisTick:{show:false},
				axisLine:{
					show:true,
					lineStyle:{
						color: '#0B3148',
						width: 1,
						type: 'solid'
					}
				}
			},
			yAxis : 
			{
				type : 'value',
				axisTick:{show:false},
				axisLabel:{
					textStyle:{
						color:"white", //刻度颜色
						fontSize:8  //刻度大小
						}
				},
				axisLine:{
					show:true,
					lineStyle:{
						color: '#0B3148',
						width: 1,
						type: 'solid'
					}
				},
				splitLine:{
					show:false
				}
			},
			tooltip:{
				formatter:'{c}',
				backgroundColor:'#FE8501'
			},
			series : [
				{
					name:'',
					type:'line',
					smooth:true,
					areaStyle:{
						normal:{
							color:new echarts.graphic.LinearGradient(0, 0, 0, 1, [{offset: 0, color: '#026B6F'}, {offset: 1, color: '#012138' }], false),
							opacity:0.2
						}
					},
					itemStyle : {  
                            normal : {  
                                  color:'#009991'
                            },
							lineStyle:{
								normal:{
								color:'#009895',
								opacity:1
							}
						}
                    },
					symbol:'none',
					data:data1
				}
			]
		}
	
	);
}

function renderLayer04Right(data2, data3){
	var myChart = echarts.init(document.getElementById("layer04_right_chart"));
	myChart.setOption({
			title: {
				text: ''
			},
			tooltip: {
				trigger: 'axis'
			},
			legend: {
				top:20,
				right:5,
				textStyle:{
					color:'white'
				},
				orient:'vertical',
				data:[
						{name:'订单数量',icon:'circle'},
						{name:'预约数量',icon:'circle'}
					]
			},
			grid: {
				left: '3%',
				right: '16%',
				bottom: '3%',
				top:'3%',
				containLabel: true
			},
			xAxis: {
				type: 'category',
				boundaryGap: false,
				axisTick:{show:false},
				axisLabel:{
					textStyle:{
						color:"white", //刻度颜色
						fontSize:8  //刻度大小
						}
				},
				axisLine:{
					show:true,
					lineStyle:{
						color: '#0B3148',
						width: 1,
						type: 'solid'
					}
				},
				data: get10MinutesScale()
			},
			yAxis: {
				type: 'value',
				axisTick:{show:false},
				axisLabel:{
					textStyle:{
						color:"white", //刻度颜色
						fontSize:8  //刻度大小
						}
				},
				axisLine:{
					show:true,
					lineStyle:{
						color: '#0B3148',
						width: 1,
						type: 'solid'
					}
				},
				splitLine:{
					show:false
				}
			},
			series: [
						{
							name:'订单数量',
							type:'line',
							itemStyle : {  
									normal : {  
									color:'#F3891B'
								},
								lineStyle:{
									normal:{
									color:'#F3891B',
									opacity:1
										}
								}
							},  
							data:data2
						},
						{
							name:'预约数量',
							type:'line',
							itemStyle : {  
									normal : {  
									color:'#006AD4'
								},
								lineStyle:{
									normal:{
									color:'#F3891B',
									opacity:1
										}
								}
							},
							data:data3
						}
					]
		}	
	);
}

function get10MinutesScale()
{
	var currDate = new Date();
	var odd = currDate.getMinutes()%10;
	var returnArr = new Array();
	currDate.setMinutes(currDate.getMinutes()-odd);
	for(var i = 0; i <7; i++){
		returnArr.push(currDate.getHours()+":"+(currDate.getMinutes()<10?("0"+currDate.getMinutes()):currDate.getMinutes()));
		currDate.setMinutes(currDate.getMinutes()-10);
	}
	return returnArr;
}


function getLatestDays(num)
{
	var currentDay = new Date();
	var returnDays = [];
	for (var i = 0 ; i < num ; i++)
	{
		currentDay.setDate(currentDay.getDate() - 1);
		returnDays.push((currentDay.getMonth()+1)+"/"+currentDay.getDate());
	}
	return returnDays;
}
