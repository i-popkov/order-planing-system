package com.itrexgroup.service.impl;

import com.itrexgroup.model.Warehouse;
import com.itrexgroup.service.WarehouseService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class WarehouseServiceImpl implements WarehouseService {
    private static final Map<Integer, Warehouse> warehouses = new HashMap();
    private static final AtomicInteger WAREHOUSE_ID = new AtomicInteger();
    @Override
    public void addWarehouse(Warehouse warehouse) {
        int id = WAREHOUSE_ID.incrementAndGet() - 1;
        warehouse.setId(id);
        warehouses.put(id, warehouse);
    }

    @Override
    public Warehouse getWarehouse(int id) {
        return warehouses.get(id);
    }

    @Override
    public void addProduct(Warehouse warehouse, int productId, int quantity) {
        Map<Integer, Integer> suppliesInWarehouse = warehouse.getWarehouseSupplies() == null ? new HashMap<>() : warehouse.getWarehouseSupplies();
        Integer integer = suppliesInWarehouse.isEmpty()
                ? suppliesInWarehouse.put(productId, quantity)
                : suppliesInWarehouse.put(productId, suppliesInWarehouse.get(productId) + quantity);
        warehouse.setWarehouseSupplies(suppliesInWarehouse);
    }

    @Override
    public void deleteProduct(Warehouse warehouse, int productId, int quantity) {
        //warehouseSupplies.get(warehouseId).put(productId, warehouseSupplies.get(warehouseId).get(productId) - quantity);
    }
}
