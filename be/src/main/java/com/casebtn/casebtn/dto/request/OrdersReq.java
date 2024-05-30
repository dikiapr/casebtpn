package com.casebtn.casebtn.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrdersReq {

    @NotNull(message = "Quantity is required")
    @Min(value = 0, message = "Quantity cannot be less than 0")
    private Integer quantity;

    @NotNull(message = "customerId is required")
    private Long customerId;

    @NotNull(message = "itemId is required")
    private Long itemId;

    private String orderCode;
}
