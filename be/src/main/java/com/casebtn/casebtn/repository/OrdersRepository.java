package com.casebtn.casebtn.repository;

import com.casebtn.casebtn.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
}
