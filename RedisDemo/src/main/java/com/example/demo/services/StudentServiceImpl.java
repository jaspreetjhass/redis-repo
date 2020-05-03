package com.example.demo.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.demo.exception.ApplicationException;
import com.example.demo.models.Student;
import com.example.demo.repositories.StudentRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private StudentRepository studentRepository;
	private static final Logger LOGGER = LoggerFactory.getLogger(StudentServiceImpl.class);

	@Override
	public Student fetchStudent(final Integer studentId) {
		LOGGER.trace("Enter into fetchStudent method with parameters : {}", studentId);
		final String jsonStudent = studentRepository.fetchStudent(studentId);
		Student student = null;
		try {
			if (!StringUtils.isEmpty(jsonStudent))
				student = objectMapper.readValue(jsonStudent, Student.class);
		} catch (final JsonProcessingException exception) {
			throw new ApplicationException("error occured while performing json", exception);
		}
		LOGGER.trace("Exit from fetchStudent method with output : {}", student);
		return student;
	}

	@Override
	public Student saveStudent(final Student student) {
		LOGGER.trace("Enter into saveStudent method with parameters : {}", student);
		Student studentResponse;
		try {
			final String jsonStudent = objectMapper.writeValueAsString(student);
			final String jsonStudentResponse = studentRepository.saveStudent(jsonStudent, student.getStudentId());
			studentResponse = objectMapper.readValue(jsonStudentResponse, Student.class);
		} catch (final JsonProcessingException exception) {
			throw new ApplicationException("error occured while performing json", exception);
		}
		LOGGER.trace("Exit from saveStudent method with output : {}", studentResponse);
		return studentResponse;
	}

	@Override
	public Student updateStudent(final Student student) {
		LOGGER.trace("Enter into updateStudent method with parameters : {}", student);
		Student studentResponse;
		try {
			final String jsonStudent = objectMapper.writeValueAsString(student);
			final String jsonStudentResponse = studentRepository.updateStudent(jsonStudent, student.getStudentId());
			studentResponse = objectMapper.readValue(jsonStudentResponse, Student.class);
		} catch (final JsonProcessingException exception) {
			throw new ApplicationException("error occured while performing json", exception);
		}
		LOGGER.trace("Exit from updateStudent method with output : {}", studentResponse);
		return studentResponse;
	}

	@Override
	public Student deleteStudent(final Integer studentId) {
		LOGGER.trace("Enter into deleteStudent method with parameters : {}", studentId);
		Student studentResponse = null;
		try {
			final String jsonStudentResponse = studentRepository.deleteStudent(studentId);
			if (!StringUtils.isEmpty(jsonStudentResponse))
				studentResponse = objectMapper.readValue(jsonStudentResponse, Student.class);
		} catch (final JsonProcessingException exception) {
			throw new ApplicationException("error occured while performing json", exception);
		}
		LOGGER.trace("Exit from deleteStudent method with output : {}", studentResponse);
		return studentResponse;
	}

}
