package com.fullcycle.admin.infrastructure;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class MainTest {

    @Test
    public void testMain() {
        Main.main(new String[]{});
    }
}
