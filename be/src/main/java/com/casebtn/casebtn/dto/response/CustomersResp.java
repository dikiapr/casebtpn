package com.casebtn.casebtn.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
public class CustomersResp {

    private String customerName;
    private String customerAddress;
    private String customerCode;
    private String customerPhone;
    private Date lastOrderDate;
    private String pic;
}
