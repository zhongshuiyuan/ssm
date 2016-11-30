package com.cn.hnust.cache.redis;

import java.lang.reflect.Method;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
@EnableCaching//动用spring对注解驱动缓存的支持
public class RedisCacheConfig extends CachingConfigurerSupport{

	@Bean
	public JedisConnectionFactory redisConnectionFactory() {
		JedisConnectionFactory redisConnectionFactory = new JedisConnectionFactory();

		// Defaults
		redisConnectionFactory.setHostName("127.0.0.1");
		redisConnectionFactory.setPort(6379);
		return redisConnectionFactory;
	}

	@Bean
	public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory cf) {
		RedisTemplate<String, String> redisTemplate = new RedisTemplate<String, String>();
		redisTemplate.setConnectionFactory(cf);
		return redisTemplate;
	}

	@Bean
	public CacheManager cacheManager(RedisTemplate redisTemplate) {
		RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);

		// Number of seconds before expiration. Defaults to unlimited (0)
		cacheManager.setDefaultExpiration(3000); // Sets the default expire time
													// (in seconds)
		return cacheManager;
	}
	
	//自定义缓存数据 key 生成策略
//	@Bean  
//	public KeyGenerator customKeyGenerator() {  
//	    return new KeyGenerator() {  
//	        public Object generate(Object o, Method method, Object... objects) {  
//	            StringBuilder sb = new StringBuilder();  
//	            sb.append(o.getClass().getName());  
//	            sb.append(method.getName());  
//	            for (Object obj : objects) {  
//	                sb.append(obj.toString());  
//	            }  
//	            return sb.toString();  
//	        }  
//	    };
//	} 

}
