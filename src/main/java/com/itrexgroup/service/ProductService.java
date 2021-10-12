package com.itrexgroup.service;

import com.itrexgroup.model.Product;

public interface ProductService {
    void addProduct(Product product);
    void deleteProduct(int id);
}
