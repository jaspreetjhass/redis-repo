package com.example.demo.repositories;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import com.example.demo.constants.AppConstant;
import com.example.demo.models.Student;

//@Repository
public class StudentRepositoryHashImpl implements StudentRepository {

	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	@Autowired
	private HashOperations<String, Integer, Object> hashOperations;
	private static final Logger LOGGER = LoggerFactory.getLogger(StudentRepositoryHashImpl.class);

	@Override
	public Student fetchStudent(final Integer studentId) {
		LOGGER.trace("Enter into fetchStudent method with parameters : {}", studentId);
		final Student student = (Student) hashOperations.get(AppConstant.STUDENT_KEY_PREFIX,studentId);
		LOGGER.trace("Exit from fetchStudent method with output : {}", student);
		return student;
	}

	@Override
	public Student saveStudent(final Student student) {
		LOGGER.trace("Enter into saveStudent method with parameters : {}", student);
		hashOperations.put(AppConstant.STUDENT_KEY_PREFIX, student.getStudentId(), student);
		LOGGER.trace("Exit from saveStudent method with output : {}", student);
		return student;
	}

	@Override
	public Student updateStudent(final Student student) {
		LOGGER.trace("Enter into updateStudent method with parameters : {}", student);
		hashOperations.put(AppConstant.STUDENT_KEY_PREFIX, student.getStudentId(), student);
		LOGGER.trace("Exit from updateStudent method with output : {}", student);
		return student;
	}

	@Override
	public Student deleteStudent(final Integer studentId) {
		LOGGER.trace("Enter into deleteStudent method with parameters : {}", studentId);
		final Student student = (Student) hashOperations.get(AppConstant.STUDENT_KEY_PREFIX, studentId);
		redisTemplate.delete(AppConstant.STUDENT_KEY_PREFIX);
		LOGGER.trace("Exit from deleteStudent method with output : {}", student);
		return student;
	}

}
