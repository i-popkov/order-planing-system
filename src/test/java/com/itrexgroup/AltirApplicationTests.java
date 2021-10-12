package com.itrexgroup;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.itrexgroup.config.BasicTest;
import com.itrexgroup.controller.CustomerController;
import com.itrexgroup.controller.OrderController;
import com.itrexgroup.model.Customer;
import com.itrexgroup.model.Order;
import com.itrexgroup.model.Product;
import com.itrexgroup.model.Warehouse;
import com.itrexgroup.service.WarehouseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.NavigableMap;
import java.util.TreeMap;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AltirApplicationTests extends BasicTest {
    @Autowired
    private OrderController orderController;
    @Autowired
    private CustomerController controller;
    @Autowired
    private WarehouseService warehouseService;


    @Test
    public void shouldReturnClosestWarehouse() throws JsonProcessingException {
        Product temp = new Product();
        NavigableMap<Long, Integer> customerDistanceMap1 = new TreeMap<>();
        customerDistanceMap1.put(20L, 0);
        customerDistanceMap1.put(13L, 1);
        customerDistanceMap1.put(15L, 2);
        Customer customer1 = new Customer();
        controller.createCustomer(customer1, customerDistanceMap1);
        Order order = new Order(customer1, temp, 10);
        for (int i = 0; i < 10; i++) {
            Warehouse warehouse = new Warehouse();
            warehouseService.addWarehouse(warehouse);
        }
        warehouseService.addProduct(warehouseService.getWarehouse(0), 0, 12);
        warehouseService.addProduct(warehouseService.getWarehouse(1), 0, 8);
        warehouseService.addProduct(warehouseService.getWarehouse(2), 0, 15);
        orderController.createOrder(order);

        assertThat(order.getWarehouse()).isEqualTo((warehouseService.getWarehouse(2)));


    }

}
