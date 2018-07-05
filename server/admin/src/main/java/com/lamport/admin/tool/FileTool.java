package com.lamport.admin.tool;

import java.io.File;

/**
 * 文件操作工具类，专注于文件的增、删操作
 * @author Lin Zhao, protector of Sherry
 *
 */
public class FileTool {
	/**
	 * 删除单个文件
	 * @param fileName
	 * 	要删除的文件名
	 * @return boolean
	 */
	public boolean deleteFile(String fileName){
		boolean result = true;
		File file = new File(fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                System.out.println("删除单个文件 " + fileName + " 成功！");
            } else {
                System.out.println("删除单个文件 " + fileName + " 失败！");
                result = false;
            }
        } else {
        	//文件不存在，也算作删除成功（因为删文件就是为了保证该文件不存在）
            System.out.println("文件 " + fileName + " 不存在！");
        }
        return result;
	}
	/**
	 * 
	 * @param fileNames
	 * 	要删除的文件名集合（多文件）
	 * @return boolean
	 */
	public boolean deleteFiles(String[] fileNames){
		boolean result = true;
		int count = 0;
		for(String fileName : fileNames){
			File file = new File(fileName);
	        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
	        if (file.exists() && file.isFile()) {
	            if (file.delete()) {
	                System.out.println("删除单个文件 " + fileName + " 成功！");
	                count++;
	            } else {
	                System.out.println("删除单个文件 " + fileName + " 失败！");
	                result = false;
	                break;
	            }
	        } else {
	        	//文件不存在，也算作删除成功（因为删文件就是为了保证该文件不存在）
	            System.out.println("文件 " + fileName + " 不存在！");
	            count++;
	        }
		}
		System.out.println("成功删除" + count + "个文件，失败" + (fileNames.length-count) + "个文件！");
		return result;
	}
}
