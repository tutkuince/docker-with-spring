package io.tutkuince.candidateservice.client;

import io.tutkuince.candidateservice.dto.JobDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Set;

@Service
public class JobClient {
    private final WebClient client;

    // job/search?skills=
    private JobClient(@Value("${job.service.url}") String baseUrl) {
        this.client = WebClient.builder()
                .baseUrl(baseUrl)
                .build();
    }

    public Mono<List<JobDto>> getRecommendedJobs(Set<String> skills) {
        return this.client.get()
                .uri(uriBuilder -> uriBuilder.path("search").queryParam("skills", skills).build())
                .retrieve()
                .bodyToFlux(JobDto.class)
                .collectList();
    }
}
