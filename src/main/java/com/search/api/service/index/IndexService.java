package com.search.api.service.index;

import com.search.api.entities.Product;

import java.util.Set;

public interface IndexService {
    void index(Product product);
    Set<Long> search(String term);
}
