package com.example.domain.service;

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
		orderService.createOrder(order);
		assertNotNull(order.getId());
	}
	
	@Test
	void testAddItemToOrder() {
		Order order = new Order(LocalDateTime.now(), OrderStatus.PENDING);
		orderService.createOrder(order);
		OrderItem item = new OrderItem("product1", 2, new BigDecimal(50.0));
		orderService.addItemtoOrder(order.getId(), item);
		assertEquals(1, orderService.findOrderById(order.getId()).getItems().size());
	}
	
	@Test
	void testUpdateOrderStatus() {
		Order order = new Order(LocalDateTime.now(), OrderStatus.PENDING);
		orderService.createOrder(order);
		orderService.updateOrderStatus(order.getId(), OrderStatus.DELIVERED);
		assertEquals(OrderStatus.DELIVERED, orderService.findOrderById(order.getId()).getStatus());
	}
}
