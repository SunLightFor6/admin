package com.lamport.admin.mapper;

import java.util.List;

import com.lamport.admin.po.Address;
import com.lamport.admin.vo.QIDAndPage;

/**
 * Mapper, 提供Address(分部)信息的增加、删除、修改、查询功能
 * @author Lin Zhao, protector of Sherry
 *
 */
public interface AddressMapper {
	/**
	 * 创建Address(分部)
	 * @return 1 保存成功 0 保存失败
	 * @throws Exception
	 */
	public int saveAddress(Address address) throws Exception;
	/**
	 * 通过id逻辑删除Address信息
	 * @return 1 删除成功 0 删除失败
	 * @throws Exception
	 */
	public int deleteAddressLogicallyByID(int id) throws Exception;
	/**
	 * 通过qid逻辑删除Address信息
	 * @return 1 删除成功 0 删除失败
	 * @throws Exception
	 */
	public int deleteAddressLogicallyByQID(int qid) throws Exception;
	/**
	 * 通过id修改Address信息
	 * @return 1 更新成功 0 更新失败
	 * @throws Exception
	 */
	public int updateAddressByID(Address address) throws Exception;
	/**
	 * 通过id查询Address信息
	 * @param id
	 * @return Address
	 * @throws Exception
	 */
	public Address selectAddressByID(int id) throws Exception;
	/**
	 * 通过qid和页码查询Address信息
	 * @return List
	 * @throws Exception
	 */
	public List<Address> selectAddressByQIDAndPage(QIDAndPage qidAndPage) throws Exception;
	/**
	 * 通过qid查询Address信息
	 * @param qid
	 * @return List
	 * @throws Exception
	 */
	public List<Address> selectAddressByQID(int qid) throws Exception;
	/**
	 * 通过qid查询Address的总数
	 * @param qid
	 * @return	int 
	 * @throws Exception
	 */
	public int selectCountAddressByQID(int qid) throws Exception;
	
}
