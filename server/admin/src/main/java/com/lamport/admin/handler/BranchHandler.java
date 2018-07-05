package com.lamport.admin.handler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller, 进行Address基本信息的查询、修改
 * @author Lin Zhao, protector of Sherry
 *
 */
@Controller
public class BranchHandler {
	/**
	 * 创建分部
	 * @return
	 */
	public String saveBranch(){
		System.out.println("..........BranchHandler..........saveBranch()..........");
		//TODO
		return"";
	}
	/**
	 * 通过id删除Address信息
	 * @return
	 */
	public String deleteBranchByID(){
		System.out.println("..........BranchHandler..........deleteBranchByID()..........");
		//TODO
		return"";
	}
	/**
	 * 通过id更新Address信息
	 * @return
	 */
	public String updateBranchByID(){
		System.out.println("..........BranchHandler..........updateBranchByID()..........");
		//TODO
		return"";
	}
	/**
	 * 通过id查询Address信息
	 * @return
	 */
	@RequestMapping(value="/admin/selectBranchByID")
	public String selectBranchByID(){
		System.out.println("..........BranchHandler..........selectBranchByID()..........");
		//TODO
		return"";
	}
	/**
	 * 通过qid和页码查询Address信息
	 * @return
	 */
	@RequestMapping(value="/admin/selectBranchByQIDAndPage")
	public String selectBranchByQIDAndPage(){
		System.out.println("..........BranchHandler..........selectBranchByQIDAndPage()..........");
		//TODO
		return"";
	}
}
