package com.lamport.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.lamport.admin.fileupload.FileManager;
import com.lamport.admin.mapper.EnterpriseMapper;
import com.lamport.admin.mapper.MessageImgMapper;
import com.lamport.admin.mapper.MessageLikeMapper;
import com.lamport.admin.mapper.MessageMapper;
import com.lamport.admin.mapper.MessageReplyMapper;
import com.lamport.admin.mapper.SwiperMapper;
import com.lamport.admin.po.Enterprise;
import com.lamport.admin.po.Message;
import com.lamport.admin.po.MessageImg;
import com.lamport.admin.po.MessageReply;
import com.lamport.admin.po.Swiper;
import com.lamport.admin.service.MessageService;
import com.lamport.admin.tool.Const;
import com.lamport.admin.vo.QIDAndCategory;
import com.lamport.admin.vo.QIDAndPage;

/**
 * implements MessageService
 * @author Lin Zhao, protector of Sherry
 *
 */
@Service
public class MessageServiceBean implements MessageService {

	@Autowired
	private MessageMapper messageMapper;
	@Autowired
	private MessageImgMapper messageImgMapper;
	@Autowired
	private MessageLikeMapper messageLikeMapper;
	@Autowired
	private MessageReplyMapper messageReplyMapper;
	@Autowired
	private EnterpriseMapper enterpriseMapper;
	@Autowired
	private SwiperMapper swiperMapper;
	
	@Override
	public int saveMessage(Message message, MultipartFile[] imgs, String path) throws Exception {
		System.out.println("..........MessageServiceBean..........saveMessage()..........");

		int saveResult = 1;
		
		saveResult *= messageMapper.saveMessage(message);
		List<String> imgurls = new ArrayList<String>();
//		List<File> imgFiles = new ArrayList<File>();
		if(imgs!=null && imgs.length>0){
			for(int i=0; i<imgs.length; i++){
//				Creator creator = new Creator();
//				String filename = creator.createFilename();
//				String filename =  System.currentTimeMillis() + imgs[i].getOriginalFilename();
//				imgFiles.add(new File(path + Const.ImgMessagePath, filename));
//				imgurls.add( Const.ImgMessagePath + "/" + filename);
				imgurls.add(FileManager.upload(imgs[i]));
			}
			for(int i=0; i<imgurls.size(); i++){
				MessageImg messageImg = new MessageImg();
				messageImg.setMid(message.getMid());
				messageImg.setImgurl(imgurls.get(i));
				messageImg.setDeletekey(0);
				saveResult *= messageImgMapper.saveMessageImg(messageImg);
			}
//			for(int i=0; i<imgFiles.size(); i++){
//				imgs[i].transferTo(imgFiles.get(i));
//			}
		}
		saveResult = saveResult>0 ? 1 : 0;

		return saveResult;
	}

	@Override
	public int deleteMessageLogicallyByID(int id) throws Exception {
		System.out.println("..........MessageServiceBean..........deleteMessageLogicallyByID()..........");

		int deleteResult = 1;

		messageImgMapper.deleteMessageImgLogicallyByMID(id);
		messageLikeMapper.deleteMessageLikeLogicallyByMID(id);				
		messageReplyMapper.deleteMessageReplyLogicallyByMID(id);
		deleteResult *= messageMapper.deleteMessageLogicallyByID(id);
		deleteResult = deleteResult>0 ? 1: 0;
		
		return deleteResult;
	}

	@Override
	public List<Message> selectMessageByQIDAndPage(QIDAndPage qidAndPage) throws Exception {
		System.out.println("..........MessageServiceBean..........selectMessageByQIDAndPage()..........");

		List<Message> messages = null;
		int count = messageMapper.selectCountMessageByQID(qidAndPage.getQid());
		qidAndPage.getPageTool().setCount(count);
		messages = messageMapper.selectMessageByQIDAndPage(qidAndPage);
		Enterprise enterprise = enterpriseMapper.selectEnterpriseByQID(qidAndPage.getQid());
		QIDAndCategory qidAndCategory = new QIDAndCategory();
		qidAndCategory.setQid(qidAndPage.getQid());
		qidAndCategory.setCategory(Const.SwiperCategoryE);
		List<Swiper> swipers = swiperMapper.selectSwiperByQIDAndCategory(qidAndCategory);
		for(Message message : messages){
			List<MessageImg> imgs = messageImgMapper.selectMessageImgByMID(message.getMid());
			List<MessageReply> replies = messageReplyMapper.selectMessageReplyByMID(message.getMid());
			message.setImgs(imgs);
			message.setReplies(replies);
			message.setEnterprise(enterprise);
			message.setSwiper(swipers.get(0));
		}
		
		
		return messages;
	}

}
