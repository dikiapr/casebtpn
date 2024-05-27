package com.casebtn.casebtn.controller;

import com.casebtn.casebtn.dto.request.CustomersReq;
import com.casebtn.casebtn.dto.request.ItemsReq;
import com.casebtn.casebtn.dto.response.CustomersResp;
import com.casebtn.casebtn.dto.response.ItemsResp;
import com.casebtn.casebtn.dto.response.ResponseData;
import com.casebtn.casebtn.exception.DataNotFoundException;
import com.casebtn.casebtn.model.Customers;
import com.casebtn.casebtn.model.Items;
import com.casebtn.casebtn.service.ItemsService;
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
@RequestMapping("items")
public class ItemsController {

    @Autowired
    private ItemsService itemsService;

    @Autowired
    private ModelMapper modelMapper;

    public Date timeNow = new Date();

    @PostMapping
    public ResponseEntity<ResponseData<ItemsResp>> create (@Valid @RequestBody ItemsReq req, Errors errors){
        ResponseData<ItemsResp> responseData = new ResponseData<>();
        if(errors.hasErrors()){
            for(ObjectError error : errors.getAllErrors()){
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        Items items = modelMapper.map(req,Items.class);
        items.setIsAvailable(true);
        items.setLastReStock(timeNow);

        itemsService.save(items);
        ItemsResp itemsResp = modelMapper.map(items, ItemsResp.class);

        responseData.setStatus(true);
        responseData.getMessages().add("Item created successfully");
        responseData.setPayload(itemsResp);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseData);
    }

    @GetMapping
    public ResponseEntity<List<Items>> findAll(){
        return ResponseEntity.ok(itemsService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemsResp> findOneItem(@PathVariable("id") Long id) throws DataNotFoundException {
        ItemsResp result = itemsService.findOneItem(id);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseData<ItemsResp>> update (@PathVariable("id") Long id, @Valid @RequestBody ItemsReq req, Errors errors) throws DataNotFoundException{
        ResponseData<ItemsResp> responseData = new ResponseData<>();
        if(errors.hasErrors()){
            for(ObjectError error : errors.getAllErrors()){
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        Items existingItem = itemsService.findOne(id);
        if (existingItem == null) {
            responseData.setStatus(false);
            responseData.getMessages().add("Item not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
        }

        modelMapper.map(req,existingItem);
        existingItem.setIsAvailable(true);
        existingItem.setLastReStock(timeNow);

        responseData.setStatus(true);
        responseData.getMessages().add("Item updated successfully");

        itemsService.save(existingItem);
       ItemsResp updatedItemResp = modelMapper.map(existingItem, ItemsResp.class);

        responseData.setPayload(updatedItemResp);
        return ResponseEntity.ok(responseData);
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<Map<String, String>> deleteOne(@PathVariable("id") Long id) {
        Map<String, String> response = new HashMap<>();
        try {
            Items existingItem = itemsService.findOne(id);
            itemsService.removeOne(id);
            response.put("message", "Item deleted successfully");
            return ResponseEntity.ok(response);
        } catch (DataNotFoundException e) {
            response.put("message", "Item not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

}















