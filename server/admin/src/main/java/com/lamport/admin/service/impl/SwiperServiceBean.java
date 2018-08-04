package com.lamport.admin.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lamport.admin.fileupload.FileManager;
import com.lamport.admin.mapper.SwiperMapper;
import com.lamport.admin.po.Swiper;
import com.lamport.admin.service.SwiperService;
import com.lamport.admin.tool.Const;
import com.lamport.admin.vo.QIDAndCategory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * implements SwiperService
 * @author Lin Zhao, protector of Sherry
 *
 */
@Service
public class SwiperServiceBean implements SwiperService {
	
	@Autowired
	private SwiperMapper swiperMapper;
	@Autowired
	private JedisPool jedisPool;

	@Override
	public int updateMultipleSwipersByQIDAndCategory(QIDAndCategory qidAndCategory, MultipartFile[] imgs, String path) throws Exception {
		System.out.println("--- SwiperServiceBean --- updateMultipleSwipersByQIDAndCategory() ---imgs.size = " + imgs.length + " path = " + path);
		int updateResult = 1;

		List<String> imgurls = new ArrayList<String>();
//		List<File> imgFiles = new ArrayList<File>();
		if(imgs!=null && imgs.length>0){
			for(int i=0; i<imgs.length; i++){
//				Creator creator = new Creator();
//				String filename = creator.createFilename();
//				String filename =  System.currentTimeMillis() + imgs[i].getOriginalFilename();
//				imgFiles.add(new File(path + Const.ImgSwiperPath, filename));
//				imgurls.add(Const.ImgSwiperPath + "/" + filename);
//				System.out.println(Const.ImgSwiperPath + "/" + filename);
				imgurls.add(FileManager.upload(imgs[i]));
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
//			for(int i=0; i<imgFiles.size(); i++){
//				imgs[i].transferTo(imgFiles.get(i));
//			}
		}
		updateResult = (updateResult>0) ? 1 : 0;
		System.out.println("--- SwiperServiceBean --- updateMultipleSwipersByQIDAndCategory() --- updateResult = " + updateResult);
		return updateResult;
	}

	@Override
	public List<Swiper> selectSwiperByQIDAndCategory(QIDAndCategory qidAndCategory) throws Exception {
		System.out.println("..........SwiperServiceBean..........selectSwiperByQIDAndCategory()..........");

		List<Swiper> swipers = null;
		
		swipers = swiperMapper.selectSwiperByQIDAndCategory(qidAndCategory);
		
		return swipers;
	}
	
	@Override
	public List<String> selectSwiperImgurlByQIDAndCategory(QIDAndCategory qidAndCategory) throws Exception {
		System.out.println("..........SwiperServiceBean..........selectSwiperImgurlByQIDAndCategory()..........");

		List<String> swiperImgurls = null;
		
		/*------------------------------Redis相关------------------------------*/
		Jedis jedis = jedisPool.getResource();
		Gson gson = new Gson();
		String imgurls = jedis.get("swiperImgurls" + "-" + qidAndCategory.getQid() + "-" + qidAndCategory.getCategory());
		if(imgurls == null){
			//redis没有，从mySQL中查询
			swiperImgurls = swiperMapper.selectSwiperImgurlByQIDAndCategory(qidAndCategory);
			//将取出来的对象打包成json字符串
			String jsonString = gson.toJson(swiperImgurls);
			System.out.println(jsonString);
			//将json字符串放入redis中
			jedis.set("swiperImgurls" + "-" + qidAndCategory.getQid() + "-" + qidAndCategory.getCategory(), jsonString);
		}else{
			swiperImgurls = gson.fromJson(imgurls, new TypeToken<List<String>>(){}.getType());
		}
		/*------------------------------Redis相关------------------------------*/
		
		
		swiperImgurls = swiperMapper.selectSwiperImgurlByQIDAndCategory(qidAndCategory);
		
		return swiperImgurls;
	}

}
