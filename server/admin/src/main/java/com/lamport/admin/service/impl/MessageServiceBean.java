package com.lamport.admin.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
		int saveResult = 1;
		
		saveResult *= messageMapper.saveMessage(message);
		List<String> imgurls = new ArrayList<String>();
		List<File> imgFiles = new ArrayList<File>();
		if(imgs!=null && imgs.length>0){
			File f = new File(path);
			String ppath = f.getParent();
			for(int i=0; i<imgs.length; i++){
				String filename =  System.currentTimeMillis() + imgs[i].getOriginalFilename();
				imgFiles.add(new File(ppath + "/img/messageImg", filename));
				imgurls.add(imgFiles.get(i).getPath());
			}
			for(int i=0; i<imgurls.size(); i++){
				MessageImg messageImg = new MessageImg();
				messageImg.setMid(message.getMid());
				messageImg.setImgurl(imgurls.get(i));
				messageImg.setDeletekey(0);
				saveResult *= messageImgMapper.saveMessageImg(messageImg);
			}
			for(int i=0; i<imgFiles.size(); i++){
				imgs[i].transferTo(imgFiles.get(i));
			}
		}
		saveResult = saveResult>0 ? 1 : 0;

		return saveResult;
	}

	@Override
	public int deleteMessageLogicallyByID(int id) throws Exception {
		int deleteResult = 1;

		deleteResult *= messageImgMapper.deleteMessageImgLogicallyByMID(id);
		deleteResult *= messageLikeMapper.deleteMessageLikeLogicallyByMID(id);				
		deleteResult *= messageReplyMapper.deleteMessageReplyLogicallyByMID(id);
		deleteResult *= messageMapper.deleteMessageLogicallyByID(id);
		deleteResult = deleteResult>0 ? 1: 0;
		
		return deleteResult;
	}

	@Override
	public List<Message> selectMessageByQIDAndPage(QIDAndPage qidAndPage) throws Exception {
		List<Message> messages = null;

		messages = messageMapper.selectMessageByQIDAndPage(qidAndPage);
		Enterprise enterprise = enterpriseMapper.selectEnterpriseByQID(qidAndPage.getQid());
		QIDAndCategory qidAndCategory = new QIDAndCategory();
		qidAndCategory.setQid(qidAndPage.getQid());
		qidAndCategory.setCategory(Const.SwiperCategoryE);
		List<Swiper> swipers = swiperMapper.selectSwiperByQIDAndCategory(qidAndCategory);
		for(Message message : messages){
			List<MessageReply> replies = messageReplyMapper.selectMessageReplyByMID(message.getMid());
			message.setReplies(replies);
			message.setEnterprise(enterprise);
			message.setSwiper(swipers.get(0));
		}
		
		
		return messages;
	}

}
