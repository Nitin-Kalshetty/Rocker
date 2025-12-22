package com.backend.rocker.model;

import java.io.Serializable;

public class ProductInventory implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long productId;
    private Integer quantity;
    private Integer reserved;
}
