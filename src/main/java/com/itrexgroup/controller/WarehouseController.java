package com.itrexgroup.controller;

import com.itrexgroup.model.Warehouse;
import com.itrexgroup.service.WarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class WarehouseController {
    private final WarehouseService warehouseService;

    @PostMapping(value = "/warehouses")
    public ResponseEntity<?> addWarehouse(@RequestBody Warehouse warehouse) {
        warehouseService.addWarehouse(warehouse);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/warehouses/{id}")
    public ResponseEntity<Warehouse> getWarehouse(@PathVariable(name = "id") int id) {
        return warehouseService.getWarehouse(id) != null
                ? new ResponseEntity<>(warehouseService.getWarehouse(id), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/warehouses/{id}")
    public ResponseEntity<?> addProductToWarehouse(@RequestBody Warehouse warehouse, int productId, int quantity) {
        warehouseService.addProduct(warehouse, productId, quantity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/warehouses/{id}")
    public ResponseEntity<?> deleteProductFromWarehouse(@RequestBody Warehouse warehouse, int productId, int quantity) {
        warehouseService.deleteProduct(warehouse, productId, quantity);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
