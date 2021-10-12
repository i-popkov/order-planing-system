package com.itrexgroup.service;

import com.itrexgroup.model.Order;

import java.util.List;

public interface OrderService {
    void createOrder(Order order);

    Order getOrder(int id);
    List<Order> getAllOrders();
    List<Order> getOrdersByCustomerId(int id);
    boolean removeOrder(int id);
}
