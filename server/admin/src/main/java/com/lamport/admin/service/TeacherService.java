package com.lamport.admin.service;

import java.util.List;

import com.lamport.admin.po.Teacher;
import com.lamport.admin.vo.QIDAndPage;

/**
 * Service, 提供Teacher信息的增加、删除、修改、查询功能
 * @author Lin Zhao， protector of Sherry
 *
 */
public interface TeacherService {
	/**
	 * 创建Teacher信息
	 * @param teacher
	 * @return 1 保存成功 0 保存失败
	 * @throws Exception
	 */
	public int saveTeacher(Teacher teacher) throws Exception;
	/**
	 * 通过id逻辑删除Teacher信息
	 * @param id
	 * @return 1 删除成功 0 删除失败
	 * @throws Exception
	 */
	public int deleteTeacherLogicallyByID(int id) throws Exception;
	/**
	 * 通过id修改Teacher信息
	 * @param teacher
	 * @return 1 更新成功 0 更新失败
	 * @throws Exception
	 */
	public int updateTeacherByID(Teacher teacher) throws Exception; 
	/**
	 * 通过qid和页码查询Teacher信息
	 * @return List
	 * @throws Exception
	 */
	public List<Teacher> selectTeacherByQIDAndPage(QIDAndPage qidAndPage) throws Exception;
	/**
	 * 根据qid查询Teacher的总数
	 * @return int
	 * @throws Exception
	 */
	public int selectCountTeacherByQID(int qid) throws Exception;
}
