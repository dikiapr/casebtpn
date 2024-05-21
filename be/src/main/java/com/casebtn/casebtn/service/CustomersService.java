package com.casebtn.casebtn.service;

import com.casebtn.casebtn.dto.response.CustomersResp;
import com.casebtn.casebtn.model.Customers;
import com.casebtn.casebtn.repository.CustomersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomersService {
    @Autowired
    private CustomersRepository customersRepository;

    public Customers createCustomer(Customers customers){
        return customersRepository.save(customers);
    }

    public List<Customers> getAllCustomers(){
        return customersRepository.findAll();
    }
}
