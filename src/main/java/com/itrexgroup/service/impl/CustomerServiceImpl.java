package com.itrexgroup.service.impl;

import com.itrexgroup.model.Customer;
import com.itrexgroup.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final static Map<Integer, Customer> customers = new HashMap<>();
    private AtomicInteger CUSTOMER_ID = new AtomicInteger();

    @Override
    public void createCustomer(Customer customer, NavigableMap<Long, Integer> warehouseDistanceMap) {
        customer.setId(CUSTOMER_ID.incrementAndGet() - 1);
        customer.setWarehouseDistanceMap(warehouseDistanceMap);
        customers.put(customer.getId(), customer);
    }

    @Override
    public Customer getCustomer(int id) {
        return customers.get(id);
    }

    @Override
    public List<Customer> getAll() {
        return new ArrayList<>(customers.values());
    }

    @Override
    public boolean updateCustomer(Customer customer, int id) {
        return customers.put(id, customer) != null;
    }

    @Override
    public boolean deleteCustomer(int id) {
        return customers.remove(id) != null;
    }
}
