package com.tacocloud.order;

import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;

@Profile("jpa")
public interface JpaOrderRepository
        extends OrderRepository, CrudRepository<Order, Long> {
}
