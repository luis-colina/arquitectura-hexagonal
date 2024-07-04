package com.example.application.port;

import java.util.List;
import java.util.Optional;

import com.example.domain.model.Order;

public interface OrderRepository {

	Order save(Order order);
	
	Optional<Order> findById(Long id);
	
	List<Order> findAll();
	
	void deleteById(Long id);
}
