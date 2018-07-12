package com.lamport.admin.handler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.lamport.admin.service.AddressService;
import com.lamport.admin.service.AdminService;
import com.lamport.admin.service.EnterpriseService;
import com.lamport.admin.service.FreeListenBookService;
import com.lamport.admin.service.FreeListenService;
import com.lamport.admin.service.LessonService;
import com.lamport.admin.service.LoginService;
import com.lamport.admin.service.MessageReplyService;
import com.lamport.admin.service.MessageService;
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
	@Autowired
	private AdminService adminService;
	@Autowired
	private MessageService messageService;
	@Autowired 
	MessageReplyService messageReplyService;
	@Autowired
	AddressService addressService;
	
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
	
	
	/********************************EnterpriseBasicInfoHandler********************************/
	@RequestMapping(value="/test/TestUpdateEnterpriseBasicInfoByID")
	public String testUpdateEnterpriseBasicInfoByID(HttpServletRequest request) throws Exception{
		System.out.println("........TestHandler...........testUpdateEnterpriseBasicInfoByID()........");
		String result = null;
		
		Enterprise enterprise = new Enterprise();
		enterprise.setQid(2);
		enterprise.setName("古汉文渊");
		enterprise.setIntroduction("古汉文渊，带您领略古文之美");
		String path = request.getServletContext().getRealPath("/");//得到当前工程的根路径
		int updateResult = enterpriseService.updateEnterpriseByID(enterprise, null, null, path);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("reponse", updateResult);
		result = jsonObject.toString();

		System.out.println("\n" + result + "\n");
		return "/test_show.jsp";
	}
	@RequestMapping(value="/test/TestSelectEnterpriseBasicInfoByQID")
	public String testSelectEnterpriseBasicInfoByQID() throws Exception{
		System.out.println("........TestHandler...........testSelectEnterpriseBasicInfoByQID()........");
		String result = null;

		Enterprise enterprie = enterpriseService.selectEnterpriseByQID(1);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("name", enterprie.getName());
		jsonObject.addProperty("videopath", enterprie.getVideopath());
		jsonObject.addProperty("introduction", enterprie.getIntroduction());
		jsonObject.addProperty("jczs", enterprie.getJczs());
		JsonArray jsonArray = new JsonArray();
		for(Swiper swiper : enterprie.getSwipers()){
			jsonArray.add(swiper.getImgurl());
		}
		jsonObject.add("imgs", jsonArray);
		result = jsonObject.toString();

		System.out.println("\n" + result + "\n");
		return "/test_show.jsp";
	}
	/********************************EnterpriseBasicInfoHandler********************************/
	

	/********************************BranchHandler********************************/
	@RequestMapping(value="/test/TestSaveBranch")
	public String testSaveBranch() throws Exception{
		System.out.println("........TestHandler...........testSaveBranch()........");
		String result = null;

		Address address = new Address();
		address.setQid(6);
		address.setBranch("湘潭分部");
		address.setAddress("长沙市湘潭区");
		address.setTel("19604040303");
		address.setLongitude(124.8887);
		address.setLatitude(39.9966);
		int saveResult = addressService.saveAddress(address);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("reponse", saveResult);
		result = jsonObject.toString();

		System.out.println("\n" + result + "\n");
		return "/test_show.jsp";
	}
	@RequestMapping(value="/test/TestDeleteBranchLogicallyByID")
	public String testDeleteBranchLogicallyByID() throws Exception{
		System.out.println("........TestHandler...........testDeleteBranchLogicallyByID()........");
		String result = null;

		int id=2;
		int deleteResult = addressService.deleteAddressLogicallyByID(id);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("response", deleteResult);
		result = jsonObject.toString();

		System.out.println("\n" + result + "\n");
		return "/test_show.jsp";
	}
	@RequestMapping(value="/test/TestUpdateBranchByID")
	public String testUpdateBranchByID() throws Exception{
		System.out.println("........TestHandler...........testUpdateBranchByID()........");
		String result = null;

		Address address = new Address();
		address.setId(4);
		address.setBranch("扬州分舵");
		address.setAddress("扬州市");
		address.setTel("13478654765");
		address.setLongitude(123.2333);
		address.setLatitude(32.2333);
		int updateResult = addressService.updateAddressByID(address);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("response", updateResult);
		result = jsonObject.toString();

		System.out.println("\n" + result + "\n");
		return "/test_show.jsp";
	}
	@RequestMapping(value="/test/TestSelectBranchByQIDAndPage")
	public String testSelectBranchByQIDAndPage() throws Exception{
		System.out.println("........TestHandler...........testSelectBranchByQIDAndPage()........");
		String result = null;

		QIDAndPage qidAndPage = new QIDAndPage();
		qidAndPage.setQid(1);
		qidAndPage.setPageTool(new PageTool(1, 10));
		List<Address> addresses = addressService.selectAddressByQIDAndPage(qidAndPage);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("code", 0);
		jsonObject.addProperty("msg", "");
		jsonObject.addProperty("count", qidAndPage.getPageTool().getCount());
		JsonArray jsonArray = new JsonArray();
		for(Address address : addresses){
			JsonObject object = new JsonObject();
			object.addProperty("id", address.getId());
			object.addProperty("branch", address.getBranch());
			object.addProperty("address", address.getAddress());
			object.addProperty("tel", address.getTel());
			object.addProperty("longitude", address.getLongitude());
			object.addProperty("latitude", address.getLatitude());
			jsonArray.add(object);
		}
		jsonObject.add("data", jsonArray);
		result = jsonObject.toString();

		System.out.println("\n" + result + "\n");
		return "/test_show.jsp";
	}	
	@RequestMapping(value="/test/TestSelectBranchByQID")
	public String testSelectBranchByQID() throws Exception{
		System.out.println("........TestHandler...........testSelectBranchByQID()........");
		String result = null;

		int qid = 1;
		List<Address> addresses = addressService.selectAddressByQID(qid);
		JsonObject jsonObject = new JsonObject();
		JsonArray jsonArray = new JsonArray();
		for(Address address : addresses){
			JsonObject object = new JsonObject();
			object.addProperty("id", address.getId());
			object.addProperty("branch", address.getBranch());
			jsonArray.add(object);
		}
		jsonObject.add("branches", jsonArray);
		result = jsonObject.toString();

		System.out.println("\n" + result + "\n");
		return "/test_show.jsp";
	}
	/********************************BranchHandler********************************/
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
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
	
	
	
	
	
	
	
	
	
	
	

	/********************************MessageHandler********************************/
	@RequestMapping(value="/test/TestSaveMessage")
	public String testSaveMessage(HttpServletRequest request) throws Exception{
		System.out.println("........TestHandler...........testSaveMessage()........");
		String result = null;
		
		Message new_message = new Message();
		new_message.setMtitle("潘");
		new_message.setQid(6);
		String path = request.getServletContext().getRealPath("/");//得到当前工程的根路径
		int saveResult = messageService.saveMessage(new_message, null, path);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("reponse", saveResult);
		result = jsonObject.toString();
		
		System.out.println("\n" + result + "\n");
		return "/test_show.jsp";
	}
	@RequestMapping(value="/test/TestDeleteMessageLogicallyByID")
	public String testDeleteMessageLogicallyByID(HttpServletRequest request) throws Exception{
		System.out.println("........TestHandler...........testDeleteMessageLogicallyByID()........");
		String result = null;
		
		int id=2;
		int deleteResult = messageService.deleteMessageLogicallyByID(id);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("response", deleteResult);
		result = jsonObject.toString();
		
		System.out.println("\n" + result + "\n");
		return "/test_show.jsp";
	}	
	@RequestMapping(value="/test/TestDeleteMessageReplyLogicallyByID")
	public String testDeleteMessageReplyLogicallyByID(HttpServletRequest request) throws Exception{
		System.out.println("........TestHandler...........testDeleteMessageReplyLogicallyByID()........");
		String result = null;
		
		int id=1;
		int deleteResult = messageReplyService.deleteMessageReplyLogicallyByID(id);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("response", deleteResult);
		result = jsonObject.toString();
		
		System.out.println("\n" + result + "\n");
		return "/test_show.jsp";
	}
	@RequestMapping(value="/test/TestSelectMessageByQIDAndPage")
	public String testSelectMessageByQIDAndPage(HttpServletRequest request) throws Exception{
		System.out.println("........TestHandler...........testSelectMessageByQIDAndPage()........");
		String result = null;
		
		QIDAndPage qidAndPage = new QIDAndPage();
		qidAndPage.setQid(1);
		qidAndPage.setPageTool(new PageTool(1, 10));
		List<Message> messages = messageService.selectMessageByQIDAndPage(qidAndPage);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("pages", qidAndPage.getPageTool().getPages());
		jsonObject.addProperty("page", qidAndPage.getPageTool().getPage());
		JsonArray jsonArray = new JsonArray();
		for(Message message : messages){
			JsonObject object = new JsonObject();
			object.addProperty("id", message.getMid());
			object.addProperty("name", message.getEnterprise().getName());
			object.addProperty("logo", message.getSwiper().getImgurl());
			object.addProperty("content", message.getMtitle());
			JsonArray imgurls = new JsonArray();
			for(MessageImg img: message.getImgs()){
				imgurls.add(img.getImgurl());
			}
			object.add("imgurls", imgurls);
			object.addProperty("stime", message.getMtime());
			JsonArray comments = new JsonArray();
			for(MessageReply reply: message.getReplies()){
				JsonObject comment = new JsonObject();
				comment.addProperty("id", reply.getId());
				comment.addProperty("name", reply.getUser().getNickname());
				comment.addProperty("comment", reply.getContent());
				comments.add(comment);
			}
			object.add("comments", comments);
			jsonArray.add(object);
		}
		jsonObject.add("messages", jsonArray);
		result = jsonObject.toString();	
		
		System.out.println("\n" + result + "\n");
		return "/test_show.jsp";
	}
	/********************************MessageHandler********************************/
	

	/********************************AdminHandler********************************/
	@RequestMapping(value="/test/TestUpdatePasswordByID")
	public String testUpdatePasswordByID() throws Exception{
		System.out.println("........TestHandler...........testUpdatePasswordByID()........");
		String result = null;

		Admin admin = new Admin();
		admin.setId(1);
		admin.setPassword("12306");
		String sourse_password = "12306";
		String new_password = "60321";
		
		JsonObject jsonObject = new JsonObject();
		if(admin.getPassword().equals(sourse_password)){
			admin.setPassword(new_password);
			int updateResult = adminService.updatePasswordByID(admin);
			if(updateResult == 1){
				jsonObject.addProperty("status", "success");
				jsonObject.addProperty("message", "密码修改成功");
			}else{
				jsonObject.addProperty("status", "fail");
				jsonObject.addProperty("message", "密码修改失败");
			}
		}else{
			jsonObject.addProperty("status", "fail");
			jsonObject.addProperty("message", "原密码输入错误");
		}
		result = jsonObject.toString();
		
		System.out.println("\n" + result + "\n");
		return "/test_show.jsp";
	}
	@RequestMapping(value="/test/TestSelectEnterpriseBasicDataByQID")
	public String testSelectEnterpriseBasicDataByQID() throws Exception{
		System.out.println("........TestHandler...........testSelectEnterpriseBasicDataByQID()........");
		String result = null;

		Enterprise enterprise = enterpriseService.selectEnterpriseByQID(1);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("enterprise_name", enterprise.getName());
		jsonObject.addProperty("enterprise_id", enterprise.getQid());
		result = jsonObject.toString();
		
		System.out.println("\n" + result + "\n");
		return "/test_show.jsp";
	}
	/********************************AdminHandler********************************/
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
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
