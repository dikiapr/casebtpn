package com.casebtn.casebtn.controller;

import com.casebtn.casebtn.dto.request.CustomersReq;
import com.casebtn.casebtn.dto.response.ResponseData;
import com.casebtn.casebtn.exception.DataNotFoundException;
import com.casebtn.casebtn.model.Customers;
import com.casebtn.casebtn.service.CustomersService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("customers")
public class CustomersController {

    @Autowired
    private CustomersService customersService;

    @Autowired
    private ModelMapper modelMapper;
    public Date timeNow = new Date();

    @PostMapping
    public ResponseEntity<ResponseData<Customers>> create (@Valid @RequestBody CustomersReq req, Errors errors){
        ResponseData<Customers> responseData = new ResponseData<>();
        if(errors.hasErrors()){
            for(ObjectError error : errors.getAllErrors()){
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        Customers customers = modelMapper.map(req,Customers.class);
        customers.setIsActive(true);
        customers.setLastOrderDate(timeNow);
        customers.setPic("foto 1");

        responseData.setStatus(true);
        responseData.getMessages().add("Customer created successfully");
        responseData.setPayload(customersService.save(customers));
        return ResponseEntity.status(HttpStatus.CREATED).body(responseData);
    }

    @GetMapping
    public ResponseEntity<List<Customers>> findAll(){
        return ResponseEntity.ok(customersService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customers> findOne(@PathVariable("id") Long id) throws DataNotFoundException {
        Customers result = customersService.findOne(id);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseData<Customers>> update (@PathVariable("id") Long id, @Valid @RequestBody CustomersReq req, Errors errors) throws DataNotFoundException{
        ResponseData<Customers> responseData = new ResponseData<>();
        if(errors.hasErrors()){
            for(ObjectError error : errors.getAllErrors()){
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        Customers existingCustomer = customersService.findOne(id);
        if (existingCustomer == null) {
            responseData.setStatus(false);
            responseData.getMessages().add("Customer not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
        }

        modelMapper.map(req,existingCustomer);
        existingCustomer.setIsActive(true);
        existingCustomer.setLastOrderDate(timeNow);
        existingCustomer.setPic("foto 1");

        responseData.setStatus(true);
        responseData.getMessages().add("Customer updated successfully");
        responseData.setPayload(customersService.save(existingCustomer));
        return ResponseEntity.ok(responseData);
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<Map<String, String>> deleteOne(@PathVariable("id") Long id) {
        Map<String, String> response = new HashMap<>();
        try {
            Customers existingCustomer = customersService.findOne(id);
            customersService.removeOne(id);
            response.put("message", "Customer deleted successfully");
            return ResponseEntity.ok(response);
        } catch (DataNotFoundException e) {
            response.put("message", "Customer not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}


















