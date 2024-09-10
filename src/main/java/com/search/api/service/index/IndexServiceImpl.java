package com.search.api.service.index;

import com.search.api.entities.Product;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class IndexServiceImpl implements IndexService {
    private final Map<String, Set<Long>> index = new HashMap<>();

    @Override
    public void index(Product product) {
        String[] terms = (product.getName() + " " + product.getDescription()).toLowerCase().split("\\s+");
        for (String term : terms) {
            index.computeIfAbsent(term, k -> new HashSet<>()).add(product.getId());
        }
    }

    @Override
    public Set<Long> search(String term) {
        return index.getOrDefault(term, Collections.emptySet());
    }
}
