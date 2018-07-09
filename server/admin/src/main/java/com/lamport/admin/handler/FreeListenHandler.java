package com.lamport.admin.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lamport.admin.service.FreeListenService;

/**
 * Controller, 进行FreeListen(免费试听课)信息的增加、删除、修改、查询
 * @author Lin Zhao, protector of Sherry
 *
 */
@Controller
public class FreeListenHandler {
	
	@Autowired
	private FreeListenService freeListenService;
	
	/**
	 * 创建FreeListen
	 * @return
	 */
	@RequestMapping(value="/admin/saveFreeListen")
	@ResponseBody
	public String saveFreeListen() throws Exception{
		System.out.println("..........FreeListenHandler..........saveFreeListen()..........");
		String result = null;
		//TODO
		return result;
	}
	/**
	 * 通过id逻辑删除FreeListen信息
	 * @return
	 */
	@RequestMapping(value="/admin/deleteFreeListenLogicallyByID")
	@ResponseBody
	public String deleteFreeListenLogicallyByID() throws Exception{
		System.out.println("..........FreeListenHandler..........deleteFreeListenLogicallyByID()..........");
		String result = null;
		//TODO
		return result;
	}
	/**
	 * 通过id修改FreeListen信息
	 * @return
	 */
	@RequestMapping(value="/admin/updateFreeListenByID")
	@ResponseBody
	public String updateFreeListenByID() throws Exception{
		System.out.println("..........FreeListenHandler..........updateFreeListenByID()..........");
		String result = null;
		//TODO
		return result;
	}
	/**
	 * 通过qid和页码查询FreeListen信息
	 * @return
	 */
	@RequestMapping(value="/admin/selectFreeListenByQIDAndPage")
	@ResponseBody
	public String selectFreeListenByQIDAndPage() throws Exception{
		System.out.println("..........FreeListenHandler..........selectFreeListenByQIDAndPage()..........");
		String result = null;
		//TODO
		return result;
	}
}
