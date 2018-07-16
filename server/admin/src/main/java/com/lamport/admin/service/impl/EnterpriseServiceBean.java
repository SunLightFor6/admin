package com.lamport.admin.service.impl;

import java.io.File;
import java.util.ArrayList;
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
import com.lamport.admin.po.Swiper;
import com.lamport.admin.service.EnterpriseService;
import com.lamport.admin.tool.Const;
import com.lamport.admin.tool.Creator;
import com.lamport.admin.tool.FileTool;
import com.lamport.admin.tool.PageTool;
import com.lamport.admin.vo.QIDAndCategory;

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
		System.out.println("..........EnterpriseServiceBean..........saveEnterprise()..........");

		int saveResult = 1;
		
		enterprise.setDeletekey(0);
		saveResult *= enterpriseMapper.saveEnterprise(enterprise);
		Admin admin = new Admin();
		admin.setQid(enterprise.getQid());
		admin.setAdminname(Creator.createAdminName());
		admin.setPassword(admin.getAdminname());
		admin.setJurisdiction(Const.AdminJurisdiction);
		admin.setDeletekey(0);
		saveResult *= adminMapper.saveAdmin(admin);
		enterprise.setAdminister(admin);
		
		return saveResult;
	}

	@Override
	public int deleteEnterpriseLogicallyByID(int id) throws Exception {
		System.out.println("..........EnterpriseServiceBean..........deleteEnterpriseLogicallyByID()..........");

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
		result = result > 0 ? 1 : 0;
		
		return result;
	}

	@Override
	public int updateEnterpriseByID(Enterprise enterprise, MultipartFile[] imgs, MultipartFile video, String path) throws Exception {
		System.out.println("..........EnterpriseServiceBean..........updateEnterpriseByID()..........");
		int updateResult = 1;
		
		QIDAndCategory qidAndCategory = new QIDAndCategory();
		qidAndCategory.setQid(enterprise.getQid());
		qidAndCategory.setCategory(Const.SwiperCategoryE);
		List<String> imgurls = new ArrayList<String>();
		List<File> imgFiles = new ArrayList<File>();
		if(imgs!=null && imgs.length>0){
			for(int i=0; i<imgs.length; i++){
				Creator creator = new Creator();
				String filename = creator.createFilename();
//				String filename =  System.currentTimeMillis() + imgs[i].getOriginalFilename();
				imgFiles.add(new File(path + Const.ImgSwiperPath, filename));
				imgurls.add(Const.ImgSwiperPath + "/" + filename);
			}
			updateResult *= swiperMapper.deleteSwiperLogicallyByQIDAndCategory(qidAndCategory);	
			for(int i=0; i<imgurls.size(); i++){
				Swiper swiper = new Swiper();
				swiper.setCategory(qidAndCategory.getCategory());
				swiper.setQid(qidAndCategory.getQid());
				swiper.setImgurl(imgurls.get(i));
				swiper.setDeletekey(0);
				updateResult *= swiperMapper.saveSwiper(swiper);
			}
		}
		
		String videoPath = null;
		File videoFile = null;
		if(video != null){
			Creator creator = new Creator();
			String filename = creator.createFilename();
//			String filename = System.currentTimeMillis() + video.getOriginalFilename();
			videoFile = new File(path + Const.VideoPath, filename);
			videoPath = Const.VideoPath + "/" + filename;
		}
		String oldVideo = path + enterpriseMapper.selectEnterpriseVideopathByQID(enterprise.getQid());
		enterprise.setVideopath(videoPath);
		updateResult = enterpriseMapper.updateEnterpriseByID(enterprise);
		if(imgurls != null){
			for(int i=0; i<imgFiles.size(); i++){
				imgs[i].transferTo(imgFiles.get(i));
			}
		}
		if(videoPath != null){
			video.transferTo(videoFile);//保存文件
			FileTool.deleteFile(oldVideo);//删除文件？？？？？？
		}		
		updateResult = (updateResult>0) ? 1 : 0;

		return updateResult;
	}

	@Override
	public Enterprise selectEnterpriseByQID(int qid) throws Exception {
		System.out.println("..........EnterpriseServiceBean..........selectEnterpriseByQID()..........");

		Enterprise enterprise = null;

		QIDAndCategory qidAndCategory = new QIDAndCategory();
		qidAndCategory.setCategory(Const.SwiperCategoryE);
		qidAndCategory.setQid(qid);
		enterprise = enterpriseMapper.selectEnterpriseByQID(qid);
		List<Swiper> swipers = swiperMapper.selectSwiperByQIDAndCategory(qidAndCategory);
		enterprise.setSwipers(swipers);
		
		return enterprise;
	}

	@Override
	public List<Enterprise> selectEnterpriseByPage(PageTool pageTool) throws Exception {
		System.out.println("..........EnterpriseServiceBean..........selectEnterpriseByPage()..........");

		List<Enterprise> enterprises = null;
		
		int count = enterpriseMapper.selectCountEnterprise();
		pageTool.setCount(count);
		enterprises = enterpriseMapper.selectEnterpriseByPage(pageTool);
		
		return enterprises;
	}

}
