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
import com.lamport.education.service.FreeListenBookService;
import com.lamport.education.service.FreeListenService;
import com.lamport.education.util.PageBean;
import com.lamport.education.vo.LessonInfoVo;

@Controller
public class FreeListenHandler {
	@Autowired
	FreeListenService freeListenService;
	@Autowired
	FreeListenBookService freeListenBookService;
	/**
	 * 传入uid以及currentPage 来获取预约课程
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/selectAllFreeListenByQidAndPage")
	@ResponseBody
	public List<FreeListen> selectAllFreeListen(HttpServletRequest request) throws Exception{
		System.out.println("....FreeListenHandler.....selectAllFreeListen..........");
		int fid = Integer.parseInt(request.getParameter("id"));
		PageBean pageBean = new PageBean(5, fid);

		HttpSession session = request.getSession();	
		int qid = (Integer)session.getAttribute("qid");
		return freeListenService.selectFreeListenPage(pageBean, qid);
	}
	
	 
	
	@RequestMapping("/selectAllFreeListenByBranchNameAndCategoryAndQid")
	@ResponseBody
	public List<FreeListen> selectAllFreeListenByBranchNameAndCategoryAndQid(HttpServletRequest request) throws Exception{
		System.out.println("....FreeListenHandler......selectAllFreeListenByBranchNameAndCategoryAndQid.........");
		int rowId = Integer.parseInt(request.getParameter("rowId"));
		PageBean pageBean = new PageBean(2, rowId);
		HttpSession session = request.getSession();	
		int qid = (Integer)session.getAttribute("qid");
		String branchName =request.getParameter("branchName");
		String category =request.getParameter("category");
		return freeListenService.selectFreeListenPageByBranchNameAndCategory(pageBean, qid, branchName, category);
	}
	
	@RequestMapping("/selectFreeListenByFid")
	@ResponseBody
	public LessonInfoVo  selectFreeListenByFid(HttpServletRequest request) throws Exception{
		System.out.println("....FreeListenHandler......selectFreeListenByFid..........");
		int fid = Integer.parseInt(request.getParameter("fid"));
		return freeListenService.selectFreeListenByFid(fid);
	}
}
