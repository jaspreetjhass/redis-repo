package com.example.demo.repositories;

import java.io.IOException;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.example.demo.constants.AppConstant;
import com.example.demo.exception.ApplicationException;
import com.example.demo.models.Student;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class StudentRepositoryCallbackImpl implements StudentRepository {

	@Autowired
	private RedisTemplate<String, ?> redisTemplate;
	@Autowired
	private ObjectMapper objectMapper;
	private static final Logger LOGGER = LoggerFactory.getLogger(StudentRepositoryCallbackImpl.class);

	@Override
	public Student fetchStudent(final Integer studentId) {
		LOGGER.trace("Enter into fetchStudent method with parameters : {}", studentId);
		final RedisCallback<Student> redisCallback = (redisConnection) -> {
			final byte[] result = redisConnection.hGet(AppConstant.STUDENT_KEY_PREFIX.getBytes(),
					String.valueOf(studentId).getBytes());
			Student student = null;
			try {
				if (!StringUtils.isEmpty(Arrays.toString(result)))
					student = objectMapper.readValue(result, Student.class);
			} catch (final IOException exception) {
				throw new ApplicationException("Error while converting json to java type", exception);
			}
			return student;
		};
		final Student student = redisTemplate.execute(redisCallback);
		LOGGER.trace("Exit from fetchStudent method with output : {}", student);
		return student;
	}

	@Override
	public Student saveStudent(final Student student) {
		LOGGER.trace("Enter into saveStudent method with parameters : {}", student);
		final RedisCallback<Student> redisCallback = (redisConnection) -> {
			try {
				final byte[] studentBytes = objectMapper.writeValueAsBytes(student);
				redisConnection.hSet(AppConstant.STUDENT_KEY_PREFIX.getBytes(),
						String.valueOf(student.getStudentId()).getBytes(), studentBytes);
			} catch (final JsonProcessingException exception) {
				throw new ApplicationException("Error while converting json to java type", exception);
			}
			return student;
		};

		final Student studentResponse = redisTemplate.execute(redisCallback);
		LOGGER.trace("Exit from saveStudent method with output : {}", studentResponse);
		return studentResponse;
	}

	@Override
	public Student updateStudent(final Student student) {
		LOGGER.trace("Enter into updateStudent method with parameters : {}", student);
		final RedisCallback<Student> redisCallback = (redisConnection) -> {
			try {
				final byte[] studentBytes = objectMapper.writeValueAsBytes(student);
				redisConnection.hSet(AppConstant.STUDENT_KEY_PREFIX.getBytes(),
						String.valueOf(student.getStudentId()).getBytes(), studentBytes);
			} catch (final JsonProcessingException exception) {
				throw new ApplicationException("Error while converting json to java type", exception);
			}
			return student;
		};

		final Student studentResponse = redisTemplate.execute(redisCallback);
		LOGGER.trace("Exit from updateStudent method with output : {}", studentResponse);
		return studentResponse;
	}

	@Override
	public Student deleteStudent(final Integer studentId) {
		LOGGER.trace("Enter into deleteStudent method with parameters : {}", studentId);
		final RedisCallback<Student> redisCallback = (redisConnection) -> {
			final byte[] result = redisConnection.hGet(AppConstant.STUDENT_KEY_PREFIX.getBytes(),
					String.valueOf(studentId).getBytes());
			Student student = null;
			try {
				if (!StringUtils.isEmpty(Arrays.toString(result))) {
					student = objectMapper.readValue(result, Student.class);
					redisConnection.unlink(AppConstant.STUDENT_KEY_PREFIX.getBytes());
				}
			} catch (final IOException exception) {
				throw new ApplicationException("Error while converting json to java type", exception);
			}
			return student;
		};
		final Student student = redisTemplate.execute(redisCallback);
		LOGGER.trace("Exit from deleteStudent method with output : {}", student);
		return student;
	}

}
