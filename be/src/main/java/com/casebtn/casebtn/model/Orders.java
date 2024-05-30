package com.casebtn.casebtn.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
public class Orders implements Serializable {

    @Serial
    private static final long serialVersionUID = -2579171482441386867L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    private String orderCode;

    private Date orderDate;

    private Integer totalPrice;

    private Integer quantity;

    private Long customerId;
    private Long itemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customerId", insertable = false, updatable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Customers customers;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "itemId", insertable = false, updatable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Items items;
}















