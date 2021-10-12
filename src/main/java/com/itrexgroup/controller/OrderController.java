package com.itrexgroup.controller;

import com.itrexgroup.model.Order;
import com.itrexgroup.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping(value = "/orders")
    public ResponseEntity<?> createOrder(@RequestParam(value = "order") Order order) {
        orderService.createOrder(order);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/orders/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable(name = "id") int id) {
        return orderService.removeOrder(id)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @GetMapping(value = "/orders")
    public ResponseEntity<List<Order>> getAllOrders() {
        return orderService.getAllOrders() != null &&  !orderService.getAllOrders().isEmpty()
                ? new ResponseEntity<>(orderService.getAllOrders(), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/orders/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable(name = "id") int id) {
        return orderService.getOrder(id) != null
                ? new ResponseEntity<>(orderService.getOrder(id), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/orders/customer/{id}")
    public ResponseEntity<Order> getOrderByCustomerId(@PathVariable(name = "id") int id) {
        return orderService.getOrder(id) != null
                ? new ResponseEntity<>(orderService.getOrder(id), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
