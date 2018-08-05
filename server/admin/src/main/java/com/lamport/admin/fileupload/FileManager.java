package com.lamport.admin.fileupload;

import java.io.File;
import java.io.IOException;

import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

/**
 * <strong>类概要： FastDFS Java客户端工具类</strong> <br>
 * <strong>创建时间： 2016-9-26 上午10:26:48</strong> <br>
 * 
 * @Project springmvc-main(com.wl.bean)
 * @author Wang Liang
 * @version 1.0.0
 */
public class FileManager implements FileManagerConfig {

	private static final long serialVersionUID = 1L;
	private static TrackerClient trackerClient;
	private static TrackerServer trackerServer;
	private static StorageServer storageServer;
	private static StorageClient storageClient;

	static {
		try {
			// String classPath = new
			// File(FileManager.class.getResource("/").getFile()).getCanonicalPath();

			// String fdfsClientConfigFilePath = classPath + File.separator
			// getCanonicalPath+ CLIENT_CONFIG_FILE;
			String fdfsClientConfigFilePath = CLIENT_CONFIG_FILE;
			ClientGlobal.init(fdfsClientConfigFilePath);

			trackerClient = new TrackerClient();
			trackerServer = trackerClient.getConnection();

			storageClient = new StorageClient(trackerServer, storageServer);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * <strong>方法概要： 文件上传</strong> <br>
	 * <strong>创建时间： 2016-9-26 上午10:26:11</strong> <br>
	 * 
	 * @param FastDFSFile
	 *            file
	 * @return fileAbsolutePath
	 * @author Wang Liang
	 */
	public static String upload(FastDFSFile file, NameValuePair[] valuePairs) {
		String[] uploadResults = null;
		try {
			uploadResults = storageClient.upload_file(file.getContent(), file.getExt(), valuePairs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String groupName = uploadResults[0];
		String remoteFileName = uploadResults[1];

		String fileAbsolutePath = PROTOCOL + TRACKER_NGNIX_ADDR
		// + trackerServer.getInetSocketAddress().getHostName()
		// + SEPARATOR + TRACKER_NGNIX_PORT
				+ SEPARATOR + groupName + SEPARATOR + remoteFileName;
		return fileAbsolutePath;
	}

	/**
	 * 文件上传的接口
	 * 
	 * @param file
	 *            传入单个MultipartFile文件对象
	 * @return
	 * @throws IOException
	 */
	public static String upload(MultipartFile file) throws IOException {
		byte[] bytes = file.getBytes();
		int length = bytes.length;
		String filename = file.getOriginalFilename();
		String ext = "";
		if (filename.lastIndexOf('.') != -1) {
			ext = filename.substring(filename.lastIndexOf('.'), filename.length());
		}
		FastDFSFile fileDFS = new FastDFSFile(bytes, ext);
		NameValuePair[] meta_list = new NameValuePair[4];
		meta_list[0] = new NameValuePair("fileName", filename);
		meta_list[1] = new NameValuePair("fileLength", String.valueOf(length));
		meta_list[2] = new NameValuePair("fileExt", ext);
		meta_list[3] = new NameValuePair("fileAuthor", "Lamport");
		return FileManager.upload(fileDFS, meta_list);
	}
}
