package com.okei.visitingschedule.entity.schedule;

import javax.persistence.*;

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
}
