package com.example.demo.models;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppErrorResponse {

	private String status;
	private String message;
	private String path;
	private LocalDateTime localDateTime;

}
