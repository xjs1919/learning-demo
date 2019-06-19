package com.github.xjs.bloomfilter.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.rebloom.client.Client;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisConfig {
	@Autowired
	RedisProperties redisProperties;
	@Bean
	public JedisPool jedisPool() {
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxIdle(redisProperties.getPoolMaxIdle());
		poolConfig.setMaxTotal(redisProperties.getPoolMaxTotal());
		poolConfig.setMaxWaitMillis(redisProperties.getPoolMaxWait() * 1000);
		JedisPool jp = new JedisPool(poolConfig, redisProperties.getHost(), redisProperties.getPort(),
				redisProperties.getTimeout()*1000, redisProperties.getPassword(), 0);
		return jp;
	}
	@Bean
	public Client bloomFilter(JedisPool jp) {
		return new Client(jp);
	}
}
