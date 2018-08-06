package com.lamport.admin.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.lamport.admin.po.Swiper;
import com.lamport.admin.vo.QIDAndCategory;

/**
 * Service, 提供Swiper信息的修改、查询功能
 * @author Lin Zhao, protector of Sherry
 *
 */
public interface SwiperService {
	/**
	 * 通过qid和category更新Swiper信息
	 * @param qidAndCategory
	 * @return int 更新成功的条数
	 * @throws Exception
	 */
	public void updateMultipleSwipersByQIDAndCategory(QIDAndCategory qidAndCategory, MultipartFile[] imgs, String path) throws Exception;
	/**
	 * 通过qid和category查询Swiper(轮播图)信息
	 * @param qidAndCategory
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
