package org.jp.listeners;

import org.jp.models.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.stereotype.Service;

import lombok.extern.java.Log;

@Service
@Log
public class OrderDataListener implements MessageListener {

	@Autowired
	private Jackson2JsonRedisSerializer<Order> jackson2JsonRedisSerializer;

	@Override
	public void onMessage(Message message, byte[] pattern) {
		Order order = jackson2JsonRedisSerializer.deserialize(message.getBody());
		log.info("Message received from '".concat(new String(pattern)).concat("' is : ") + order);
	}
}
