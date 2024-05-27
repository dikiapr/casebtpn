package com.casebtn.casebtn.service;

import com.casebtn.casebtn.dto.response.CustomersResp;
import com.casebtn.casebtn.exception.DataNotFoundException;
import com.casebtn.casebtn.model.Customers;
import com.casebtn.casebtn.repository.CustomersRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomersService {
    @Autowired
    private CustomersRepository customersRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Customers save(Customers customers){
        return customersRepository.save(customers);
    }

    public List<Customers> findAll(){
        return customersRepository.findAll();
    }

    public Customers findOne(Long id) throws DataNotFoundException{
       return customersRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Customer not found"));
    }

    public CustomersResp findOneCustomer(Long id) throws DataNotFoundException{
        Customers customers = customersRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Customer not found"));

        return modelMapper.map(customers, CustomersResp.class);
    }

    public void removeOne(Long id){
        customersRepository.deleteById(id);
    }
}
