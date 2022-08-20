package com.example.demo.service;

import com.example.demo.model.ProductDetail;
import com.example.demo.repository.ProductDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductDetailService {
    @Autowired
    private ProductDetailRepository repository;
    public List<ProductDetail> getProductDetail() {
        return repository.findAll();
    }
}