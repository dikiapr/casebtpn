package com.casebtn.casebtn.dto.response;

import lombok.Data;
import java.util.Date;

@Data
public class ItemsResp {

    private String itemName;

    private String itemCode;

    private Integer stock;

    private Integer price;

    private Date lastReStock;

    private Boolean isAvailable;
}
