package com.lamport.admin.handler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.lamport.admin.mapper.AddressMapper;
import com.lamport.admin.po.Address;
import com.lamport.admin.po.Admin;
import com.lamport.admin.po.FreeListen;
import com.lamport.admin.po.Lesson;
import com.lamport.admin.service.FreeListenService;
import com.lamport.admin.vo.FreeListenQueryCondition;

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
	public String saveFreeListen(FreeListen freeListen, MultipartFile imgFile, HttpServletRequest request) throws Exception{
		System.out.println("..........FreeListenHandler..........saveFreeListen()..........freeListen:" + freeListen.getTitle() + " imgFile" + imgFile);
		String result = null;
		
		HttpSession session = request.getSession();
		Admin admin = (Admin)session.getAttribute("admin");
		freeListen.setQid(admin.getQid());
		String path = request.getServletContext().getRealPath("/");//得到当前工程的根路径
		int saveResult = freeListenService.saveFreeListen(freeListen, imgFile, path);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("reponse", saveResult);
		result = jsonObject.toString();
		
		return result;	
	}
	/**
	 * 通过id逻辑删除FreeListen信息
	 * @return
	 */
	@RequestMapping(value="/admin/deleteFreeListenLogicallyByID")
	@ResponseBody
	public String deleteFreeListenLogicallyByID(int id) throws Exception{
		System.out.println("..........FreeListenHandler..........deleteFreeListenLogicallyByID()..........id = " + id);
		String result = null;
		
		int deleteResult = freeListenService.deleteFreeListenLogicallyByID(id);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("response", deleteResult);
		result = jsonObject.toString();
		
		return result;
	}
	/**
	 * 通过id修改FreeListen信息
	 * @return
	 */
	@RequestMapping(value="/admin/updateFreeListenByID")
	@ResponseBody
	public String updateFreeListenByID(FreeListen freeListen, MultipartFile imgFile, HttpServletRequest request) throws Exception{
		System.out.println("..........FreeListenHandler..........updateFreeListenByID()..........freeListen:" + freeListen.getTitle() + " imgFile" + imgFile);
		String result = null;
		
		String path = request.getServletContext().getRealPath("/");//得到当前工程的根路径
		int updateResult = freeListenService.updateFreeListenByID(freeListen, imgFile, path);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("reponse", updateResult);
		result = jsonObject.toString();
		
		return result;
	}
	/**
	 * 通过多条件查询FreeListen信息
	 * @return
	 */
	@RequestMapping(value="/admin/selectFreeListenByFreeListenQueryCondition")
	@ResponseBody
	public String selectFreeListenByFreeListenQueryCondition(FreeListenQueryCondition freeListenQueryCondition, HttpServletRequest request) throws Exception{
		System.out.println("..........FreeListenHandler..........selectFreeListenByFreeListenQueryCondition()..........freeListenQueryCondition:" + freeListenQueryCondition.getBranch());
		String result = null;
		
		HttpSession session = request.getSession();
		
		Admin admin = (Admin)session.getAttribute("admin");
		freeListenQueryCondition.setQid(admin.getQid());
		List<FreeListen> freeListens = freeListenService.selectFreeListenByFreeListenQueryCondition(freeListenQueryCondition);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("code", 0);
		jsonObject.addProperty("msg", "");
		jsonObject.addProperty("count", freeListenQueryCondition.getPageTool().getCount());
		JsonArray jsonArray = new JsonArray();
		
		for(FreeListen freeListen : freeListens) {
			JsonObject object = new JsonObject();
			object.addProperty("courseid", freeListen.getId());
			object.addProperty("coursename", freeListen.getTitle());
			object.addProperty("courseimg", freeListen.getImgurl());
			object.addProperty("coursecategory", freeListen.getCategory());
			object.addProperty("branch", freeListen.getBranch().getBranch());			
			object.addProperty("pubtime", freeListen.getPubtime());
			object.addProperty("fdesc", freeListen.getFdesc());
			object.addProperty("status", freeListen.getStatus());
			jsonArray.add(object);
		}
		
		return result;
	}
}
