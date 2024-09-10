package com.search.api.service.search;

import com.search.api.entities.Product;
import com.search.api.repository.ProductRepository;
import com.search.api.service.index.IndexService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService{
    private final IndexService indexService;
    private final ProductRepository productRepository;

    @Override
    public List<Product> search(String query) {
        String[] terms = query.toLowerCase().split("\\s+");
        Set<Long> resultIds = new HashSet<>(indexService.search(terms[0]));

        for (int i = 1; i < terms.length; i++) {
            resultIds.retainAll(indexService.search(terms[i]));
        }

        return resultIds.stream()
                .map(id -> productRepository.findById(id).orElse(null))
                .collect(Collectors.toList());
    }

    @Override
    public void index(List<Product> products) {
        products.forEach(indexService::index);
    }
}
