package com.example.demo.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@RedisHash(value = "employee")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee implements Serializable {

	private static final long serialVersionUID = 2024205997144228797L;

	@Id
	private Integer employeeId;
	private String employeeName;
	private Integer employeeAge;
	private Date employeeDOB;
	private List<String> hobbies;

}
