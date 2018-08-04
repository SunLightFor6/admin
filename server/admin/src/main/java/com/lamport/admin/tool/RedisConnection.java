package com.lamport.admin.tool;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 工具类，专注于对Redis连接的操作
 * @author Lin Zhao, protector of Sherry
 *
 */
public class RedisConnection {
	//要注意在Maven中配置jedis-2.9.0.jar的Maven依赖
	public static Jedis getJedis(){
		JedisPoolConfig config = new JedisPoolConfig();
		
		//只能通过构造方法传值
		JedisPool jedisPool = new JedisPool(config, "172.24.203.4", 6379, 60000, "123");
		return jedisPool.getResource();
	}
}
