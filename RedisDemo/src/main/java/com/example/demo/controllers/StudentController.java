package com.example.demo.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Student;
import com.example.demo.services.StudentService;

@RestController
@RequestMapping("redisApi")
public class StudentController {

	@Autowired
	private StudentService studentService;
	private static final Logger LOGGER = LoggerFactory.getLogger(StudentController.class);

	@GetMapping("/students/{studentId}")
	public Student fetchStudentDetails(@PathVariable("studentId") final Integer studentId) {
		LOGGER.trace("Enter into fetchStudentDetails method with parameters : {}", studentId);
		final Student student = studentService.fetchStudent(studentId);
		LOGGER.trace("Exit from fetchStudentDetails method with output : {}", student);
		return student;
	}

	@PostMapping("/students")
	public Student persistStudent(@RequestBody final Student student) {
		LOGGER.trace("Enter into persistStudent method with parameters : {}", student);
		final Student studentResponse = studentService.saveStudent(student);
		LOGGER.trace("Exit from persistStudent method with output : {}", studentResponse);
		return studentResponse;
	}

	@PutMapping("/students/{studentId}")
	public Student modifyStudent(@PathVariable("studentId") final Integer studentId,
			@RequestBody final Student student) {
		LOGGER.trace("Enter into modifyStudent method with parameters : {}", student);
		final Student studentResponse = studentService.updateStudent(student);
		LOGGER.trace("Exit from modifyStudent method with output : {}", studentResponse);
		return studentResponse;
	}

	@DeleteMapping("/students/{studentId}")
	public Student removeStudent(@PathVariable("studentId") final Integer studentId) {
		LOGGER.trace("Enter into removeStudent method with parameters : {}", studentId);
		final Student studentResponse = studentService.deleteStudent(studentId);
		LOGGER.trace("Exit from removeStudent method with output : {}", studentResponse);
		return studentResponse;
	}

}
