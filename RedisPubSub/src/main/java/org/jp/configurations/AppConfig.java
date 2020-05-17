package org.jp.configurations;

import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.Executors;

import org.jp.error.handlers.RedisErrorHandler;
import org.jp.listeners.OrderDataListener;
import org.jp.listeners.RedisMessageListener;
import org.jp.models.Order;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.hash.ObjectHashMapper;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.Topic;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class AppConfig {

	@Bean
	RedisMessageListenerContainer redisMessageListenerContainer(RedisConnectionFactory redisConnectionFactory,
			RedisMessageListener messageListener, RedisErrorHandler redisErrorHandler,
			OrderDataListener orderDataListener) {
		RedisMessageListenerContainer redisMessageListenerContainer = new RedisMessageListenerContainer();
		redisMessageListenerContainer.setConnectionFactory(redisConnectionFactory);
		redisMessageListenerContainer.addMessageListener(orderDataListener,
				Collections.checkedCollection(Arrays.asList(new PatternTopic("order:*")), PatternTopic.class));
		redisMessageListenerContainer.addMessageListener(messageListener, channelTopic());
		redisMessageListenerContainer.setTaskExecutor(Executors.newFixedThreadPool(100));
		redisMessageListenerContainer.setErrorHandler(redisErrorHandler);
		return redisMessageListenerContainer;
	}

	@Bean
	Topic channelTopic() {
		return new ChannelTopic("topic123");
	}

	@Bean
	ObjectHashMapper objectHashMapper() {
		return new ObjectHashMapper();
	}

	@Bean
	Jackson2JsonRedisSerializer<Order> jackson2JsonRedisSerializer() {
		return new Jackson2JsonRedisSerializer<>(Order.class);
	}

	@Bean
	JdkSerializationRedisSerializer jdkSerializationRedisSerializer() {
		return new JdkSerializationRedisSerializer();
	}

	@Bean
	GenericToStringSerializer<Integer> genericToStringSerializer() {
		return new GenericToStringSerializer<>(Integer.class);
	}

	@Bean
	RedisTemplate<String, Order> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
		RedisTemplate<String, Order> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(redisConnectionFactory);
		redisTemplate.setValueSerializer(jackson2JsonRedisSerializer());
		return redisTemplate;
	}

	@Bean
	RedisTemplate<Object, Object> redisTemplateObject(RedisConnectionFactory redisConnectionFactory) {
		RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(redisConnectionFactory);
		redisTemplate.setValueSerializer(new StringRedisSerializer());
		return redisTemplate;
	}

}
