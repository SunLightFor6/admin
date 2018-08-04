package com.lamport.admin.fileupload;

import org.csource.common.NameValuePair;

import java.io.FileInputStream;
import java.io.IOException;

public class Test {
	public static void main(String[] args) throws IOException {
		// 获取文件后缀名
		String ext = "jpg";
		FileInputStream fileReader = new FileInputStream("d:/dog.jpg");
		byte[] bytes = new byte[10000000];// 10M
		int length = fileReader.read(bytes);
		FastDFSFile file = new FastDFSFile(bytes, ext);
		NameValuePair[] meta_list = new NameValuePair[4];
		meta_list[0] = new NameValuePair("fileName", "abc");
		meta_list[1] = new NameValuePair("fileLength", String.valueOf(length));
		meta_list[2] = new NameValuePair("fileExt", ext);
		meta_list[3] = new NameValuePair("fileAuthor", "WangLiang");
		String filePath = FileManager.upload(file, meta_list);
		System.out.println(filePath);
	}
}
