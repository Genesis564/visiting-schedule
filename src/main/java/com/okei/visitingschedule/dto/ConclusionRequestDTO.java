package com.okei.visitingschedule.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * A DTO for the {@link com.okei.visitingschedule.entity.schedule.Conclusion} entity
 */
public class ConclusionRequestDTO implements Serializable{

    private final String virtuesOfOccupation;
    private final String problems;

    public ConclusionRequestDTO(String virtuesOfOccupation, String problems) {
        this.virtuesOfOccupation = virtuesOfOccupation;
        this.problems = problems;
    }

    public String getVirtuesOfOccupation() {
        return virtuesOfOccupation;
    }

    public String getProblems() {
        return problems;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConclusionRequestDTO that = (ConclusionRequestDTO) o;
        return Objects.equals(virtuesOfOccupation, that.virtuesOfOccupation) && Objects.equals(problems, that.problems) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(virtuesOfOccupation, problems);
    }

    @Override
    public String toString() {
        return "ConcolusionRequestDTO{" +
                "virtuesOfOccupation='" + virtuesOfOccupation + '\'' +
                ", problems='" + problems + '\'' +
                '}';
    }
}
