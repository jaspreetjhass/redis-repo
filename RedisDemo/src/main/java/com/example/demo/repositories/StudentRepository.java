package com.example.demo.repositories;

public interface StudentRepository {

	String fetchStudent(Integer studentId);

	String saveStudent(String student, Integer StudentId);

	String updateStudent(String student, Integer studentId);

	String deleteStudent(Integer studentId);

}
