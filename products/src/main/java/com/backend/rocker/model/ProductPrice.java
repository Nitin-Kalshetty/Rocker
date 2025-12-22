package com.backend.rocker.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class ProductPrice implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long productId;
    private BigDecimal price;
    private String currency;
    private BigDecimal discountPrice;
}
