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
import com.lamport.education.po.FreeListen;
import com.lamport.education.po.User;
import com.lamport.education.service.FreeListenBookService;
import com.lamport.education.service.FreeListenService;
import com.lamport.education.util.Config;
import com.lamport.education.vo.FreeListenQueryCondition;

@Controller
public class FreeListenHandler {
	
	@Autowired
	FreeListenService freeListenService;
	@Autowired
	FreeListenBookService freeListenBookService;
	
	/**
	 * 通过fid查询FreeListen信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/selectFreeListenByID")
	@ResponseBody
	public String selectFreeListenByID(int id, HttpServletRequest request) throws Exception{
		System.out.println("..........FreeListenHandler..........selectFreeListenByID..........");
		String result = null;

		FreeListen freeListen = freeListenService.selectFreeListenByFid(id);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("id", freeListen.getId());
		jsonObject.addProperty("title", freeListen.getTitle());
		jsonObject.addProperty("branch", freeListen.getAddress().getBranch());
		jsonObject.addProperty("category", freeListen.getCategory());
		jsonObject.addProperty("status", freeListen.getStatus());
		jsonObject.addProperty("imgurl", freeListen.getImgurl());
		jsonObject.addProperty("fdesc", freeListen.getFdesc());
		result = jsonObject.toString();
		
		return result;
	}

	/**
	 * 通过多条件查询FreeListen信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/selectFreeListenByFreeListenQueryCondition")
	@ResponseBody
	public String selectFreeListenByFreeListenQueryCondition(FreeListenQueryCondition freeListenQueryCondition, HttpServletRequest request) throws Exception{
		System.out.println("..........FreeListenHandler..........selectFreeListenByFreeListenQueryCondition..........");
		String result = null;
		
		HttpSession session = request.getSession();	
		int qid = (Integer)session.getAttribute("qid");
		freeListenQueryCondition.setQid(qid);
		freeListenQueryCondition.initPageBean(Config.FreeListenPageSize);
		List<FreeListen> freeListens = freeListenService.selectFreeListenByFreeListenQueryCondition(freeListenQueryCondition);
		JsonObject jsonObject = new JsonObject();
		JsonArray jsonArray = new JsonArray();
		if(freeListens!=null && !freeListens.isEmpty()){
			for(FreeListen freeListen : freeListens){
				JsonObject object = new JsonObject();
				object.addProperty("fid", freeListen.getId());
				object.addProperty("imgurl", freeListen.getImgurl());
				object.addProperty("title", freeListen.getTitle());
				object.addProperty("fdesc", freeListen.getFdesc());
				jsonArray.add(object);
			}
		}
		jsonObject.add("freelistens", jsonArray);
		result = jsonObject.toString();

		return result;
	}

	/**
	 * 通过id查询预约时所需要的信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/selectReserveInfoByID")
	@ResponseBody
	public String selectReserveInfoByID(int id, HttpServletRequest request) throws Exception{
		System.out.println("..........FreeListenHandler..........selectReserveInfoByID..........");
		String result = null;
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		FreeListen freeListen = freeListenService.selectFreeListenByFid(id);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("id", freeListen.getId());
		jsonObject.addProperty("title", freeListen.getTitle());
		jsonObject.addProperty("branch", freeListen.getAddress().getBranch());
		jsonObject.addProperty("username", user.getUsername());
		jsonObject.addProperty("tel", user.getTel());
		result = jsonObject.toString();
		
		return result;
	}
	
	
	
	
	
	
//	/** 
//	 * @param request 
//	 * 传入uid以及currentPage 来获取预约课程
//	 * @return
//	 * @throws Exception 
//	 */
//	@RequestMapping("/selectHomePageFreeListenByQid")
//	@ResponseBody
//	public List<FreeListen> selectHomePageFreeListenByQid(HttpServletRequest request) throws Exception{
//		System.out.println("....FreeListenHandler.....selectAllFreeListen..........");
//		// 需要更改，等redis 学完后配合redis进行更改
//		PageBean pageBean = new PageBean(3,0);
//		HttpSession session = request.getSession();	
//		int qid = (Integer)session.getAttribute("qid");
//		return freeListenService.selectHomePageFreeListenByQid(pageBean, qid);
//	}
}
