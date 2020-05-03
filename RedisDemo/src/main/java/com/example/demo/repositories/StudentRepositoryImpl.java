package com.example.demo.repositories;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.example.demo.constants.AppConstant;
import com.example.demo.exception.ApplicationException;
import com.example.demo.models.Student;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

//@Repository
public class StudentRepositoryImpl implements StudentRepository {

	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	@Autowired
	private ValueOperations<String, String> valueOperations;
	private static final Logger LOGGER = LoggerFactory.getLogger(StudentRepositoryImpl.class);

	@Override
	public Student fetchStudent(final Integer studentId) {
		LOGGER.trace("Enter into fetchStudent method with parameters : {}", studentId);
		final String key = AppConstant.STUDENT_KEY_PREFIX.concat(AppConstant.COLON).concat(String.valueOf(studentId));
		final String studentStr = valueOperations.get(key);
		Student student = null;
		try {
			if (!StringUtils.isEmpty(studentStr))
				student = objectMapper.readValue(studentStr, Student.class);
		} catch (JsonProcessingException exception) {
			throw new ApplicationException("Error while converting json to java type", exception);
		}
		LOGGER.trace("Exit from fetchStudent method with output : {}", studentStr);
		return student;
	}

	@Override
	public Student saveStudent(final Student student) {
		LOGGER.trace("Enter into saveStudent method with parameters : {}", student);
		final String key = AppConstant.STUDENT_KEY_PREFIX.concat(AppConstant.COLON)
				.concat(String.valueOf(student.getStudentId()));
		String studentStr = null;
		try {
			studentStr = objectMapper.writeValueAsString(student);
			valueOperations.set(key, studentStr);
		} catch (JsonProcessingException exception) {
			throw new ApplicationException("Error while converting json to java type", exception);
		}
		LOGGER.trace("Exit from saveStudent method with output : {}", student);
		return student;
	}

	@Override
	public Student updateStudent(final Student student) {
		LOGGER.trace("Enter into updateStudent method with parameters : {},{}", student);
		final String key = AppConstant.STUDENT_KEY_PREFIX.concat(AppConstant.COLON)
				.concat(String.valueOf(student.getStudentId()));
		String studentStr = null;
		try {
			studentStr = objectMapper.writeValueAsString(student);
			valueOperations.set(key, studentStr);
		} catch (JsonProcessingException exception) {
			throw new ApplicationException("Error while converting json to java type", exception);
		}
		LOGGER.trace("Exit from updateStudent method with output : {}", student);
		return student;
	}

	@Override
	public Student deleteStudent(final Integer studentId) {
		LOGGER.trace("Enter into deleteStudent method with parameters : {}", studentId);
		final String key = AppConstant.STUDENT_KEY_PREFIX.concat(AppConstant.COLON).concat(String.valueOf(studentId));
		final String studentStr = valueOperations.get(key);
		Student student = null;
		try {
			if (!StringUtils.isEmpty(studentStr))
				student = objectMapper.readValue(studentStr, Student.class);
			redisTemplate.delete(key);
		} catch (JsonProcessingException exception) {
			throw new ApplicationException("Error while converting json to java type", exception);
		}
		LOGGER.trace("Exit from deleteStudent method with output : {}", student);
		return student;
	}

}
