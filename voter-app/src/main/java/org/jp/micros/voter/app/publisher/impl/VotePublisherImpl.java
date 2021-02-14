package org.jp.micros.voter.app.publisher.impl;

import org.jp.micros.voter.app.constant.AppConstant;
import org.jp.micros.voter.app.publisher.VotePublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class VotePublisherImpl implements VotePublisher {

	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	@Override
	public void castVote(String streamName) {
		redisTemplate.convertAndSend(AppConstant.VOTING_CHANNEL, streamName);
		log.info("message published successfully");
	}

}
