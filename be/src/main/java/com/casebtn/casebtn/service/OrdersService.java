package com.casebtn.casebtn.service;

import com.casebtn.casebtn.dto.request.OrdersReq;
import com.casebtn.casebtn.dto.response.CustomersResp;
import com.casebtn.casebtn.dto.response.OrdersResp;
import com.casebtn.casebtn.exception.DataNotFoundException;
import com.casebtn.casebtn.model.Customers;
import com.casebtn.casebtn.model.Items;
import com.casebtn.casebtn.model.Orders;
import com.casebtn.casebtn.repository.CustomersRepository;
import com.casebtn.casebtn.repository.ItemsRepository;
import com.casebtn.casebtn.repository.OrdersRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrdersService {

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private CustomersRepository customersRepository;

    @Autowired
    private ItemsRepository itemsRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Date timeNow = new Date();

    public List<Orders> findAll(){
        return ordersRepository.findAll();
    }

    public OrdersResp createOrder(OrdersReq req) throws DataNotFoundException {
        Customers customer = customersRepository.findById(req.getCustomerId())
                .orElseThrow(() -> new DataNotFoundException("Customer not found"));
        Items item = itemsRepository.findById(req.getItemId())
                .orElseThrow(() -> new DataNotFoundException("Item not found"));

        Orders order = modelMapper.map(req,Orders.class);
        order.setCustomers(customer);
        order.setItems(item);
        order.setTotalPrice(req.getQuantity() * item.getPrice());
        order.setOrderDate(timeNow);
        order.setOrderCode("Ord-001");

        ordersRepository.save(order);

        OrdersResp result = modelMapper.map(order,OrdersResp.class);
        customer.setLastOrderDate(timeNow);

        customersRepository.save(customer);

        return result;
    }

    public Orders findOne(Long id) throws DataNotFoundException{
        return ordersRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Order not found"));
    }

    public OrdersResp updateOrder(Long id, OrdersReq req) throws DataNotFoundException {
        Orders existingOrder = ordersRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Order not found"));
        Customers customer = customersRepository.findById(req.getCustomerId())
                .orElseThrow(() -> new DataNotFoundException("Customer not found"));
        Items item = itemsRepository.findById(req.getItemId())
                .orElseThrow(() -> new DataNotFoundException("Item not found"));

        existingOrder.setCustomers(customer);
        existingOrder.setItems(item);
        existingOrder.setItemId(req.getItemId());
        existingOrder.setCustomerId(req.getCustomerId());
        existingOrder.setQuantity(req.getQuantity());
        existingOrder.setOrderCode(req.getOrderCode());
        existingOrder.setTotalPrice(req.getQuantity() * item.getPrice());

        ordersRepository.save(existingOrder);

        OrdersResp result = modelMapper.map(existingOrder,OrdersResp.class);
        customer.setLastOrderDate(timeNow);

        customersRepository.save(customer);

        return result;
    }


    public OrdersResp findOneOrder(Long id) throws DataNotFoundException{
        Orders orders = ordersRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Order not found"));

        return modelMapper.map(orders, OrdersResp.class);
    }

    public void removeOne(Long id){
       ordersRepository.deleteById(id);
    }
}
















