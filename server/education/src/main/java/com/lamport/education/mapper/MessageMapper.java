package com.lamport.education.mapper;

import java.util.List;

import com.lamport.education.po.Message;
import com.lamport.education.vo.QIDAndPage;

public interface MessageMapper {
	/**
	 * 通过qid和页码查询Message信息
	 * @param qidAndPage
	 * @return List<Message>
	 * @throws Exception
	 */
	public List<Message> selectMessageByQidAndPage(QIDAndPage qidAndPage) throws Exception;
	
	/**
	 * 通过下拉刷新查询Message信息
	 * @param qid
	 * @param maxId
	 * @return
	 * @throws Exception
	 */
	public List<Message> selectMessageByQidDownFresh(int qid,int maxId) throws Exception;
	
}
