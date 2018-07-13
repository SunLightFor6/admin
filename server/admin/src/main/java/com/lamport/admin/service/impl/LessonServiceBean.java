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
import com.lamport.admin.tool.FileTool;
import com.lamport.admin.vo.LessonQueryCondition;

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
		int saveResult = 1;
		
		String imgurl = null;
		File imgFile = null;
		if(img != null){
			File f = new File(path);
			String ppath = f.getParent();
			String filename = System.currentTimeMillis() + img.getOriginalFilename();
			imgFile = new File(ppath + "/img/teacher", filename);
			imgurl = imgFile.getPath();
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
		int deleteResult = 1;

		deleteResult *= sorderMapper.deleteSorderLogicallyByLID(id);
		deleteResult *= lessonBranchMapper.deleteLessonBranchLogicallyByLID(id);
		deleteResult *= lessonMapper.deleteLessonLogicallyByID(id);
		deleteResult = deleteResult>0 ? 1 : 0;
		
		return deleteResult;
	}

	@Override
	public int updateLessonByID(Lesson lesson, MultipartFile img, String path) throws Exception {
		int updateResult = 1;
		
		String imgurl = null;
		File imgFile = null;
		if(img != null){
			File f = new File(path);
			String ppath = f.getParent();
			String filename = System.currentTimeMillis() + img.getOriginalFilename();
			imgFile = new File(ppath + "/img/lesson", filename);
			imgurl = imgFile.getPath();
		}
		String oldImgurl = lessonMapper.selectLessonImgurlByID(lesson.getLid());
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
		List<Lesson> lessons = null;

		lessons = lessonMapper.selectLessonByLessonQueryCondition(lessonQueryCondition);
		for(Lesson lesson : lessons){
			List<Integer> branchids = lessonBranchMapper.selectBranchIDByLID(lesson.getLid());
			List<Address> addresses = new ArrayList<Address>();
			for(Integer branchid : branchids){
				addresses.add(addressMapper.selectAddressByID(branchid));
			}
			lesson.setBranches(addresses);
		}
		
		return lessons;
	}

	@Override
	public int selectCountLessonByQID(int qid) throws Exception {
		int countLesson = 0;
		
		countLesson = lessonMapper.selectCountLessonByQID(qid);
		
		return countLesson;
	}

}
