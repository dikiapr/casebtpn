package com.casebtn.casebtn.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


import java.util.Date;

@Data
public class ItemsReq {

    @NotEmpty(message = "Item name is required")
    private String itemName;

    @NotEmpty(message = "Item code is required")
    private String itemCode;

    @NotNull(message = "Stock is required")
    @Min(value = 0, message = "Stock cannot be less than 0")
    private Integer stock;

    @NotNull(message = "Price is required")
    @Min(value = 0, message = "Price cannot be less than 0")
    private Integer price;

}
