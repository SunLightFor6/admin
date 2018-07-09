package com.lamport.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.lamport.admin.mapper.FreeListenBookMapper;
import com.lamport.admin.mapper.FreeListenMapper;
import com.lamport.admin.po.FreeListen;
import com.lamport.admin.service.FreeListenService;
import com.lamport.admin.vo.QIDAndPage;

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
	
	@Override
	public int saveFreeListen(FreeListen freeListen, MultipartFile img) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteFreeListenLogicallyByID(int id) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateFreeListenByID(FreeListen freeListen, MultipartFile img) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<FreeListen> selectFreeListenByQIDAndPage(QIDAndPage qidAndPage) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int selectCountFreeListenByQID(int qid) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

}
