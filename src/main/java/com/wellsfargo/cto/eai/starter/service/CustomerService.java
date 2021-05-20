package com.wellsfargo.cto.eai.starter.service;

import com.wellsfargo.cto.eai.starter.model.Customer;
import com.wellsfargo.cto.eai.starter.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }
}