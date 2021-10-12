package com.itrexgroup.service.impl;

import com.itrexgroup.model.Product;
import com.itrexgroup.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ProductServiceImpl implements ProductService {
    private static final AtomicInteger PRODUCT_ID = new AtomicInteger();
    private static final Map<Integer, Product> products = new HashMap<>();

    @Override
    public void addProduct(Product product) {
        products.put(PRODUCT_ID.incrementAndGet() - 1, product);
    }

    @Override
    public void deleteProduct(int id) {
        products.remove(id);
    }
}
