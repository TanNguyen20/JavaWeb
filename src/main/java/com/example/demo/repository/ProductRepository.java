package com.example.demo.repository;

import com.example.demo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    Product findByName(String name);
//    name table Product in line 3
    @Query("SELECT p FROM Product p WHERE p.name LIKE %:name%")
    List<Product> findByNameLike(@Param("name") String name);
}