package com.casebtn.casebtn.controller;

import com.casebtn.casebtn.model.Customers;
import com.casebtn.casebtn.service.CustomersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/customers")
public class CustomersController {

    @Autowired
    private CustomersService customersService;

    @GetMapping
    public ResponseEntity<List<Customers>> getAllCustomers(){
        return ResponseEntity.ok(customersService.getAllCustomers());
    }
}
