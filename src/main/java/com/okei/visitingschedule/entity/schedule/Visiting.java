package com.okei.visitingschedule.entity.schedule;

import com.okei.visitingschedule.repos.VisitingCriteriaRepo;
import com.okei.visitingschedule.services.VisitingCriteriaService;
import org.springframework.beans.factory.annotation.Autowired;

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


    public List<VisitingCriteria> getCriteria() {
        return criteria;
    }

    public Visiting(String date, Set<Status> status, StudyGroup studyGroup, Position position, AcademicDiscipline academicDiscipline,VisitingCriteriaService visitingCriteriaService) {
        this.date = date;
        this.status = status;
        this.studyGroup = studyGroup;
        this.position = position;
        this.academicDiscipline = academicDiscipline;
        this.criteria = visitingCriteriaService.findAll();
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
