package com.example.routingandfilteringbook;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookApplicationTests {

    @Autowired
    private TestRestTemplate rest;

    @Test
    public void availableTest() {
        String resp = rest.getForObject("/available", String.class);
        assertThat(resp).isEqualTo("Spring in Action");
    }

    @Test
    public void checkedOutTest() {
        String resp = rest.getForObject("/checked-out", String.class);
        assertThat(resp).isEqualTo("Spring Boot in Action");
    }
}
