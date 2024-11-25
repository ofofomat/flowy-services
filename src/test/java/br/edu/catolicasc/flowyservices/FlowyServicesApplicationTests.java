package br.edu.catolicasc.flowyservices;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class FlowyServicesApplicationTests {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void contextLoads() {
        assertNotNull(applicationContext, "The application context should have loaded.");
    }

    @Test
    void testBeanPresence() {
        boolean beanPresent = applicationContext.containsBean("someBeanName");
        assertTrue(beanPresent, "The bean should be present in the application context.");
    }
}
