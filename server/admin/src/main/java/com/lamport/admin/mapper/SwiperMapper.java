package com.lamport.admin.mapper;

import java.util.List;

import com.lamport.admin.po.Swiper;
import com.lamport.admin.vo.QIDAndCategory;

/**
 * Mapper, 提供Swiper信息的增加、删除、更新、查询功能
 * @author Lin Zhao， protector of Sherry
 *
 */
public interface SwiperMapper {
	/**
	 * 创建Swiper(轮播图)
	 * @return 1 保存成功 0 保存失败
	 * @throws Exception
	 */
	public int saveSwiper(Swiper swiper) throws Exception;
	/**
	 * 通过qid和category逻辑删除Swiper(轮播图)信息
	 * @return 1 删除成功 0 删除失败
	 * @throws Exception
	 */
	public int deleteSwiperLogicallyByQIDAndCategory(QIDAndCategory qidAndCategory) throws Exception;
	/**
	 * 通过qid逻辑删除Swiper(轮播图)信息
	 * @return 1 删除成功 0 删除失败
	 * @throws Exception
	 */
	public int deleteSwiperLogicallyByQID(int qid) throws Exception;
	/**
	 * 通过qid和category查询Swiper(轮播图)信息
	 * @return List
	 * @throws Exception
	 */
	public List<Swiper> selectSwiperByQIDAndCategory(QIDAndCategory qidAndCategory) throws Exception;
	/**
	 * 通过qid和category查询Swiper(轮播图)的imgurl信息
	 * @return List
	 * @throws Exception
	 */
	public List<String> selectSwiperImgurlByQIDAndCategory(QIDAndCategory qidAndCategory) throws Exception;
}
