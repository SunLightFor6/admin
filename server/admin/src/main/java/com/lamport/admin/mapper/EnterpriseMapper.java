package com.lamport.admin.mapper;

import java.util.List;

import com.lamport.admin.po.Enterprise;
import com.lamport.admin.tool.PageTool;

/**
 * Mapper, 提供Enterprise信息的增加、删除、更新、查询功能
 * @author Lin Zhao， protector of Sherry
 *
 */
public interface EnterpriseMapper {
	/**
	 * 创建企业
	 * @return 1 保存成功 0 保存失败 
	 * @throws Exception
	 */
	public int saveEnterprise(Enterprise enterprise) throws Exception;
	/**
	 * 通过企业id删除企业
	 * @return 1 保存成功 0 保存失败 
	 * @throws Exception
	 */
	public int deleteEnterpriseByID(int qid) throws Exception;
	/**
	 * 通过页码查询企业信息
	 * @return List<Enterprise>
	 * @throws Exception
	 */
	public List<Enterprise> selectEnterpriseByPage(PageTool pageTool) throws Exception;
	/**
	 * 通过qid查询企业信息
	 * @return Enterprise
	 * @throws Exception
	 */
	public Enterprise selectEnterpriseByQID(int qid) throws Exception;
	/**
	 * 通过qid更新企业信息
	 * @return 1 更新成功 0 更新失败
	 * @throws Exception
	 */
	public int updateEnterpriseByQID(Enterprise enterprise) throws Exception;
	/**
	 * 通过qid查询企业视频存放路径
	 * @param qid
	 * @return String
	 * @throws Exception
	 */
	public String selectEnterpriseVideoByQID(String qid) throws Exception;
}
