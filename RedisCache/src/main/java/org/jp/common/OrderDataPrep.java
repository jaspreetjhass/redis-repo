package org.jp.common;

import java.util.stream.LongStream;

import org.jp.models.Order;
import org.jp.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.extern.java.Log;

@Component
@Log
public class OrderDataPrep implements CommandLineRunner {

	@Autowired
	private OrderService orderService;

	@Override
	public void run(String... args) throws Exception {
		orderService.saveOrders();
		Thread.sleep(5000);
		LongStream.range(1, 5).forEach(num -> {
			Order order = orderService.fetchOrder("5");
			log.info("order fetched is : " + order);
			try {
				Thread.sleep(8000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		log.info("fetch task completed");
	}

}
