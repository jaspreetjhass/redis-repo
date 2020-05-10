package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminToolObject {

	private String rosettaStone;
	private String requestType;
	private Boolean runningStatus;
	private Boolean stopTriggered;
	private Long totalRecords;
	private Long processed;
	private Long inProgress;
	private Long notStarted;

}
