package com.example.demo.repositories;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import com.example.demo.constants.AppConstant;

@Repository
public class StudentRepositoryImpl implements StudentRepository {

	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	@Autowired
	private ValueOperations<String, String> valueOperations;
	private static final Logger LOGGER = LoggerFactory.getLogger(StudentRepositoryImpl.class);

	@Override
	public String fetchStudent(final Integer studentId) {
		LOGGER.trace("Enter into fetchStudent method with parameters : {}", studentId);
		final String key = AppConstant.STUDENT_KEY_PREFIX.concat(AppConstant.COLON).concat(String.valueOf(studentId));
		final String student = valueOperations.get(key);
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LOGGER.trace("Exit from fetchStudent method with output : {}", student);
		return student;
	}

	@Override
	public String saveStudent(final String student, final Integer studentId) {
		LOGGER.trace("Enter into saveStudent method with parameters : {},{}", student, studentId);
		final String key = AppConstant.STUDENT_KEY_PREFIX.concat(AppConstant.COLON).concat(String.valueOf(studentId));
		valueOperations.set(key, student);
		LOGGER.trace("Exit from saveStudent method with output : {}", student);
		return student;
	}

	@Override
	public String updateStudent(final String student, final Integer studentId) {
		LOGGER.trace("Enter into updateStudent method with parameters : {},{}", student, studentId);
		final String key = AppConstant.STUDENT_KEY_PREFIX.concat(AppConstant.COLON).concat(String.valueOf(studentId));
		valueOperations.set(key, student);
		LOGGER.trace("Exit from updateStudent method with output : {}", student);
		return student;
	}

	@Override
	public String deleteStudent(final Integer studentId) {
		LOGGER.trace("Enter into deleteStudent method with parameters : {}", studentId);
		final String key = AppConstant.STUDENT_KEY_PREFIX.concat(AppConstant.COLON).concat(String.valueOf(studentId));
		final String student = valueOperations.get(key);
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		redisTemplate.delete(key);
		LOGGER.trace("Exit from deleteStudent method with output : {}", student);
		return student;
	}

}
