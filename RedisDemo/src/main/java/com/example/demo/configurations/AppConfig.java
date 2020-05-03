package com.example.demo.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.example.demo.constants.AppConstant;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class AppConfig {

	@Bean
	public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
		RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(redisConnectionFactory);
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setValueSerializer(new StringRedisSerializer());
		//redisTemplate.setHashKeySerializer(new StringRedisSerializer());
		//redisTemplate.setHashValueSerializer(new StringRedisSerializer());
		redisTemplate.setEnableTransactionSupport(Boolean.TRUE);
		return redisTemplate;
	}

	@Bean
	public ValueOperations<String, String> valueOperations(RedisTemplate<String, String> redisTemplate) {
		return redisTemplate.opsForValue();
	}

	@Bean
	public HashOperations<String, Integer, Object> hashOperations(RedisTemplate<String, String> redisTemplate) {
		return redisTemplate.opsForHash();
	}

	@Bean
	public ObjectMapper jackson2ObjectMapperBuilder() {
		return Jackson2ObjectMapperBuilder.json().simpleDateFormat(AppConstant.DATE_FORMAT).build();
	}

}
