package com.lamport.admin.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.lamport.admin.po.Message;
import com.lamport.admin.vo.QIDAndPage;

/**
 * Service, 提供Messgae信息的增加、删除、查询功能
 * @author Lin Zhao, protector of Sherry
 *
 */
public interface MessageService {
	/**
	 * 创建Message信息
	 * @param message
	 * @param uploads
	 * @return 1 保存成功 0 保存失败
	 * @throws Exception
	 */
	public int saveMessage(Message message, MultipartFile[] imgs, String path) throws Exception;
	/**
	 * 通过id逻辑删除Message信息
	 * @param id
	 * @return 1 删除成功 0 删除失败
	 * @throws Exception
	 */
	public int deleteMessageLogicallyByID(int id) throws Exception;
	/**
	 * 通过id和页码查询Message信息
	 * @param qidAndPage
	 * @return List
	 * @throws Exception
	 */
	public List<Message> selectMessageByQIDAndPage(QIDAndPage qidAndPage) throws Exception;
}
