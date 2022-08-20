package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import com.example.demo.utils.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import io.swagger.v3.oas.annotations.tags.Tag;


import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600) // fix cors call api from difference domain or port
@RestController
@Tag(name = "Product")
public class ProductController {

    @Autowired
    private ProductService service;

    //-------------------------------- Start CRUD --------------------------------//

    @GetMapping("/")
    public String home() {
        Log.info("Home, info log");
        return "home";
    }

    @PostMapping("/addProduct")
    public Product addProduct(@RequestBody @Valid Product product, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            Log.error("Loi khi them san pham moi");
            throw new Exception(bindingResult.getFieldError().getDefaultMessage());
        }
        return service.saveProduct(product);
    }

    @PostMapping("/addProducts")
    public List<Product> addProducts(@RequestBody List<Product> products) {
        return service.saveProducts(products);
    }

    @GetMapping("/products")
    public List<Product> findAllProducts() {
        return service.getProducts();
    }

    @GetMapping("/productById/{id}")
    public Product findProductById(@PathVariable int id) {
        return service.getProductById(id);
    }

    @GetMapping("/product/{name}")
    public Product findProductByName(@PathVariable String name) {
        return service.getProductByName(name);
    }

    @RequestMapping(value = "/product", params = "id")
    public Product findProductByIdWithGetParams(@RequestParam int id) {
        return service.getProductById(id);
    }

    @RequestMapping(value = "/product", params = "name")
    public List<Product> findProductByNameWithGetParams(@RequestParam String name) {
        return service.getProductByNameWithRegex(name);
    }

    @PutMapping("/updateProduct")
    public Product updateProduct(@RequestBody Product product) {
        return service.updateProduct(product);
    }

    @DeleteMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable int id) {
        return service.deleteProduct(id);
    }

    //-------------------------------- End CRUD API --------------------------------//

    @GetMapping("/redirect")
    public RedirectView redirect(){
        return new RedirectView("/products");
    }

    @GetMapping("/forward")
    public ModelAndView forward(ModelMap model){
        model.addAttribute("attribute", "forwardWithForwardPrefix");
        return new ModelAndView("forward:/products",model);
    }

    //    response json data
    @RequestMapping(value = "returnJSONData",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE) // change text/plain to application/json
    public Map<String, Object> responseJSON(){
        Map<String, Object> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("results", 1);
        return map;
    }

    // response json data with status code
    @GetMapping("/returnJsonWithStatusCode")
    public ResponseEntity<HashMap<String, Object>> getStatusCode() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("results", 1);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
    }

}