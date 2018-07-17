package com.lamport.education.mapper;

import java.util.List;

import com.lamport.education.po.FreeListenBook;
import com.lamport.education.vo.BusinessVo;
import com.lamport.education.vo.FreeListenBookVo;

public interface FreeListenBookMapper {
	
	public void saveFreeListenBook(FreeListenBook freeListenBook) throws Exception;
	public void deleteBookRequestLogicallyByFbid(int fbid) throws Exception; //取消预约，删除预约信息，同时设置deletekey和userdeletekey
	public void deleteFinalFreeListenBookLogicallyByFbid(int fbid) throws Exception; //删除已处理预约，只设置userdeletekey
	public List<FreeListenBookVo> selectFreeListenBookPageByUid(BusinessVo businessVo) throws Exception;
	public List<FreeListenBookVo> selectFreeListenBookPageByUidAndStatus(BusinessVo businessVo) throws Exception;
	
}
