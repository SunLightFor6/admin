package com.lamport.admin.handler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.lamport.admin.po.Admin;
import com.lamport.admin.po.FreeListenBook;
import com.lamport.admin.service.FreeListenBookService;
import com.lamport.admin.tool.Const;
import com.lamport.admin.vo.BookQueryCondition;

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
	public String processFreeListenBookByID(int id) throws Exception{
		System.out.println("..........FreeListenBookHandler..........processFreeListenBookByID()..........id = " + id);
		String result = null;

		FreeListenBook freeListenBook = new FreeListenBook();
		freeListenBook.setId(id);
		freeListenBook.setStatus(Const.BookStatusProcessed);
		int updateResult = freeListenBookService.updateFreeListenBookByID(freeListenBook);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("response", updateResult);
		result = jsonObject.toString();
		
		return result;
	}
	/**
	 * 通过BookQueryCondition查询FreeListenBook信息
	 * @return
	 */
	@RequestMapping(value="/admin/selectFreeListenBookByBookQueryCondition")
	@ResponseBody
	public String selectFreeListenBookByBookQueryCondition(BookQueryCondition bookQueryCondition, HttpServletRequest request) throws Exception{
		System.out.println("..........FreeListenBookHandler..........selectFreeListenBookByBookQueryCondition()..........bookQueryCondition: " + bookQueryCondition.getNickname() + " " + bookQueryCondition.getStatus() + " " + bookQueryCondition.getBeginTime() + " " + bookQueryCondition.getEndTime() + " " + bookQueryCondition.getFid());
		String result = null;
		
		HttpSession session = request.getSession();
		Admin admin = (Admin)session.getAttribute("admin");
		bookQueryCondition.setQid(admin.getQid());
		bookQueryCondition.setPageTool();
		List<FreeListenBook> freeListenBooks = freeListenBookService.selectFreeListenBookByBookQueryCondition(bookQueryCondition);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("code", 0);
		jsonObject.addProperty("msg", "");
		jsonObject.addProperty("count", bookQueryCondition.getPageTool().getCount());
		JsonArray jsonArray = new JsonArray();
		for(FreeListenBook freeListenBook : freeListenBooks){
			JsonObject object = new JsonObject();
			object.addProperty("bookcourseid", freeListenBook.getId());
			object.addProperty("freelistenid", freeListenBook.getFreeListen().getId());
			object.addProperty("bookcoursename", freeListenBook.getFreeListen().getTitle());
			object.addProperty("usernickname", freeListenBook.getUsername());
			object.addProperty("usertel", freeListenBook.getTel());
			object.addProperty("bookstatus", freeListenBook.getStatus());
			object.addProperty("booktime", freeListenBook.getBooktime());
			object.addProperty("message", freeListenBook.getComment());
			jsonArray.add(object);
		}
		jsonObject.add("data", jsonArray);
		result = jsonObject.toString();

		return result;
	}
	/**
	 * 通过BookQueryCondition查询未处理的FreeListenBook信息
	 * @return
	 */
	@RequestMapping(value="/admin/selectFreeListenBookUnprocessedByBookQueryCondition")
	@ResponseBody
	public String selectFreeListenBookUnprocessedByBookQueryCondition(BookQueryCondition bookQueryCondition, HttpServletRequest request) throws Exception{
		System.out.println("..........FreeListenBookHandler..........selectFreeListenBookUnprocessedByBookQueryCondition()..........bookQueryCondition: " + bookQueryCondition.getNickname() + " " + bookQueryCondition.getStatus() + " " + bookQueryCondition.getBeginTime() + " " + bookQueryCondition.getEndTime() + " " + bookQueryCondition.getFid());
		String result = null;
		
		HttpSession session = request.getSession();
		Admin admin = (Admin)session.getAttribute("admin");
		bookQueryCondition.setQid(admin.getQid());
		bookQueryCondition.setStatus(Const.BookStatusUnprocessed);
		bookQueryCondition.setPageTool();
		List<FreeListenBook> freeListenBooks = freeListenBookService.selectFreeListenBookByBookQueryCondition(bookQueryCondition);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("code", 0);
		jsonObject.addProperty("msg", "");
		jsonObject.addProperty("count", bookQueryCondition.getPageTool().getCount());
		JsonArray jsonArray = new JsonArray();
		for(FreeListenBook freeListenBook : freeListenBooks){
			JsonObject object = new JsonObject();
			object.addProperty("bookcourseid", freeListenBook.getId());
			object.addProperty("freelistenid", freeListenBook.getFid());
			object.addProperty("bookcoursename", freeListenBook.getFreeListen().getTitle());
			object.addProperty("usernickname", freeListenBook.getUsername());
			object.addProperty("usertel", freeListenBook.getTel());
			object.addProperty("bookstatus", freeListenBook.getStatus());
			object.addProperty("booktime", freeListenBook.getBooktime());
			object.addProperty("message", freeListenBook.getComment());
			jsonArray.add(object);
		}
		jsonObject.add("data", jsonArray);
		result = jsonObject.toString();

		return result;
	}
}
