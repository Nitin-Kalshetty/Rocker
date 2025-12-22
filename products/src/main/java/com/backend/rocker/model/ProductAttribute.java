package com.backend.rocker.model;

import java.io.Serializable;

public class ProductAttribute implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Long productId;
    private String attrKey;
    private String attrValue;
}
