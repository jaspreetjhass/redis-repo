package com.example.demo.services;

import com.example.demo.models.Student;

public interface StudentService {

	Student fetchStudent(Integer studentId);

	Student saveStudent(Student student);

	Student updateStudent(Student student);

	Student deleteStudent(Integer studentId);

}