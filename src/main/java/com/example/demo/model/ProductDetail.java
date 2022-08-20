package com.example.demo.model;

import javax.persistence.*;
import com.example.demo.model.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;


@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PRODUCT_DETAIL")
public class ProductDetail {
    @Id
    @GeneratedValue
    private int id;

    private String expirationDate;
    private String address;

    @OneToOne
    @JsonIgnore
    @ToString.Exclude
    private Product product;
}
