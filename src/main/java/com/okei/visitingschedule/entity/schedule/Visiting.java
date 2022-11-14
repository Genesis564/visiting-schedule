package com.okei.visitingschedule.entity.schedule;

import com.okei.visitingschedule.services.VisitingCriteriaService;

import javax.persistence.*;
import java.util.*;

@Entity
public class Visiting {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private Date date;

    @ElementCollection(targetClass = Status.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "visiting_status", joinColumns = @JoinColumn(name = "visiting_id"))
    @Enumerated(EnumType.STRING)
    private Set<Status> status;

    @ManyToOne
    @JoinColumn(name = "study_group_id")
    private StudyGroup studyGroup;

    @ManyToOne
    @JoinColumn(name = "position_id")
    private Position position;

    @ManyToOne
    @JoinColumn(name = "academic_discipline_id")
    private AcademicDiscipline academicDiscipline;

    @OneToMany
    @JoinColumn(name = "criteria_Id")
    private List<VisitingCriteria> criteria;

    @OneToMany
    @JoinColumn(name = "criteria_score_id")
    private List<CriteriaScore> criteriaScore;


    public List<VisitingCriteria> getCriteria() {
        return criteria;
    }

    public Visiting(Date date, Set<Status> status, StudyGroup studyGroup, Position position, AcademicDiscipline academicDiscipline,List<VisitingCriteria> criteria, List<CriteriaScore> criteriaScore) {
        this.date = date;
        this.status = status;
        this.studyGroup = studyGroup;
        this.position = position;
        this.academicDiscipline = academicDiscipline;
        this.criteria = criteria;
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
