package org.jp.runners;

import java.util.Date;
import java.util.UUID;
import java.util.stream.LongStream;

import org.jp.models.Customer;
import org.jp.models.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import lombok.extern.java.Log;

@Component
@Log
public class OrderDataPrep implements CommandLineRunner {

	@Autowired
	private RedisTemplate<String, Order> redisTemplate;

	@Override
	public void run(String... args) throws Exception {
		log.info("Customer data prep started");
		LongStream.range(1, 200).parallel().forEach(num -> {
			redisTemplate
					.convertAndSend("order:".concat(String.valueOf(num)),
							Order.builder().orderId(num).orderDate(new Date())
									.customer(Customer.builder().customerId(UUID.randomUUID().getLeastSignificantBits())
											.customerName("Cust".concat(" ").concat(String.valueOf(num))).build())
									.build());
		});
	}

}
