package com.itrexgroup.service;

import com.itrexgroup.model.Customer;


import java.util.List;
import java.util.NavigableMap;

public interface CustomerService {
    void createCustomer(Customer customer, NavigableMap<Long, Integer> warehouseDistanceMap);
    List<Customer> getAll();
    Customer getCustomer(int id);
    boolean updateCustomer(Customer customer, int id);
    boolean deleteCustomer(int id);
}
