package com.backend.rocker.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "product_image", schema = "product_service")
public class ProductImage implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "is_primary")
    private Boolean isPrimary;
}
