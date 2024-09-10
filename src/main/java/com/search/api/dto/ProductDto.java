package com.search.api.dto;

public record ProductDto(
    Long id,
    String name,
    String description,
    double price
) {
}

