package io.tutkuince.candidateservice;

import io.tutkuince.candidateservice.dto.CandidateDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Set;

@SpringBootTest
@AutoConfigureWebTestClient
class CandidateServiceIT extends BaseTest {

    @Autowired
    private WebTestClient client;

    @Test
    void allCandidatesTest() {
        this.client.get()
                .uri("/candidate/all")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody()
                .jsonPath("$").isNotEmpty();
    }

    @Test
    void allCandidateByIdTest() {
        this.client.get()
                .uri("/candidate/1")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody()
                .jsonPath("$.id").isEqualTo(1)
                .jsonPath("$.name").isEqualTo("sam")
                .jsonPath("$.skills.size()").isEqualTo(2)
                .jsonPath("$.recommendedJobs.size()").isEqualTo(2);
    }

    @Test
    void postCandidateTest() {
        CandidateDto candidateDto = CandidateDto.create(null, "tutku", Set.of("k8s", "docker"));
        this.client.post()
                .uri("/candidate")
                .bodyValue(candidateDto)
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody()
                .jsonPath("$.id").isNotEmpty()
                .jsonPath("$.name").isEqualTo("tutku");
    }

    @Test
    void jobServiceReturns4xx() {
        this.client.get()
                .uri("/candidate/2")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody()
                .jsonPath("$.id").isEqualTo(2)
                .jsonPath("$.name").isEqualTo("jake")
                .jsonPath("$.skills.size()").isEqualTo(1)
                .jsonPath("$.recommendedJobs").isEmpty();
    }

    @Test
    void jobServiceReturns5xx() {
        this.client.get()
                .uri("/candidate/3")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody()
                .jsonPath("$.id").isEqualTo(3)
                .jsonPath("$.name").isEqualTo("mike")
                .jsonPath("$.skills.size()").isEqualTo(1)
                .jsonPath("$.recommendedJobs").isEmpty();
    }

}
