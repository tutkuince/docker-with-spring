package io.tutkuince.jobservice.service;

import io.tutkuince.jobservice.dto.JobDto;
import io.tutkuince.jobservice.repository.JobRepository;
import io.tutkuince.jobservice.util.EntityDtoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Set;

@Service
public class JobService {
    private JobRepository repository;

    @Autowired
    public JobService(JobRepository repository) {
        this.repository = repository;
    }

    public Flux<JobDto> allJobs() {
        return this.repository.findAll()
                .map(EntityDtoUtil::toJobDto);
    }

    public Flux<JobDto> jobsBySkillsIn(Set<String> skills) {
        return this.repository.findBySkillsIn(skills)
                .map(EntityDtoUtil::toJobDto);
    }

    public Mono<JobDto> save(Mono<JobDto> mono) {
        return mono.map(EntityDtoUtil::toJobEntity)
                .flatMap(this.repository::save)
                .map(EntityDtoUtil::toJobDto)
    }
}
