package com.example.demo.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Student;
import com.example.demo.repositories.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;
	private static final Logger LOGGER = LoggerFactory.getLogger(StudentServiceImpl.class);

	@Override
	public Student fetchStudent(final Integer studentId) {
		LOGGER.trace("Enter into fetchStudent method with parameters : {}", studentId);
		final Student student = studentRepository.fetchStudent(studentId);
		LOGGER.trace("Exit from fetchStudent method with output : {}", student);
		return student;
	}

	@Override
	public Student saveStudent(final Student student) {
		LOGGER.trace("Enter into saveStudent method with parameters : {}", student);
		final Student studentResponse = studentRepository.saveStudent(student);
		LOGGER.trace("Exit from saveStudent method with output : {}", studentResponse);
		return studentResponse;
	}

	@Override
	public Student updateStudent(final Student student) {
		LOGGER.trace("Enter into updateStudent method with parameters : {}", student);
		final Student studentResponse = studentRepository.updateStudent(student);
		LOGGER.trace("Exit from updateStudent method with output : {}", studentResponse);
		return studentResponse;
	}

	@Override
	public Student deleteStudent(final Integer studentId) {
		LOGGER.trace("Enter into deleteStudent method with parameters : {}", studentId);
		final Student student = studentRepository.deleteStudent(studentId);
		LOGGER.trace("Exit from deleteStudent method with output : {}", student);
		return student;
	}

}
