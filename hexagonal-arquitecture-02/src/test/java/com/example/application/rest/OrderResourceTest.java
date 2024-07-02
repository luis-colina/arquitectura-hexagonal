package com.example.application.rest;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import com.example.domain.model.Order;
import com.example.domain.model.OrderItem;
import com.example.domain.model.OrderStatus;

import io.quarkus.test.junit.QuarkusTest;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class OrderResourceTest {
	
	@Test
	public void testCreateOrder() {
		Order order = new Order(LocalDateTime.now(), OrderStatus.PENDING);
		given()
			.contentType("application/json")
			.body(order)
			.when().post("/orders")
			.then()
				.statusCode(201)
				.body("status", is(OrderStatus.PENDING.toString()));
		
	}
	
	@Test
	public void testAddItemToOrder() {
        Order order = new Order(LocalDateTime.now(), OrderStatus.PENDING);
        given()
            .contentType("application/json")
            .body(order)
            .when().post("/orders")
            .then()
                .statusCode(201);
        
        OrderItem item = new OrderItem("product1", 2, new BigDecimal(50.0));
        given()
            .contentType("application/json")
            .body(item)
            .when().post("/orders/{orderId}/items", order.getId())
            .then()
                .statusCode(200);
    }
	
	@Test
	public void testUpdateOrderStatus() {
		Order order = new Order(LocalDateTime.now(), OrderStatus.PENDING);
		given()
			.contentType("application/json")
			.body(order)
			.when().post("/orders")
			.then()
				.statusCode(201);

		given()
			.contentType("application/json")
			.body(OrderStatus.DELIVERED)
			.when().put("/orders/{orderId}/status", order.getId())
			.then()
				.statusCode(200)
				.body("status", is(OrderStatus.DELIVERED.toString()));
	}

}
