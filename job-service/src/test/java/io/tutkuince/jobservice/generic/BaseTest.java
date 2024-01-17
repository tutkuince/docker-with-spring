package io.tutkuince.jobservice.generic;


import org.springframework.boot.testcontainers.context.ImportTestcontainers;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.BindMode;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@Testcontainers
public abstract class BaseTest {
    public static final String INIT_JS = "/docker-entrypoint-initdb.d/init.js";
    public static final int MONGO_PORT = 27017;
    public static final String MONGO_URI_FORMAT = "mongodb://job_user:job_password@%s:%s/job";

    @Container
    private static final GenericContainer<?> mongo = new GenericContainer(DockerImageName.parse("mongo"))
            .withExposedPorts(MONGO_PORT)
            .withClasspathResourceMapping("data/job-init.js", INIT_JS, BindMode.READ_ONLY)
            .waitingFor(Wait.forListeningPort());

    @DynamicPropertySource
    static void mongoProperties(DynamicPropertyRegistry registry) {
        mongo.start();
        String formattedURI = String.format(MONGO_URI_FORMAT, mongo.getHost(), mongo.getMappedPort(MONGO_PORT));
        registry.add("spring.data.mongodb.uri", () -> formattedURI);
    }
}

