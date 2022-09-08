package com.example.cassoviacodezadanie.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getProducts(){
        return productService.getProducts();
    }

    @PostMapping
    public void createProduct(
            @RequestParam Long customerId,
            @RequestBody Product product) {

        productService.createProduct(customerId, product);
    }

    @PutMapping(path = "/{customerId}")
    public void updateProduct(
            @PathVariable("customerId") Long customerId,
            @RequestParam(required = true) Long productId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) Double price)
    {
        productService.updateProduct(customerId, productId, name, description, price);
    }
}
