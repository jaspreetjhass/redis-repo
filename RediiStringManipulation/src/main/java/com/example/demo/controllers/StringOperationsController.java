package com.example.demo.controllers;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.services.StringOperationsService;

@RestController
@RequestMapping("redis/stringOperations")
public class StringOperationsController {

	@Autowired
	private StringOperationsService stringOperationsService;
	private static final Logger LOGGER = LoggerFactory.getLogger(StringOperationsController.class);

	@GetMapping("set/{key}/{value}/{timeout}")
	public void setString(@PathVariable final String key, @PathVariable final String value,
			@PathVariable final Integer timeout) {
		LOGGER.trace("Enter into setString method with parameters : {},{},{} ", key, value, timeout);
		stringOperationsService.setString(key, value, timeout);
		LOGGER.trace("Exit from setString method");
	}

	@GetMapping("setXX/{key}/{value}/{timeout}")
	public Boolean setXXString(@PathVariable final String key, @PathVariable final String value,
			@PathVariable final Integer timeout) {
		LOGGER.trace("Enter into setXXString method with parameters : {},{},{} ", key, value, timeout);
		final Boolean result = stringOperationsService.setXXString(key, value, timeout);
		LOGGER.trace("Exit from setXXString method with output : {} ", result);
		return result;
	}

	@GetMapping("setNX/{key}/{value}/{timeout}")
	public Boolean setNXString(@PathVariable final String key, @PathVariable final String value,
			@PathVariable final Integer timeout) {
		LOGGER.trace("Enter into setNXString method with parameters : {},{},{} ", key, value, timeout);
		final Boolean result = stringOperationsService.setNXString(key, value, timeout);
		LOGGER.trace("Exit from setNXString method with output : {} ", result);
		return result;
	}

	@GetMapping("getString/{key}")
	public String getString(@PathVariable final String key) {
		LOGGER.trace("Enter into getString method with parameters : {} ", key);
		final String result = stringOperationsService.getString(key);
		LOGGER.trace("Exit from getString method with output : {} ", result);
		return result;
	}

	@GetMapping("keyExists/{key}")
	public Boolean keyExists(@PathVariable final String key) {
		LOGGER.trace("Enter into keyExists method with parameters : {} ", key);
		final Boolean result = stringOperationsService.keyExists(key);
		LOGGER.trace("Exit from keyExists method with output : {} ", result);
		return result;
	}

	@GetMapping("incr/{key}")
	public Long incr(@PathVariable final String key) {
		LOGGER.trace("Enter into incr method with parameters : {}  ", key);
		final Long result = stringOperationsService.incr(key);
		LOGGER.trace("Exit from incr method with output : {} ", result);
		return result;
	}

	@GetMapping("decr/{key}")
	public Long decr(@PathVariable final String key) {
		LOGGER.trace("Enter into decr method with parameters : {} ", key);
		final Long result = stringOperationsService.decr(key);
		LOGGER.trace("Exit from decr method with output : {} ", result);
		return result;
	}

	@GetMapping("incrBy/{key}/{delta}")
	public Long incrBy(@PathVariable final String key, @PathVariable final Long delta) {
		LOGGER.trace("Enter into incrBy method with parameters : {},{} ", key, delta);
		final Long result = stringOperationsService.incrBy(key, delta);
		LOGGER.trace("Exit from incrBy method with output : {} ", result);
		return result;
	}

	@GetMapping("decrBy/{key}/{delta}")
	public Long decrBy(@PathVariable final String key, @PathVariable final Long delta) {
		LOGGER.trace("Enter into decrBy method with parameters : {},{}", key, delta);
		final Long result = stringOperationsService.decrBy(key, delta);
		LOGGER.trace("Exit from decrBy method with output : {} ", result);
		return result;
	}

	@GetMapping("incrByFloat/{key}/{delta}")
	public Double incrByFloat(@PathVariable final String key, @PathVariable final Double delta) {
		LOGGER.trace("Enter into incrByFloat method with parameters : {},{} ", key, delta);
		final Double result = stringOperationsService.incrByFloat(key, delta);
		LOGGER.trace("Exit from incrByFloat method with output : {} ", result);
		return result;
	}

	@GetMapping("deleteKey/{key}")
	public Boolean deleteKey(@PathVariable final String key) {
		LOGGER.trace("Enter into deleteKey method with parameters : {} ", key);
		final Boolean result = stringOperationsService.deleteKey(key);
		LOGGER.trace("Exit from deleteKey method with output : {} ", result);
		return result;
	}

	@GetMapping("unlinkKey/{key}")
	public Boolean unlinkKey(@PathVariable final String key) {
		LOGGER.trace("Enter into unlinkKey method with parameters : {} ", key);
		final Boolean result = stringOperationsService.unlinkKey(key);
		LOGGER.trace("Exit from unlinkKey method with output : {} ", result);
		return result;
	}

	@GetMapping("keys/{pattern}")
	public Set<String> keys(@PathVariable final String pattern) {
		LOGGER.trace("Enter into keys method with parameters : {} ", pattern);
		final Set<String> result = stringOperationsService.keys(pattern);
		LOGGER.trace("Exit from keys method with output : {} ", result);
		return result;
	}

	@GetMapping("scan/{pattern}/{count}")
	public Set<String> scan(@PathVariable final String pattern, @PathVariable final Long count) {
		LOGGER.trace("Enter into scan method with parameters : {},{} ", pattern, count);
		final Set<String> result = stringOperationsService.scan(pattern, count);
		LOGGER.trace("Exit from scan method with output : {} ", result);
		return result;
	}

	@GetMapping("range/{key}/{start}/{end}")
	public String range(@PathVariable final String key, @PathVariable final Long start, @PathVariable final Long end) {
		LOGGER.trace("Enter into keys method with parameters : {},{},{} ", key, start, end);
		final String result = stringOperationsService.range(key, start, end);
		LOGGER.trace("Exit from range method with output : {} ", result);
		return result;
	}

	@GetMapping("append/{key}/{value}")
	public Integer append(@PathVariable final String key, @PathVariable final String value) {
		LOGGER.trace("Enter into keys method with parameters : {},{} ", key, value);
		final Integer result = stringOperationsService.append(key, value);
		LOGGER.trace("Exit from append method with output : {} ", result);
		return result;
	}

	@GetMapping("size/{key}")
	public Long size(@PathVariable final String key) {
		LOGGER.trace("Enter into keys method with parameters : {} ", key);
		final Long result = stringOperationsService.size(key);
		LOGGER.trace("Exit from size method with output : {} ", result);
		return result;
	}

}
