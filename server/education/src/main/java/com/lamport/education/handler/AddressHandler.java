package com.lamport.education.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.lamport.education.service.AddressService;


@Controller
public class AddressHandler {
	
	@Autowired
	AddressService addressService;
	
	@RequestMapping("/selectAllCategoryByQid")
	@ResponseBody
	public String selectAllCategoryByQid(HttpServletRequest request) throws Exception{
		System.out.println("..........BranchHandler..........selectAllCategoryByQid..........");
		String result = null;
		
		HttpSession session = request.getSession();	
		int qid = (Integer)session.getAttribute("qid");
		System.out.println("qid = " + qid);					/*####################*/
		
		
		
		return result;
//		return addressService.selectAllCategoryByQid(qid);
	}
	
}
