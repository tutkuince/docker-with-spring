package io.tutkuince.candidateservice.util;

import io.tutkuince.candidateservice.dto.CandidateDetailsDto;
import io.tutkuince.candidateservice.dto.CandidateDto;
import io.tutkuince.candidateservice.entity.Candidate;
import org.springframework.beans.BeanUtils;

public class EntityDtoUtil {
    public static CandidateDto toCandidateDto(Candidate candidate) {
        CandidateDto dto = new CandidateDto();
        BeanUtils.copyProperties(candidate, dto);
        dto.setHostName(AppUtil.getHostName());
        return dto;
    }

    public static Candidate toCandidate(CandidateDto dto) {
        Candidate candidate = new Candidate();
        BeanUtils.copyProperties(dto, candidate);
        return candidate;
    }

    public static CandidateDetailsDto toCandidateDetailsDto(Candidate candidate) {
        CandidateDetailsDto dto = new CandidateDetailsDto();
        BeanUtils.copyProperties(candidate, dto);
        dto.setHostName(AppUtil.getHostName());
        return dto;
    }
}
