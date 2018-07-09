package com.lamport.admin.handler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller, 进行FreeListenBook(免费试听课预约)信息的修改、查询
 * @author Lin Zhao, protector of Sherry
 *
 */
@Controller
public class FreeListenBookHandler {
	/**
	 * 通过id修改待处理的FreeListenBook信息
	 * @return
	 */
	@RequestMapping(value="/admin/processFreeListenBookByID")
	public String processFreeListenBookByID() throws Exception{
		System.out.println("..........FreeListenBookHandler..........processFreeListenBookByID()..........");
		//TODO
		return"";
	}
	/**
	 * 通过BookQueryCondition查询FreeListenBook信息
	 * @return
	 */
	@RequestMapping(value="/admin/selectFreeListenBookByBookQueryCondition")
	public String selectFreeListenBookByBookQueryCondition() throws Exception{
		System.out.println("..........FreeListenBookHandler..........selectFreeListenBookByBookQueryCondition()..........");
		//TODO
		return"";
	}
	/**
	 * 通过BookQueryCondition查询未处理的FreeListenBook信息
	 * @return
	 */
	@RequestMapping(value="/admin/selectFreeListenBookUnprocessedByBookQueryCondition")
	public String selectFreeListenBookUnprocessedByBookQueryCondition() throws Exception{
		System.out.println("..........FreeListenBookHandler..........selectFreeListenBookUnprocessedByBookQueryCondition()..........");
		//TODO
		return"";
	}
}
