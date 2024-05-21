package com.casebtn.casebtn.repository;

import com.casebtn.casebtn.model.Customers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomersRepository extends JpaRepository<Customers,Long> {
}
