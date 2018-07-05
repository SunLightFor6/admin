package com.lamport.admin.handler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller, 进行文件（图片、视频等）的增加、删除
 * @author Lin Zhao, protector of Sherry
 *
 */
@Controller
public class FileHandler {
	
	/**
	 * 保存图片文件
	 * @return
	 */
	@RequestMapping(value="/admin/saveImg")
	public String saveImg(){
		System.out.println("..........FileHandler..........saveImg()..........");
		//TODO
		return"";
	}
	/**
	 * 保存视频文件
	 * @return
	 */
	@RequestMapping(value="/admin/saveVideo")
	public String saveVideo(){
		System.out.println("..........FileHandler..........saveVideo()..........");
		//TODO
		return"";
	}
}
