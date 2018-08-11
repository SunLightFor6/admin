package com.lamport.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.lamport.admin.fileupload.FileManager;
import com.lamport.admin.mapper.AdminMapper;
import com.lamport.admin.mapper.EnterpriseMapper;
import com.lamport.admin.mapper.SwiperMapper;
import com.lamport.admin.mapper.UserMapper;
import com.lamport.admin.po.Admin;
import com.lamport.admin.po.Enterprise;
import com.lamport.admin.po.Swiper;
import com.lamport.admin.service.EnterpriseService;
import com.lamport.admin.tool.Const;
import com.lamport.admin.tool.Creator;
import com.lamport.admin.tool.PageTool;
import com.lamport.admin.vo.QIDAndCategory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;

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
	private UserMapper userMapper;
	@Autowired
	private EnterpriseMapper enterpriseMapper;
	@Autowired
	private SwiperMapper swiperMapper;
//	@Autowired
//	private AddressMapper addressMapper;
//	@Autowired
//	private FreeListenMapper freeListenMapper;
//	@Autowired
//	private LessonMapper lessonMapper;
//	@Autowired
//	private LessonBranchMapper lessonBranchMapper;
//	@Autowired
//	private MessageMapper messageMapper;
//	@Autowired
//	private FreeListenBookMapper freeListenBookMapper;
//	@Autowired
//	private SorderMapper sorderMapper;
//	@Autowired
//	private TeacherMapper teacherMapper;
//	@Autowired
//	private SignMapper signMapper;
//	@Autowired
//	private PointMapper pointMapper;
//	@Autowired
//	private MessageImgMapper messageImgMapper;
//	@Autowired
//	private MessageLikeMapper messageLikeMapper;
//	@Autowired
//	private MessageReplyMapper messageReplyMapper;
	@Autowired
	private JedisPool jedisPool;

	@Override
	public int saveEnterprise(Enterprise enterprise) throws Exception {
		System.out.println("..........EnterpriseServiceBean..........saveEnterprise()..........");

		int saveResult = 1;
		
		//设置enterprise的剩余属性
		enterprise.setMoneytoperpoint(Const.MoneyToPerPoint);
		enterprise.setPerpointtomoney(Const.PerPointToMoney);
		enterprise.setBasicsignpoint(Const.BasicSignPoint);
		enterprise.setDiscountrate(Const.DiscountRate);
		enterprise.setPointkey(Const.PointKey);
		enterprise.setDeletekey(0);
		//保存enterprise信息
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
		
//		List<Sorder> sorders = sorderMapper.selectSorderByQID(id);
//		List<FreeListen> freeListens = freeListenMapper.selectFreeListenByQID(id);
//		List<Address> addresses = addressMapper.selectAddressByQID(id);
//		List<User> users = userMapper.selectUserByQID(id);
//		List<Message> messages = messageMapper.selectMessageByQID(id);
		result *= adminMapper.deleteAdminLogicallyByQID(id);
//		sorderMapper.deleteMultiRefundLogicallyByOID(sorders);
//		sorderMapper.deleteSorderLogicallyByQID(id);
//		lessonMapper.deleteLessonLogicallyByQID(id);
//		freeListenBookMapper.deleteMultiFreeListenBookLogicallyByFID(freeListens);
//		freeListenMapper.deleteFreeListenLogicallyByQID(id);
//		lessonBranchMapper.deleteMultiLessonBranchLogicallyByBranchID(addresses);
//		addressMapper.deleteAddressLogicallyByQID(id);
//		swiperMapper.deleteSwiperLogicallyByQID(id);
//		teacherMapper.deleteTeacherLogicallyByQID(id);
//		signMapper.deleteMultiSignLogicallyByUID(users);
//		pointMapper.deleteMultiPointLogicallyByUID(users);
		userMapper.deleteUserLogicallyByQID(id);
//		messageImgMapper.deleteMultiMessageImgLogicallyByMID(messages);
//		messageLikeMapper.deleteMultiMessageLikeLogicallyByMID(messages);
//		messageReplyMapper.deleteMultiMessageReplyLogicallyByMID(messages);
//		messageMapper.deleteMessageLogicallyByQID(id);
		result *= enterpriseMapper.deleteEnterpriseLogicallyByID(id);
		result = result > 0 ? 1 : 0;
		
		/*------------------------------Redis相关------------------------------*/
		//删除分部后，Lesson和FreeListen已经没有必要保存，将HomePageLesson和HomePageFreeListen信息从Redis中删除
		Jedis jedis = jedisPool.getResource();
		String lessonKey = "homePageLesson" + "-" + id;
		String freelistenKey = "homePageFreeListen" + "-" + id;
		String swiperEKey = "swiperImgurls" + "-" + id + "-" + Const.SwiperCategoryE;
		String swiperTKey = "swiperImgurls" + "-" + id + "-" + Const.SwiperCategoryT;
		//开启事务
		Transaction transaction = jedis.multi();
		//删除
		transaction.del(lessonKey);
		transaction.del(freelistenKey);
		transaction.del(swiperEKey);
		transaction.del(swiperTKey);
		//结束事务
		transaction.exec();
		jedis.close();
		/*------------------------------Redis相关------------------------------*/
		
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
//		List<File> imgFiles = new ArrayList<File>();
		if(imgs!=null && imgs.length>0){
			System.out.println("Now imgs is not null");
			for(int i=0; i<imgs.length; i++){
//				String filename =  System.currentTimeMillis() + imgs[i].getOriginalFilename();
//				imgFiles.add(new File(path + Const.ImgSwiperPath, filename));
//				imgurls.add(Const.ImgSwiperPath + "/" + filename);
				imgurls.add(FileManager.upload(imgs[i]));
			}
			swiperMapper.deleteSwiperLogicallyByQIDAndCategory(qidAndCategory);	
			for(int i=0; i<imgurls.size(); i++){
				Swiper swiper = new Swiper();
				swiper.setCategory(qidAndCategory.getCategory());
				swiper.setQid(qidAndCategory.getQid());
				swiper.setImgurl(imgurls.get(i));
				swiper.setDeletekey(0);
				
				System.out.println("imgurl = " + swiper.getImgurl());
				
				updateResult *= swiperMapper.saveSwiper(swiper);
			}
		}
		
		String videoPath = null;
//		File videoFile = null;
		if(video != null){
			videoPath = FileManager.upload(video);
		}
//		String oldVideo = path + enterpriseMapper.selectEnterpriseVideopathByQID(enterprise.getQid());
		enterprise.setVideopath(videoPath);
		System.out.println(enterprise.getVideopath());
		updateResult = enterpriseMapper.updateEnterpriseByID(enterprise);		
		updateResult = (updateResult>0) ? 1 : 0;

		/*------------------------------Redis相关------------------------------*/
		//修改企业后，Swiper已经发生了变化，将Swiper信息从Redis中删除
		Jedis jedis = jedisPool.getResource();
		String swiperEKey = "swiperImgurls" + "-" + enterprise.getQid() + "-" + Const.SwiperCategoryE;
		//开启事务
		Transaction transaction = jedis.multi();
		//删除
		transaction.del(swiperEKey);
		//结束事务
		transaction.exec();
		jedis.close();
		/*------------------------------Redis相关------------------------------*/
		
		return updateResult;
	}
	
	@Override
	public void updateEnterpriseConfigByID(Enterprise enterprise) throws Exception{
		System.out.println("..........EnterpriseServiceBean..........updateEnterpriseConfigByID()..........");
		
		enterpriseMapper.updateEnterpriseConfigByID(enterprise);
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
