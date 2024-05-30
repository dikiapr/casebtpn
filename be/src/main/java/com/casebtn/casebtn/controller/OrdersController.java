package com.casebtn.casebtn.controller;

import com.casebtn.casebtn.dto.request.OrdersReq;
import com.casebtn.casebtn.dto.response.CustomersResp;
import com.casebtn.casebtn.dto.response.OrdersResp;
import com.casebtn.casebtn.exception.DataNotFoundException;
import com.casebtn.casebtn.model.Customers;
import com.casebtn.casebtn.model.Orders;
import com.casebtn.casebtn.service.OrdersService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    @GetMapping
    public ResponseEntity<List<Orders>> findAll(){
        return ResponseEntity.ok(ordersService.findAll());
    }

    @PostMapping
    public ResponseEntity<OrdersResp> createOrder(@Valid @RequestBody OrdersReq order) throws DataNotFoundException {
        OrdersResp result = ordersService.createOrder(order);

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrdersResp> updateOrder(@PathVariable("id") Long id,
                                             @RequestBody OrdersReq req) throws DataNotFoundException {

        OrdersResp result = ordersService.updateOrder(id, req);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdersResp> findOneOrder(@PathVariable("id") Long id) throws DataNotFoundException {
        OrdersResp result = ordersService.findOneOrder(id);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<Map<String, String>> deleteOne(@PathVariable("id") Long id) {
        Map<String, String> response = new HashMap<>();
        try {
            Orders existingOrder = ordersService.findOne(id);
            ordersService.removeOne(id);
            response.put("message", "Order deleted successfully");
            return ResponseEntity.ok(response);
        } catch (DataNotFoundException e) {
            response.put("message", "Order not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
