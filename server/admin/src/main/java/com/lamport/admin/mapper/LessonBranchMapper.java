package com.lamport.admin.mapper;

import java.util.List;

import org.springframework.stereotype.Controller;

import com.lamport.admin.po.Address;
import com.lamport.admin.po.LessonBranch;
import com.lamport.admin.vo.BranchIDAndPage;

/**
 * Controller, 提供LessonBranch(课程-分部映射)的增加、删除功能
 * @author Lin Zhao, protector of Sherry
 *
 */
@Controller
public interface LessonBranchMapper {
	/**
	 * 创建LessonBranch
	 * @param lessonBranch
	 * @return 1 保存成功 0 保存失败
	 * @throws Exception
	 */
	public int saveLessonBranch(LessonBranch lessonBranch) throws Exception;
	/**
	 * 通过lid逻辑删除LessonBranch信息
	 * @return 1 删除成功 0 删除失败
	 * @throws Exception
	 */
	public int deleteLessonBranchLogicallyByLID(int lid) throws Exception;
	/**
	 * 通过branchid逻辑删除LessonBranch信息
	 * @return 1 删除成功 0 删除失败
	 * @throws Exception
	 */
	public int deleteLessonBranchLogicallyByBranchID(int branchid) throws Exception;
	/**
	 * 通过branchid批量逻辑删除LessonBranch信息
	 * @param addresses
	 * @throws Exception
	 */
	public void deleteMultiLessonBranchLogicallyByBranchID(List<Address> addresses) throws Exception;
	/**
	 * 通过branchid查询LessonBranch信息
	 * @param branchid
	 * @return List
	 * @throws Exception
	 */
	public List<LessonBranch> selectLessonBranchByBranchID(int branchid) throws Exception;
	/**
	 * 通过qid查询LessonBranch信息
	 * @param qid
	 * @return List
	 * @throws Exception
	 */
	public List<LessonBranch> selectLessonBranchByQID(int qid) throws Exception;
	/**
	 * 通过branchid和页码查询LessonBranch信息
	 * @param branchIDAndPage
	 * @return List
	 * @throws Exception
	 */
	public List<LessonBranch> selectLessonBranchByBranchIDAndPage(BranchIDAndPage branchIDAndPage) throws Exception;
	/**
	 * 通过branchid查询lid
	 * @param branchid
	 * @return List
	 * @throws Exception
	 */
	public List<Integer> selectLIDByBranchID(int branchid) throws Exception;
	/**
	 * 通过branchid和页码查询lid
	 * @return List
	 * @throws Exception
	 */
	public List<Integer> selectLIDByBranchIDAndPage(BranchIDAndPage branchIDAndPage) throws Exception;
	/**
	 * 通过lid查询branchid
	 * @param lid
	 * @return List
	 * @throws Exception
	 */
	public List<Integer> selectBranchIDByLID(int lid) throws Exception;
	/**
	 * 通过lid查询LessonBranch的条数
	 * @param lid
	 * @return int
	 * @throws Exception
	 */
	public int selectCountLessonBranchByLID(int lid) throws Exception;
	/**
	 * 通过branchid查询LessonBranch的条数
	 * @param branchid
	 * @return int
	 * @throws Exception
	 */
	public int selectCountLessonBranchByBranchID(int branchid) throws Exception;
	/**
	 * 通过qid查询LessonBranch的条数
	 * @param qid
	 * @return int
	 * @throws Exception
	 */
	public int selectCountLessonBranchByQID(int qid) throws Exception;
}
