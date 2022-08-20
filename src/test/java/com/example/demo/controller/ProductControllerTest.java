package com.example.demo.controller;

import com.example.demo.model.ProductDetail;
import org.junit.Test;
import org.mockito.Mock;
import org.junit.Before;
import org.mockito.Mockito;
import org.mockito.InjectMocks;
import org.junit.runner.RunWith;
import com.example.demo.model.Product;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.mockito.junit.MockitoJUnitRunner;
import com.example.demo.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class ProductControllerTest {

    private MockMvc mockMvc;
    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectWriter = objectMapper.writer();

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    ProductDetail productDetail = new ProductDetail();
    Product REACORD_1 = new Product(1, "san pham 1", 23, 34222,productDetail);
    Product REACORD_2 = new Product(2, "san pham 2", 23, 34222,productDetail);
    Product REACORD_3 = new Product(3, "san pham 3", 23, 34222,productDetail);

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    public void getAllRecords_susscess() throws Exception {
        List<Product> records = new ArrayList<>(Arrays.asList(REACORD_1, REACORD_2, REACORD_3));

        Mockito.when(productService.getProducts()).thenReturn(records);

        mockMvc.perform(MockMvcRequestBuilders.get("/products")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].name").value("san pham 1"));
    }

}
