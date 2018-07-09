package com.lamport.admin.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lamport.admin.service.FreeListenBookService;

/**
 * Controller, 进行FreeListenBook(免费试听课预约)信息的修改、查询
 * @author Lin Zhao, protector of Sherry
 *
 */
@Controller
public class FreeListenBookHandler {
	
	@Autowired
	private FreeListenBookService freeListenBookService;
	
	/**
	 * 通过id修改待处理的FreeListenBook信息
	 * @return
	 */
	@RequestMapping(value="/admin/processFreeListenBookByID")
	@ResponseBody
	public String processFreeListenBookByID() throws Exception{
		System.out.println("..........FreeListenBookHandler..........processFreeListenBookByID()..........");
		String result = null;
		//TODO
		return result;
	}
	/**
	 * 通过BookQueryCondition查询FreeListenBook信息
	 * @return
	 */
	@RequestMapping(value="/admin/selectFreeListenBookByBookQueryCondition")
	@ResponseBody
	public String selectFreeListenBookByBookQueryCondition() throws Exception{
		System.out.println("..........FreeListenBookHandler..........selectFreeListenBookByBookQueryCondition()..........");
		String result = null;
		//TODO
		return result;
	}
	/**
	 * 通过BookQueryCondition查询未处理的FreeListenBook信息
	 * @return
	 */
	@RequestMapping(value="/admin/selectFreeListenBookUnprocessedByBookQueryCondition")
	@ResponseBody
	public String selectFreeListenBookUnprocessedByBookQueryCondition() throws Exception{
		System.out.println("..........FreeListenBookHandler..........selectFreeListenBookUnprocessedByBookQueryCondition()..........");
		String result = null;
		//TODO
		return result;
	}
}
