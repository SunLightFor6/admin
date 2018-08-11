package com.lamport.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.lamport.admin.fileupload.FileManager;
import com.lamport.admin.mapper.AddressMapper;
import com.lamport.admin.mapper.LessonBranchMapper;
import com.lamport.admin.mapper.LessonMapper;
import com.lamport.admin.mapper.SorderMapper;
import com.lamport.admin.po.Address;
import com.lamport.admin.po.Lesson;
import com.lamport.admin.po.LessonBranch;
import com.lamport.admin.po.Sorder;
import com.lamport.admin.service.LessonService;
import com.lamport.admin.vo.BranchIDAndPage;
import com.lamport.admin.vo.LessonQueryCondition;
import com.lamport.admin.vo.QIDAndBranch;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;

/**
 * implements LessonService
 * @author Lin Zhao, protector of Sherry
 *
 */
@Service
public class LessonServiceBean implements LessonService {

	@Autowired
	private LessonMapper lessonMapper;
	@Autowired
	private LessonBranchMapper lessonBranchMapper;
	@Autowired
	private SorderMapper sorderMapper;
	@Autowired
	private AddressMapper addressMapper;
	@Autowired
	private JedisPool jedisPool;
	
	@Override
	public int saveLesson(Lesson lesson, MultipartFile img, String path) throws Exception {
		System.out.println("..........LessonServiceBean..........saveLesson()..........");
		int saveResult = 1;
		
		String imgurl = null;
		if(img != null){
			imgurl = FileManager.upload(img);
		}
		lesson.setImgurl(imgurl);
		lesson.setDeletekey(0);
		saveResult *= lessonMapper.saveLesson(lesson);
		if(lesson.getBranchs()!=null){
			for(int i=0; i<lesson.getBranchs().length; i++){
				QIDAndBranch qidAndBranch = new QIDAndBranch();
				qidAndBranch.setQid(lesson.getQid());
				qidAndBranch.setBranch(lesson.getBranchs()[i]);
				Address address = addressMapper.selectAddressIDByQIDAndBranch(qidAndBranch);
				LessonBranch lessonBranch = new LessonBranch();
				lessonBranch.setLid(lesson.getLid());
				lessonBranch.setBranchid(address.getId());
				lessonBranch.setDeletekey(0);
				saveResult *= lessonBranchMapper.saveLessonBranch(lessonBranch);
			}
		}
		
		/*------------------------------Redis相关------------------------------*/
		//Lesson已经发生了变化，将旧的HomePageLesson信息从Redis中删除
		Jedis jedis = jedisPool.getResource();
		String key = "homePageLesson" + "-" + lesson.getQid();
		//开启事务
		Transaction transaction = jedis.multi();
		//删除
		transaction.del(key);
		//结束事务
		transaction.exec();
		jedis.close();
		/*------------------------------Redis相关------------------------------*/
		
		return saveResult;
	}

	@Override
	public int deleteLessonLogicallyByID(int id, int qid) throws Exception {
		System.out.println("..........LessonServiceBean..........deleteLessonLogicallyByID()..........");

		int deleteResult = 1;
		
		List<Sorder> sorders = sorderMapper.selectSorderByLID(id);
		if(sorders!=null && !sorders.isEmpty()){
			sorderMapper.deleteMultiRefundLogicallyByOID(sorders);
		}
		sorderMapper.deleteSorderLogicallyByLID(id);
		lessonBranchMapper.deleteLessonBranchLogicallyByLID(id);
		deleteResult *= lessonMapper.deleteLessonLogicallyByID(id);
		deleteResult = deleteResult>0 ? 1 : 0;
		
		/*------------------------------Redis相关------------------------------*/
		//Lesson已经发生了变化，将旧的HomePageLesson信息从Redis中删除
		Jedis jedis = jedisPool.getResource();
		String key = "homePageLesson" + "-" + qid;
		//开启事务
		Transaction transaction = jedis.multi();
		//删除
		transaction.del(key);
		//结束事务
		transaction.exec();
		jedis.close();
		/*------------------------------Redis相关------------------------------*/
		
		return deleteResult;
	}

	@Override
	public int updateLessonByID(Lesson lesson, MultipartFile img, String path) throws Exception {
		System.out.println("..........LessonServiceBean..........updateLessonByID()..........");

		int updateResult = 1;
		
		String imgurl = null;
		if(img != null){
			imgurl = FileManager.upload(img);
		}
//		String oldImgurl = path + lessonMapper.selectLessonImgurlByID(lesson.getLid());
		lesson.setImgurl(imgurl);
		updateResult *= lessonMapper.updateLessonByID(lesson);
		if(lesson.getBranchs()!=null){
			for(int i=0; i<lesson.getBranchs().length; i++){
				QIDAndBranch qidAndBranch = new QIDAndBranch();
				qidAndBranch.setQid(lesson.getQid());
				qidAndBranch.setBranch(lesson.getBranchs()[i]);
				Address address = addressMapper.selectAddressIDByQIDAndBranch(qidAndBranch);
				LessonBranch lessonBranch = new LessonBranch();
				lessonBranch.setLid(lesson.getLid());
				lessonBranch.setBranchid(address.getId());
				lessonBranch.setDeletekey(0);
				updateResult *= lessonBranchMapper.saveLessonBranch(lessonBranch);
			}
		}
		
		/*------------------------------Redis相关------------------------------*/
		//Lesson已经发生了变化，将旧的HomePageLesson信息从Redis中删除
		Jedis jedis = jedisPool.getResource();
		String key = "homePageLesson" + "-" + lesson.getQid();
		//开启事务
		Transaction transaction = jedis.multi();
		//删除
		transaction.del(key);
		//结束事务
		transaction.exec();
		jedis.close();
		/*------------------------------Redis相关------------------------------*/
		
		return updateResult;
	}

	@Override
	public List<Lesson> selectLessonByLessonQueryCondition(LessonQueryCondition lessonQueryCondition) throws Exception {
		System.out.println("..........LessonServiceBean..........selectLessonByLessonQueryCondition()..........");

		List<Lesson> lessons = null;
		
		if(lessonQueryCondition.getBranch() == null || lessonQueryCondition.getBranch().equals("")){
			//如果没有给出分部名称，则查找该企业下的所有精品课
			lessonQueryCondition.setBranch(null);
			//先查询该企业下Lesson的总数
			int count = lessonMapper.selectCountLessonByLessonQueryCondition(lessonQueryCondition);
			lessonQueryCondition.getPageTool().setCount(count);
			//查询不带有Address信息的Lesson信息
			lessons = lessonMapper.selectLessonByLessonQueryCondition(lessonQueryCondition);
			//依次为lesson放入Address信息
			List<Lesson> temp_lessons = new ArrayList<Lesson>();
			if(lessons!=null && !lessons.isEmpty()){
				for(Lesson lesson : lessons){
					Lesson temp_lesson = lessonMapper.selectLessonWithBranchesByLID(lesson.getLid());
					temp_lessons.add(temp_lesson);
				}
			}
			//将带有Address信息的Lesson信息赋给lessons
			lessons = temp_lessons;
//			lessons = lessonMapper.selectLessonWithBranchesByLessonQueryCondition(lessonQueryCondition);
		}else{
			//如果给出分部名称，则查找在该分部下的课程
			QIDAndBranch qidAndBranch = new QIDAndBranch();
			qidAndBranch.setQid(lessonQueryCondition.getQid());
			qidAndBranch.setBranch(lessonQueryCondition.getBranch());
			//根据qid和分部名称获取分部信息(主要为分部id)
			Address branch = addressMapper.selectAddressIDByQIDAndBranch(qidAndBranch);
			BranchIDAndPage branchIDAndPage = new BranchIDAndPage();
			if(branch == null){
				branchIDAndPage.setBranchid(0);
			}else{
				branchIDAndPage.setBranchid(branch.getId());
			}
			branchIDAndPage.setPageTool(lessonQueryCondition.getPageTool());
			//查询在该分部下有多少个Lesson(即查询branchid为目标id的LessonBranch记录有多少条)
			int count = lessonBranchMapper.selectCountLessonBranchByBranchID(branchIDAndPage.getBranchid());
			lessonQueryCondition.getPageTool().setCount(count);
			//根据branchid和Page查询LessonBranch信息
			List<LessonBranch> lessonBranchs = lessonBranchMapper.selectLessonBranchByBranchIDAndPage(branchIDAndPage);
			lessons = new ArrayList<Lesson>();
			if(lessonBranchs!=null && !lessonBranchs.isEmpty()){
				for(LessonBranch lessonBranch : lessonBranchs){
					Lesson lesson = lessonMapper.selectLessonWithBranchesByLID(lessonBranch.getLid());
					lessons.add(lesson);
				}
			}
		}
		
		return lessons;
	}

	@Override
	public int selectCountLessonByQID(int qid) throws Exception {
		System.out.println("..........LessonServiceBean..........selectCountLessonByQID()..........");

		int countLesson = 0;
		
		countLesson = lessonMapper.selectCountLessonByQID(qid);
		
		return countLesson;
	}

}
