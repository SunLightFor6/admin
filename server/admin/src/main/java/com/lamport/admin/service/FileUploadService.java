package com.lamport.admin.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Service 文件上传操作，上传文件到fastDFS服务器
 * @author Sun
 */
public interface FileUploadService {
	
	/**
	 * 上传文件到fastDFS文件服务器
	 * @param file	要上传的文件
	 * @return	文件路径
	 * @throws Exception
	 */
	public String uploadFile(MultipartFile file) throws Exception;
}
