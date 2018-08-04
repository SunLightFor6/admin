package com.lamport.admin.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.lamport.admin.po.Address;
import com.lamport.admin.service.FileUploadService;

/**
 * Controller 文件上传请求处理
 * @author Sun
 */
@Controller
public class FileHandler {
	
	@Autowired
	private FileUploadService fileUploadService;
	
	@RequestMapping(value = "/admin/uploadFilesInEditor")
	@ResponseBody
	public String uploadFileToFastDFS( MultipartFile file) throws Exception {
		System.out.println("..........FileHandler..........uploadFileToFastDFS()..........");
		String result = "";
		String filepath = fileUploadService.uploadFile(file);
	
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("code", 0);
		jsonObject.addProperty("msg", "");
		JsonObject object = new JsonObject();
		object.addProperty("src", filepath);
		object.addProperty("title", file.getOriginalFilename());
		jsonObject.add("data", object);
		result = jsonObject.toString();
		
		return result;
	}
}
