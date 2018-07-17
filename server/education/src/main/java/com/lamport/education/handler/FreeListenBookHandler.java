package com.lamport.education.handler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lamport.education.po.FreeListen;
import com.lamport.education.po.FreeListenBook;
import com.lamport.education.po.User;
import com.lamport.education.service.FreeListenBookService;
import com.lamport.education.util.PageBean;
import com.lamport.education.vo.FreeListenBookVo;

@Controller
public class FreeListenBookHandler {
	@Autowired
	FreeListenBookService freeListenBookService;
	
	@RequestMapping("/selectAllFreeListenBookByPageAndStatusAndUid")
	@ResponseBody
	public List<FreeListenBookVo> selectAllFreeListenBookByPageAndStatusAndUid(HttpServletRequest request) throws Exception{
		System.out.println("....FreeListenHandler......selectAllFreeListenBookByPageAndStatusAndUid........");
		int rowid = Integer.parseInt(request.getParameter("rowId"));
		PageBean pageBean = new PageBean(5,rowid);
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		int uid = user.getUid();	
		String status = request.getParameter("status");
		System.out.println("status........."+status);
		return freeListenBookService.selectFreeListenBookPageByUidAndStatus(pageBean, uid, status);
	}
	
	@RequestMapping("/saveFreeListenByLid")
	@ResponseBody
	public void saveFreeListenByLid(HttpServletRequest request) throws Exception{
		System.out.println("....FreeListenHandler......saveFreeListenByLid........");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		int uid = user.getUid();	
		int fid = Integer.parseInt(request.getParameter("fid"));
		String tel = request.getParameter("tel");
		String userName = request.getParameter("userName");
		String comment = request.getParameter("comment");
		FreeListenBook freeListenBook = new FreeListenBook();
		freeListenBook.setTel(tel);
		freeListenBook.setComment(comment);
		freeListenBook.setUid(uid);
		freeListenBook.setStatus("待处理");
		freeListenBook.setFid(fid);
	    freeListenBookService.saveFreeListenBook(freeListenBook);
		
	}
	
	
}
