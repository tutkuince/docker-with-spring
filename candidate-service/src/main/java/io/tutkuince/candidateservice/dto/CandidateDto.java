package io.tutkuince.candidateservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "create")
public class CandidateDto {
    private String id;
    private String name;
    private Set<String> skills;
}
