package io.tutkuince.candidateservice.controller;

import io.tutkuince.candidateservice.dto.CandidateDto;
import io.tutkuince.candidateservice.service.CandidateService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("candidate")
public class CandidateController {
    private final CandidateService candidateService;

    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @GetMapping("all")
    public Flux<CandidateDto> all() {
        return this.candidateService.allCandidates();
    }

    @GetMapping("{id}")
    public Mono<CandidateDto> findById(@PathVariable String id) {
        return this.candidateService.candidateById(id);
    }

    @PostMapping
    public Mono<CandidateDto> save(@RequestBody Mono<CandidateDto> dto) {
        return this.candidateService.save(dto);
    }
}