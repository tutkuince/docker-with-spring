package io.tutkuince.jobservice;

import io.tutkuince.jobservice.generic.BaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest
@AutoConfigureWebTestClient
class JobServiceIT extends BaseTest {

    @Autowired
    private WebTestClient client;

    @Test
    void allJobsTest() {
        this.client.get()
                .uri("/job/all")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody()
                .consumeWith(e -> System.out.println(new String(e.getResponseBody())))
                .jsonPath("$").isNotEmpty();
    }

}
