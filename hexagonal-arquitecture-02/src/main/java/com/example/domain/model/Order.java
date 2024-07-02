package com.example.domain.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String customerId;
	private LocalDateTime creationDate;
	private OrderStatus status;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<OrderItem> items = new ArrayList<>();
	
	public Order(Long id, String customerId, OrderStatus status, List<OrderItem> items) {
		super();
		this.id = id;
		this.customerId = customerId;
		this.status = status;
		this.items = items;
	}
	
	public Order(LocalDateTime creationDate, OrderStatus status) {
		this.setCreationDate(creationDate);
		this.status = status;
	}
	
	public void addItem(OrderItem item) {
        this.items.add(item);
	}
	
	public void removeItem(OrderItem item) {
        this.items.add(item);
	}
	
	public void updateStatus(OrderStatus status) {
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public List<OrderItem> getItems() {
		return items;
	}

	public void setItems(List<OrderItem> items) {
		this.items = items;
	}
	
}
