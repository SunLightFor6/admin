package com.lamport.admin.service.impl;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.lamport.admin.mapper.TeacherMapper;
import com.lamport.admin.po.Teacher;
import com.lamport.admin.service.TeacherService;
import com.lamport.admin.tool.FileTool;
import com.lamport.admin.vo.QIDAndPage;

/**
 * implements TeacherService
 * @author Lin Zhao, protector of Sherry
 *
 */
@Service
public class TeacherServiceBean implements TeacherService {

	@Autowired
	private TeacherMapper teacherMapper;
	
	@Override
	public int saveTeacher(Teacher teacher, MultipartFile teacher_img, String path) throws Exception {
		int saveResult = 1;
		
		String tphoto = null;
		File tphotoFile = null;
		if(teacher_img != null){
			File f = new File(path);
			String ppath = f.getParent();
			String filename = System.currentTimeMillis() + teacher_img.getOriginalFilename();
			tphotoFile = new File(ppath + "/img/teacher", filename);
			tphoto = tphotoFile.getPath();
		}
		teacher.setTphoto(tphoto);
		teacher.setDeletekey(0);
		saveResult = teacherMapper.saveTeacher(teacher);
		if(tphoto != null){
			teacher_img.transferTo(tphotoFile);//保存文件
		}
		
		return saveResult;
	}

	@Override
	public int deleteTeacherLogicallyByID(int id) throws Exception {
		int deleteResult = 1;

		deleteResult *= teacherMapper.deleteTeacherLogicallyByID(id);
		
		return deleteResult;
	}

	@Override
	public int updateTeacherByID(Teacher teacher, MultipartFile imgFile, String path) throws Exception {
		int updateResult = 1;
		
		String tphoto = null;
		File tphotoFile = null;
		if(imgFile != null){
			File f = new File(path);
			String ppath = f.getParent();
			String filename = System.currentTimeMillis() + imgFile.getOriginalFilename();
			tphotoFile = new File(ppath + "/img/teacher", filename);
			tphoto = tphotoFile.getPath();
		}
		String oldTphoto = teacherMapper.selectTeacherTphotoByID(teacher.getTid());
		teacher.setTphoto(tphoto);
		updateResult = teacherMapper.updateTeacherByID(teacher);
		if(tphoto != null){
			imgFile.transferTo(tphotoFile);//保存文件
			FileTool.deleteFile(oldTphoto);//删除文件？？？？？？
		}
		
		return updateResult;
	}

	@Override
	public List<Teacher> selectTeacherByQIDAndPage(QIDAndPage qidAndPage) throws Exception {
		List<Teacher> teachers = null;
		
		int count = teacherMapper.selectCountTeacherByQID(qidAndPage.getQid());
		qidAndPage.getPageTool().setCount(count);
		teachers = teacherMapper.selectTeacherByQIDAndPage(qidAndPage);
		
		return teachers;
	}

	@Override
	public int selectCountTeacherByQID(int qid) throws Exception {
		int countTeacher = 0;
		
		countTeacher = teacherMapper.selectCountTeacherByQID(qid);
		
		return countTeacher;
	}

}
