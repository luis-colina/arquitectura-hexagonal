package com.example.application.service;

import java.util.List;
import java.util.Optional;

import com.example.application.port.OrderRepository;
import com.example.domain.model.Order;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class OrderService {
	
	@Inject
	private OrderRepository orderRepository;

	public OrderService(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	public Order save(Order order) {
		return orderRepository.save(order);
	}

	public Optional<Order> findById(Long id) {
		return orderRepository.findById(id);
	}

	public List<Order> findAll() {
		return orderRepository.findAll();
	}

	public void deleteById(Long id) {
		orderRepository.deleteById(id);
	}
	
}