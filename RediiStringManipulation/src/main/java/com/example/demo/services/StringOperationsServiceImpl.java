package com.example.demo.services;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repositories.StringOperationsRepository;

@Service
public class StringOperationsServiceImpl implements StringOperationsService {

	@Autowired
	private StringOperationsRepository stringOperationsRepository;
	private static final Logger LOGGER = LoggerFactory.getLogger(StringOperationsServiceImpl.class);

	@Override
	public void setString(final String key, final String value, final Integer timeout) {
		LOGGER.trace("Enter into setString method with parameters : {},{},{} ", key, value, timeout);
		stringOperationsRepository.setString(key, value, timeout);
		LOGGER.trace("Exit from setString method");
	}

	@Override
	public Boolean setXXString(final String key, final String value, final Integer timeout) {
		LOGGER.trace("Enter into setXXString method with parameters : {},{},{} ", key, value, timeout);
		final Boolean result = stringOperationsRepository.setXXString(key, value, timeout);
		LOGGER.trace("Exit from setXXString method with output : {} ", result);
		return result;
	}

	@Override
	public Boolean setNXString(final String key, final String value, final Integer timeout) {
		LOGGER.trace("Enter into setNXString method with parameters : {},{},{} ", key, value, timeout);
		final Boolean result = stringOperationsRepository.setNXString(key, value, timeout);
		LOGGER.trace("Exit from setNXString method with output : {} ", result);
		return result;
	}

	@Override
	public String getString(final String key) {
		LOGGER.trace("Enter into getString method with parameters : {} ", key);
		final String result = stringOperationsRepository.getString(key);
		LOGGER.trace("Exit from getString method with output : {} ", result);
		return result;
	}

	@Override
	public Boolean keyExists(final String key) {
		LOGGER.trace("Enter into keyExists method with parameters : {} ", key);
		final Boolean result = stringOperationsRepository.keyExists(key);
		LOGGER.trace("Exit from keyExists method with output : {} ", result);
		return result;
	}

	@Override
	public Long incr(final String key) {
		LOGGER.trace("Enter into incr method with parameters : {}  ", key);
		final Long result = stringOperationsRepository.incr(key);
		LOGGER.trace("Exit from incr method with output : {} ", result);
		return result;
	}

	@Override
	public Long decr(final String key) {
		LOGGER.trace("Enter into decr method with parameters : {} ", key);
		final Long result = stringOperationsRepository.decr(key);
		LOGGER.trace("Exit from decr method with output : {} ", result);
		return result;
	}

	@Override
	public Long incrBy(final String key, final Long delta) {
		LOGGER.trace("Enter into incrBy method with parameters : {},{} ", key, delta);
		final Long result = stringOperationsRepository.incrBy(key, delta);
		LOGGER.trace("Exit from incrBy method with output : {} ", result);
		return result;
	}

	@Override
	public Long decrBy(final String key, final Long delta) {
		LOGGER.trace("Enter into decrBy method with parameters : {},{}", key, delta);
		final Long result = stringOperationsRepository.decrBy(key, delta);
		LOGGER.trace("Exit from decrBy method with output : {} ", result);
		return result;
	}

	@Override
	public Double incrByFloat(final String key, final Double delta) {
		LOGGER.trace("Enter into incrByFloat method with parameters : {},{} ", key, delta);
		final Double result = stringOperationsRepository.incrByFloat(key, delta);
		LOGGER.trace("Exit from incrByFloat method with output : {} ", result);
		return result;
	}

	@Override
	public Boolean deleteKey(final String key) {
		LOGGER.trace("Enter into deleteKey method with parameters : {} ", key);
		final Boolean result = stringOperationsRepository.deleteKey(key);
		LOGGER.trace("Exit from deleteKey method with output : {} ", result);
		return result;
	}

	@Override
	public Boolean unlinkKey(final String key) {
		LOGGER.trace("Enter into unlinkKey method with parameters : {} ", key);
		final Boolean result = stringOperationsRepository.unlinkKey(key);
		LOGGER.trace("Exit from unlinkKey method with output : {} ", result);
		return result;
	}

	@Override
	public Set<String> keys(final String pattern) {
		LOGGER.trace("Enter into keys method with parameters : {} ", pattern);
		final Set<String> result = stringOperationsRepository.keys(pattern);
		LOGGER.trace("Exit from keys method with output : {} ", result);
		return result;
	}

	@Override
	public Set<String> scan(final String pattern, final Long count) {
		LOGGER.trace("Enter into scan method with parameters : {},{} ", pattern, count);
		final Set<String> result = stringOperationsRepository.scan(pattern, count);
		LOGGER.trace("Exit from scan method with output : {} ", result);
		return result;
	}
	
	@Override
	public String range(String key, Long start, Long end) {
		LOGGER.trace("Enter into keys method with parameters : {},{},{} ", key, start, end);
		String result = stringOperationsRepository.range(key, start, end);
		LOGGER.trace("Exit from range method with output : {} ", result);
		return result;
	}

	@Override
	public Integer append(String key, String value) {
		LOGGER.trace("Enter into keys method with parameters : {},{} ", key, value);
		Integer result = stringOperationsRepository.append(key, value);
		LOGGER.trace("Exit from append method with output : {} ", result);
		return result;
	}

	@Override
	public Long size(String key) {
		LOGGER.trace("Enter into keys method with parameters : {} ", key);
		Long result = stringOperationsRepository.size(key);
		LOGGER.trace("Exit from size method with output : {} ", result);
		return result;
	}

}
