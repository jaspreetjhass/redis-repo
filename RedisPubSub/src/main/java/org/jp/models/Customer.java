package org.jp.models;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer implements Serializable {

	private static final long serialVersionUID = -8652905210399897991L;
	private Long customerId;
	private String customerName;

}
