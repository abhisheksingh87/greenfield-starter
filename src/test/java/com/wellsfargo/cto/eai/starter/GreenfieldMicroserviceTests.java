package com.wellsfargo.cto.eai.starter;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class GreenfieldMicroserviceTests {

	@Autowired
	private Environment environment;

	@Test
	void contextLoads() {

		assertEquals(environment.getProperty("app-config.url"),
					    "http://yahoo.com");
	}

}
