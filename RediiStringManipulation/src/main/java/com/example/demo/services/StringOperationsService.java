package com.example.demo.services;

import java.util.Set;

public interface StringOperationsService {

	void setString(String key, String value, Integer timeout);

	Boolean setXXString(String key, String value, Integer timeout);

	Boolean setNXString(String key, String value, Integer timeout);

	String getString(String key);

	Boolean keyExists(String key);

	Long incr(String key);

	Long decr(String key);

	Long incrBy(String key,Long delta);

	Long decrBy(String key,Long delta);

	Double incrByFloat(String key,Double delta);

	Boolean deleteKey(String key);

	Boolean unlinkKey(String key);

	Set<String> keys(String pattern);

	Set<String> scan(String pattern, Long count);
	
	String range(String key, Long start, Long end);

	Integer append(String key, String value);

	Long size(String key);
}
