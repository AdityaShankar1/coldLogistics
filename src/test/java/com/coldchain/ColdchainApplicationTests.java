package com.coldchain;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import com.coldchain.TestcontainersConfiguration;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class ColdchainApplicationTests {

    @Test
    void contextLoads() {
    }

}
