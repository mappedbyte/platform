package com.francis.platform;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class PlatformApplicationTests {

    @Test
    void contextLoads() {
        String gensalt = BCrypt.gensalt();
        System.out.println(BCrypt.hashpw("123456", gensalt));
    }

}
