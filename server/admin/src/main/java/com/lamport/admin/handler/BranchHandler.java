package com.lamport.admin.handler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller, 进行Address(分部)基本信息的增加、删除、修改、查询
 * @author Lin Zhao, protector of Sherry
 *
 */
@Controller
public class BranchHandler {
	/**
	 * 创建分部
	 * @return
	 */
	@RequestMapping(value="/admin/saveBranch")
	public String saveBranch(){
		System.out.println("..........BranchHandler..........saveBranch()..........");
		//TODO
		return"";
	}
	/**
	 * 通过id逻辑删除Address信息
	 * @return
	 */
	@RequestMapping(value="/admin/deleteBranchLogicallyByID")
	public String deleteBranchLogicallyByID(){
		System.out.println("..........BranchHandler..........deleteBranchLogicallyByID()..........");
		//TODO
		return"";
	}
	/**
	 * 通过id更新Address信息
	 * @return
	 */
	@RequestMapping(value="/admin/updateBranchByID")
	public String updateBranchByID(){
		System.out.println("..........BranchHandler..........updateBranchByID()..........");
		//TODO
		return"";
	}
	/**
	 * 通过id查询Address信息，弃用
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
