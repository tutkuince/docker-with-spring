package io.tutkuince.candidateservice.service;

import io.tutkuince.candidateservice.client.JobClient;
import io.tutkuince.candidateservice.dto.CandidateDetailsDto;
import io.tutkuince.candidateservice.dto.CandidateDto;
import io.tutkuince.candidateservice.repository.CandidateRepository;
import io.tutkuince.candidateservice.util.EntityDtoUtil;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CandidateService {
    private final CandidateRepository candidateRepository;
    private final JobClient client;

    public CandidateService(CandidateRepository candidateRepository, JobClient client) {
        this.candidateRepository = candidateRepository;
        this.client = client;
    }

    public Flux<CandidateDto> allCandidates() {
        return this.candidateRepository
                .findAll()
                .map(EntityDtoUtil::toCandidateDto);
    }

    public Mono<CandidateDetailsDto> candidateById(String id) {
        return this.candidateRepository
                .findById(id)
                .map(EntityDtoUtil::toCandidateDetailsDto)
                .flatMap(this::addRecommendedJobs);
    }

    public Mono<CandidateDto> save(Mono<CandidateDto> dto) {
        return dto
                .map(EntityDtoUtil::toCandidate)
                .flatMap(this.candidateRepository::save)
                .map(EntityDtoUtil::toCandidateDto);
    }

    private Mono<CandidateDetailsDto> addRecommendedJobs(CandidateDetailsDto dto) {
        return this.client.getRecommendedJobs(dto.getSkills())
                .doOnNext(dto::setRecommendedJobs)
                .thenReturn(dto);
    }
}
