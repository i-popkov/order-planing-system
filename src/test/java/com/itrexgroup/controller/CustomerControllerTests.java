package com.itrexgroup.controller;

import com.itrexgroup.config.BasicTest;
import com.itrexgroup.model.Customer;
import com.itrexgroup.service.CustomerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.NavigableMap;
import java.util.TreeMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerTests extends BasicTest {
    @Autowired
    CustomerController controller;
    @Autowired
    CustomerService customerService;
    @Autowired
    private MockMvc mvc;
    private String uri = "/customers";

    @Test
    public void shouldReturnCustomersList() throws Exception {
        NavigableMap<Long, Integer> warehouseDistanceMap = new TreeMap<>();
        warehouseDistanceMap.put(10L, 5);
        warehouseDistanceMap.put(5L, 3);
        Customer customer = new Customer();
        customer.setWarehouseDistanceMap(warehouseDistanceMap);
        controller.createCustomer(customer, warehouseDistanceMap);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertThat(status).isEqualTo(200);
    }

    @Test
    public void shouldCreateUser() {
        NavigableMap<Long, Integer> warehouseDistanceMap = new TreeMap<>();
        warehouseDistanceMap.put(10L, 5);
        warehouseDistanceMap.put(5L, 3);
        assertThat(controller.createCustomer(new Customer(), warehouseDistanceMap)).isEqualTo(new ResponseEntity<>(HttpStatus.CREATED));
    }

    @Test
    public void whenValidId_ThenCustomerShouldBeFound() throws Exception {
        NavigableMap<Long, Integer> warehouseDistanceMap = new TreeMap<>();
        warehouseDistanceMap.put(10L, 5);
        warehouseDistanceMap.put(5L, 3);
        controller.createCustomer(new Customer(), warehouseDistanceMap);
        warehouseDistanceMap.put(15L, 20);
        controller.createCustomer(new Customer(), warehouseDistanceMap);
        mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk());
    }

    @Test
    public void whenValidId_ThenShouldUpdateCustomer() {
        NavigableMap<Long, Integer> warehouseDistanceMap = new TreeMap<>();
        warehouseDistanceMap.put(10L, 5);
        warehouseDistanceMap.put(5L, 3);
        Customer temp = controller.createCustomer(new Customer(), warehouseDistanceMap).getBody();
        Customer newCustomer = new Customer();
        controller.updateCustomer(0, newCustomer);
        assertThat(controller.getCustomer(0).getBody()).isNotEqualTo(temp);
    }

    @Test
    public void whenValidId_ThenShouldDeleteCustomer(){
        NavigableMap<Long, Integer> warehouseDistanceMap = new TreeMap<>();
        warehouseDistanceMap.put(10L, 5);
        warehouseDistanceMap.put(5L, 3);
        controller.createCustomer(new Customer(), warehouseDistanceMap).getBody();
        assertThat(controller.getCustomer(0)).isNotEqualTo(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        controller.deleteCustomer(0);
        assertThat(controller.getCustomer(0)).isEqualTo(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


}
