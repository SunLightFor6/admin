package com.lamport.admin.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.lamport.admin.mapper.AddressMapper;
import com.lamport.admin.mapper.FreeListenBookMapper;
import com.lamport.admin.mapper.FreeListenMapper;
import com.lamport.admin.po.Address;
import com.lamport.admin.po.FreeListen;
import com.lamport.admin.po.Lesson;
import com.lamport.admin.po.LessonBranch;
import com.lamport.admin.service.FreeListenService;
import com.lamport.admin.tool.FileTool;
import com.lamport.admin.vo.FreeListenQueryCondition;

/**
 * implements FreeListenService
 * @author Lin Zhao, protector of Sherry
 *
 */
@Service
public class FreeListenServiceBean implements FreeListenService {


	@Autowired
	private FreeListenBookMapper freeListenBookMapper;	
	@Autowired
	private FreeListenMapper freeListenMapper;
	@Autowired
	private AddressMapper addressMapper;
	
	@Override
	public int saveFreeListen(FreeListen freeListen, MultipartFile img, String path) throws Exception {
		System.out.println("..........FreeListenServiceBean..........saveFreeListen()..........");

		int saveResult = 1;
		
		String imgurl = null;
		File imgFile = null;
		if(img != null) {
			File f = new File(path);
			String ppath = f.getParent();
			String filename = System.currentTimeMillis() + img.getOriginalFilename();
			imgFile = new File(ppath + "img/teacher", filename);
			imgurl = imgFile.getPath();
		}
		
		freeListen.setImgurl(imgurl);
		freeListen.setDeletekey(0);
		
		saveResult = freeListenMapper.saveFreeListen(freeListen);
		
		if(imgurl != null) {
			img.transferTo(imgFile);
		}
		return saveResult;
	}

	@Override
	public int deleteFreeListenLogicallyByID(int id) throws Exception {
		System.out.println("..........FreeListenServiceBean..........deleteFreeListenLogicallyByID()..........");

		int deleteResult = 0;
		
		deleteResult = freeListenMapper.deleteFreeListenLogicallyByID(id);
		
		return deleteResult;
	}

	@Override
	public int updateFreeListenByID(FreeListen freeListen, MultipartFile img, String path) throws Exception {
		System.out.println("..........FreeListenServiceBean..........updateFreeListenByID()..........");

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
		String oldImgurl = freeListenMapper.selectFreeListenImgurlByID(freeListen.getId());
		freeListen.setImgurl(imgurl);
		updateResult = freeListenMapper.updateFreeListenByID(freeListen);
		if(imgurl != null){
			img.transferTo(imgFile);//保存文件
			FileTool.deleteFile(oldImgurl);
		}
		return updateResult;
	}

	@Override
	public List<FreeListen> selectFreeListenByFreeListenQueryCondition(FreeListenQueryCondition freeListenQueryCondition) throws Exception {
		System.out.println("..........FreeListenServiceBean..........selectFreeListenByFreeListenQueryCondition()..........");

		List<FreeListen> freeListens = null;
		List<FreeListen> freeListens_query = null;

		freeListens = freeListenMapper.selectFreeListenByFreeListenQueryCondition(freeListenQueryCondition);
		for(FreeListen freeListen : freeListens){
			Address address = addressMapper.selectAddressByID(freeListen.getBranchid());
			freeListen.setBranch(address);
			if(freeListenQueryCondition.getBranch().equals("") || address.getBranch().equals(freeListenQueryCondition.getBranch())) {
				freeListens_query.add(freeListen);
			}
		}
		return freeListens_query;
	}

	@Override
	public int selectCountFreeListenByQID(int qid) throws Exception {
		System.out.println("..........FreeListenServiceBean..........selectCountFreeListenByQID()..........");

		int countFreeListen = 0;
		
		countFreeListen = freeListenMapper.selectCountFreeListenByQID(qid);
		
		return countFreeListen;
	}

}
