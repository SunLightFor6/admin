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
import com.lamport.admin.po.Address;
import com.lamport.admin.po.Admin;
import com.lamport.admin.service.AddressService;
import com.lamport.admin.vo.QIDAndPage;

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
	public String saveBranch(Address address, HttpServletRequest request) throws Exception{
		System.out.println("..........BranchHandler..........saveBranch()..........address:" + address.getAddress() + " " + address.getBranch() + " " + address.getLatitude() + " " + address.getLongitude());
		String result = null;
		
		HttpSession session = request.getSession();
		Admin admin = (Admin)session.getAttribute("admin");
		address.setQid(admin.getQid());
		int saveResult = addressService.saveAddress(address);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("reponse", saveResult);
		result = jsonObject.toString();
		
		return result;
	}
	/**
	 * 通过id逻辑删除Address信息
	 * @return
	 */
	@RequestMapping(value="/admin/deleteBranchLogicallyByID")
	@ResponseBody
	public String deleteBranchLogicallyByID(int id) throws Exception{
		System.out.println("..........BranchHandler..........deleteBranchLogicallyByID()..........id = " + id);
		String result = null;
		
		int deleteResult = addressService.deleteAddressLogicallyByID(id);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("response", deleteResult);
		result = jsonObject.toString();
		
		return result;
	}
	/**
	 * 通过id修改Address信息
	 * @return
	 */
	@RequestMapping(value="/admin/updateBranchByID")
	@ResponseBody
	public String updateBranchByID(Address address) throws Exception{
		System.out.println("..........BranchHandler..........updateBranchByID()..........address:" + address.getAddress() + " " + address.getBranch() + " " + address.getLatitude() + " " + address.getLongitude());
		String result = null;

		int updateResult = addressService.updateAddressByID(address);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("response", updateResult);
		result = jsonObject.toString();
		
		return result;
	}
	/**
	 * 通过qid和页码查询Address信息
	 * @return
	 */
	@RequestMapping(value="/admin/selectBranchByQIDAndPage")
	@ResponseBody
	public String selectBranchByQIDAndPage(QIDAndPage qidAndPage, HttpServletRequest request) throws Exception{
		System.out.println("..........BranchHandler..........selectBranchByQIDAndPage()..........qidAndPage:" + qidAndPage.getPageTool().getPage() + " " + qidAndPage.getPageTool().getLimit());
		String result = null;

		HttpSession session = request.getSession();
		Admin admin = (Admin)session.getAttribute("admin");
		qidAndPage.setQid(admin.getQid());
		List<Address> addresses = addressService.selectAddressByQIDAndPage(qidAndPage);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("code", 0);
		jsonObject.addProperty("msg", "");
		jsonObject.addProperty("count", qidAndPage.getPageTool().getCount());
		JsonArray jsonArray = new JsonArray();
		for(Address address : addresses){
			JsonObject object = new JsonObject();
			object.addProperty("id", address.getId());
			object.addProperty("branch", address.getBranch());
			object.addProperty("address", address.getAddress());
			object.addProperty("tel", address.getTel());
			object.addProperty("longitude", address.getLongitude());
			object.addProperty("latitude", address.getLatitude());
			jsonArray.add(object);
		}
		jsonObject.add("data", jsonArray);
		result = jsonObject.toString();
		
		return result;
	}
	/**
	 * 通过qid查询Address信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/admin/selectBranchByQID")
	@ResponseBody
	public String selectBranchByQID(HttpServletRequest request) throws Exception{
		System.out.println("..........BranchHandler..........selectBranchByQID()..........");
		String result = null;

		HttpSession session = request.getSession();
		Admin admin = (Admin)session.getAttribute("admin");
		int qid = admin.getQid();
		List<Address> addresses = addressService.selectAddressByQID(qid);
		JsonObject jsonObject = new JsonObject();
		JsonArray jsonArray = new JsonArray();
		for(Address address : addresses){
			JsonObject object = new JsonObject();
			object.addProperty("id", address.getId());
			object.addProperty("branch", address.getBranch());
			jsonArray.add(object);
		}
		jsonObject.add("branches", jsonArray);
		result = jsonObject.toString();
		
		return result;
	}
}
