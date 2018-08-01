package com.lamport.education.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lamport.education.mapper.SwiperMapper;
import com.lamport.education.service.SwiperService;
import com.lamport.education.vo.QIDAndCategory;

@Service
public class SwiperServiceBean implements SwiperService {

	@Autowired
	SwiperMapper swiperMapper;
	
	@Override
	public List<String> selectSwiperImgurlByQIDAndCategory(QIDAndCategory qidAndCategory) throws Exception {
		List<String> swiperImgurls = null;
		
		swiperImgurls = swiperMapper.selectSwiperImgurlByQIDAndCategory(qidAndCategory);
		
		return swiperImgurls;
	}

}
