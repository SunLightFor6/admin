package com.lamport.admin.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lamport.admin.service.AddressService;

/**
 * Controller, 进行Address(分部)基本信息的增加、删除、修改、查询
 * @author Lin Zhao, protector of Sherry
 *
 */
@Controller
public class BranchHandler {
	
	@Autowired
	private AddressService addressService;
	
	/**
	 * 创建分部
	 * @return
	 */
	@RequestMapping(value="/admin/saveBranch")
	@ResponseBody
	public String saveBranch() throws Exception{
		System.out.println("..........BranchHandler..........saveBranch()..........");
		String result = null;
		//TODO
		return result;
	}
	/**
	 * 通过id逻辑删除Address信息
	 * @return
	 */
	@RequestMapping(value="/admin/deleteBranchLogicallyByID")
	@ResponseBody
	public String deleteBranchLogicallyByID() throws Exception{
		System.out.println("..........BranchHandler..........deleteBranchLogicallyByID()..........");
		String result = null;
		//TODO
		return result;
	}
	/**
	 * 通过id修改Address信息
	 * @return
	 */
	@RequestMapping(value="/admin/updateBranchByID")
	@ResponseBody
	public String updateBranchByID() throws Exception{
		System.out.println("..........BranchHandler..........updateBranchByID()..........");
		String result = null;
		//TODO
		return result;
	}
	/**
	 * 通过qid和页码查询Address信息
	 * @return
	 */
	@RequestMapping(value="/admin/selectBranchByQIDAndPage")
	@ResponseBody
	public String selectBranchByQIDAndPage() throws Exception{
		System.out.println("..........BranchHandler..........selectBranchByQIDAndPage()..........");
		String result = null;
		//TODO
		return result;
	}
}
