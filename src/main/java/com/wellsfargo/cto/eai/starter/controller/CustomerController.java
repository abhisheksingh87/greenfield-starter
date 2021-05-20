package com.wellsfargo.cto.eai.starter.controller;

import com.wellsfargo.cto.eai.starter.model.Customer;
import com.wellsfargo.cto.eai.starter.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/all")
    public ResponseEntity<List<Customer>> getCustomers()  {
        return ResponseEntity.ok(customerService.getCustomers());
    }

    @PostMapping("/save")
    public void saveCustomer(@RequestBody Customer customer)  {
         customerService.saveCustomer(customer);
    }
}
