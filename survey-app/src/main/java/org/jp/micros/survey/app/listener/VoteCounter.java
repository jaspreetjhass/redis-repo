package org.jp.micros.survey.app.listener;

import java.util.List;
import java.util.StringJoiner;

import org.jp.micros.survey.app.constant.AppConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class VoteCounter implements MessageListener {

	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	@Override
	public void onMessage(final Message message, final byte[] pattern) {
		log.info("message received sucessfully");
		final StringJoiner stringJoiner = new StringJoiner(AppConstant.HYPHEN);
		stringJoiner.add(AppConstant.VOTING).add(new String(message.getBody()));
		// start transaction
		final RedisCallback<List<Object>> redisCallback = (redisConnection) -> {
			redisConnection.multi();
			redisConnection.hIncrBy(AppConstant.VOTING.getBytes(), stringJoiner.toString().getBytes(),
					AppConstant.NUMBER_ONE);
			redisConnection.hIncrBy(AppConstant.VOTING.getBytes(), AppConstant.TOTAL.getBytes(),
					AppConstant.NUMBER_ONE);
			return redisConnection.exec();
		};
		redisTemplate.executePipelined(redisCallback);
		// execute transaction
	}

}
