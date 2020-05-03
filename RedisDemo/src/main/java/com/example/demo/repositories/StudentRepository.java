package com.example.demo.repositories;

import com.example.demo.models.Student;

public interface StudentRepository {

	Student fetchStudent(Integer studentId);

	Student saveStudent(Student student);

	Student updateStudent(Student student);

	Student deleteStudent(Integer studentId);

}
