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

    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return this.productId;
    }

    public void setProductId(final Long productId) {
        this.productId = productId;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(final String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Boolean getPrimary() {
        return this.isPrimary;
    }

    public void setPrimary(final Boolean primary) {
        this.isPrimary = primary;
    }
}
