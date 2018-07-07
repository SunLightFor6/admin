package com.lamport.admin.handler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller, 进行FreeListen(精品课)信息的增加、删除、修改、查询
 * @author Lin Zhao, protector of Sherry
 *
 */
@Controller
public class FreeListenHandler {
	/**
	 * 创建FreeListen
	 * @return
	 */
	@RequestMapping(value="/admin/saveFreeListen")
	public String saveFreeListen(){
		System.out.println("..........FreeListenHandler..........saveFreeListen()..........");
		//TODO
		return"";
	}
	/**
	 * 通过id删除FreeListen信息
	 * @return
	 */
	@RequestMapping(value="/admin/deleteFreeListenByID")
	public String deleteFreeListenByID(){
		System.out.println("..........FreeListenHandler..........deleteFreeListenByID()..........");
		//TODO
		return"";
	}
	/**
	 * 通过id更新FreeListen信息
	 * @return
	 */
	@RequestMapping(value="/admin/updateFreeListenByID")
	public String updateFreeListenByID(){
		System.out.println("..........FreeListenHandler..........updateFreeListenByID()..........");
		//TODO
		return"";
	}
	/**
	 * 通过qid和页码查询FreeListen信息
	 * @return
	 */
	@RequestMapping(value="/admin/selectFreeListenByQIDAndPage")
	public String selectFreeListenByQIDAndPage(){
		System.out.println("..........FreeListenHandler..........selectFreeListenByQIDAndPage()..........");
		//TODO
		return"";
	}
}
