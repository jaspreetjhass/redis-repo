package org.jp.listeners;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

import lombok.extern.java.Log;

@Service
@Log
public class RedisMessageListener implements MessageListener {

	@Override
	public void onMessage(Message message, byte[] pattern) {
		log.info("Message received from '" + new String(pattern) + "' : " + message);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
