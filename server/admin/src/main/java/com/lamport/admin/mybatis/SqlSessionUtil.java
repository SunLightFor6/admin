package com.lamport.admin.mybatis;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlSessionUtil {
	private static SqlSessionFactory ssf = null;
	
	
	/*
	 * 静态代码块
	 * 1.在类被加载时应用（在构造器之前）
	 * 2.静态块中只允许使用静态属性
	 */
	static{
		//加载Mybatis的主配置文件
		InputStream is = null;
		try{
			is = Resources.getResourceAsStream("SqlMapConfig.xml");
		}catch(IOException e){
			e.printStackTrace();
		}
		//创建生产session的工厂类	session不是作用域 -- 相当于connection
		SqlSessionFactoryBuilder sfb = new SqlSessionFactoryBuilder();
		ssf = sfb.build(is);
	}
	
	public static SqlSession getSession(){
		return ssf.openSession();
	}
}
