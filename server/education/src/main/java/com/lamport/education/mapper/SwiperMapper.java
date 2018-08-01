package com.lamport.education.mapper;

import java.util.List;

import com.lamport.education.vo.QIDAndCategory;

public interface SwiperMapper {
	
	/**
	 * 通过qid和category查询Swiper(轮播图)的imgurl信息
	 * @param qidAndCategory
	 * @return List
	 * @throws Exception
	 */
	public List<String> selectSwiperImgurlByQIDAndCategory(QIDAndCategory qidAndCategory) throws Exception;

}
