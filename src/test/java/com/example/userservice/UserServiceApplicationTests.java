package com.example.userservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")  // Use the test profile
class UserServiceApplicationTests {

	@Test
	void contextLoads() {
	}

}
