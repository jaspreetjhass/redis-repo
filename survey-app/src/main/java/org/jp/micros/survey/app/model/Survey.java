package org.jp.micros.survey.app.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Survey implements Serializable {

	private static final long serialVersionUID = 213504135782891018L;
	private String stream;
	private Double percentage;

}
