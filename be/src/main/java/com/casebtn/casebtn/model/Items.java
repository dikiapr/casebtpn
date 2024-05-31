package com.casebtn.casebtn.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Items implements Serializable {

    @Serial
    private static final long serialVersionUID = -4371240142344332621L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;

    private String itemName;

    private String itemCode;

    private Integer stock;

    private Integer price;

    private Boolean isAvailable;

    private Date lastReStock;

}



















