package com.casebtn.casebtn.dto.response;

import lombok.Data;

import java.util.Date;

@Data
public class OrdersResp {

    public Date orderDate;
    public Integer totalPrice;
    public Integer quantity;
}
