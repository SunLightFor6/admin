package com.lamport.admin.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.lamport.admin.fileupload.FileManager;
import com.lamport.admin.service.FileUploadService;

@Service
public class FileUploadServiceBean implements FileUploadService {

	@Override
	public String uploadFile(MultipartFile file) throws Exception {
		return FileManager.upload(file);
	}

}
