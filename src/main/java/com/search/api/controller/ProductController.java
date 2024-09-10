package com.search.api.controller;

import com.search.api.dto.ProductDto;
import com.search.api.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDto>> getProducts() {
        return ResponseEntity.ok().body(productService.getAllProducts());
    }

    @GetMapping("/search/{term}")
    public ResponseEntity<List<ProductDto>> searchProducts(@PathVariable String term) {
        return ResponseEntity.ok().body(productService.searchProducts(term));
    }

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
        return ResponseEntity.ok().body(productService.createProduct(productDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok().body(productService.getProductById(id));
    }

    @GetMapping("/index")
    public ResponseEntity<Void> indexProducts() {
        productService.indexProducts();
        return ResponseEntity.ok().build();
    }
}
