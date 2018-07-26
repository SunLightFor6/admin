package com.lamport.education.vo;

import java.util.List;

public class EnterpriseCategoryVo {
	
	private int qid;
	private List<BranchCategoryVo> branchCategoryVos;
	
	public int getQid() {
		return qid;
	}
	
	public void setQid(int qid) {
		this.qid = qid;
	}
	
	public List<BranchCategoryVo> getBranchCategoryVo() {
		return branchCategoryVos;
	}
	
	public void setBranchCategoryVo(List<BranchCategoryVo> branchCategoryVo) {
		this.branchCategoryVos = branchCategoryVo;
	}
	
} 
