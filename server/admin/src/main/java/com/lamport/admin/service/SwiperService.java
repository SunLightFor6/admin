package com.lamport.admin.service;

import java.util.List;

import com.lamport.admin.po.Swiper;

/**
 * Service, 提供Swiper信息的增加、删除、更新、查询功能
 * @author Lin Zhao， protector of Sherry
 *
 */
public interface SwiperService {
	/**
	 * 通过qid和category查询Swiper(轮播图)信息
	 * @return List
	 * @throws Exception
	 */
	public List<Swiper> selectSwiperByQIDAndCategory(Swiper swiper) throws Exception;
}
