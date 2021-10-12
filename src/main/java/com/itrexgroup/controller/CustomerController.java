package com.itrexgroup.controller;

import com.itrexgroup.model.Customer;
import com.itrexgroup.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NavigableMap;

@RestController
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping(value = "/customers")
    public ResponseEntity<Customer> createCustomer(@RequestParam(value = "customer") Customer customer,
                                                   @RequestParam(value = "warehouseDistanceMap") NavigableMap<Long, Integer> warehouseDistanceMap) {
        customerService.createCustomer(customer, warehouseDistanceMap);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/customers")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return customerService.getAll() != null &&  !customerService.getAll().isEmpty()
                ? new ResponseEntity<>(customerService.getAll(), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/customers/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable(name = "id") int id) {
        return customerService.getCustomer(id) != null
                ? new ResponseEntity<>(customerService.getCustomer(id), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/customers/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable(name = "id") int id, @RequestBody Customer customer) {
     return customerService.updateCustomer(customer, id)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/customers/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable(name = "id") int id) {
       return customerService.deleteCustomer(id)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
