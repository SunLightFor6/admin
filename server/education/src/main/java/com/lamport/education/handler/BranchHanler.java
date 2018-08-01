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
import com.lamport.education.po.Address;
import com.lamport.education.service.AddressService;

@Controller
public class BranchHanler {
	
	@Autowired
	AddressService addressService;
	
	@RequestMapping("/selectBranchByQID")
	@ResponseBody
	public String selectBranchByQID(HttpServletRequest request) throws Exception{
		System.out.println("..........BranchHanler..........selectBranchByQID..........");
		String result = null;
		
		HttpSession session = request.getSession();	
		int qid = (Integer)session.getAttribute("qid");
		List<Address> addresses = addressService.selectAddressByQid(qid);
		JsonObject jsonObject = new JsonObject();
		JsonArray jsonArray = new JsonArray();
		if(addresses!=null && !addresses.isEmpty()){
			for(Address address : addresses){
				JsonObject object = new JsonObject();
				object.addProperty("branch", address.getBranch());
				object.addProperty("address", address.getAddress());
				object.addProperty("tel", address.getTel());
				object.addProperty("longitude", address.getLongitude());
				object.addProperty("latitude", address.getLatitude());
				jsonArray.add(object);
			}
		}
		jsonObject.add("branches", jsonArray);
		result = jsonObject.toString();
		
		return result;
	}

}
