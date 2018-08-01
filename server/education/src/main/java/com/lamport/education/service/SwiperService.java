package com.lamport.education.service;

import java.util.List;

import com.lamport.education.vo.QIDAndCategory;

/**
 * Service, 提供Swiper信息的修改、查询功能
 * @author Lin Zhao, protector of Sherry
 *
 */
public interface SwiperService {
	/**
	 * 通过qid和category查询Swiper(轮播图)的imgurl信息
	 * @return List
	 * @throws Exception
	 */
	public List<String> selectSwiperImgurlByQIDAndCategory(QIDAndCategory qidAndCategory) throws Exception;
}
