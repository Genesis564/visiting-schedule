package com.okei.visitingschedule.entity.schedule;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "academic_discipline")
public class AcademicDiscipline {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String disciplineName;

    public AcademicDiscipline(String disciplineName) {
        this.disciplineName = disciplineName;
    }

    public AcademicDiscipline() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDisciplineName() {
        return disciplineName;
    }

    public void setDisciplineName(String disciplineName) {
        this.disciplineName = disciplineName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AcademicDiscipline that = (AcademicDiscipline) o;
        return Objects.equals(id, that.id) && Objects.equals(disciplineName, that.disciplineName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, disciplineName);
    }

    @Override
    public String toString() {
        return "AcademicDiscipline{" +
                "id=" + id +
                ", disciplineName='" + disciplineName + '\'' +
                '}';
    }
}
