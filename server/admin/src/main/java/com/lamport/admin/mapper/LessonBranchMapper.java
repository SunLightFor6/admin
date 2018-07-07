package com.lamport.admin.mapper;

import org.springframework.stereotype.Controller;

import com.lamport.admin.po.LessonBranch;

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
	 * 通过lid删除LessonBranch
	 * @param lid
	 * @return int 删除的LessonBranch的总条数
	 * @throws Exception
	 */
	public int deleteLessonBranchByLID(int lid) throws Exception;
}
