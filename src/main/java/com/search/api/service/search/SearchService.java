package com.search.api.service.search;

import com.search.api.entities.Product;

import java.util.List;

public interface SearchService {
    List<Product> search(String query);
    void index(List<Product> products);
}
