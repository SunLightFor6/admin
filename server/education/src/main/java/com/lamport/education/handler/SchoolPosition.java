package com.lamport.education.handler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lamport.education.po.Address;
import com.lamport.education.po.User;
import com.lamport.education.service.AddressService;
import com.lamport.education.util.PageBean;

@Controller
public class SchoolPosition {
	@Autowired
	AddressService addressService;
	
	
	@RequestMapping("/selectSchoolPostionByQidAndPage")
	@ResponseBody
	public List<Address> findSchoolPostionByQid(HttpServletRequest request) throws Exception{
		System.out.println("....SchoolPosition......./selectSchoolPostionByQidAndPage..........");
		PageBean page = new PageBean(5, -1);
		HttpSession session = request.getSession();	
		int qid = (Integer)session.getAttribute("qid");
		return addressService.selectAddressPageByQid(page, qid);
		
	}
}
