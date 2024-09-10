package com.search.api.exception;

public class ProductNotFound extends RuntimeException {
    public ProductNotFound(String message) {
        super(message);
    }

    public ProductNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductNotFound(Throwable cause) {
        super(cause);
    }

    public ProductNotFound() {
        super();
    }

    public static ProductNotFound byId(Long id) {
        return new ProductNotFound("Product with id " + id + " not found");
    }
}
