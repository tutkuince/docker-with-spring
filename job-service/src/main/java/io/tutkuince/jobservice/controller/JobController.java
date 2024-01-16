package io.tutkuince.jobservice.controller;

import io.tutkuince.jobservice.dto.JobDto;
import io.tutkuince.jobservice.service.JobService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Set;

@RestController
@RequestMapping("job")
public class JobController {
    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("all")
    public Flux<JobDto> all() {
        return this.jobService.allJobs();
    }

    @GetMapping("search")
    public Flux<JobDto> search(@RequestParam Set<String> skills) {
        return this.jobService.jobsBySkillsIn(skills);
    }

    @PostMapping
    public Mono<JobDto> save(@RequestBody Mono<JobDto> mono) {
        return this.jobService.save(mono);
    }
}
