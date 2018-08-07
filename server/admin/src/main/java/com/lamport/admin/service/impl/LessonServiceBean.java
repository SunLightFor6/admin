package com.lamport.admin.service.impl;

import java.io.File;
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
import com.lamport.admin.service.LessonService;
import com.lamport.admin.tool.Const;
import com.lamport.admin.tool.FileTool;
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
//		File imgFile = null;
		if(img != null){
//			Creator creator = new Creator();
//			String filename = creator.createFilename();
//			String filename = System.currentTimeMillis() + img.getOriginalFilename();
//			imgFile = new File(path + Const.ImgLessonPath, filename);
//			imgurl = Const.ImgLessonPath + "/" + filename;
			imgurl = FileManager.upload(img);
		}
		lesson.setImgurl(imgurl);
		lesson.setDeletekey(0);
		saveResult *= lessonMapper.saveLesson(lesson);
		if(lesson.getBranches() != null){
			for(Address address : lesson.getBranches()){
				LessonBranch lessonBranch = new LessonBranch();
				lessonBranch.setLid(lesson.getLid());
				lessonBranch.setBranchid(address.getId());
				lessonBranch.setDeletekey(0);
				saveResult *= lessonBranchMapper.saveLessonBranch(lessonBranch);
			}
		}
//		if(imgurl != null){
//			img.transferTo(imgFile);//保存文件
//		}
		
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
		/*------------------------------Redis相关------------------------------*/
		
		return saveResult;
	}

	@Override
	public int deleteLessonLogicallyByID(int id, int qid) throws Exception {
		System.out.println("..........LessonServiceBean..........deleteLessonLogicallyByID()..........");

		int deleteResult = 1;

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
		/*------------------------------Redis相关------------------------------*/
		
		return deleteResult;
	}

	@Override
	public int updateLessonByID(Lesson lesson, MultipartFile img, String path) throws Exception {
		System.out.println("..........LessonServiceBean..........updateLessonByID()..........");

		int updateResult = 1;
		
		String imgurl = null;
//		File imgFile = null;
		if(img != null){
//			Creator creator = new Creator();
//			String filename = creator.createFilename();
//			String filename = System.currentTimeMillis() + img.getOriginalFilename();
//			imgFile = new File(path + Const.ImgLessonPath, filename);
//			imgurl = Const.ImgLessonPath + "/" + filename;
			imgurl = FileManager.upload(img);
		}
		String oldImgurl = path + lessonMapper.selectLessonImgurlByID(lesson.getLid());
		updateResult *= lessonBranchMapper.deleteLessonBranchLogicallyByLID(lesson.getLid());
		lesson.setImgurl(imgurl);
		updateResult *= lessonMapper.updateLessonByID(lesson);
		for(Address address : lesson.getBranches()){
			LessonBranch lessonBranch = new LessonBranch();
			lessonBranch.setLid(lesson.getLid());
			lessonBranch.setBranchid(address.getId());
			lessonBranch.setDeletekey(0);
			updateResult *= lessonBranchMapper.saveLessonBranch(lessonBranch);
		}
//		if(imgurl != null){
//			img.transferTo(imgFile);//保存文件
//			FileTool.deleteFile(oldImgurl);
//		}
		
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
		/*------------------------------Redis相关------------------------------*/
		
		return updateResult;
	}

	@Override
	public List<Lesson> selectLessonByLessonQueryCondition(LessonQueryCondition lessonQueryCondition) throws Exception {
		System.out.println("..........LessonServiceBean..........selectLessonByLessonQueryCondition()..........");

		List<Lesson> lessons = null;
		
		if(lessonQueryCondition.getBranch() == null || lessonQueryCondition.getBranch().equals("")){
			//如果没有给出分部名称，则查找所有分部
			lessonQueryCondition.setBranch(null);
			
			int count = lessonBranchMapper.selectCountLessonBranchByQID(lessonQueryCondition.getQid());
			lessonQueryCondition.getPageTool().setCount(count);
			List<LessonBranch> lessonBranchs = lessonBranchMapper.selectLessonBranchByQID(lessonQueryCondition.getQid());
			lessons = new ArrayList<Lesson>();
			for(LessonBranch lessonBranch : lessonBranchs){
				Lesson lesson = lessonBranch.getLesson();
				Address address = addressMapper.selectAddressByID(lessonBranch.getBranchid());
				List<Address> addresses = new ArrayList<Address>();
				addresses.add(address);
				lesson.setBranches(addresses);
				lessons.add(lesson);
			}			
//			int count = lessonMapper.selectCountLessonByQID(lessonQueryCondition.getQid());//？？
//			lessonQueryCondition.getPageTool().setCount(count);
//			lessons = lessonMapper.selectLessonByLessonQueryCondition(lessonQueryCondition);
//			for(Lesson lesson : lessons){
//				List<Integer> branchids = lessonBranchMapper.selectBranchIDByLID(lesson.getLid());
//				List<Address> addresses = new ArrayList<Address>();
//				for(Integer branchid : branchids){
//					Address address = addressMapper.selectAddressByID(branchid);
//					addresses.add(address);
//				}
//				lesson.setBranches(addresses);
//			}
		}else{
			//如果给出分部名称，则查找在该分部下的课程
			QIDAndBranch qidAndBranch = new QIDAndBranch();
			qidAndBranch.setQid(lessonQueryCondition.getQid());
			qidAndBranch.setBranch(lessonQueryCondition.getBranch());
			Address branch = addressMapper.selectAddressIDByQIDAndBranch(qidAndBranch);
			BranchIDAndPage branchIDAndPage = new BranchIDAndPage();
			if(branch == null){
				branchIDAndPage.setBranchid(0);
			}else{
				branchIDAndPage.setBranchid(branch.getId());
			}
			branchIDAndPage.setPageTool(lessonQueryCondition.getPageTool());
			int count = lessonBranchMapper.selectCountLessonBranchByBranchID(branchIDAndPage.getBranchid());
			lessonQueryCondition.getPageTool().setCount(count);
			List<Integer> lids = lessonBranchMapper.selectLIDByBranchIDAndPage(branchIDAndPage);
			lessons = new ArrayList<Lesson>();
			for(Integer lid : lids){
				Lesson lesson = lessonMapper.selectLessonByLID(lid);
				List<Address> branches = new ArrayList<Address>();
				branches.add(branch);
				lesson.setBranches(branches);
				lessons.add(lesson);
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
