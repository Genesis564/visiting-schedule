package com.okei.visitingschedule.entity.schedule;

import javax.persistence.*;
import java.util.*;

@Entity
public class Visiting {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String date;

    @ElementCollection(targetClass = Status.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "visiting_status", joinColumns = @JoinColumn(name = "visiting_id"))
    @Enumerated(EnumType.STRING)
    private Set<Status> status;

    @OneToOne
    @JoinColumn(name = "study_group_id")
    private StudyGroup studyGroup;

    @OneToOne
    @JoinColumn(name = "position_id")
    private Position position;

    @OneToOne
    @JoinColumn(name = "academic_discipline_id")
    private AcademicDiscipline academicDiscipline;

    @ElementCollection(targetClass = VisitingCriteria.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "visiting_criteria", joinColumns = @JoinColumn(name = "visiting_id"))
    @Enumerated(EnumType.STRING)
    private Set<VisitingCriteria> criteria;

    public Visiting(String date, Set<Status> status, StudyGroup studyGroup, Position position, AcademicDiscipline academicDiscipline) {
        this.date = date;
        this.status = status;
        this.studyGroup = studyGroup;
        this.position = position;
        this.academicDiscipline = academicDiscipline;
        this.criteria = new HashSet<>(Arrays.asList(VisitingCriteria.values()));
    }

    public Visiting() {
    }

    public AcademicDiscipline getAcademicDiscipline() {
        return academicDiscipline;
    }

    public void setAcademicDiscipline(AcademicDiscipline academicDiscipline) {
        this.academicDiscipline = academicDiscipline;
    }

    public void setStudyGroup(StudyGroup studyGroup) {
        this.studyGroup = studyGroup;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public StudyGroup getStudyGroup() {
        return studyGroup;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
