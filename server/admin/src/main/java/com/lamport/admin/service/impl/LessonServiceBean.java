package com.lamport.admin.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
	
	@Override
	public int saveLesson(Lesson lesson, MultipartFile img, String path) throws Exception {
		System.out.println("..........LessonServiceBean..........saveLesson()..........");

		int saveResult = 1;
		
		String imgurl = null;
		File imgFile = null;
		if(img != null){
			String filename = System.currentTimeMillis() + img.getOriginalFilename();
			imgFile = new File(path + Const.ImgLessonPath, filename);
			imgurl = Const.ImgLessonPath + "/" + filename;
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
		if(imgurl != null){
			img.transferTo(imgFile);//保存文件
		}
		
		return saveResult;
	}

	@Override
	public int deleteLessonLogicallyByID(int id) throws Exception {
		System.out.println("..........LessonServiceBean..........deleteLessonLogicallyByID()..........");

		int deleteResult = 1;

		deleteResult *= sorderMapper.deleteSorderLogicallyByLID(id);
		deleteResult *= lessonBranchMapper.deleteLessonBranchLogicallyByLID(id);
		deleteResult *= lessonMapper.deleteLessonLogicallyByID(id);
		deleteResult = deleteResult>0 ? 1 : 0;
		
		return deleteResult;
	}

	@Override
	public int updateLessonByID(Lesson lesson, MultipartFile img, String path) throws Exception {
		System.out.println("..........LessonServiceBean..........updateLessonByID()..........");

		int updateResult = 1;
		
		String imgurl = null;
		File imgFile = null;
		if(img != null){
			String filename = System.currentTimeMillis() + img.getOriginalFilename();
			imgFile = new File(path + Const.ImgLessonPath, filename);
			imgurl = Const.ImgLessonPath + "/" + filename;
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
		if(imgurl != null){
			img.transferTo(imgFile);//保存文件
			FileTool.deleteFile(oldImgurl);
		}
		return updateResult;
	}

	@Override
	public List<Lesson> selectLessonByLessonQueryCondition(LessonQueryCondition lessonQueryCondition) throws Exception {
		System.out.println("..........LessonServiceBean..........selectLessonByLessonQueryCondition()..........");

		List<Lesson> lessons = null;

		if(lessonQueryCondition.getBranch() == null || lessonQueryCondition.getBranch().equals("")){
			lessonQueryCondition.setBranch(null);
			int count = lessonMapper.selectCountLessonByQID(lessonQueryCondition.getQid());
			lessonQueryCondition.getPageTool().setCount(count);
			lessons = lessonMapper.selectLessonByLessonQueryCondition(lessonQueryCondition);
			for(Lesson lesson : lessons){
				List<Integer> branchids = lessonBranchMapper.selectBranchIDByLID(lesson.getLid());
				List<Address> addresses = new ArrayList<Address>();
				for(Integer branchid : branchids){
					Address address = addressMapper.selectAddressByID(branchid);
					addresses.add(address);
				}
				lesson.setBranches(addresses);
			}
		}else{
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
