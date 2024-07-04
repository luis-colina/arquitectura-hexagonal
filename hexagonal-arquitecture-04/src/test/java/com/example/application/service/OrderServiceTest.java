package com.example.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import com.example.domain.model.Order;
import com.example.domain.model.OrderItem;
import com.example.domain.model.OrderStatus;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

@QuarkusTest
class OrderServiceTest {

	@Inject
	OrderService orderService;
	
	@Test
	void testCreateOrder() {
		Order order = new Order(LocalDateTime.now(), OrderStatus.PENDING);
		orderService.save(order);
		assertNotNull(order.getId());
	}
	
	@Test
	void testAddItemToOrder() {
		Order order = new Order(LocalDateTime.now(), OrderStatus.PENDING);
		orderService.save(order);
		OrderItem item = new OrderItem("product1", 2, new BigDecimal(50.0));
		orderService.addItemtoOrder(order.getId(), item);
		assertEquals(1, orderService.findById(order.getId()).get().getItems().size());
	}
	
	@Test
	void testUpdateOrderStatus() {
		Order order = new Order(LocalDateTime.now(), OrderStatus.PENDING);
		orderService.save(order);
		orderService.updateOrderStatus(order.getId(), OrderStatus.DELIVERED);
		assertEquals(OrderStatus.DELIVERED, orderService.findById(order.getId()).get().getStatus());
	}
}
