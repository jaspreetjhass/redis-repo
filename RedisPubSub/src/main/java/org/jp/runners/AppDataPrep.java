package org.jp.runners;

import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class AppDataPrep implements CommandLineRunner {

	@Autowired
	private RedisTemplate<Object, Object> redisTemplate;

	@Override
	public void run(String... args) throws Exception {
		String message = "Hello ";
		IntStream.range(1, 50).forEach(num -> redisTemplate.convertAndSend("topic123", message.concat(String.valueOf(num))));
	}

}
