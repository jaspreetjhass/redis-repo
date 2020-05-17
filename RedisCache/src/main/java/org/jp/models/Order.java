package org.jp.models;

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
public class Order implements Serializable {

	private static final long serialVersionUID = -988663373571001980L;
	private Long orderId;
	private Date orderDate;
	private Customer customer;
	private String paymentMode;

}
