package com.lamport.education.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.lamport.education.mapper.AddressMapper;
import com.lamport.education.po.Address;
import com.lamport.education.po.FreeListen;
import com.lamport.education.po.Lesson;
import com.lamport.education.service.AddressService;
import com.lamport.education.vo.BranchCategoryVo;
import com.lamport.education.vo.EnterpriseCategoryVo;

@Service
public class AddressServiceBean implements AddressService {
	
	@Autowired
	AddressMapper addressMapper;
	
	@Override
	public List<Address> selectAddressByQid(int qid) throws Exception {
		List<Address> address = null;

		address = addressMapper.selectAddressByQid(qid);
		
		return address;
	}
	
	@Override
	public String selectAllCategoryByQid(int qid) throws Exception {
		EnterpriseCategoryVo enterpriseCategoryVo = addressMapper.selectAllCategoryByQid(qid);
		List<BranchCategoryVo> branchCategoryVos = enterpriseCategoryVo.getBranchCategoryVo();
		JsonArray jsonArray = new JsonArray(); //
		for(BranchCategoryVo branchCategoryVo:branchCategoryVos){
			JsonObject branchObj = new JsonObject(); // 添加分支
			branchObj.addProperty("text", branchCategoryVo.getBranch());	
			JsonArray freeOrLessonArray = new JsonArray(); // 试听 付费 数组		
			JsonObject lessonObj = new JsonObject(); // 精品课对象
			lessonObj.addProperty("text", "精品课");
			JsonArray lessonArray = new JsonArray(); // 精品课数组
			JsonObject freeListenObj = new JsonObject(); // 免费课对象
			freeListenObj.addProperty("text", "试听课");
			JsonArray freeListenArray = new JsonArray(); // 免费课数组
			for(FreeListen freeListen:branchCategoryVo.getFreeListenCategorys()){
				JsonObject freeListenCategoryObj = new JsonObject(); // 添加方向
				freeListenCategoryObj.addProperty("text", freeListen.getCategory());
				freeListenArray.add(freeListenCategoryObj);
			}
			for(Lesson lesson:branchCategoryVo.getLessonCategorys()){
				JsonObject lessonCategoryObj = new JsonObject(); // 添加方向
				lessonCategoryObj.addProperty("text", lesson.getCategory());
				lessonArray.add(lessonCategoryObj);
			}
			lessonObj.add("children", lessonArray);
			freeListenObj.add("children", freeListenArray);
			freeOrLessonArray.add(lessonObj);
			freeOrLessonArray.add(freeListenObj);
			branchObj.add("children", freeOrLessonArray);
			jsonArray.add(branchObj);
		}	
		System.out.println(jsonArray.toString());
		return jsonArray.toString();
	}
	


}
