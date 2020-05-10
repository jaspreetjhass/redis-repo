package com.example.demo.repositories;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.ScanOptions.ScanOptionsBuilder;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

@Repository
public class StringOperationsRepositoryImpl implements StringOperationsRepository {

	@Autowired
	private ValueOperations<String, String> valueOperations;
	private static final Logger LOGGER = LoggerFactory.getLogger(StringOperationsRepositoryImpl.class);

	@Override
	public void setString(final String key, final String value, final Integer timeout) {
		LOGGER.trace("Enter into setString method with parameters : {},{},{} ", key, value, timeout);
		valueOperations.set(key, value, timeout, TimeUnit.SECONDS);
		LOGGER.trace("Exit from setString method");
	}

	@Override
	public Boolean setXXString(final String key, final String value, final Integer timeout) {
		LOGGER.trace("Enter into setXXString method with parameters : {},{},{} ", key, value, timeout);
		final Boolean result = valueOperations.setIfPresent(key, value, timeout, TimeUnit.SECONDS);
		LOGGER.trace("Exit from setXXString method with output : {} ", result);
		return result;
	}

	@Override
	public Boolean setNXString(final String key, final String value, final Integer timeout) {
		LOGGER.trace("Enter into setNXString method with parameters : {},{},{} ", key, value, timeout);
		final Boolean result = valueOperations.setIfAbsent(key, value, timeout, TimeUnit.SECONDS);
		LOGGER.trace("Exit from setNXString method with output : {} ", result);
		return result;
	}

	@Override
	public String getString(final String key) {
		LOGGER.trace("Enter into getString method with parameters : {} ", key);
		final String result = valueOperations.get(key);
		LOGGER.trace("Exit from getString method with output : {} ", result);
		return result;
	}

	@Override
	public Boolean keyExists(final String key) {
		LOGGER.trace("Enter into keyExists method with parameters : {} ", key);
		final Boolean result = valueOperations.getOperations().hasKey(key);
		LOGGER.trace("Exit from keyExists method with output : {} ", result);
		return result;
	}

	@Override
	public Long incr(final String key) {
		LOGGER.trace("Enter into incr method with parameters : {}  ", key);
		final Long result = valueOperations.increment(key);
		LOGGER.trace("Exit from incr method with output : {} ", result);
		return result;
	}

	@Override
	public Long decr(final String key) {
		LOGGER.trace("Enter into decr method with parameters : {} ", key);
		final Long result = valueOperations.decrement(key);
		LOGGER.trace("Exit from decr method with output : {} ", result);
		return result;
	}

	@Override
	public Long incrBy(final String key, final Long delta) {
		LOGGER.trace("Enter into incrBy method with parameters : {},{} ", key, delta);
		final Long result = valueOperations.increment(key, delta);
		LOGGER.trace("Exit from incrBy method with output : {} ", result);
		return result;
	}

	@Override
	public Long decrBy(final String key, final Long delta) {
		LOGGER.trace("Enter into decrBy method with parameters : {},{}", key, delta);
		final Long result = valueOperations.decrement(key, delta);
		LOGGER.trace("Exit from decrBy method with output : {} ", result);
		return result;
	}

	@Override
	public Double incrByFloat(final String key, final Double delta) {
		LOGGER.trace("Enter into incrByFloat method with parameters : {},{} ", key, delta);
		final Double result = valueOperations.increment(key, delta);
		LOGGER.trace("Exit from incrByFloat method with output : {} ", result);
		return result;
	}

	@Override
	public Boolean deleteKey(final String key) {
		LOGGER.trace("Enter into deleteKey method with parameters : {} ", key);
		final Boolean result = valueOperations.getOperations().delete(key);
		LOGGER.trace("Exit from deleteKey method with output : {} ", result);
		return result;
	}

	@Override
	public Boolean unlinkKey(final String key) {
		LOGGER.trace("Enter into unlinkKey method with parameters : {} ", key);
		final Boolean result = valueOperations.getOperations().unlink(key);
		LOGGER.trace("Exit from unlinkKey method with output : {} ", result);
		return result;
	}

	@Override
	public Set<String> keys(final String pattern) {
		LOGGER.trace("Enter into keys method with parameters : {} ", pattern);
		final Set<String> result = valueOperations.getOperations().keys(pattern);
		LOGGER.trace("Exit from keys method with output : {} ", result);
		return result;
	}

	@Override
	public Set<String> scan(final String pattern, final Long count) {
		LOGGER.trace("Enter into scan method with parameters : {},{} ", pattern, count);
		final RedisCallback<Set<String>> redisCallback = (redisConnection) -> {
			final Set<String> keys = new HashSet<>();
			final Cursor<byte[]> cursor = redisConnection
					.scan(ScanOptions.scanOptions().count(count).match(pattern).build());
			redisConnection.scan(new ScanOptionsBuilder().count(count).match(pattern).build());
			while (cursor.hasNext()) {
				keys.add(new String(cursor.next()));
			}
			return keys;
		};
		final Set<String> result = valueOperations.getOperations().execute(redisCallback);
		LOGGER.trace("Exit from scan method with output : {} ", result);
		return result;
	}

	@Override
	public String range(final String key, final Long start, final Long end) {
		LOGGER.trace("Enter into keys method with parameters : {},{},{} ", key, start, end);
		final String result = valueOperations.get(key, start, end);
		LOGGER.trace("Exit from range method with output : {} ", result);
		return result;
	}

	@Override
	public Integer append(final String key, final String value) {
		LOGGER.trace("Enter into keys method with parameters : {},{} ", key, value);
		final Integer result = valueOperations.append(key, value);
		LOGGER.trace("Exit from append method with output : {} ", result);
		return result;
	}

	@Override
	public Long size(final String key) {
		LOGGER.trace("Enter into keys method with parameters : {} ", key);
		final Long result = valueOperations.size(key);
		// valueOperations.getOperations().execute(session)
		LOGGER.trace("Exit from size method with output : {} ", result);
		return result;
	}

}
