package com.lamport.education.mapper;

import com.lamport.education.vo.EnterpriseCategoryVo;

public interface BranchMapper {
	public EnterpriseCategoryVo selectAllCategoryByQid(int qid);
}
