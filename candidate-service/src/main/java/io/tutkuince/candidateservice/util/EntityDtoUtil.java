package io.tutkuince.candidateservice.util;

import io.tutkuince.candidateservice.dto.CandidateDto;
import io.tutkuince.candidateservice.entity.Candidate;
import org.springframework.beans.BeanUtils;

public class EntityDtoUtil {
    public static CandidateDto toCandidateDto(Candidate candidate) {
        CandidateDto dto = new CandidateDto();
        BeanUtils.copyProperties(candidate, dto);
        return dto;
    }

    public static Candidate toCandidate(CandidateDto dto) {
        Candidate candidate = new Candidate();
        BeanUtils.copyProperties(dto, candidate);
        return candidate;
    }
}
