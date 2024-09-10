package com.search.api.service.product;

import com.search.api.dto.ProductDto;

import java.util.List;

public interface ProductService {
    ProductDto getProductById(Long id);
    List<ProductDto> getAllProducts();
    ProductDto createProduct(ProductDto productDto);
    ProductDto updateProduct(ProductDto productDto, Long id);
    void deleteProduct(Long id);
    List<ProductDto> searchProducts(String term);
    void indexProducts();
}
