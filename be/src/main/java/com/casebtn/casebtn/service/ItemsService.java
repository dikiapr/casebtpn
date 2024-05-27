package com.casebtn.casebtn.service;

import com.casebtn.casebtn.dto.response.CustomersResp;
import com.casebtn.casebtn.dto.response.ItemsResp;
import com.casebtn.casebtn.exception.DataNotFoundException;
import com.casebtn.casebtn.model.Customers;
import com.casebtn.casebtn.model.Items;
import com.casebtn.casebtn.repository.ItemsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemsService {

    @Autowired
    private ItemsRepository itemsRepository;
    @Autowired
    private ModelMapper modelMapper;

    public List<Items> findAll(){
        return itemsRepository.findAll();
    }

    public Items save(Items items){
        return itemsRepository.save(items);
    }

    public Items findOne(Long id) throws DataNotFoundException {
        return itemsRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Item not found"));
    }

    public ItemsResp findOneItem(Long id) throws DataNotFoundException{
        Items items = itemsRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Item not found"));

        return modelMapper.map(items, ItemsResp.class);
    }

    public void removeOne(Long id){
        itemsRepository.deleteById(id);
    }
}












