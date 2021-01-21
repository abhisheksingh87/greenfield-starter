package com.wellsfargo.cto.eai.starter.transactions;

import com.wellsfargo.cto.eai.starter.GreenfieldMicroservice;
import com.wellsfargo.cto.eai.starter.model.Customer;
import com.wellsfargo.cto.eai.starter.repository.CustomerRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.SessionSynchronization;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = GreenfieldMicroservice.class)
public class MongoTransactionsTemplateTest {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private MongoTransactionManager mongoTransactionManager;

    @BeforeEach
    public void testSetup() {
        if (!mongoTemplate.collectionExists(Customer.class)) {
            mongoTemplate.createCollection(Customer.class);
        }
    }

    @AfterEach
    public void tearDown() {
       mongoTemplate.dropCollection(Customer.class);
    }

    @Test
    public void testTransactionTemplate() {
        mongoTemplate.setSessionSynchronization(SessionSynchronization.ALWAYS);

        Customer customer1 = Customer.builder().firstName("mark")
                .lastName("smith")
                .phoneNumber("7589756789")
                .build();

        Customer customer2 = Customer.builder().firstName("ron")
                .lastName("smith")
                .phoneNumber("7589756789")
                .build();
        TransactionTemplate transactionTemplate = new TransactionTemplate(mongoTransactionManager);
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                mongoTemplate.insert(customer1);
                mongoTemplate.insert(customer2);
            }

            ;
        });

        Query query = new Query().addCriteria(Criteria.where("firstName").is("mark"));
        List<Customer> customers = mongoTemplate.find(query, Customer.class);

        Assertions.assertThat(customers.size()).isEqualTo(1);
    }
}
