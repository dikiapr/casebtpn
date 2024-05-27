package com.casebtn.casebtn.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.Date;

@Data
public class CustomersReq {

    @NotEmpty(message = "Customer name is required")
    private String customerName;

    @NotEmpty(message = "Customer address is required")
    private String customerAddress;

    @NotEmpty(message = "Customer code is required")
    private String customerCode;

    @NotEmpty(message = "Customer phone is required")
    private String customerPhone;

    private Boolean isActive;

    private Date lastOrderDate;

    private String pic;
}
