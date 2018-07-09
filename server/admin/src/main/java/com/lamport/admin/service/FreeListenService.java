package com.lamport.admin.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.lamport.admin.po.FreeListen;
import com.lamport.admin.vo.QIDAndPage;

/**
 * Service, 提供FreeListen信息的增加、删除、修改、查询功能
 * @author Lin Zhao， protector of Sherry
 *
 */
public interface FreeListenService {
	/**
	 * 创建FreeListen
	 * @param freeListen
	 * @return 1 保存成功 0 保存失败
	 * @throws Exception
	 */
	public int saveFreeListen(FreeListen freeListen, MultipartFile upload) throws Exception;
	/**
	 * 通过id逻辑删除FreeListen信息
	 * @param id
	 * @return 1 删除成功 0 删除失败
	 * @throws Exception
	 */
	public int deleteFreeListenLogicallyByID(int id) throws Exception;
	/**
	 * 通过id更新FreeListen信息
	 * @param freeListen
	 * @return 1 更新成功 0 更新失败
	 * @throws Exception
	 */
	public int updateFreeListenByID(FreeListen freeListen, MultipartFile upload) throws Exception;
	/**
	 * 通过qid和页码查询FreeListen信息
	 * @return
	 * @throws Exception
	 */
	public List<FreeListen> selectFreeListenByQIDAndPage(QIDAndPage qidAndPage) throws Exception;
	/**
	 * 通过qid查询FreeListen的总数
	 * @return int
	 * @throws Exception
	 */
	public int selectCountFreeListenByQID(int qid) throws Exception;
}