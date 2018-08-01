package com.lamport.education.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lamport.education.mapper.FreeListenMapper;
import com.lamport.education.po.FreeListen;
import com.lamport.education.service.FreeListenService;
import com.lamport.education.vo.FreeListenQueryCondition;

@Service
public class FreeListenServiceBean implements FreeListenService {

	@Autowired
    FreeListenMapper freeListenMapper;
	
	@Override
	public FreeListen selectFreeListenByFid(int fid) throws Exception {
		FreeListen freeListen = null;
		
		freeListen = freeListenMapper.selectFreeListenByFid(fid);
		
		return freeListen;
	}

	@Override
	public List<FreeListen> selectFreeListenByFreeListenQueryCondition(FreeListenQueryCondition freeListenQueryCondition) throws Exception {
		List<FreeListen> freeListens = null;
		
		freeListens = freeListenMapper.selectFreeListenByFreeListenQueryCondition(freeListenQueryCondition);
		
		return  freeListens;
	}

}
