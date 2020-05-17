package org.jp.models;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer implements Serializable {

	private static final long serialVersionUID = -3734377598471409344L;
	private Long customerId;
	private String customerName;

}
