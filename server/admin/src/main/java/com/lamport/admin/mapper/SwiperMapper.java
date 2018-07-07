package com.lamport.admin.mapper;

import java.util.List;

import com.lamport.admin.po.Swiper;

/**
 * Mapper, 提供Swiper信息的增加、删除、更新、查询功能
 * @author Lin Zhao， protector of Sherry
 *
 */
public interface SwiperMapper {
	/**
	 * 通过qid和category查询Swiper(轮播图)信息
	 * @return List
	 * @throws Exception
	 */
	public List<Swiper> selectSwiperByQIDAndCategory(Swiper swiper) throws Exception;
	/**
	 * 通过id删除Swiper(轮播图)信息
	 * @return 1 删除成功 0 删除失败
	 * @throws Exception
	 */
	public int deleteSwiperByID(int id) throws Exception;
	/**
	 * 创建Swiper(轮播图)
	 * @return 1 保存成功 0 保存失败
	 * @throws Exception
	 */
	public int saveSwiper(Swiper swiper) throws Exception;
}
