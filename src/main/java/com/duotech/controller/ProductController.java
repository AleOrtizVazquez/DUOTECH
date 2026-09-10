package com.duotech.controller;

import com.duotech.repository.ProductRepository;
import com.duotech.entity.Product;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/catalog")
public class ProductController {
    private final ProductRepository repository;

    public ProductController(ProductRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Product> all() {
        return repository.findByActiveTrueOrderByNameAsc();
    }

    @GetMapping("/{id}")
    public Product one(@PathVariable Long id) {
        return repository.findById(id).orElseThrow();
    }
}
