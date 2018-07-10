package com.lamport.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.lamport.admin.mapper.SwiperMapper;
import com.lamport.admin.service.SwiperService;
import com.lamport.admin.vo.QIDAndCategory;

/**
 * implements SwiperService
 * @author Lin Zhao, protector of Sherry
 *
 */
@Service
public class SwiperServiceBean implements SwiperService {
	
	@Autowired
	private SwiperMapper swiperMapper;

	@Override
	public int updateMultipleSwipersByQIDAndCategory(QIDAndCategory qidAndCategory, MultipartFile[] imgs)
			throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<String> selectSwiperImgurlByQIDAndCategory(QIDAndCategory qidAndCategory) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
