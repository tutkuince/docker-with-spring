package io.tutkuince.candidateservice;

import org.junit.ClassRule;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.File;

@Testcontainers
public class BaseTest {

    private static final int MONGO_PORT = 27017;
    private static final String MONGO = "mongo";
    private static final String MONGO_URI = "mongodb://candidate_user:candidate_password@%s:%s/candidate";

    @ClassRule
    private static final DockerComposeContainer<?> COMPOSE = new DockerComposeContainer<>(new File("docker-compose.yml"));

    @DynamicPropertySource
    static void mongoProperties(DynamicPropertyRegistry registry) {
        COMPOSE
                .withEnv("HOST_PORT", "0")
                .withExposedService(MONGO, MONGO_PORT, Wait.forListeningPort())
                .start();

        String serviceHost = COMPOSE.getServiceHost(MONGO, MONGO_PORT);
        Integer servicePort = COMPOSE.getServicePort(MONGO, MONGO_PORT);
        registry.add("spring.data.mongodb.uri", () -> String.format(MONGO_URI, serviceHost, servicePort));
    }
}
