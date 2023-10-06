package com.binar.binarfud;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class LoggingTest {

    @Test
    void testLog() {
        log.info("Binariaannnn!!!!");
        log.warn("hayoo lagi ngapainnn");
        log.error("yahahaha error yaaa");
    }
}
