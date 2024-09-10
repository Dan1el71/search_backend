package com.search.api.service.product;

import com.search.api.dto.ProductDto;
import com.search.api.dto.ProductMapper;
import com.search.api.entities.Product;
import com.search.api.exception.ProductNotFound;
import com.search.api.repository.ProductRepository;
import com.search.api.service.search.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final SearchService searchService;

    @Override
    public ProductDto getProductById(Long id) {
        return productRepository.findById(id)
                .map(productMapper::toDto)
                .orElseThrow(() -> new ProductNotFound("Product not found"));
    }

    @Override
    public List<ProductDto> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toDto)
                .toList();
    }

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        Product product = productMapper.toEntity(productDto);
        product = productRepository.save(product);
        return productMapper.toDto(product);
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto, Long id) {
        return productRepository.findById(id)
                .map(product -> {
                    product.setName(productDto.name());
                    product.setDescription(productDto.description());
                    product.setPrice(productDto.price());
                    product = productRepository.save(product);
                    return productMapper.toDto(product);
                })
                .orElseThrow(() -> new ProductNotFound("Product not found"));
    }

    @Override
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFound("Product not found");
        }
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductDto> searchProducts(String term) {
        List<Product> products = searchService.search(term);

        return products.stream()
                .map(productMapper::toDto)
                .toList();
    }

    @Override
    public void indexProducts() {
        List<Product> products = productRepository.findAll();
        searchService.index(products);
    }
}
