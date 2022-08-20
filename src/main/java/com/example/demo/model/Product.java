package com.example.demo.model;

import javax.persistence.*;
import javax.validation.constraints.*;

import com.example.demo.model.ProductDetail;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "PRODUCT_TBL")
public class Product {

    @Id
    @GeneratedValue
    private int id;

    @NotEmpty(message = "Thiếu username")
    private String name;

    @Min(value=1, message="must be equal or greater than 1")
    private int quantity;

    @Min(value=1, message="must be equal or greater than 1")
    private double price;

    @OneToOne(mappedBy = "product", cascade =CascadeType.ALL)
    @JoinColumn(name = "PRODUCT_DETAIL_ID", referencedColumnName = "id")
    private ProductDetail productDetail;
}