package io.tutkuince.candidateservice;

import io.tutkuince.candidateservice.dto.Service;
import org.junit.ClassRule;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.File;

@Testcontainers
public abstract class BaseTest {

    private static final String MONGO = "mongo";
    private static final int MONGO_PORT = 27017;
    private static final String MONGO_URI = "mongodb://candidate_user:candidate_password@%s:%s/candidate";
    private static final String MONGO_PORT_ENV_VAR = "HOST_MONGO_PORT";
    private static final String JOB_MOCK = "job-mock";
    private static final int JOB_PORT = 1080;
    private static final String JOB_URI = "http://%s:%s/job";
    private static final String JOB_PORT_ENV_VAR = "HOST_MOCK_PORT";

    private static final Service MONGO_SERVICE = Service.create(
            MONGO, MONGO_PORT, "0", MONGO_URI, MONGO_PORT_ENV_VAR
    );

    private static final Service JOB_SERVICE = Service.create(
            JOB_MOCK, JOB_PORT, "0", JOB_URI, JOB_PORT_ENV_VAR
    );

    @ClassRule
    private static final DockerComposeContainer<?> COMPOSE = new DockerComposeContainer<>(new File("docker-compose.yml"));

    @DynamicPropertySource
    static void mongoProperties(DynamicPropertyRegistry registry) {
        COMPOSE
                .withEnv(MONGO_SERVICE.getHostPortEnvVariable(), MONGO_SERVICE.getHostPort())
                .withEnv(JOB_SERVICE.getHostPortEnvVariable(), JOB_SERVICE.getHostPort())
                .withExposedService(MONGO_SERVICE.getName(), MONGO_SERVICE.getPort(), Wait.forListeningPort())
                .withExposedService(JOB_SERVICE.getName(), JOB_SERVICE.getPort(), Wait.forListeningPort())
                .start();

        String mongoServiceHost = COMPOSE.getServiceHost(MONGO_SERVICE.getName(), MONGO_SERVICE.getPort());
        Integer mongoServicePort = COMPOSE.getServicePort(MONGO_SERVICE.getName(), MONGO_SERVICE.getPort());
        String jobServiceHost = COMPOSE.getServiceHost(JOB_SERVICE.getName(), JOB_SERVICE.getPort());
        Integer jobServicePort = COMPOSE.getServicePort(JOB_SERVICE.getName(), JOB_SERVICE.getPort());
        registry.add("spring.data.mongodb.uri", () -> String.format(MONGO_SERVICE.getUri(), mongoServiceHost, mongoServicePort));
        registry.add("job.service.uri", () -> String.format(JOB_SERVICE.getUri(), jobServiceHost, jobServicePort));
    }
}
