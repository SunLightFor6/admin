package com.lamport.admin.mapper;

import com.lamport.admin.po.MessageImg;

/**
 * Mapper, 提供MessageImg信息的增加、删除功能
 * @author Lin Zhao， protector of Sherry
 *
 */
public interface MessageImgMapper {
	/**
	 * 创建MessageImg信息
	 * @param messageImg
	 * @return 1 保存成功 0 保存失败
	 * @throws Exception
	 */
	public int saveMessageImg(MessageImg messageImg) throws Exception;	
	/**
	 * 通过mid逻辑删除MessageImg信息
	 * @param mid
	 * @return 1 删除成功 0 删除失败
	 * @throws Exception
	 */
	public int deleteMessageImgLogicallyByMID(int mid) throws Exception;
}
