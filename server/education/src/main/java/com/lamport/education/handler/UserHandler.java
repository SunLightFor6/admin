package com.lamport.education.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.JsonObject;
import com.lamport.education.po.User;
import com.lamport.education.service.FreeListenBookService;
import com.lamport.education.service.SorderService;
import com.lamport.education.service.UserService;
import com.lamport.education.util.Config;
import com.lamport.education.vo.UIDAndStatus;

@Controller
public class UserHandler {
	
	@Autowired
	UserService userService;
	@Autowired
	FreeListenBookService freeListenBookService;
	@Autowired
	SorderService sorderService;
	
	/**
	 * 更新user信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/updateUser")
	@ResponseBody
	public String updateUser(User u, HttpServletRequest request) throws Exception{
		System.out.println("..........UserHandler..........updateUser..........");
		String result = null;
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		user.setTel(u.getTel());
		user.setNickname(u.getNickname());
		user.setUsername(u.getUsername());
		userService.updateUser(user);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("state", 1);
		result = jsonObject.toString();
		
		return result;
	}

	/**
	 * 更新user的userimg信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/updateUserimg")
	@ResponseBody
	public String updateUserimg(@RequestParam MultipartFile newUserimg, HttpServletRequest request) throws Exception{
		System.out.println("..........UserHandler..........updateUserimg..........");
		String result = null;
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		userService.updateUserimg(user, newUserimg);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("state", 1);
		jsonObject.addProperty("userimg", user.getUserimg());
		result = jsonObject.toString();
		
		return result;
	}
	
	/**
	 * 通过uid查询User信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/selectUserByUID")
	@ResponseBody
	public String selectUserByUID(HttpServletRequest request) throws Exception{
		System.out.println("..........UserHandler..........selectUserByUID..........");
		String result = null;
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("uid");
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("tel", user.getTel());
		jsonObject.addProperty("nickname", user.getNickname());
		jsonObject.addProperty("username", user.getUsername());
		jsonObject.addProperty("userimg", user.getUserimg());
		result = jsonObject.toString();

		return result;
	}
	
	/**
	 * 获取user的nickname, userimg，如果user为null，则这两个属性均为null
	 * 获取user的待处理的预约和待付款的订单，如果user为null，则这两个属性均为0
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("selectMyOwnInfo")
	@ResponseBody
	public String selectMyOwnInfo(HttpServletRequest request) throws Exception{
		System.out.println("..........UserHandler..........selectMyOwnInfo..........");
		String result = null;
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user == null) {
			user = new User();
			user.setUid(-1);
		}
		UIDAndStatus uidAndStatus = new UIDAndStatus();
		uidAndStatus.setUid(user.getUid());
		uidAndStatus.setStatus(Config.BookStatusUnprocessed);
		int countBookUnprocessed = freeListenBookService.selectCountBookByUIDAndStatus(uidAndStatus);
		uidAndStatus.setStatus(Config.SorderStatusUnpaid);
		int countSorderUnpaid = sorderService.selectCountSorderByUIDAndStatus(uidAndStatus);
		
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("nickname", user.getNickname());
		jsonObject.addProperty("uid", user.getUid());
		jsonObject.addProperty("username", user.getUsername());
		jsonObject.addProperty("userimg", user.getUserimg());
		jsonObject.addProperty("countBookUnprocessed", countBookUnprocessed);
		jsonObject.addProperty("countSorderUnpaid", countSorderUnpaid);
		result = jsonObject.toString();

		return result;
	}
	
	
	
	

	

	
	
//	@RequestMapping("/selectUserByUserIdAndTel")
//	@ResponseBody
//	public User selectUserByUserIdAndTel(HttpServletRequest request) throws Exception{
//		System.out.println("....UserHandler.....selectUserByUserIdAndTel.......");
//		HttpSession session = request.getSession();
//		int qid = (Integer)session.getAttribute("qid");
//		String tel = request.getParameter("tel");
//		return userService.selectLoginInfoByQidAndTel(qid, tel);
//	}
 
//	@RequestMapping("/selectUserByUid")
//	@ResponseBody
//	public User selectUserByUid(HttpServletRequest request) throws Exception{
//		System.out.println("..........EnterpriseHandler..........selectUserNameById..........");
//		
//		HttpSession session = request.getSession();
//		User user = (User) session.getAttribute("user");
//		int uid = user.getUid();		
//		
//		return userService.selectUserByUid(uid);
//	}
}
