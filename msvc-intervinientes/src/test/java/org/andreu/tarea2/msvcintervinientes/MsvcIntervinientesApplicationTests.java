package org.andreu.tarea2.msvcintervinientes;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class MsvcIntervinientesApplicationTests {

	@Autowired
	private ApplicationContext ctx;

	@Test
	void contextLoads() {
		assertNotNull(ctx);
	}

	@Test
	void testMain() {
		MsvcIntervinientesApplication.main(new String[] {});
		assertTrue(true);
	}

}
