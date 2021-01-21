package com.wellsfargo.cto.eai.starter.repository;

import com.wellsfargo.cto.eai.starter.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String> {
}
