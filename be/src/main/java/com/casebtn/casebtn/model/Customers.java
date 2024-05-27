package com.casebtn.casebtn.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customers implements Serializable {

    @Serial
    private static final long serialVersionUID = -2494694862548044328L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    private String customerName;

    private String customerAddress;

    private String customerCode;

    private String customerPhone;

    private Boolean isActive;

    private Date lastOrderDate;

    private String pic;
}
