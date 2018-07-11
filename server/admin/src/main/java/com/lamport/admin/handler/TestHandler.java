package com.lamport.admin.handler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
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
import com.lamport.admin.service.EnterpriseService;
import com.lamport.admin.service.FreeListenBookService;
import com.lamport.admin.service.FreeListenService;
import com.lamport.admin.service.LessonService;
import com.lamport.admin.service.LoginService;
import com.lamport.admin.service.SorderService;
import com.lamport.admin.service.SwiperService;
import com.lamport.admin.service.TeacherService;
import com.lamport.admin.service.TestService;
import com.lamport.admin.service.UserService;
import com.lamport.admin.tool.Const;
import com.lamport.admin.tool.PageTool;
import com.lamport.admin.vo.BookQueryCondition;
import com.lamport.admin.vo.QIDAndCategory;
import com.lamport.admin.vo.QIDAndPage;
import com.lamport.admin.vo.SorderQueryCondition;

@Controller
public class TestHandler {
	@Autowired
	private TestService testService;
	@Autowired 
	private LoginService loginService;
	@Autowired 
	private EnterpriseService enterpriseService;
	@Autowired
	private FreeListenService freeListenService;
	@Autowired
	private FreeListenBookService freeListenBookService;
	@Autowired
	private LessonService lessonService;
	@Autowired
	private SorderService sorderService;
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private UserService userService;
	@Autowired
	private SwiperService swiperService;
	
	@RequestMapping(value="/test/TestCopyDemo")
	public String testCopyDemo() throws Exception{
		System.out.println("........TestHandler...........testcopyDemo()........");
		String result = null;

		System.out.println("\n" + result + "\n");
		return "/test_show.jsp";
	}
	
	
	/********************************LogInAndOutHandler********************************/
	@RequestMapping(value="/test/TestAdminLogin")
	public String testAdminLogin() throws Exception{
		System.out.println("........TestHandler...........testAdminLogin()........");
		
		JsonObject jsonObject = new JsonObject();
		Admin admin = new Admin();
		admin.setAdminname("feiyy");
		admin.setPassword("123456");
		int result = loginService.isAdminLoginSuccessful(admin);
		jsonObject.addProperty("response", result);
		System.out.println("\n" + jsonObject.toString() + "\n");
		
		return "/test_show.jsp";
	}
	/********************************LogInAndOutHandler********************************/
	
	
	/********************************SuperAdminHandler********************************/
	@RequestMapping(value="/test/TestSaveEnterprise")
	public String testSaveEnterprise() throws Exception{
		System.out.println("........TestHandler...........testSaveEnterprise()........");
		
		Enterprise enterprise = new Enterprise();
		int saveResult = enterpriseService.saveEnterprise(enterprise);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("response", saveResult);
		System.out.println("\n" + jsonObject.toString() + "\n");
		
		return "/test_show.jsp";
	}
	@RequestMapping(value="/test/TestDeleteEnterpriseLogicallyByID")
	public String deleteEnterpriseLogicallyByID() throws Exception{
		System.out.println("........TestHandler...........testSaveEnterprise()........");
		
		int deleteResult = enterpriseService.deleteEnterpriseLogicallyByID(6);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("response", deleteResult);
		System.out.println("\n" + jsonObject.toString() + "\n");
		
		return "/test_show.jsp";
	}
	@RequestMapping(value="/test/TestSelectEnterpriseByPage")
	public String testSelectEnterpriseByPage() throws Exception{
		System.out.println("........TestHandler...........testSelectEnterpriseByPage()........");
		
		PageTool pageTool = new PageTool(1, 10);
		List<Enterprise> enterprises = enterpriseService.selectEnterpriseByPage(pageTool);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("code", 0);
		jsonObject.addProperty("msg", "");
		jsonObject.addProperty("count", pageTool.getCount());
		JsonArray jsonArray = new JsonArray();
		for(Enterprise enterprise : enterprises){
			JsonObject object = new JsonObject();
			object.addProperty("id", enterprise.getQid());
			object.addProperty("admin", enterprise.getAdminister().getAdminname());
			object.addProperty("enterprise", enterprise.getName());
			jsonArray.add(object);
		}
		jsonObject.add("data", jsonArray);
		System.out.println("\n" + jsonObject.toString() + "\n");
		
		return "/test_show.jsp";
	}
	/********************************SuperAdminHandler********************************/
	
	
	/********************************StatisticsHandler********************************/
	@RequestMapping(value="/test/TestStatistics")
	public String testStatistics() throws Exception{
		System.out.println("........TestHandler...........testStatistics()........");
		String result = null;
		
		int qid=1;
		int countUser = userService.selectCountUserByQID(qid);
		int countCourse = lessonService.selectCountLessonByQID(qid)+freeListenService.selectCountFreeListenByQID(qid);
		int countTeacher = teacherService.selectCountTeacherByQID(qid);
		int countSorder = sorderService.selectCountSorderByQID(qid);
		int countBook = freeListenBookService.selectCountFreeListenBookByQID(qid);
		double countIncome = sorderService.selectCountSorderActualByQID(qid);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("user_total", countUser);
		jsonObject.addProperty("course_total", countCourse);
		jsonObject.addProperty("teacher_total", countTeacher);
		jsonObject.addProperty("order_total", countSorder);
		jsonObject.addProperty("book_total", countBook);
		jsonObject.addProperty("profit_total", countIncome);
		result = jsonObject.toString();

		System.out.println("\n" + result + "\n");
		return "/test_show.jsp";
	}
	/********************************StatisticsHandler********************************/
	

	/********************************SorderHandler********************************/
	@RequestMapping(value="/test/TestVerifySorderByID")
	public String testVerifySorderByID() throws Exception{
		System.out.println("........TestHandler...........testVerifySorderByID()........");
		String result = null;
		
		int oid = 49;
		Sorder sorder = new Sorder();
		sorder.setOid(oid);
		sorder.setStatus(Const.SorderStatusCAV);
		int updateResult = sorderService.updateSorderByID(sorder);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("response", updateResult);
		result = jsonObject.toString();
		
		System.out.println("\n" + result + "\n");
		return "/test_show.jsp";
	}
	@RequestMapping(value="/test/TestProcessRefundByID")
	public String testProcessRefundByID() throws Exception{
		System.out.println("........TestHandler...........TestProcessRefundByID()........");
		String result = null;

		int oid = 43;
		Sorder sorder = new Sorder();
		sorder.setOid(oid);
		sorder.setRefund(new Refund());
		sorder.getRefund().setOid(oid);
		sorder.setStatus(Const.SorderStatusRefunded);
		sorder.getRefund().setStatus(Const.RefundStatusProcessed);
		int updateResult = sorderService.updateRefundByID(sorder);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("response", updateResult);
		result = jsonObject.toString();
		
		System.out.println("\n" + result + "\n");
		return "/test_show.jsp";
	}
	@RequestMapping(value="/test/TestSelectSorderBySorderQueryCondition")
	public String testSelectSorderBySorderQueryCondition() throws Exception{
		System.out.println("........TestHandler...........testSelectSorderBySorderQueryCondition()........");
		String result = null;

		SorderQueryCondition sorderQueryCondition = new SorderQueryCondition();
		sorderQueryCondition.setQid(1);
		sorderQueryCondition.setPageTool(new PageTool(1, 10));
		sorderQueryCondition.setBeginTime("2018-06-01");
		sorderQueryCondition.setEndTime("2018-07-01");
		List<Sorder> sorders = sorderService.selectSorderBySorderQueryCondition(sorderQueryCondition);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("code", 0);
		jsonObject.addProperty("msg", "");
		jsonObject.addProperty("count", sorderQueryCondition.getPageTool().getCount());
		JsonArray jsonArray = new JsonArray();
		for(Sorder sorder : sorders){
			JsonObject object = new JsonObject();
			object.addProperty("courseid", sorder.getOid());
			object.addProperty("orderid", sorder.getOid());
			object.addProperty("usernickname", sorder.getUsername());
			object.addProperty("usertel", sorder.getTel());
			object.addProperty("total", sorder.getTotal());
			object.addProperty("actual", sorder.getActual());
			object.addProperty("orderstatus", sorder.getStatus());
			object.addProperty("ordertime", sorder.getOrdertime());
			object.addProperty("transactionid", sorder.getTransactionid());
			jsonArray.add(object);
		}
		jsonObject.add("data", jsonArray);
		result = jsonObject.toString();
		
		System.out.println("\n" + result + "\n");
		return "/test_show.jsp";
	}
	/********************************SorderHandler********************************/
	
	
	/********************************FreeListenBookHandler********************************/
	@RequestMapping(value="/test/TestProcessFreeListenBookByID")
	public String testProcessFreeListenBookByID() throws Exception{
		System.out.println("........TestHandler...........testProcessFreeListenBookByID()........");
		String result = null;

		int id=7;
		FreeListenBook freeListenBook = new FreeListenBook();
		freeListenBook.setId(id);
		freeListenBook.setStatus(Const.BookStatusProcessed);
		int updateResult = freeListenBookService.updateFreeListenBookByID(freeListenBook);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("response", updateResult);
		result = jsonObject.toString();
		
		System.out.println("\n" + result + "\n");
		return "/test_show.jsp";
	}
	@RequestMapping(value="/test/TestSelectFreeListenBookByBookQueryCondition")
	public String testSelectFreeListenBookByBookQueryCondition() throws Exception{
		System.out.println("........TestHandler...........testSelectFreeListenBookByBookQueryCondition()........");
		String result = null;

		BookQueryCondition bookQueryCondition = new BookQueryCondition();
		bookQueryCondition.setQid(1);
		bookQueryCondition.setPageTool(new PageTool(1, 10));
		bookQueryCondition.setBeginTime("2018-06-01");
		bookQueryCondition.setEndTime("2018-07-01");
		List<FreeListenBook> freeListenBooks = freeListenBookService.selectFreeListenBookByBookQueryCondition(bookQueryCondition);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("code", 0);
		jsonObject.addProperty("msg", "");
		jsonObject.addProperty("count", bookQueryCondition.getPageTool().getCount());
		JsonArray jsonArray = new JsonArray();
		System.out.println(freeListenBooks.size());
		for(FreeListenBook freeListenBook : freeListenBooks){
			System.out.println(freeListenBook.getFreeListen().getId() + "\t" + freeListenBook.getFreeListen().getTitle()
							+ freeListenBook.getComment());
			JsonObject object = new JsonObject();
			object.addProperty("bookcourseid", freeListenBook.getFreeListen().getId());
			object.addProperty("bookcoursename", freeListenBook.getFreeListen().getTitle());
			object.addProperty("usernickname", freeListenBook.getUsername());
			object.addProperty("usertel", freeListenBook.getTel());
			object.addProperty("bookstatus", freeListenBook.getStatus());
			object.addProperty("booktime", freeListenBook.getBooktime());
			object.addProperty("message", freeListenBook.getComment());
			jsonArray.add(object);
		}
		jsonObject.add("data", jsonArray);
		result = jsonObject.toString();
		
		System.out.println("\n" + result + "\n");
		return "/test_show.jsp";
	}
	/********************************FreeListenBookHandler********************************/
	

	/********************************TeacherHandler********************************/
	@RequestMapping(value="/test/TestSaveTeacher")
	public String testSaveTeacher(HttpServletRequest request) throws Exception{
		System.out.println("........TestHandler...........testSaveTeacher()........");
		String result = null;

		Teacher teacher = new Teacher();
		teacher.setQid(6);
		teacher.setTname("潘");
		teacher.setIntroduction("伟伟");
		String path = request.getServletContext().getRealPath("/");//得到当前工程的根路径
		int saveResult = teacherService.saveTeacher(teacher, null, path);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("reponse", saveResult);
		result = jsonObject.toString();
		
		System.out.println("\n" + result + "\n");
		return "/test_show.jsp";
	}
	@RequestMapping(value="/test/TestDeleteTeacherLogicallyByID")
	public String testDeleteTeacherLogicallyByID(HttpServletRequest request) throws Exception{
		System.out.println("........TestHandler...........testDeleteTeacherLogicallyByID()........");
		String result = null;

		int id = 4;
		int deleteResult = teacherService.deleteTeacherLogicallyByID(id);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("response", deleteResult);
		result = jsonObject.toString();
		
		System.out.println("\n" + result + "\n");
		return "/test_show.jsp";
	}
	@RequestMapping(value="/test/TestUpdateTeacherByID")
	public String testUpdateTeacherByID(HttpServletRequest request) throws Exception{
		System.out.println("........TestHandler...........testUpdateTeacherByID()........");
		String result = null;

		Teacher teacher = new Teacher();
		MultipartFile imgFile = null;
		teacher.setTname("aaa");
		teacher.setIntroduction("QQQ");
		teacher.setTid(4);
		String path = request.getServletContext().getRealPath("/");//得到当前工程的根路径
		int updateResult = teacherService.updateTeacherByID(teacher, imgFile, path);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("reponse", updateResult);
		result = jsonObject.toString();
		
		System.out.println("\n" + result + "\n");
		return "/test_show.jsp";
	}
	@RequestMapping(value="/test/TestUpdateTeacherSwiperByQID")
	public String testUpdateTeacherSwiperByQID(HttpServletRequest request) throws Exception{
		System.out.println("........TestHandler...........testUpdateTeacherSwiperByQID()........");
		String result = null;

		QIDAndCategory qidAndCategory = new QIDAndCategory();
		qidAndCategory.setCategory(Const.SwiperCategoryT);
		qidAndCategory.setQid(1);
		String path = request.getServletContext().getRealPath("/");//得到当前工程的根路径
		int updateResult = swiperService.updateMultipleSwipersByQIDAndCategory(qidAndCategory, null, path);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("reponse", updateResult);
		result = jsonObject.toString();
		
		System.out.println("\n" + result + "\n");
		return "/test_show.jsp";
	}
	@RequestMapping(value="/test/TestSelectTeacherByQIDAndPage")
	public String testSelectTeacherByQIDAndPage() throws Exception{
		System.out.println("........TestHandler...........testSelectTeacherByQIDAndPage()........");
		String result = null;

		QIDAndPage qidAndPage = new QIDAndPage();
		qidAndPage.setPageTool(new PageTool(1, 10));
		qidAndPage.setQid(1);
		List<Teacher> teachers = teacherService.selectTeacherByQIDAndPage(qidAndPage);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("code", 0);
		jsonObject.addProperty("msg", "");
		jsonObject.addProperty("count", qidAndPage.getPageTool().getCount());
		JsonArray jsonArray = new JsonArray();
		for(Teacher teacher : teachers){
			JsonObject object = new JsonObject();
			object.addProperty("id", teacher.getTid());
			object.addProperty("teachername", teacher.getTname());
			object.addProperty("teacherimg", teacher.getTphoto());
			object.addProperty("teacherintro", teacher.getIntroduction());
			jsonArray.add(object);
		}
		jsonObject.add("data", jsonArray);
		result = jsonObject.toString();
		
		System.out.println("\n" + result + "\n");
		return "/test_show.jsp";
	}
	@RequestMapping(value="/test/TestSelectTeacherSwiperByQID")
	public String testSelectTeacherSwiperByQID() throws Exception{
		System.out.println("........TestHandler...........testSelectTeacherSwiperByQID()........");
		String result = null;

		QIDAndCategory qidAndCategory = new QIDAndCategory();
		qidAndCategory.setQid(1);
		qidAndCategory.setCategory(Const.SwiperCategoryT);
		List<String> swiperImgurls = swiperService.selectSwiperImgurlByQIDAndCategory(qidAndCategory);
		
		JsonObject jsonObject = new JsonObject();
		for(String swiperImgurl : swiperImgurls){
			jsonObject.addProperty(swiperImgurl, swiperImgurl);
		}
		result = jsonObject.toString();
		
		System.out.println("\n" + result + "\n");
		return "/test_show.jsp";
	}
	/********************************TeacherHandler********************************/
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
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
