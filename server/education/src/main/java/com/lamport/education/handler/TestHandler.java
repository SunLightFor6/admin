package com.lamport.education.handler;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.lamport.education.mapper.LessonMapper;
import com.lamport.education.po.Address;
import com.lamport.education.po.Enterprise;
import com.lamport.education.po.FreeListen;
import com.lamport.education.po.FreeListenBook;
import com.lamport.education.po.Lesson;
import com.lamport.education.po.Message;
import com.lamport.education.po.MessageImg;
import com.lamport.education.po.MessageLike;
import com.lamport.education.po.MessageReply;
import com.lamport.education.po.Refund;
import com.lamport.education.po.Sorder;
import com.lamport.education.po.Teacher;
import com.lamport.education.po.User;
import com.lamport.education.service.AddressService;
import com.lamport.education.service.EnterpriseService;
import com.lamport.education.service.FreeListenBookService;
import com.lamport.education.service.FreeListenService;
import com.lamport.education.service.HomeInfoService;
import com.lamport.education.service.LessonService;
import com.lamport.education.service.MessageService;
import com.lamport.education.service.SorderService;
import com.lamport.education.service.SwiperService;
import com.lamport.education.service.TeacherService;
import com.lamport.education.service.UserService;
import com.lamport.education.util.Config;
import com.lamport.education.vo.BookQueryCondition;
import com.lamport.education.vo.FreeListenQueryCondition;
import com.lamport.education.vo.LessonQueryCondition;
import com.lamport.education.vo.QIDAndCategory;
import com.lamport.education.vo.QIDAndPage;
import com.lamport.education.vo.SorderQueryCondition;
import com.lamport.education.vo.UIDAndStatus;

@Controller
public class TestHandler {

	@Autowired
	SwiperService swiperService;
	@Autowired
	HomeInfoService homeInfoService;
	@Autowired
	AddressService addressService;
	@Autowired
	EnterpriseService enterpriseService;
	@Autowired
	FreeListenBookService freeListenBookService;
	@Autowired
	FreeListenService freeListenService;
	@Autowired
	LessonService lessonService;	
	@Autowired
	MessageService messageService;
	@Autowired
	UserService userService;
	@Autowired
	TeacherService teacherService;	
	@Autowired
	SorderService sorderService;
	@Autowired
	LessonMapper lessonMapper;
	
	@RequestMapping(value="/test/TestCopyDemo")
	public String testCopyDemo() throws Exception{
		System.out.println("........TestHandler...........testcopyDemo()........");
		String result = null;

		
		System.out.println();/*####################*/
		
		
		System.out.println("\nresult:\n" + result + "\n");
		return "/test_show.jsp";
	}
	
	/********************************HomeHandler********************************/
	@RequestMapping(value="/test/TestSelectHomeInfoByQID")
	public String testSelectHomeInfoByQID() throws Exception{
		System.out.println("........TestHandler...........testSelectHomeInfoByQID()........");
		String result = null;
		
		int qid = 1;
		System.out.println("qid = "+ qid);				/*####################*/
		//查询企业轮播图
		QIDAndCategory qidAndCategory = new QIDAndCategory();
		qidAndCategory.setQid(qid);
		qidAndCategory.setCategory(Config.EnterpriseSwiper);
		List<String> swiperImgurls = swiperService.selectSwiperImgurlByQIDAndCategory(qidAndCategory);
		//查询首页展示的试听课程信息
		QIDAndPage qidAndPage = new QIDAndPage();
		qidAndPage.setQid(qid);
		qidAndPage.setStartId(0);
		qidAndPage.initPageBean(Config.HomeFreeListenPageSize);
		List<FreeListen> freeListens = homeInfoService.selectHomePageFreeListenByQid(qidAndPage);
		//查询首页展示的精品课程信息
		qidAndPage.initPageBean(Config.HomeLessonPageSize);
		List<Lesson> lessons = homeInfoService.selectHomePageLessonByQid(qidAndPage);
		JsonObject jsonObject = new JsonObject();
		JsonArray swiperArray = new JsonArray();
		if(swiperImgurls!=null && !swiperImgurls.isEmpty()){
			for(String swiperImgurl : swiperImgurls){
				JsonObject object = new JsonObject();
				object.addProperty("imgurl", swiperImgurl);
				swiperArray.add(object);
			}
		}
		jsonObject.add("swipers", swiperArray);
		JsonArray freeListenArray = new JsonArray();
		if(freeListens!=null && !freeListens.isEmpty()){
			for(FreeListen freeListen : freeListens){
				JsonObject object = new JsonObject();
				object.addProperty("fid", freeListen.getId());
				object.addProperty("imgurl", freeListen.getImgurl());
				object.addProperty("title", freeListen.getTitle());
				object.addProperty("fdesc", freeListen.getFdesc());
				freeListenArray.add(object);
			}
		}
		jsonObject.add("freelistens", freeListenArray);
		JsonArray lessonArray = new JsonArray();
		if(lessons!=null && !lessons.isEmpty()){
			for(Lesson lesson : lessons){
				JsonObject object = new JsonObject();
				object.addProperty("lid", lesson.getLid());
				object.addProperty("imgurl", lesson.getImgurl());
				object.addProperty("lname", lesson.getLname());
				object.addProperty("ldesc", lesson.getLdesc());
				lessonArray.add(object);
			}
		}
		jsonObject.add("lessons", lessonArray);
		result = jsonObject.toString();

		System.out.println("\nresult:\n" + result + "\n");
		return "/test_show.jsp";
	}
	/********************************HomeHandler********************************/
	
	
	/********************************AddressHandler********************************/
	@RequestMapping(value="/test/TestSelectAllCategoryByQid")
	public String testSelectAllCategoryByQid() throws Exception{
		System.out.println("........TestHandler...........testSelectAllCategoryByQid()........");
		String result = null;

		int qid = 1;
		System.out.println("qid = " + qid);					/*####################*/
		result = addressService.selectAllCategoryByQid(qid);
		
		System.out.println("\nresult:\n" + result + "\n");
		return "/test_show.jsp";
	}
	/********************************AddressHandler********************************/
	
	
	/********************************EnterpriseHandler********************************/
	@RequestMapping(value="/test/TestSelectEnterPriseByQid")
	public String testSelectEnterPriseByQid() throws Exception{
		System.out.println("........TestHandler...........testSelectEnterPriseByQid()........");
		String result = null;

		int qid = 1;
		System.out.println("qid = " + qid);/*####################*/
		Enterprise enterprise = enterpriseService.selectEnterpriseByQid(qid);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("name", enterprise.getName());
		jsonObject.addProperty("videopath", enterprise.getVideopath());
		jsonObject.addProperty("introduction", enterprise.getIntroduction());
		jsonObject.addProperty("jczs", enterprise.getJczs());
		result = jsonObject.toString();
		
		System.out.println("\nresult:\n" + result + "\n");
		return "/test_show.jsp";
	}
	/********************************EnterpriseHandler********************************/
	
	
	/********************************FreeListenBookHandler********************************/
	@RequestMapping(value="/test/TestSaveFreeListenBook")
	public String testSaveFreeListenBook() throws Exception{
		System.out.println("........TestHandler...........testSaveFreeListenBook()........");
		String result = null;

		FreeListenBook freeListenBook = new FreeListenBook();
		freeListenBook.setFid(1);
		freeListenBook.setUsername("费园园");
		freeListenBook.setTel("18622351756");
		freeListenBook.setComment("Please quikly");
		User user = new User();
		user.setUid(1);
		System.out.println();/*####################*/
		freeListenBook.setUid(user.getUid());
		freeListenBook.setStatus(Config.BookStatusUnprocessed);
	    freeListenBookService.saveFreeListenBook(freeListenBook);
	    JsonObject jsonObject = new JsonObject();
	    jsonObject.addProperty("state", 1);
	    result = jsonObject.toString();
		
		System.out.println("\nresult:\n" + result + "\n");
		return "/test_show.jsp";
	}
	@RequestMapping(value="/test/TestDeleteFreeListenBookProcessed")
	public String testDeleteFreeListenBookProcessed() throws Exception{
		System.out.println("........TestHandler...........testDeleteFreeListenBookProcessed()........");
		String result = null;

		FreeListenBook freeListenBook = new FreeListenBook();
		freeListenBook.setId(16);
		System.out.println();/*####################*/
		freeListenBookService.deleteFreeListenBookLogicallyById(freeListenBook);
		JsonObject jsonObject = new JsonObject();
	    jsonObject.addProperty("state", 1);
	    result = jsonObject.toString();
		
		System.out.println("\nresult:\n" + result + "\n");
		return "/test_show.jsp";
	}
	@RequestMapping(value="/test/TestCancelFreeListenBookUnprocessed")
	public String testCancelFreeListenBookUnprocessed() throws Exception{
		System.out.println("........TestHandler...........testCancelFreeListenBookUnprocessed()........");
		String result = null;

		FreeListenBook freeListenBook = new FreeListenBook();
		freeListenBook.setId(8);
		System.out.println();/*####################*/
		freeListenBookService.deleteFreeListenBookPowerfullyById(freeListenBook);
		JsonObject jsonObject = new JsonObject();
	    jsonObject.addProperty("state", 1);
	    result = jsonObject.toString();
		
		System.out.println("\nresult:\n" + result + "\n");
		return "/test_show.jsp";
	}
	@RequestMapping(value="/test/TestSelectFreeListenBookByBookQueryCondition")
	public String testSelectFreeListenBookByBookQueryCondition() throws Exception{
		System.out.println("........TestHandler...........testSelectFreeListenBookByBookQueryCondition()........");
		String result = null;

		BookQueryCondition bookQueryCondition = new BookQueryCondition();
		bookQueryCondition.setStartId(1000);
		User user = new User();
		user.setUid(1);
		System.out.println();/*####################*/
		bookQueryCondition.setUid(user.getUid());
		bookQueryCondition.initPageBean(Config.BookPageSize);
		System.out.println("status = " + bookQueryCondition.getStatus());			/*####################*/
		List<FreeListenBook> freeListenBooks = freeListenBookService.selectFreeListenBookByBookQueryCondition(bookQueryCondition);
		JsonObject jsonObject = new JsonObject();
		JsonArray jsonArray = new JsonArray();
		if(freeListenBooks!=null && !freeListenBooks.isEmpty()){
			for(FreeListenBook freeListenBook : freeListenBooks){
				JsonObject object = new JsonObject();
				object.addProperty("id", freeListenBook.getId());
				object.addProperty("booktime", freeListenBook.getBooktime());
				object.addProperty("status", freeListenBook.getBooktime());
				object.addProperty("title", freeListenBook.getFreeListen().getTitle());
				object.addProperty("imgurl", freeListenBook.getFreeListen().getImgurl());
				jsonArray.add(object);
			}
		}
		jsonObject.add("freelistenbooks", jsonArray);
		result = jsonObject.toString();
		
		System.out.println("\nresult:\n" + result + "\n");
		return "/test_show.jsp";
	}
	@RequestMapping(value="/test/TestSelectFreeListenBookProcessedByBookQueryCondition")
	public String testSelectFreeListenBookProcessedByBookQueryCondition() throws Exception{
		System.out.println("........TestHandler...........testSelectFreeListenBookProcessedByBookQueryCondition()........");
		String result = null;

		BookQueryCondition bookQueryCondition = new BookQueryCondition();
		bookQueryCondition.setStartId(1000);
		User user = new User();
		user.setUid(1);
		System.out.println();/*####################*/
		bookQueryCondition.setUid(user.getUid());
		bookQueryCondition.setStatus(Config.BookStatusProcessed);
		bookQueryCondition.initPageBean(Config.BookPageSize);
		System.out.println("status = " + bookQueryCondition.getStatus());			/*####################*/
		List<FreeListenBook> freeListenBooks = freeListenBookService.selectFreeListenBookByBookQueryCondition(bookQueryCondition);
		JsonObject jsonObject = new JsonObject();
		JsonArray jsonArray = new JsonArray();
		if(freeListenBooks!=null && !freeListenBooks.isEmpty()){
			for(FreeListenBook freeListenBook : freeListenBooks){
				JsonObject object = new JsonObject();
				object.addProperty("id", freeListenBook.getId());
				object.addProperty("booktime", freeListenBook.getBooktime());
				object.addProperty("status", freeListenBook.getBooktime());
				object.addProperty("title", freeListenBook.getFreeListen().getTitle());
				object.addProperty("imgurl", freeListenBook.getFreeListen().getImgurl());
				jsonArray.add(object);
			}
		}
		jsonObject.add("freelistenbooks", jsonArray);
		result = jsonObject.toString();
		
		System.out.println("\nresult:\n" + result + "\n");
		return "/test_show.jsp";
	}
	@RequestMapping(value="/test/TestSelectFreeListenBookUnprocessedByBookQueryCondition")
	public String testSelectFreeListenBookUnprocessedByBookQueryCondition() throws Exception{
		System.out.println("........TestHandler...........testSelectFreeListenBookUnprocessedByBookQueryCondition()........");
		String result = null;

		BookQueryCondition bookQueryCondition = new BookQueryCondition();
		bookQueryCondition.setStartId(1000);
		User user = new User();
		user.setUid(1);
		System.out.println();/*####################*/
		bookQueryCondition.setUid(user.getUid());
		bookQueryCondition.setStatus(Config.BookStatusUnprocessed);
		bookQueryCondition.initPageBean(Config.BookPageSize);
		System.out.println("status = " + bookQueryCondition.getStatus());			/*####################*/
		List<FreeListenBook> freeListenBooks = freeListenBookService.selectFreeListenBookByBookQueryCondition(bookQueryCondition);
		JsonObject jsonObject = new JsonObject();
		JsonArray jsonArray = new JsonArray();
		if(freeListenBooks!=null && !freeListenBooks.isEmpty()){
			for(FreeListenBook freeListenBook : freeListenBooks){
				JsonObject object = new JsonObject();
				object.addProperty("id", freeListenBook.getId());
				object.addProperty("booktime", freeListenBook.getBooktime());
				object.addProperty("status", freeListenBook.getBooktime());
				object.addProperty("title", freeListenBook.getFreeListen().getTitle());
				object.addProperty("imgurl", freeListenBook.getFreeListen().getImgurl());
				jsonArray.add(object);
			}
		}
		jsonObject.add("freelistenbooks", jsonArray);
		result = jsonObject.toString();
		
		System.out.println("\nresult:\n" + result + "\n");
		return "/test_show.jsp";
	}
	/********************************FreeListenBookHandler********************************/
	
	
	/********************************FreeListenHandler********************************/
	@RequestMapping(value="/test/TestSelectFreeListenByID")
	public String testSelectFreeListenByID() throws Exception{
		System.out.println("........TestHandler...........testSelectFreeListenByID()........");
		String result = null;

		int id = 1;
		System.out.println();/*####################*/
		FreeListen freeListen = freeListenService.selectFreeListenByFid(id);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("id", freeListen.getId());
		jsonObject.addProperty("title", freeListen.getTitle());
		jsonObject.addProperty("branch", freeListen.getAddress().getBranch());
		jsonObject.addProperty("category", freeListen.getCategory());
		jsonObject.addProperty("status", freeListen.getStatus());
		jsonObject.addProperty("imgurl", freeListen.getImgurl());
		jsonObject.addProperty("fdesc", freeListen.getFdesc());
		result = jsonObject.toString();
		
		System.out.println("\nresult:\n" + result + "\n");
		return "/test_show.jsp";
	}
	@RequestMapping(value="/test/TestSelectFreeListenByFreeListenQueryCondition")
	public String testSelectFreeListenByFreeListenQueryCondition() throws Exception{
		System.out.println("........TestHandler...........testSelectFreeListenByFreeListenQueryCondition()........");
		String result = null;

		FreeListenQueryCondition freeListenQueryCondition = new FreeListenQueryCondition();
		freeListenQueryCondition.setStartId(1000);
		freeListenQueryCondition.setBranch("实训中心");
		freeListenQueryCondition.setCategory("java");
		int qid = 1;
		System.out.println();/*####################*/
		freeListenQueryCondition.setQid(qid);
		freeListenQueryCondition.initPageBean(Config.FreeListenPageSize);
		List<FreeListen> freeListens = freeListenService.selectFreeListenByFreeListenQueryCondition(freeListenQueryCondition);
		JsonObject jsonObject = new JsonObject();
		JsonArray jsonArray = new JsonArray();
		if(freeListens!=null && !freeListens.isEmpty()){
			for(FreeListen freeListen : freeListens){
				JsonObject object = new JsonObject();
				object.addProperty("fid", freeListen.getId());
				object.addProperty("imgurl", freeListen.getImgurl());
				object.addProperty("title", freeListen.getTitle());
				object.addProperty("fdesc", freeListen.getFdesc());
				jsonArray.add(object);
			}
		}
		jsonObject.add("freelistens", jsonArray);
		result = jsonObject.toString();
		
		System.out.println("\nresult:\n" + result + "\n");
		return "/test_show.jsp";
	}
	@RequestMapping(value="/test/TestSelectReserveInfoByID")
	public String testSelectReserveInfoByID() throws Exception{
		System.out.println("........TestHandler...........testSelectReserveInfoByID()........");
		String result = null;

		int id = 1;
		User user = new User();
		user.setUid(1);
		user.setUsername("sherry");
		user.setTel("18622351756");
		System.out.println();/*####################*/
		FreeListen freeListen = freeListenService.selectFreeListenByFid(id);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("id", freeListen.getId());
		jsonObject.addProperty("title", freeListen.getTitle());
		jsonObject.addProperty("branch", freeListen.getAddress().getBranch());
		jsonObject.addProperty("username", user.getUsername());
		jsonObject.addProperty("tel", user.getTel());
		result = jsonObject.toString();
		
		System.out.println("\nresult:\n" + result + "\n");
		return "/test_show.jsp";
	}
	/********************************FreeListenHandler********************************/
	
	
	/********************************LessonHandler********************************/	
	@RequestMapping(value="/test/TestSelectLessonByLid")
	public String testSelectLessonByLid() throws Exception{
		System.out.println("........TestHandler...........testSelectLessonByLid()........");
		String result = null;

		int lid = 1;
		System.out.println();/*####################*/
		Lesson lesson = lessonService.selectLessonByLid(lid);
		JsonObject jsonObject = new JsonObject();
		JsonArray jsonArray = new JsonArray();
		jsonObject.addProperty("lid", lesson.getLid());
		jsonObject.addProperty("lname", lesson.getLname());
		jsonObject.addProperty("imgurl", lesson.getImgurl());
		jsonObject.addProperty("lprice", lesson.getLprice());
		jsonObject.addProperty("status", lesson.getStatus());
		jsonObject.addProperty("category", lesson.getCategory());
		jsonObject.addProperty("ldesc", lesson.getLdesc());
		if(lesson.getBranches()!=null && !lesson.getBranches().isEmpty()){
			for(Address address : lesson.getBranches()){
				JsonObject object = new JsonObject();
				object.addProperty("branch", address.getBranch());
				jsonArray.add(object);
			}
		}else{
			System.out.println("lesson..getBranches() is null or lesson..getBranches().size is 0");
		}
		jsonObject.add("branches", jsonArray);
		result = jsonObject.toString();
		
		System.out.println("\nresult:\n" + result + "\n");
		return "/test_show.jsp";
	}	
	@RequestMapping(value="/test/TestSelectLessonByLessonQueryCondition")
	public String testSelectLessonByLessonQueryCondition() throws Exception{
		System.out.println("........TestHandler...........testSelectLessonByLessonQueryCondition()........");
		String result = null;

		LessonQueryCondition lessonQueryCondition = new LessonQueryCondition();
		lessonQueryCondition.setStartId(1000);
		lessonQueryCondition.setBranch("实训中心");
		lessonQueryCondition.setCategory("HTML5");
		int qid = 1;
		System.out.println();/*####################*/
		lessonQueryCondition.setQid(qid);
		lessonQueryCondition.initPageBean(Config.LessonPageSize);
		List<Lesson> lessons = lessonService.selectLessonByLessonQueryCondition(lessonQueryCondition);
		JsonObject jsonObject = new JsonObject();
		JsonArray jsonArray = new JsonArray();
		if(lessons!=null && !lessons.isEmpty()){
			for(Lesson lesson : lessons){
				JsonObject object = new JsonObject();
				object.addProperty("lid", lesson.getLid());
				object.addProperty("imgurl", lesson.getImgurl());
				object.addProperty("lname", lesson.getLname());
				object.addProperty("ldesc", lesson.getLdesc());
				jsonArray.add(object);
			}
		}
		jsonObject.add("lessons", jsonArray);
		result = jsonObject.toString();
		
		System.out.println("\nresult:\n" + result + "\n");
		return "/test_show.jsp";
	}
	/********************************LessonHandler********************************/
	
	
	/********************************MessageHandler********************************/	
	@RequestMapping(value="/test/TestAddMessageReply")
	public String testAddMessageReply() throws Exception{
		System.out.println("........TestHandler...........testAddMessageReply()........");
		String result = null;

		MessageReply messageReply = new MessageReply();
		messageReply.setMid(2);
		messageReply.setContent("太阳当空照，花儿对我笑");
		User user = new User();
		user.setUid(1);
		System.out.println();/*####################*/
		messageReply.setUid(user.getUid());
		messageService.saveMessageReply(messageReply);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("state", 1);
		result = jsonObject.toString();
		
		System.out.println("\nresult:\n" + result + "\n");
		return "/test_show.jsp";
	}	
	@RequestMapping(value="/test/TestAddLike")
	public String testAddLike() throws Exception{
		System.out.println("........TestHandler...........testAddLike()........");
		String result = null;

		MessageLike messageLike = new MessageLike();
		messageLike.setMid(2);
		User user = new User();
		user.setUid(1);
		System.out.println();/*####################*/
		messageLike.setUid(user.getUid());
		messageService.saveMessageLike(messageLike);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("nickname", user.getNickname());
		result = jsonObject.toString();
		
		System.out.println("\nresult:\n" + result + "\n");
		return "/test_show.jsp";
	}	
	@RequestMapping(value="/test/TestDeleteLike")
	public String testDeleteLike() throws Exception{
		System.out.println("........TestHandler...........testDeleteLike()........");
		String result = null;

		MessageLike messageLike = new MessageLike();
		messageLike.setMid(2);
		User user = new User();
		user.setUid(1);
		System.out.println();/*####################*/
		messageLike.setUid(user.getUid());
		messageService.deleteMessageLikeLogicallyByMidAndUid(messageLike);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("state", 1);
		result = jsonObject.toString();
		
		System.out.println("\nresult:\n" + result + "\n");
		return "/test_show.jsp";
	}	
	@RequestMapping(value="/test/TestSelectMessageByQidAndPage")
	public String testSelectMessageByQidAndPage() throws Exception{
		System.out.println("........TestHandler...........testSelectMessageByQidAndPage()........");
		String result = null;

		QIDAndPage qidAndPage = new QIDAndPage();
		qidAndPage.setStartId(1000);
		int qid = 1;
		System.out.println();/*####################*/
		qidAndPage.setQid(qid);
		qidAndPage.initPageBean(Config.MessagePageSize);
		List<Message> messages =  messageService.selectMessageByQidAndPage(qidAndPage);
		JsonObject jsonObject = new JsonObject();
		JsonArray jsonArray = new JsonArray();
		if(messages!=null && !messages.isEmpty()){
			for(Message message : messages){
				JsonObject object = new JsonObject();
				object.addProperty("mid", message.getMid());
				object.addProperty("mtitle", message.getMtitle());
				object.addProperty("mtime", message.getMtime());
				JsonArray imgArray = new JsonArray();
				if(message.getImgs()!=null && !message.getImgs().isEmpty()){
					for(MessageImg img : message.getImgs()){
						JsonObject imgObject = new JsonObject();
						imgObject.addProperty("imgurl", img.getImgurl());
						imgArray.add(imgObject);
					}
				}
				object.add("messageimgs", imgArray);
				JsonArray likeArray = new JsonArray();
				if(message.getLikes()!=null && !message.getLikes().isEmpty()){
					for(MessageLike like : message.getLikes()){
						JsonObject likeObject = new JsonObject();
						likeObject.addProperty("nickname", like.getUser().getNickname());
						likeArray.add(likeObject);
					}
				}
				object.add("messagelikes", likeArray);
				JsonArray replyArray = new JsonArray();
				if(message.getReplies()!=null && !message.getReplies().isEmpty()){
					for(MessageReply reply : message.getReplies()){
						JsonObject replyObject = new JsonObject();
						replyObject.addProperty("nickname", reply.getUser().getNickname());
						replyObject.addProperty("content", reply.getContent());
						replyArray.add(replyObject);
					}
				}
				object.add("messagereply", replyArray);
				jsonArray.add(object);
			}
		}
		jsonObject.add("messages", jsonArray);
		result = jsonObject.toString();
		
		System.out.println("\nresult:\n" + result + "\n");
		return "/test_show.jsp";
	}	
	@RequestMapping(value="/test/TestSelectMessageByQidDownFresh")
	public String testSelectMessageByQidDownFresh() throws Exception{
		System.out.println("........TestHandler...........testSelectMessageByQidDownFresh()........");
		String result = null;

		int maxId = 7;
		int qid = 1;
		System.out.println();/*####################*/
		System.out.println("maxId = " + maxId);							/*####################*/
		List<Message> messages =messageService.selectMessageByQidDownFresh(qid, maxId);
		JsonObject jsonObject = new JsonObject();
		JsonArray jsonArray = new JsonArray();
		if(messages!=null && !messages.isEmpty()){
			for(Message message : messages){
				JsonObject object = new JsonObject();
				object.addProperty("mid", message.getMid());
				object.addProperty("mtitle", message.getMtitle());
				object.addProperty("mtime", message.getMtime());
				JsonArray imgArray = new JsonArray();
				if(message.getImgs()!=null && !message.getImgs().isEmpty()){
					for(MessageImg img : message.getImgs()){
						JsonObject imgObject = new JsonObject();
						imgObject.addProperty("imgurl", img.getImgurl());
						imgArray.add(imgObject);
					}
				}
				object.add("messageimgs", imgArray);
				JsonArray likeArray = new JsonArray();
				if(message.getLikes()!=null && !message.getLikes().isEmpty()){
					for(MessageLike like : message.getLikes()){
						JsonObject likeObject = new JsonObject();
						likeObject.addProperty("nickname", like.getUser().getNickname());
						likeArray.add(likeObject);
					}
				}
				object.add("messagelikes", likeArray);
				JsonArray replyArray = new JsonArray();
				if(message.getReplies()!=null && !message.getReplies().isEmpty()){
					for(MessageReply reply : message.getReplies()){
						JsonObject replyObject = new JsonObject();
						replyObject.addProperty("nickname", reply.getUser().getNickname());
						replyObject.addProperty("content", reply.getContent());
						replyArray.add(replyObject);
					}
				}
				object.add("messagereply", replyArray);
				jsonArray.add(object);
			}
		}
		jsonObject.add("messages", jsonArray);
		result = jsonObject.toString();
		
		System.out.println("\nresult:\n" + result + "\n");
		return "/test_show.jsp";
	}
	/********************************MessageHandler********************************/
	
	
	/********************************BranchHandler********************************/	
	@RequestMapping(value="/test/TestSelectBranchByQID")
	public String testSelectBranchByQID() throws Exception{
		System.out.println("........TestHandler...........testSelectBranchByQID()........");
		String result = null;

		int qid = 1;
		System.out.println();/*####################*/
		List<Address> addresses = addressService.selectAddressByQid(qid);
		JsonObject jsonObject = new JsonObject();
		JsonArray jsonArray = new JsonArray();
		if(addresses!=null && !addresses.isEmpty()){
			for(Address address : addresses){
				JsonObject object = new JsonObject();
				object.addProperty("branch", address.getBranch());
				object.addProperty("address", address.getAddress());
				object.addProperty("tel", address.getTel());
				object.addProperty("longitude", address.getLongitude());
				object.addProperty("latitude", address.getLatitude());
				jsonArray.add(object);
			}
		}
		jsonObject.add("branches", jsonArray);
		result = jsonObject.toString();
		
		System.out.println("\nresult:\n" + result + "\n");
		return "/test_show.jsp";
	}
	/********************************BranchHandler********************************/
	
	
	/********************************SorderHandler********************************/
	@RequestMapping(value="/test/TestSaveSorderPaid")
	public String testSaveSorderPaid() throws Exception{
		System.out.println("........TestHandler...........testSaveSorderPaid()........");
		String result = null;

		Sorder sorder = new Sorder();
		sorder.setLid(1);
		sorder.setTotal(0.01);
		sorder.setActual(0.01);
		sorder.setUsername("费园园");
		sorder.setTel("18622351756");
		sorder.setBranch("实训中心");
		int qid = 1;
		User user = new User();
		user.setUid(1);
		System.out.println();/*####################*/
		sorder.setQid(qid);
		sorder.setUid(user.getUid());
		sorder.setStatus(Config.SorderStatusPaid);
		//TODO 加优惠券/transactionId/		
		sorderService.saveSorder(sorder);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("state", 1);
		result = jsonObject.toString();
		
		System.out.println("\nresult:\n" + result + "\n");
		return "/test_show.jsp";
	}	
	@RequestMapping(value="/test/TestSaveSorderUnpaid")
	public String testSaveSorderUnpaid() throws Exception{
		System.out.println("........TestHandler...........testSaveSorderUnpaid()........");
		String result = null;

		Sorder sorder = new Sorder();
		sorder.setLid(2);
		sorder.setTotal(0.02);
		sorder.setActual(0.02);
		int qid = 1;
		User user = new User();
		user.setUid(1);
		System.out.println();/*####################*/
		sorder.setQid(qid);
		sorder.setUid(user.getUid());
		sorder.setStatus(Config.SorderStatusUnpaid);
		//TODO actual等如何设置
		sorderService.saveSorder(sorder);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("state", 1);
		result = jsonObject.toString();
		
		System.out.println("\nresult:\n" + result + "\n");
		return "/test_show.jsp";
	}	
	@RequestMapping(value="/test/TestSaveRefund")
	public String testSaveRefund() throws Exception{
		System.out.println("........TestHandler...........testSaveRefund()........");
		String result = null;

		Refund refund = new Refund();
		refund.setOid(49);
		refund.setRefundreason("不想上了");
		System.out.println();/*####################*/
		Sorder sorder = new Sorder();
		sorder.setOid(refund.getOid());
		sorder.setStatus(Config.SorderStatusRefunding);
		refund.setStatus(Config.RefundStatusUnprocessed);
		sorderService.saveRefund(sorder, refund);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("state", 1);
		result = jsonObject.toString();
		
		System.out.println("\nresult:\n" + result + "\n");
		return "/test_show.jsp";
	}	
	@RequestMapping(value="/test/TestCancelUnpaidSorder")
	public String testCancelUnpaidSorder() throws Exception{
		System.out.println("........TestHandler...........testCancelUnpaidSorder()........");
		String result = null;

		Sorder sorder = new Sorder();
		sorder.setOid(47);
		System.out.println();/*####################*/
		sorderService.deleteSorderPowerfullyByOID(sorder);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("state", 1);
		result = jsonObject.toString();
		
		System.out.println("\nresult:\n" + result + "\n");
		return "/test_show.jsp";
	}	
	@RequestMapping(value="/test/TestCancelRefundUnprocessed")
	public String testCancelRefundUnprocessed() throws Exception{
		System.out.println("........TestHandler...........testCancelRefundUnprocessed()........");
		String result = null;

		Refund refund = new Refund();
		refund.setOid(50);
		System.out.println();/*####################*/
		Sorder sorder = new Sorder();
		sorder.setOid(refund.getOid());
		sorder.setStatus(Config.SorderStatusPaid);
		sorderService.deleteRefundPowerfullyByOID(refund, sorder);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("state", 1);
		result = jsonObject.toString();
		
		System.out.println("\nresult:\n" + result + "\n");
		return "/test_show.jsp";
	}	
	@RequestMapping(value="/test/TestDeleteSorderCAV")
	public String testDeleteSorderCAV() throws Exception{
		System.out.println("........TestHandler...........testDeleteSorderCAV()........");
		String result = null;

		Sorder sorder = new Sorder();
		sorder.setOid(43);
		System.out.println();/*####################*/
		sorderService.deleteSorderLogicallyByOID(sorder);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("state", 1);
		result = jsonObject.toString();
		
		System.out.println("\nresult:\n" + result + "\n");
		return "/test_show.jsp";
	}	
	@RequestMapping(value="/test/TestDeleteSorderRefunded")
	public String testDeleteSorderRefunded() throws Exception{
		System.out.println("........TestHandler...........testDeleteSorderRefunded()........");
		String result = null;

		Sorder sorder = new Sorder();
		sorder.setOid(70);
		System.out.println();/*####################*/
		Refund refund = new Refund();
		refund.setOid(sorder.getOid());
		sorderService.deleteRefundLogicallyByOID(refund, sorder);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("state", 1);
		result = jsonObject.toString();
		
		System.out.println("\nresult:\n" + result + "\n");
		return "/test_show.jsp";
	}	
	@RequestMapping(value="/test/TestPaySorderUnpaid")
	public String testPaySorderUnpaid() throws Exception{
		System.out.println("........TestHandler...........testPaySorderUnpaid()........");
		String result = null;

		Sorder sorder = new Sorder();
		sorder.setOid(51);
		sorder.setTotal(0.01);
		sorder.setActual(0.01);
		sorder.setUsername("希拉里");
		sorder.setTel("21323112311");
		sorder.setBranch("实训中心");
		int qid = 1;
		System.out.println();/*####################*/
		sorder.setQid(qid);
		sorder.setStatus(Config.SorderStatusPaid);
		sorderService.updateSorderByOID(sorder);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("state", 1);
		result = jsonObject.toString();
		
		System.out.println("\nresult:\n" + result + "\n");
		return "/test_show.jsp";
	}	
	@RequestMapping(value="/test/TestSelectSorderBySorderQueryCondition")
	public String testSelectSorderBySorderQueryCondition() throws Exception{
		System.out.println("........TestHandler...........testSelectSorderBySorderQueryCondition()........");
		String result = null;

		SorderQueryCondition sorderQueryCondition = new SorderQueryCondition();
		sorderQueryCondition.setStartId(1000);
		User user = new User();
		user.setUid(1);
		System.out.println();/*####################*/
		sorderQueryCondition.setUid(user.getUid());
		sorderQueryCondition.initPageBean(Config.SorderPageSize);
		List<Sorder> sorders = sorderService.selectSorderBySorderQueryCondition(sorderQueryCondition);
		JsonObject jsonObject = new JsonObject();
		JsonArray jsonArray = new JsonArray();
		if(sorders!=null && !sorders.isEmpty()){
			for(Sorder sorder : sorders){
				JsonObject object = new JsonObject();
				object.addProperty("oid", sorder.getOid());
				object.addProperty("status", sorder.getStatus());
				object.addProperty("total", sorder.getActual());
				object.addProperty("actual", sorder.getActual());
				object.addProperty("ordertime", sorder.getOrdertime());
				object.addProperty("lname", sorder.getLesson().getLname());
				object.addProperty("imgurl", sorder.getLesson().getImgurl());
				jsonArray.add(object);
			}
		}
		jsonObject.add("sorders", jsonArray);
		result = jsonObject.toString();
		
		System.out.println("\nresult:\n" + result + "\n");
		return "/test_show.jsp";
	}
	@RequestMapping(value="/test/TestSelectSorderUnpaidBySorderQueryCondition")
	public String testSelectSorderUnpaidBySorderQueryCondition() throws Exception{
		System.out.println("........TestHandler...........testSelectSorderUnpaidBySorderQueryCondition()........");
		String result = null;

		SorderQueryCondition sorderQueryCondition = new SorderQueryCondition();
		sorderQueryCondition.setStartId(1000);
		User user = new User();
		user.setUid(1);
		System.out.println();/*####################*/
		sorderQueryCondition.setUid(user.getUid());
		sorderQueryCondition.setStatus(Config.SorderStatusUnpaid);
		sorderQueryCondition.initPageBean(Config.SorderPageSize);
		List<Sorder> sorders = sorderService.selectSorderBySorderQueryCondition(sorderQueryCondition);
		JsonObject jsonObject = new JsonObject();
		JsonArray jsonArray = new JsonArray();
		if(sorders!=null && !sorders.isEmpty()){
			for(Sorder sorder : sorders){
				JsonObject object = new JsonObject();
				object.addProperty("oid", sorder.getOid());
				object.addProperty("status", sorder.getStatus());
				object.addProperty("total", sorder.getActual());
				object.addProperty("actual", sorder.getActual());
				object.addProperty("ordertime", sorder.getOrdertime());
				object.addProperty("lname", sorder.getLesson().getLname());
				object.addProperty("imgurl", sorder.getLesson().getImgurl());
				jsonArray.add(object);
			}
		}
		jsonObject.add("sorders", jsonArray);
		result = jsonObject.toString();
		
		System.out.println("\nresult:\n" + result + "\n");
		return "/test_show.jsp";
	}	
	@RequestMapping(value="/test/TestSelectSorderPaidBySorderQueryCondition")
	public String testSelectSorderPaidBySorderQueryCondition() throws Exception{
		System.out.println("........TestHandler...........testSelectSorderPaidBySorderQueryCondition()........");
		String result = null;

		SorderQueryCondition sorderQueryCondition = new SorderQueryCondition();
		sorderQueryCondition.setStartId(1000);
		User user = new User();
		user.setUid(1);
		System.out.println();/*####################*/
		sorderQueryCondition.setUid(user.getUid());
		sorderQueryCondition.setStatus(Config.SorderStatusPaid);
		sorderQueryCondition.initPageBean(Config.SorderPageSize);
		List<Sorder> sorders = sorderService.selectSorderBySorderQueryCondition(sorderQueryCondition);
		JsonObject jsonObject = new JsonObject();
		JsonArray jsonArray = new JsonArray();
		if(sorders!=null && !sorders.isEmpty()){
			for(Sorder sorder : sorders){
				JsonObject object = new JsonObject();
				object.addProperty("oid", sorder.getOid());
				object.addProperty("status", sorder.getStatus());
				object.addProperty("total", sorder.getActual());
				object.addProperty("actual", sorder.getActual());
				object.addProperty("ordertime", sorder.getOrdertime());
				object.addProperty("lname", sorder.getLesson().getLname());
				object.addProperty("imgurl", sorder.getLesson().getImgurl());
				jsonArray.add(object);
			}
		}
		jsonObject.add("sorders", jsonArray);
		result = jsonObject.toString();
		
		System.out.println("\nresult:\n" + result + "\n");
		return "/test_show.jsp";
	}	
	@RequestMapping(value="/test/TestSelectSorderCAVBySorderQueryCondition")
	public String testSelectSorderCAVBySorderQueryCondition() throws Exception{
		System.out.println("........TestHandler...........testSelectSorderCAVBySorderQueryCondition()........");
		String result = null;

		SorderQueryCondition sorderQueryCondition = new SorderQueryCondition();
		sorderQueryCondition.setStartId(1000);
		User user = new User();
		user.setUid(1);
		System.out.println();/*####################*/
		sorderQueryCondition.setUid(user.getUid());
		sorderQueryCondition.setStatus(Config.SorderStatusCAV);
		sorderQueryCondition.initPageBean(Config.SorderPageSize);
		List<Sorder> sorders = sorderService.selectSorderBySorderQueryCondition(sorderQueryCondition);
		JsonObject jsonObject = new JsonObject();
		JsonArray jsonArray = new JsonArray();
		if(sorders!=null && !sorders.isEmpty()){
			for(Sorder sorder : sorders){
				JsonObject object = new JsonObject();
				object.addProperty("oid", sorder.getOid());
				object.addProperty("status", sorder.getStatus());
				object.addProperty("total", sorder.getActual());
				object.addProperty("actual", sorder.getActual());
				object.addProperty("ordertime", sorder.getOrdertime());
				object.addProperty("lname", sorder.getLesson().getLname());
				object.addProperty("imgurl", sorder.getLesson().getImgurl());
				jsonArray.add(object);
			}
		}
		jsonObject.add("sorders", jsonArray);
		result = jsonObject.toString();
		
		System.out.println("\nresult:\n" + result + "\n");
		return "/test_show.jsp";
	}	
	@RequestMapping(value="/test/TestSelectRefundBySorderQueryCondition")
	public String testSelectRefundBySorderQueryCondition() throws Exception{
		System.out.println("........TestHandler...........testSelectRefundBySorderQueryCondition()........");
		String result = null;

		SorderQueryCondition sorderQueryCondition = new SorderQueryCondition();
		sorderQueryCondition.setStartId(1000);
		User user = new User();
		user.setUid(1);
		System.out.println();/*####################*/
		sorderQueryCondition.setUid(user.getUid());
		sorderQueryCondition.setStatus("退款");						/*####################*/
		sorderQueryCondition.initPageBean(Config.SorderPageSize);
		List<Sorder> sorders = sorderService.selectSorderBySorderQueryCondition(sorderQueryCondition);
		JsonObject jsonObject = new JsonObject();
		JsonArray jsonArray = new JsonArray();
		if(sorders!=null && !sorders.isEmpty()){
			for(Sorder sorder : sorders){
				JsonObject object = new JsonObject();
				object.addProperty("oid", sorder.getOid());
				object.addProperty("status", sorder.getStatus());
				object.addProperty("total", sorder.getActual());
				object.addProperty("actual", sorder.getActual());
				object.addProperty("ordertime", sorder.getOrdertime());
				object.addProperty("lname", sorder.getLesson().getLname());
				object.addProperty("imgurl", sorder.getLesson().getImgurl());
				jsonArray.add(object);
			}
		}
		jsonObject.add("sorders", jsonArray);
		result = jsonObject.toString();
		
		System.out.println("\nresult:\n" + result + "\n");
		return "/test_show.jsp";
	}	
	@RequestMapping(value="/test/TestSelectRefundInfoByOID")
	public String testSelectRefundInfoByOID() throws Exception{
		System.out.println("........TestHandler...........testSelectRefundInfoByOID()........");
		String result = null;

		int oid = 56;
		System.out.println();/*####################*/
		Sorder sorder = sorderService.selectSorderByOid(oid);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("oid", oid);
		jsonObject.addProperty("lname", sorder.getLesson().getLname());
		jsonObject.addProperty("actual", sorder.getActual());
		result = jsonObject.toString();
		
		System.out.println("\nresult:\n" + result + "\n");
		return "/test_show.jsp";
	}	
	@RequestMapping(value="/test/TestSelectUnpaidSorderInfoByOID")
	public String testSelectUnpaidSorderInfoByOID() throws Exception{
		System.out.println("........TestHandler...........testSelectUnpaidSorderInfoByOID()........");
		String result = null;

		int oid = 47;
		User user = new User();
		user.setUid(1);
		System.out.println();/*####################*/
		Lesson lesson = lessonService.selectLessonByOid(oid);
		JsonObject jsonObject = new JsonObject();
		JsonArray jsonArray = new JsonArray();
		jsonObject.addProperty("oid", oid);
		jsonObject.addProperty("lname", lesson.getLname());
		jsonObject.addProperty("lprice", lesson.getLprice());
		jsonObject.addProperty("username", user.getUsername());
		jsonObject.addProperty("tel", user.getTel());
		/*优惠券、积分……*/
		if(lesson.getBranches()!=null && !lesson.getBranches().isEmpty()){
			for(Address address : lesson.getBranches()){
				JsonObject object = new JsonObject();
				object.addProperty("branch", address.getBranch());
				jsonArray.add(object);
			}
		}
		jsonObject.add("branches", jsonArray);
		result = jsonObject.toString();
		
		System.out.println("\nresult:\n" + result + "\n");
		return "/test_show.jsp";
	}
	/********************************SorderHandler********************************/
	
	
	/********************************TeacherHandler********************************/	
	@RequestMapping(value="/test/TestSelectTeacherByQIDAndPage")
	public String testSelectTeacherByQIDAndPage() throws Exception{
		System.out.println("........TestHandler...........testSelectTeacherByQIDAndPage()........");
		String result = null;

		QIDAndPage qidAndPage = new QIDAndPage();
		qidAndPage.setStartId(1000);
		int qid = 1;
		System.out.println();/*####################*/
		qidAndPage.setQid(qid);
		qidAndPage.initPageBean(Config.TeacherPageSize);
		List<Teacher> teachers = teacherService.selectTeacherByQIDAndPage(qidAndPage);
		JsonObject jsonObject = new JsonObject();
		JsonArray jsonArray = new JsonArray();
		if(teachers!=null && !teachers.isEmpty()){
			for(Teacher teacher : teachers){
				JsonObject object = new JsonObject();
				object.addProperty("tid", teacher.getTid());
				object.addProperty("tname", teacher.getTname());
				object.addProperty("tphoto", teacher.getTphoto());
				object.addProperty("introduction", teacher.getIntroduction());
				jsonArray.add(object);
			}
		}
		jsonObject.add("teachers", jsonArray);
		result = jsonObject.toString();
		
		System.out.println("\nresult:\n" + result + "\n");
		return "/test_show.jsp";
	}	
	@RequestMapping(value="/test/TestSelectTeacherSwiperByQID")
	public String testSelectTeacherSwiperByQID() throws Exception{
		System.out.println("........TestHandler...........testSelectTeacherSwiperByQID()........");
		String result = null;

		int qid = 1;
		System.out.println();/*####################*/
		QIDAndCategory qidAndCategory = new QIDAndCategory();
		qidAndCategory.setQid(qid);
		qidAndCategory.setCategory(Config.TeacherSwiper);
		List<String> swiperImgurls = swiperService.selectSwiperImgurlByQIDAndCategory(qidAndCategory);
		JsonObject jsonObject = new JsonObject();
		JsonArray jsonArray = new JsonArray();
		if(swiperImgurls!=null && !swiperImgurls.isEmpty()){
			for(String imgurl : swiperImgurls){
				JsonObject object = new JsonObject();
				object.addProperty("imgurl", imgurl);
				jsonArray.add(object);
			}
		}
		jsonObject.add("swipers", jsonArray);
		result = jsonObject.toString();
		
		System.out.println("\nresult:\n" + result + "\n");
		return "/test_show.jsp";
	}
	/********************************TeacherHandler********************************/
	
	
	/********************************UserHandler********************************/	
	@RequestMapping(value="/test/TestUpdateUser")
	public String testUpdateUser() throws Exception{
		System.out.println("........TestHandler...........testUpdateUser()........");
		String result = null;

		User u = new User();
		u.setTel("17863538273");
		u.setNickname("希拉里");
		u.setUsername("克林顿");
		User user = new User();
		user.setUid(10);
		System.out.println();/*####################*/
		user.setTel(u.getTel());
		user.setNickname(u.getNickname());
		user.setUsername(u.getUsername());
		userService.updateUser(user);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("state", 1);
		result = jsonObject.toString();
		
		System.out.println("\nresult:\n" + result + "\n");
		return "/test_show.jsp";
	}	
	@RequestMapping(value="/test/TestUpdateUserimg")
	public String testUpdateUserimg() throws Exception{
		System.out.println("........TestHandler...........testUpdateUserimg()........");
		String result = null;

		MultipartFile newUserimg = null;
		User user = new User();
		user.setUid(10);
		System.out.println();/*####################*/
		userService.updateUserimg(user, newUserimg);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("state", 1);
		jsonObject.addProperty("userimg", user.getUserimg());
		result = jsonObject.toString();
		
		System.out.println("\nresult:\n" + result + "\n");
		return "/test_show.jsp";
	}	
	@RequestMapping(value="/test/TestSelectUserByUID")
	public String testSelectUserByUID() throws Exception{
		System.out.println("........TestHandler...........testSelectUserByUID()........");
		String result = null;

		User user = new User();
		System.out.println();/*####################*/
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("tel", user.getTel());
		jsonObject.addProperty("nickname", user.getNickname());
		jsonObject.addProperty("username", user.getUsername());
		jsonObject.addProperty("userimg", user.getUserimg());
		result = jsonObject.toString();
		
		System.out.println("\nresult:\n" + result + "\n");
		return "/test_show.jsp";
	}	
	@RequestMapping(value="/test/TestSelectMyOwnInfo")
	public String testSelectMyOwnInfo() throws Exception{
		System.out.println("........TestHandler...........testSelectMyOwnInfo()........");
		String result = null;

		User user = null;
		System.out.println();/*####################*/
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
		jsonObject.addProperty("username", user.getUsername());
		jsonObject.addProperty("countBookUnprocessed", countBookUnprocessed);
		jsonObject.addProperty("countSorderUnpaid", countSorderUnpaid);
		result = jsonObject.toString();
		
		System.out.println("\nresult:\n" + result + "\n");
		return "/test_show.jsp";
	}
	/********************************UserHandler********************************/
	
	
	/********************************LogInAndOutHandler********************************/
	/********************************LogInAndOutHandler********************************/

}
