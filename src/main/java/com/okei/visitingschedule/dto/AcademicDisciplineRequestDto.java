package com.okei.visitingschedule.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.okei.visitingschedule.entity.schedule.AcademicDiscipline} entity
 */
public class AcademicDisciplineRequestDto implements Serializable {
    private final String disciplineName;

    public AcademicDisciplineRequestDto(String disciplineName) {
        this.disciplineName = disciplineName;
    }

    public String getDisciplineName() {
        return disciplineName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AcademicDisciplineRequestDto entity = (AcademicDisciplineRequestDto) o;
        return Objects.equals(this.disciplineName, entity.disciplineName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(disciplineName);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "disciplineName = " + disciplineName + ")";
    }
}