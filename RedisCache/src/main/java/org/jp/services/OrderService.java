package org.jp.services;

import java.util.Date;
import java.util.UUID;
import java.util.stream.LongStream;

import org.jp.models.Customer;
import org.jp.models.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import lombok.extern.java.Log;

@Service
@Log
public class OrderService {

	@Autowired
	private RedisTemplate<String, Order> redisTemplate;

	public void saveOrders() {
		LongStream.range(1, 10).parallel().forEach(num -> {
			Order order = Order.builder().orderId(num).orderDate(new Date())
					.customer(Customer.builder().customerId(UUID.randomUUID().getLeastSignificantBits())
							.customerName("cust ".concat(String.valueOf(num))).build())
					.build();
			redisTemplate.opsForHash().put("order", order.getOrderId(), order);
		});
		log.info("orders saved successfully");
	}

	@Cacheable(cacheNames = { "orders" })
	public Order fetchOrder(String Id) {
		return (Order) redisTemplate.opsForHash().get("order", Id);
	}

}
