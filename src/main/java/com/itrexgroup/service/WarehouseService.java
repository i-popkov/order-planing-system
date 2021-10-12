package com.itrexgroup.service;

import com.itrexgroup.model.Warehouse;

public interface WarehouseService {
    void addWarehouse(Warehouse warehouse);
    Warehouse getWarehouse(int id);
    void addProduct(Warehouse warehouse, int productId, int quantity);
    void deleteProduct(Warehouse warehouse, int productId, int quantity);
}
