package com.example.adapter.out.persistence;

import java.util.List;
import java.util.Optional;

import com.example.application.port.OrderRepository;
import com.example.domain.model.Order;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class JpaOrderRepository implements OrderRepository {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
    public Order save(Order order) {
        if (order.getId() == null) {
            entityManager.persist(order);
            return order;
        } else {
            return entityManager.merge(order);
        }
    }

    @Override
    public Optional<Order> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Order.class, id));
    }

    @Override
    public List<Order> findAll() {
        return entityManager.createQuery("SELECT o FROM Order o", Order.class).getResultList();
    }

    @Override
    public void deleteById(Long id) {
        findById(id).ifPresent(entityManager::remove);
    }

}
