package com.lamport.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.lamport.admin.mapper.AddressMapper;
import com.lamport.admin.mapper.AdminMapper;
import com.lamport.admin.mapper.EnterpriseMapper;
import com.lamport.admin.mapper.FreeListenMapper;
import com.lamport.admin.mapper.LessonMapper;
import com.lamport.admin.mapper.MessageMapper;
import com.lamport.admin.mapper.SorderMapper;
import com.lamport.admin.mapper.SwiperMapper;
import com.lamport.admin.mapper.TeacherMapper;
import com.lamport.admin.mapper.UserMapper;
import com.lamport.admin.po.Admin;
import com.lamport.admin.po.Enterprise;
import com.lamport.admin.service.EnterpriseService;
import com.lamport.admin.tool.Const;
import com.lamport.admin.tool.Creator;
import com.lamport.admin.tool.PageTool;

/**
 * implements EnterpriseService
 * @author Lin Zhao, protector of Sherry
 *
 */
@Service
public class EnterpriseServiceBean implements EnterpriseService {
	
	@Autowired
	private AdminMapper adminMapper;
	@Autowired
	private AddressMapper addressMapper;
	@Autowired
	private EnterpriseMapper enterpriseMapper;
	@Autowired
	private FreeListenMapper freeListenMapper;
	@Autowired
	private LessonMapper lessonMapper;
	@Autowired
	private MessageMapper messageMapper;
	@Autowired
	private SorderMapper sorderMapper;
	@Autowired
	private SwiperMapper swiperMapper;
	@Autowired
	private TeacherMapper teacherMapper;
	@Autowired
	private UserMapper userMapper;

	@Override
	public int saveEnterprise(Enterprise enterprise) throws Exception {
		int result = 0;
		
		enterprise.setDeletekey(0);
		int saveEnterpriseResult = enterpriseMapper.saveEnterprise(enterprise);
		Admin admin = new Admin();
		admin.setQid(enterprise.getQid());
		admin.setAdminname(Creator.createAdminName());
		admin.setPassword(admin.getAdminname());
		admin.setJurisdiction(Const.AdminJurisdiction);
		admin.setDeletekey(0);
		int saveAminResult = adminMapper.saveAdmin(admin);
		if(saveEnterpriseResult==1 && saveAminResult==1){
			result = 1;
		}
		
		return result;
	}

	@Override
	public int deleteEnterpriseLogicallyByID(int id) throws Exception {
		int result = 1;
		
		result *= adminMapper.deleteAdminLogicallyByQID(id);
		sorderMapper.deleteSorderLogicallyByQID(id);
		lessonMapper.deleteLessonLogicallyByQID(id);
		freeListenMapper.deleteFreeListenLogicallyByQID(id);
		addressMapper.deleteAddressLogicallyByQID(id);
		swiperMapper.deleteSwiperLogicallyByQID(id);
		teacherMapper.deleteTeacherLogicallyByQID(id);
		userMapper.deleteUserLogicallyByQID(id);
		messageMapper.deleteMessageLogicallyByQID(id);
		result *= enterpriseMapper.deleteEnterpriseLogicallyByID(id);
		
		return result;
	}

	@Override
	public int updateEnterpriseByID(Enterprise enterprise, MultipartFile[] imgs, MultipartFile video) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Enterprise selectEnterpriseByQID(int qid) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Enterprise> selectEnterpriseByPage(PageTool pageTool) throws Exception {
		List<Enterprise> enterprises = null;
		
		int count = enterpriseMapper.selectCountEnterprise();
		pageTool.setCount(count);
		enterprises = enterpriseMapper.selectEnterpriseByPage(pageTool);
		
		return enterprises;
	}

}
