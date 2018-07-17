package com.lamport.education.mybatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.lamport.education.mapper.AddressMapper;
import com.lamport.education.po.Address;

public class AddressMapperTest {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		SqlSession sqlSession = SqlSessionUtil.getSession();
		AddressMapper addressMapper = sqlSession.getMapper(AddressMapper.class);
	    Map map = new HashMap();
		map.put("startRow", 0);
		map.put("pageSize", 2);
//		List<Address>list = addressMapper.selectAddressPageByQid(map);
//		for(Address address:list){
//			System.out.println("id="+address.getId()+"  ,Qid="+address.getQid()+" address= "+address.getAddress()+" tel" +address.getTel()+"long "+ address.getLatitude());
//		} 
	}

}
