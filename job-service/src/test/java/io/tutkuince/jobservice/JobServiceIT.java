package io.tutkuince.jobservice;

import io.tutkuince.jobservice.dto.JobDto;
import io.tutkuince.jobservice.generic.BaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Set;

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
                .jsonPath("$").isNotEmpty();
    }

    @Test
    void searchBySkillsTest() {
        this.client.get()
                .uri("/job/search?skills=java")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody()
                .jsonPath("$.size()").isEqualTo(3);
    }

    @Test
    void postJobTest() {
        JobDto jobDto = JobDto.create(null, "K8s Engineer", "google", Set.of("k8s"), 200000, true);
        this.client.post()
                .uri("/job")
                .bodyValue(jobDto)
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody()
                .jsonPath("$.id").isNotEmpty();
    }

}
