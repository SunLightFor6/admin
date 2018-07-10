package com.lamport.admin.handler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lamport.admin.po.Address;
import com.lamport.admin.po.Admin;
import com.lamport.admin.po.Enterprise;
import com.lamport.admin.po.FreeListen;
import com.lamport.admin.po.FreeListenBook;
import com.lamport.admin.po.Lesson;
import com.lamport.admin.po.Message;
import com.lamport.admin.po.MessageImg;
import com.lamport.admin.po.MessageLike;
import com.lamport.admin.po.MessageReply;
import com.lamport.admin.po.Refund;
import com.lamport.admin.po.Sorder;
import com.lamport.admin.po.Swiper;
import com.lamport.admin.po.Teacher;
import com.lamport.admin.po.User;
import com.lamport.admin.service.LoginService;
import com.lamport.admin.service.TestService;

@Controller
public class TestHandler {
	@Autowired
	private TestService testService;
	@Autowired LoginService loginService;
	
	@RequestMapping(value="/test/TestAdminLogin")
	public String testAdminLogin() throws Exception{
		System.out.println("........TestHandler...........testAdminLogin()........");
		Admin admin = new Admin();
		admin.setAdminname("feiyy");
		admin.setPassword("123456");
		int result = loginService.isAdminLoginSuccessful(admin);
		
		System.out.println("\n" + "Login result = " + result + "\n");
		return "/test_show.jsp";
	}
	
	@RequestMapping(value="/test/TestHandler_selectSorderAll")
	@ResponseBody
	public String selectSorderAll() throws Exception{
		System.out.println("........TestHandler...........selectSorderAll()........");
		String result = null;
		
		List<Address> addresses = null;
		List<Admin> admins = null;
		List<Enterprise> enterprises = null;
		List<FreeListen> freeListens = null; 
		List<FreeListenBook> freeListenBooks = null;
		List<Lesson> lessons = null;
		List<Message> messages = null;
		List<MessageImg> messageImgs = null;
		List<MessageLike> messageLikes = null;
		List<MessageReply> messageReplies = null;
		List<Refund> refunds = null;
		List<Sorder> sorders = null;
		List<Swiper> swipers = null;
		List<Teacher> teachers = null;
		List<User> users = null;
		

		addresses = testService.selectAddressAll();
		admins = testService.selectAdminAll();
		enterprises = testService.selectEnterpriseAll();
		freeListens = testService.selectFreeListenAll();
		freeListenBooks = testService.selectFreeListenBookAll();
		lessons = testService.selectLessonAll();
		messages = testService.selectMessageAll();
		messageImgs = testService.selectMessageImgAll();
		messageLikes = testService.selectMessageLikeAll();
		messageReplies = testService.selectMessageReplyAll();
		refunds = testService.selectRefundAll();
		sorders  = testService.selectSorderAll();
		swipers = testService.selectSwiperAll();
		teachers = testService.selectTeacherAll();
		users = testService.selectUserAll();

		System.out.println();
		for(Admin admin: admins){
			System.out.println(admin.getAdminname() + "\t" + admin.getPassword() + "\t" + admin.getJurisdiction());
		}
		System.out.println();
		
		
		System.out.println("Yean! Everything is OK!");
		result = "/test_show.jsp";
		return result;
	}
}
