package io.tutkuince.jobservice.util;

import io.tutkuince.jobservice.dto.JobDto;
import io.tutkuince.jobservice.entity.Job;
import org.springframework.beans.BeanUtils;

public class EntityDtoUtil {

    public static JobDto toJobDto(Job job) {
        JobDto dto = new JobDto();
        BeanUtils.copyProperties(job, dto);
        return dto;
    }

    public static Job toJobEntity(JobDto dto) {
        Job job = new Job();
        BeanUtils.copyProperties(dto, job);
        return job;
    }
}
