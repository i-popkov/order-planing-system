package com.itrexgroup.service.impl;
import com.itrexgroup.model.Order;
import com.itrexgroup.model.Warehouse;
import com.itrexgroup.service.OrderService;
import com.itrexgroup.service.WarehouseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final WarehouseService warehouseService;
    private static final Map<Integer, Order> orders = new HashMap();
    private static final AtomicInteger ORDER_ID = new AtomicInteger();
    @Override
    public void createOrder(Order order) {
        int id = ORDER_ID.incrementAndGet() - 1;
        order.setId(id);
        order.setWarehouse(getClosestWarehouse(order));
        orders.put(ORDER_ID.incrementAndGet(), order);
    }

    @Override
    public Order getOrder(int id) {
        return orders.get(id);
    }

    @Override
    public List<Order> getAllOrders() {
        return new ArrayList<>(orders.values());
    }

    @Override
    public List<Order> getOrdersByCustomerId(int id) {
        return orders.values()
                .stream()
                .filter(order -> order.getCustomer().getId() == id)
                .collect(Collectors.toList());
    }

    @Override
    public boolean removeOrder(int id) {
        return orders.remove(id) != null;
    }

    private Warehouse getClosestWarehouse(Order order) {
        NavigableMap<Long, Integer> customerWarehouses = order.getCustomer().getWarehouseDistanceMap();
        Warehouse warehouse = warehouseService.getWarehouse(customerWarehouses
                .entrySet()
                .stream()
                .filter(x-> checkIfProductAvailable(x.getValue(), order.getProduct().getId(), order.getQuantity()))
                .findFirst().get().getValue().intValue());
        return warehouse;
    }

    private boolean checkIfProductAvailable(int warehouseId, int productId, int quantity) {
        Warehouse warehouse = warehouseService.getWarehouse(warehouseId);
        return warehouse.getWarehouseSupplies().get(productId) != null && warehouse.getWarehouseSupplies().get(productId) >= quantity;
    }
}
