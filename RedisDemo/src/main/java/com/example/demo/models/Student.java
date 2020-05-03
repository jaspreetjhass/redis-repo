package com.example.demo.models;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student implements Serializable {

	private static final long serialVersionUID = -578458768232697565L;
	private Integer studentId;
	private String studentName;
	private String studentAge;
	private Date studentDOB;

}
