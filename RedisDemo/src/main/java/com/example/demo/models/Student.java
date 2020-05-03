package com.example.demo.models;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {

	private Integer studentId;
	private String studentName;
	private String studentAge;
	private Date studentDOB;
	
}
