package com.backend.rocker.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "product_inventory", schema="product_service")
public class ProductInventory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "reserved")
    private Integer reserved;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getReserved() {
        return reserved;
    }

    public void setReserved(Integer reserved) {
        this.reserved = reserved;
    }
}
