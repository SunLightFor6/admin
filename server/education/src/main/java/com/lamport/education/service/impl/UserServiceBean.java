package com.lamport.education.service.impl;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.lamport.education.util.FileTool;
import com.lamport.education.mapper.UserMapper;
import com.lamport.education.po.User;
import com.lamport.education.util.Config;
import com.lamport.education.vo.QIDAndTel;

@Service
public class UserServiceBean implements com.lamport.education.service.UserService {
	
	@Autowired
	UserMapper userMapper;
	
	@Override
	public void updateUser(User user) throws Exception { 
		userMapper.updateUser(user);
	}
	
	@Override
	public void updateUserimg(User user, MultipartFile multipartFile) throws Exception {
		String userimg = null;
		File imgFile = null;
		if(multipartFile != null){
			String filename = System.currentTimeMillis() + "_" + user.getUid();
			imgFile = new File(Config.RootPath + Config.ImgUserPath, filename);
			userimg = Config.ImgUserPath + "/" + filename;
		}
		String oldUserImg = Config.RootPath + user.getUserimg();
		if(userimg != null){
			user.setUserimg(userimg);
			userMapper.updateUserimg(user);
			multipartFile.transferTo(imgFile);
			FileTool.deleteFile(oldUserImg);
		}
	}
	
	@Override
	public User selectUserByUid(int uid) throws Exception {		 
		return userMapper.selectUserByUId(uid);
	}
	
	@Override
	public User selectUserByQidAndTel(QIDAndTel qidAndTel) throws Exception {
		User selectUser = null;

		selectUser = userMapper.selectUserByQidAndTel(qidAndTel);
		if(selectUser == null){
			//如果用户不存在，则创建用户对象
			User user = new User();
			user.setQid(qidAndTel.getQid());
			user.setTel(qidAndTel.getTel());
			user.setNickname(qidAndTel.getTel().substring(0, 3) + "****" + qidAndTel.getTel().substring(7, 11));
			user.setUserimg(Config.ImgUserPath + "/default.jpg");
			user.setDeletekey(0);
			userMapper.saveUser(user);
			selectUser = user;
		}

		return selectUser;
	}
	
}
