package com.lamport.education.handler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.lamport.education.po.FreeListenBook;
import com.lamport.education.po.User;
import com.lamport.education.service.FreeListenBookService;
import com.lamport.education.util.Config;
import com.lamport.education.vo.BookQueryCondition;

@Controller
public class FreeListenBookHandler {
	
	@Autowired
	FreeListenBookService freeListenBookService;
	
	/**
	 * 创建FreeListenBook信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/saveFreeListenBook")
	@ResponseBody
	public String saveFreeListenBook(FreeListenBook freeListenBook, HttpServletRequest request) throws Exception{
		System.out.println("..........FreeListenHandler..........saveFreeListenBook..........");
		String result = null;
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		freeListenBook.setUid(user.getUid());
		freeListenBook.setStatus(Config.BookStatusUnprocessed);
	    freeListenBookService.saveFreeListenBook(freeListenBook);
	    JsonObject jsonObject = new JsonObject();
	    jsonObject.addProperty("state", 1);
	    result = jsonObject.toString();
	    
	    return result;
	}
	
	/**
	 * 删除已处理的预约
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteFreeListenBookProcessed")
	@ResponseBody
	public String deleteFreeListenBookProcessed(FreeListenBook freeListenBook) throws Exception{
		System.out.println("..........FreeListenHandler..........deleteFreeListenBookProcessed..........");
		String result = null;
		
		freeListenBookService.deleteFreeListenBookLogicallyById(freeListenBook);
		JsonObject jsonObject = new JsonObject();
	    jsonObject.addProperty("state", 1);
	    result = jsonObject.toString();
		
		return result;
	}
	
	@RequestMapping(value="/cancelFreeListenBookUnprocessed")
	@ResponseBody
	public String cancelFreeListenBookUnprocessed(FreeListenBook freeListenBook) throws Exception{
		System.out.println("..........FreeListenHandler..........cancelFreeListenBookUnprocessed..........");
		String result = null;
		
		freeListenBookService.deleteFreeListenBookPowerfullyById(freeListenBook);
		JsonObject jsonObject = new JsonObject();
	    jsonObject.addProperty("state", 1);
	    result = jsonObject.toString();
		
		return result;
	}

	/**
	 * 通过多条件查询FreeListenBook信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/selectFreeListenBookByBookQueryCondition")
	@ResponseBody
	public String selectFreeListenBookByBookQueryCondition(BookQueryCondition bookQueryCondition, HttpServletRequest request) throws Exception{
		System.out.println("..........FreeListenHandler..........selectFreeListenBookByBookQueryCondition..........");
		String result = null;
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		bookQueryCondition.setUid(user.getUid());
		bookQueryCondition.initPageBean(Config.BookPageSize);
		System.out.println("status = " + bookQueryCondition.getStatus());			/*####################*/
		List<FreeListenBook> freeListenBooks = freeListenBookService.selectFreeListenBookByBookQueryCondition(bookQueryCondition);
		JsonObject jsonObject = new JsonObject();
		JsonArray jsonArray = new JsonArray();
		if(freeListenBooks!=null && !freeListenBooks.isEmpty()){
			for(FreeListenBook freeListenBook : freeListenBooks){
				JsonObject object = new JsonObject();
				object.addProperty("id", freeListenBook.getId());
				object.addProperty("booktime", freeListenBook.getBooktime());
				object.addProperty("status", freeListenBook.getBooktime());
				object.addProperty("title", freeListenBook.getFreeListen().getTitle());
				object.addProperty("imgurl", freeListenBook.getFreeListen().getImgurl());
				jsonArray.add(object);
			}
		}
		jsonObject.add("freelistenbooks", jsonArray);
		result = jsonObject.toString();

		return result;
	}
	
	/**
	 * 通过多条件查询已处理的FreeListenBook信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/selectFreeListenBookProcessedByBookQueryCondition")
	@ResponseBody
	public String selectFreeListenBookProcessedByBookQueryCondition(BookQueryCondition bookQueryCondition, HttpServletRequest request) throws Exception{
		System.out.println("..........FreeListenHandler..........selectFreeListenBookProcessedByBookQueryCondition..........");
		String result = null;
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		bookQueryCondition.setUid(user.getUid());
		bookQueryCondition.setStatus(Config.BookStatusProcessed);
		bookQueryCondition.initPageBean(Config.BookPageSize);
		System.out.println("status = " + bookQueryCondition.getStatus());			/*####################*/
		List<FreeListenBook> freeListenBooks = freeListenBookService.selectFreeListenBookByBookQueryCondition(bookQueryCondition);
		JsonObject jsonObject = new JsonObject();
		JsonArray jsonArray = new JsonArray();
		if(freeListenBooks!=null && !freeListenBooks.isEmpty()){
			for(FreeListenBook freeListenBook : freeListenBooks){
				JsonObject object = new JsonObject();
				object.addProperty("id", freeListenBook.getId());
				object.addProperty("booktime", freeListenBook.getBooktime());
				object.addProperty("status", freeListenBook.getBooktime());
				object.addProperty("title", freeListenBook.getFreeListen().getTitle());
				object.addProperty("imgurl", freeListenBook.getFreeListen().getImgurl());
				jsonArray.add(object);
			}
		}
		jsonObject.add("freelistenbooks", jsonArray);
		result = jsonObject.toString();
		
		return result;
	}
	
	/**
	 * 通过多条件查询待处理的FreeListenBook信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/selectFreeListenBookUnprocessedByBookQueryCondition")
	@ResponseBody
	public String selectFreeListenBookUnprocessedByBookQueryCondition(BookQueryCondition bookQueryCondition, HttpServletRequest request) throws Exception{
		System.out.println("..........FreeListenHandler..........selectFreeListenBookUnprocessedByBookQueryCondition..........");
		String result = null;
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		bookQueryCondition.setUid(user.getUid());
		bookQueryCondition.setStatus(Config.BookStatusUnprocessed);
		bookQueryCondition.initPageBean(Config.BookPageSize);
		System.out.println("status = " + bookQueryCondition.getStatus());			/*####################*/
		List<FreeListenBook> freeListenBooks = freeListenBookService.selectFreeListenBookByBookQueryCondition(bookQueryCondition);
		JsonObject jsonObject = new JsonObject();
		JsonArray jsonArray = new JsonArray();
		if(freeListenBooks!=null && !freeListenBooks.isEmpty()){
			for(FreeListenBook freeListenBook : freeListenBooks){
				JsonObject object = new JsonObject();
				object.addProperty("id", freeListenBook.getId());
				object.addProperty("booktime", freeListenBook.getBooktime());
				object.addProperty("status", freeListenBook.getBooktime());
				object.addProperty("title", freeListenBook.getFreeListen().getTitle());
				object.addProperty("imgurl", freeListenBook.getFreeListen().getImgurl());
				jsonArray.add(object);
			}
		}
		jsonObject.add("freelistenbooks", jsonArray);
		result = jsonObject.toString();
		
		return result;
	}
	
/*--------------------------------------------------2018.08.02 10:50--------------------------------------------------*/

	@RequestMapping(value="")
	@ResponseBody
	public String selectFreeListenBookById(int id) throws Exception{
		System.out.println("..........FreeListenHandler..........selectFreeListenBookUnprocessedByBookQueryCondition..........");
		String result = null;
		
		String nullString = null;
		FreeListenBook freeListenBook = freeListenBookService.selectFreeListenBookByID(id);
		JsonObject  jsonObject = new JsonObject();
		jsonObject.addProperty("id", freeListenBook.getId());
		if(freeListenBook!=null && freeListenBook.getFreeListen()!=null){
			jsonObject.addProperty("title", freeListenBook.getFreeListen().getTitle());
			jsonObject.addProperty("imgurl", freeListenBook.getFreeListen().getImgurl());
			if(freeListenBook.getFreeListen().getAddress()!=null){
				jsonObject.addProperty("branch", freeListenBook.getFreeListen().getAddress().getBranch());
			}else{
				jsonObject.addProperty("branch", nullString);
			}
		}else{
			jsonObject.addProperty("title", nullString);
			jsonObject.addProperty("imgurl", nullString);
			jsonObject.addProperty("branch", nullString);
		}
		jsonObject.addProperty("status", freeListenBook.getStatus());
		jsonObject.addProperty("booktime", freeListenBook.getBooktime());
		jsonObject.addProperty("comment", freeListenBook.getComment());

		return result;
	}
}
