package com.lamport.admin.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.lamport.admin.po.Enterprise;
import com.lamport.admin.tool.PageTool;

/**
 * Service, 提供Enterprise信息的增加、删除、更新、查询功能
 * @author Lin Zhao， protector of Sherry
 *
 */
public interface EnterpriseService {
	/**
	 * 创建企业
	 * @return 1 保存成功 0 保存失败 
	 * @throws Exception
	 */
	public int saveEnterprise(Enterprise enterprise) throws Exception;
	/**
	 * 通过id逻辑删除企业信息
	 * @return 1 保存成功 0 保存失败 
	 * @throws Exception
	 */
	public int deleteEnterpriseLogicallyByID(int id) throws Exception;
	/**
	 * 通过id更新企业信息
	 * @return 1 更新成功 0 更新失败
	 * @throws Exception
	 */
	public int updateEnterpriseByID(Enterprise enterprise, MultipartFile[] imgs, MultipartFile video) throws Exception;
	/**
	 * 通过qid查询企业信息
	 * @return Enterprise
	 * @throws Exception
	 */
	public Enterprise selectEnterpriseByQID(int qid) throws Exception;
	/**
	 * 通过页码查询企业信息
	 * @return List<Enterprise>
	 * @throws Exception
	 */
	public List<Enterprise> selectEnterpriseByPage(PageTool pageTool) throws Exception;
}
