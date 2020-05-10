package com.example.demo.configurations;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Component;

import com.example.demo.constants.ApplicationConstant;

@Component
public class RedisDataInitializer implements CommandLineRunner {

	@Autowired
	private HashOperations<String, String, String> hashOperations;
	private static final Logger LOGGER = LoggerFactory.getLogger(RedisDataInitializer.class);

	@Override
	public void run(String... args) throws Exception {
		LOGGER.trace("Enter into run method with parameters : {} ", Arrays.toString(args));
		Map<String, String> hashKeyValuePair1 = new HashMap<>();
		hashKeyValuePair1.put(ApplicationConstant.REQUEST_TYPE, "line-level");
		hashKeyValuePair1.put(ApplicationConstant.RUNNING_STATUS, Boolean.TRUE.toString());
		hashKeyValuePair1.put(ApplicationConstant.STOP_TRIGGERED, Boolean.FALSE.toString());
		hashKeyValuePair1.put(ApplicationConstant.TOTAL_RECORDS, "1000");
		hashKeyValuePair1.put(ApplicationConstant.PROCESSED, "0");
		hashKeyValuePair1.put(ApplicationConstant.IN_PROGRESS, "0");
		hashKeyValuePair1.put(ApplicationConstant.NOT_STARTED, "0");

		Map<String, String> hashKeyValuePair2 = new HashMap<>();
		hashKeyValuePair2.put(ApplicationConstant.REQUEST_TYPE, "account-level");
		hashKeyValuePair2.put(ApplicationConstant.RUNNING_STATUS, Boolean.TRUE.toString());
		hashKeyValuePair2.put(ApplicationConstant.STOP_TRIGGERED, Boolean.FALSE.toString());
		hashKeyValuePair2.put(ApplicationConstant.TOTAL_RECORDS, "100");
		hashKeyValuePair2.put(ApplicationConstant.PROCESSED, "45");
		hashKeyValuePair2.put(ApplicationConstant.IN_PROGRESS, "30");
		hashKeyValuePair2.put(ApplicationConstant.NOT_STARTED, "25");

		hashOperations.putAll(ApplicationConstant.REDIS_KEY.concat(ApplicationConstant.COLON).concat("12345678"),
				hashKeyValuePair1);
		hashOperations.putAll(ApplicationConstant.REDIS_KEY.concat(ApplicationConstant.COLON).concat("87654321"),
				hashKeyValuePair2);

		Runnable target = () -> {
			int i = 0;
			while (i < 1000) {
				hashOperations.increment(
						ApplicationConstant.REDIS_KEY.concat(ApplicationConstant.COLON).concat("12345678"),
						ApplicationConstant.IN_PROGRESS, 1);
				hashOperations.increment(
						ApplicationConstant.REDIS_KEY.concat(ApplicationConstant.COLON).concat("12345678"),
						ApplicationConstant.PROCESSED, 1);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				i++;
			}
		};

		Thread thread = new Thread(target);
		thread.start();

		LOGGER.trace("Exit from run method ");
	}

}
