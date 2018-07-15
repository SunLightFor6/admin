package com.lamport.admin.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.lamport.admin.mapper.SwiperMapper;
import com.lamport.admin.po.Swiper;
import com.lamport.admin.service.SwiperService;
import com.lamport.admin.tool.Const;
import com.lamport.admin.vo.QIDAndCategory;

/**
 * implements SwiperService
 * @author Lin Zhao, protector of Sherry
 *
 */
@Service
public class SwiperServiceBean implements SwiperService {
	
	@Autowired
	private SwiperMapper swiperMapper;

	@Override
	public int updateMultipleSwipersByQIDAndCategory(QIDAndCategory qidAndCategory, MultipartFile[] imgs, String path) throws Exception {
		int updateResult = 1;
		// TODO Auto-generated method stub
		List<String> imgurls = new ArrayList<String>();
		List<File> imgFiles = new ArrayList<File>();
		if(imgs!=null && imgs.length>0){
			for(int i=0; i<imgs.length; i++){
				String filename =  System.currentTimeMillis() + imgs[i].getOriginalFilename();
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
			for(int i=0; i<imgFiles.size(); i++){
				imgs[i].transferTo(imgFiles.get(i));
			}
		}
		updateResult = (updateResult>0) ? 1 : 0;
			
		return updateResult;
	}

	@Override
	public List<Swiper> selectSwiperByQIDAndCategory(QIDAndCategory qidAndCategory) throws Exception {
		List<Swiper> swipers = null;
		
		swipers = swiperMapper.selectSwiperByQIDAndCategory(qidAndCategory);
		
		return swipers;
	}
	
	@Override
	public List<String> selectSwiperImgurlByQIDAndCategory(QIDAndCategory qidAndCategory) throws Exception {
		List<String> swiperImgurls = null;
		
		swiperImgurls = swiperMapper.selectSwiperImgurlByQIDAndCategory(qidAndCategory);
		
		return swiperImgurls;
	}

}
