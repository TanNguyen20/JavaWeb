package com.example.demo.controller;
import com.example.demo.model.ProductDetail;
import com.example.demo.service.ProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600) // fix cors when difference domain or port
@RestController
@Tag(name = "Product detail")
public class ProductDetailController {

    @Autowired
    private ProductDetailService service;
    @GetMapping("/productDetails")
    public List<ProductDetail> findAllProducts() {
        return service.getProductDetail();
    }

}