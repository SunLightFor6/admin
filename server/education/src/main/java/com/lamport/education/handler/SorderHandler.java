package com.lamport.education.handler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lamport.education.mapper.LessonMapper;
import com.lamport.education.mapper.SorderMapper;
import com.lamport.education.po.Lesson;
import com.lamport.education.po.Refund;
import com.lamport.education.po.Sorder;
import com.lamport.education.po.User;
import com.lamport.education.service.SorderService;
import com.lamport.education.util.PageBean;
import com.lamport.education.vo.LessonInfoVo;
import com.lamport.education.vo.SorderInfoVo;

@Controller
public class SorderHandler {
	@Autowired
	SorderService sorderService;
	@Autowired
	LessonMapper lessonMapper;
	
	/**
	 * @param request 发来的请求，获取uid  currentPage status
	 * @return 
	 * @throws Exception 
	 * 
	 */
	@RequestMapping("/selectOrdersByUidAndStatus")
	@ResponseBody
	public  List<SorderInfoVo> selectOrdersByUidAndStatus(HttpServletRequest request) throws Exception{
		System.out.println("....SorderHandler......selectOrdersByUidAndStatus......");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		int uid = user.getUid();		
		System.out.println("uid"+uid);
		int rowId = Integer.parseInt(request.getParameter("rowId"));
		PageBean pageBean = new PageBean(5, rowId );
		String status =request.getParameter("status");
		
		return sorderService.selectSorderPageByUidAndStatus(pageBean, uid, status);
		
	}

	
	/**
	 * @param request 发来的请求，获取uid  lid username tel  调用 savePaymentInfo //添加待付款记录
	 * @return 
	 * @throws Exception 
	 * 
	 */
	@RequestMapping("/saveSorder")
	@ResponseBody
	public  void saveSorder(HttpServletRequest request) throws Exception{
		System.out.println("....LessionHandler.....saveSorder.......");
		int lid = Integer.parseInt(request.getParameter("lessonId"));
		int oid = Integer.parseInt(request.getParameter("oid"));
		if(lid==0)
			lid = sorderService.selectSorderByOid(oid).getLid();
		String userName = request.getParameter("userName");
		String tel = request.getParameter("tel");
		Sorder sorder =new Sorder();
		sorder.setOid(oid);
		sorder.setLid(lid);
		System.out.println("lid="+lid);
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
        int uid = user.getUid();	
        int qid = Integer.parseInt((String)session.getAttribute("qid"));
		sorder.setUid(uid);
		sorder.setStatus("已付款");
		LessonInfoVo lessonInfoVo = lessonMapper.selectLessonByLid(lid);
		sorder.setActual(lessonInfoVo.getPrice());
		sorder.setTotal(lessonInfoVo.getPrice());
		sorder.setBranchid(lessonInfoVo.getBid());
		sorder.setTel(tel);
		sorder.setUsername(userName);
		sorder.setQid(qid);
		sorderService.savePaymentInfo(sorder);
	}
	

	@RequestMapping("/bookSorder")
	@ResponseBody
	public  String bookSorder(HttpServletRequest request) throws Exception{
		System.out.println("....SorderHandler.....bookSorder.....");
		int lid = Integer.parseInt(request.getParameter("lid"));
		int oid = 0;
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		int qid = (Integer)session.getAttribute("qid");
		int uid = user.getUid();	
		Sorder sorder = new Sorder(); 
		sorder.setOid(0);
		sorder.setLid(lid);
		sorder.setUid(uid);
		sorder.setStatus("待付款");
		LessonInfoVo lessonInfoVo =  lessonMapper.selectLessonByLid(lid);
		sorder.setActual(0);
		sorder.setQid(qid);
		sorder.setTotal(lessonInfoVo.getPrice());
		sorder.setBranchid(lessonInfoVo.getBid());
		sorderService.saveObligation(sorder);
		return null;
	}
	
	
	
	
	/**
	 * @param request 发来的请求，获取mid  调用 deleteObligationLogicallyByOid// 来删除
	 * @return 
	 * @throws Exception 
	 * 
	 */
	@RequestMapping("/cancelUnpaidSorder")
	@ResponseBody
	public  String cancelUnpaidSorder(HttpServletRequest request) throws Exception{
		System.out.println("....SorderHandler.....cancelUnpaidSorder.....");
		int oid = Integer.parseInt(request.getParameter("oid"));
		sorderService.deleteObligationLogicallyByOid(oid);
		return null;
	}
	
	
	/**
	 * @param request 发来的请求，获取mid  调用 saveRefundRequest(Refund refund,Sorder sorder) // 来添加退款记录,并修改order状态
	 * order状态修改在这里设置为退款中
	 * @return 
	 * @throws Exception 
	 * 
	 */
	@RequestMapping("/addRefundRecord")
	@ResponseBody
	public  void addRefundRecord(HttpServletRequest request) throws Exception{
		System.out.println("....SorderHandler....addRefundRecord....");
		int oid = Integer.parseInt(request.getParameter("oid"));
		sorderService.saveRefundRequest(oid);
	}
	
	
	/**
	 * @param request 发来的请求，获取mid  调用 deleteRefundRequestLogicallyByOid // 来添加退款记录,并修改order状态
	 * order状态修改在这里设置为退款中
	 * @return 
	 * @throws Exception 
	 * 
	 */
	@RequestMapping("/cancelRefundRequest")
	@ResponseBody
	public  void cancelRefundRequest(HttpServletRequest request) throws Exception{
		System.out.println("....SorderHandler....cancelRefundRequest...");
		int oid = Integer.parseInt(request.getParameter("oid"));
		sorderService.deleteRefundRequestLogicallyByOid(oid);
	}
	
	
}
