package com.wellsfargo.cto.eai.starter.model;

import lombok.*;
import org.springframework.data.annotation.Id;

@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Customer {

    @Id
    private String customerId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
}
