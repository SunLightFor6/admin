package com.lamport.admin.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.lamport.admin.vo.QIDAndCategory;

/**
 * Service, 提供Swiper信息的增加功能
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
	public int updateMultipleSwipersByQIDAndCategory(QIDAndCategory qidAndCategory, MultipartFile[] imgs) throws Exception;
	/**
	 * 通过qid和category查询Swiper(轮播图)的imgurl信息
	 * @return List
	 * @throws Exception
	 */
	public List<String> selectSwiperImgurlByQIDAndCategory(QIDAndCategory qidAndCategory) throws Exception;
}
