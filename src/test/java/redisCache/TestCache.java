package redisCache;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Set;

import redis.clients.jedis.Jedis;

public class TestCache {
	public static void main(String[] args) throws UnsupportedEncodingException {
		// 连接本地的 Redis 服务
		Jedis jedis = new Jedis("localhost");
		// 查看服务是否运行
		System.out.println("Server is running: " + jedis.ping());
		Set<String> set = (Set<String>) jedis.keys("*");
		for(String s:set){
			System.out.println("redis中的key:"+s);
		}
	}

}
