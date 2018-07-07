var pic_amount = 0;						//------ 变量定义  图片计数器
var pic_acount_valid = false;			//------ 变量定义  图片计数器有效检验
r.photos = function(t, n, a) {
	...
	if(t = t || {}, t.photos) {			 //t就是传入的photos数据
		if(delete t.success, l) {} else {
			var p = i(t.photos),
			h = function() {				//h函数的定义 遍历所有图片 设置属性 因为要遍历所有图片，所以在这里计数
				pic_acount_valid = false;			//* 计数器无效
				u = [], p.find(t.img).each(function(e) {	//循环
					pic_amount = e+1;				//* 计数器计数
					...};
			pic_acount_valid = true;	 //* ------ 当h()遍历完所有的图片，也就是图片计数器数值有效，置为true
			...
			if(n || p.on("click", t.img, function() {		 //图片被点击时候调用的函数	
				...											//这里调用了photos函数 用来“发现所有的图片”	也就是说点击图片之后执行语句会在这个方法内部再次调用photos()
				r.photos(i.extend(t, { ... },}),!0), h()}), !n) return
	}
	... //一些函数的定义 前一张后一张等
	if(pic_acount_valid === true, u.length === pic_amount) {		//------ 判断计数器有效，且当前被发现的数据u的差哪个都和计数器计数数量一致 说明所有图片都已经被发现
		console.log("006 --成功 -- 自己加的判断：pic_acount_valid === " + pic_acount_valid + " " + "u.length = " + u.length + " " + "pic_amount = " + pic_amount);
		o(u[d].src, function(n) {
			r.close(s.loadi), s.index = r.open(i.extend({ ... }(),				 //设置区域大小等  用layer.open()打开弹出窗
			}, t)) 					//设置open()的一些属性
		},
			...		//设置关闭弹窗的一下属性 
	}
	else {
		console.log("006 --失败 -- 自己加的判断：pic_acount_valid === " + pic_acount_valid + " " + "u.length = " + u.length + " " + "pic_amount = " + pic_amount);
	}}}, ... //其余的一些函数定义等
}()