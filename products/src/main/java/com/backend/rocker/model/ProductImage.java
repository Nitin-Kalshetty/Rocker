package com.backend.rocker.model;

import java.io.Serializable;

public class ProductImage implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Long productId;
    private String imageUrl;
    private Boolean isPrimary;
}
