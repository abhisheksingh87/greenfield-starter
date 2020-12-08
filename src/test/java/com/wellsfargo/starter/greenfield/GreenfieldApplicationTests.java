package com.wellsfargo.starter.greenfield;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class GreenfieldApplicationTests {

	@Autowired
	private Environment environment;

	@Test
	void contextLoads() {

		assertEquals(environment.getProperty("app-config.url"),
					    "http://yahoo.com");
	}

}
