package io.tutkuince.candidateservice.service;

import io.tutkuince.candidateservice.dto.CandidateDto;
import io.tutkuince.candidateservice.repository.CandidateRepository;
import io.tutkuince.candidateservice.util.EntityDtoUtil;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CandidateService {
    private final CandidateRepository candidateRepository;

    public CandidateService(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    public Flux<CandidateDto> allCandidates() {
        return this.candidateRepository
                .findAll()
                .map(EntityDtoUtil::toCandidateDto);
    }

    public Mono<CandidateDto> candidateById(String id) {
        return this.candidateRepository
                .findById(id)
                .map(EntityDtoUtil::toCandidateDto);
    }

    public Mono<CandidateDto> save(Mono<CandidateDto> dto) {
        return dto
                .map(EntityDtoUtil::toCandidate)
                .flatMap(this.candidateRepository::save)
                .map(EntityDtoUtil::toCandidateDto);
    }
}
