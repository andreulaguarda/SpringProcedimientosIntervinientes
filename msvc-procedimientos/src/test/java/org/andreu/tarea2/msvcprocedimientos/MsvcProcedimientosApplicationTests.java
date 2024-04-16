package org.andreu.tarea2.msvcprocedimientos;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MsvcProcedimientosApplicationTests {

    @Autowired
    private ApplicationContext ctx;

    @Test
    void contextLoads() {
        assertNotNull(ctx);
    }

    @Test
    void testMain() {
        MsvcProcedimientosApplication.main(new String[] {});
        assertTrue(true);
    }
}