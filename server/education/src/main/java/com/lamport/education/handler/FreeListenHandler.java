package com.lamport.education.handler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lamport.education.po.FreeListen;
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
	 * @param request 
	 * 传入uid以及currentPage 来获取预约课程
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/selectHomePageFreeListenByQid")
	@ResponseBody
	public List<FreeListen> selectHomePageFreeListenByQid(HttpServletRequest request) throws Exception{
		System.out.println("....FreeListenHandler.....selectAllFreeListen..........");
		// 需要更改，等redis 学完后配合redis进行更改
		PageBean pageBean = new PageBean(3,0);
		HttpSession session = request.getSession();	
		int qid = (Integer)session.getAttribute("qid");
		return freeListenService.selectHomePageFreeListenByQid(pageBean, qid);
	}
	
	@RequestMapping("/selectFreeListenByBranchNameAndCategoryAndQidAndPage")
	@ResponseBody
	public List<FreeListen> selectFreeListenByBranchNameAndCategoryAndQidAndPage(HttpServletRequest request) throws Exception{
		System.out.println("....FreeListenHandler......selectFreeListenPageByBranchNameAndCategoryAndQid.........");
		int rowId = Integer.parseInt(request.getParameter("rowId"));
		PageBean pageBean = new PageBean(2, rowId);
		HttpSession session = request.getSession();	
		int qid = (Integer)session.getAttribute("qid");
		String branchName =request.getParameter("branchName");
		String category =request.getParameter("category");
		return freeListenService.selectFreeListenByBranchNameAndCategoryAndPage(pageBean, qid, branchName, category);
	}
	
	@RequestMapping("/selectFreeListenByFid")
	@ResponseBody
	public LessonInfoVo  selectFreeListenByFid(HttpServletRequest request) throws Exception{
		System.out.println("....FreeListenHandler......selectFreeListenByFid..........");
		int fid = Integer.parseInt(request.getParameter("fid"));
		return freeListenService.selectFreeListenByFid(fid);
	}

}
