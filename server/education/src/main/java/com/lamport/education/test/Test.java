package com.lamport.education.test;

import org.apache.ibatis.session.SqlSession;

import com.lamport.education.mapper.EnterpriseMapper;
import com.lamport.education.mybatis.SqlSessionUtil;
import com.lamport.education.po.Enterprise;
import com.lamport.education.po.Swiper;
import com.lamport.education.util.PageBean;
import com.lamport.education.vo.*;

public class Test {

	public static void main(String[] args) throws Exception {
		
		SqlSession sqlSession = SqlSessionUtil.getSession();
		EnterpriseMapper enter  = sqlSession.getMapper(EnterpriseMapper.class);
		PageBean page = new PageBean(5,0);
		EnterpriseInfoVo enterpriseVo = new EnterpriseInfoVo();
		enterpriseVo.setPage(page);
		enterpriseVo.setQid(1);
		Enterprise enterprise = enter.selectEnterpriseByQid(1);
		for(Swiper s:enterprise.getSwipers()){
			System.out.println(s.getId());
		}
	}

}
