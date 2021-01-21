package com.wellsfargo.cto.eai.starter.transactions;

import com.wellsfargo.cto.eai.starter.GreenfieldMicroservice;
import com.wellsfargo.cto.eai.starter.model.Customer;
import com.wellsfargo.cto.eai.starter.repository.CustomerRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.MongoTransactionException;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = GreenfieldMicroservice.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MongoTransactionsTest {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private MongoTransactionManager mongoTransactionManager;

    @Test
    @Transactional
    @Order(2)
    public void testMongoDBTransactions() {

        Customer customer1 = Customer.builder().firstName("mark")
                .lastName("smith")
                .phoneNumber("7589756789")
                .build();

        Customer customer2 = Customer.builder().firstName("ron")
                .lastName("smith")
                .phoneNumber("7589756789")
                .build();

        customerRepository.save(customer1);
        customerRepository.save(customer2);
        Query query = new Query().addCriteria(Criteria.where("firstName").is("mark"));
        List<Customer> customers = mongoTemplate.find(query, Customer.class);
        Assertions.assertThat(customers.size()).isEqualTo(1);
    }

    @Test
    @Transactional
    @Order(3)
    public void testListCollectionMongoDBTransaction() {
        Customer customer1 = Customer.builder().firstName("mark")
                .lastName("smith")
                .phoneNumber("7589756789")
                .build();

        Customer customer2 = Customer.builder().firstName("ron")
                .lastName("smith")
                .phoneNumber("7589756789")
                .build();

        assertThrows(MongoTransactionException.class, ()-> {
            if (mongoTemplate.collectionExists(Customer.class)) {
                customerRepository.save(customer1);
                customerRepository.save(customer2);
            }
        });
    }

    // ==== Using test instead of before and after due to @transactional doesn't allow list collection

    @Test
    @Order(1)
    public void testSetup() {
        if (!mongoTemplate.collectionExists(Customer.class)) {
            mongoTemplate.createCollection(Customer.class);
        }
    }

    @Test
    @Order(4)
    public void tearDown() {
        mongoTemplate.dropCollection(Customer.class);
    }
}
