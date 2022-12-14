package com.okei.visitingschedule.entity.schedule;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name="visiting")
public class Visiting {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(length = 9999)
    private String purposeOfTheVisit;

    private int numberOfStudents;
    @Column(length = 9999)
    private String lessonTopic;
    private String purposeOfTheLesson;
    private String date;

    @ManyToOne
    @JoinColumn(name = "study_group_id")
    private StudyGroup studyGroup;

    @ManyToOne
    @JoinColumn(name = "position_id")
    private Position position;

    @ManyToOne
    @JoinColumn(name = "academic_discipline_id")
    private AcademicDiscipline academicDiscipline;

    @OneToMany(mappedBy = "visiting")
    Set<CriteriaScore> criteriaScore;

    @OneToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    public Visiting(String purposeOfTheVisit, int numberOfStudents, String lessonTopic, String purposeOfTheLesson, String date, StudyGroup studyGroup, Position position, AcademicDiscipline academicDiscipline, Set<CriteriaScore> criteriaScore, Schedule schedule) {
        this.purposeOfTheVisit = purposeOfTheVisit;
        this.numberOfStudents = numberOfStudents;
        this.lessonTopic = lessonTopic;
        this.purposeOfTheLesson = purposeOfTheLesson;
        this.date = date;
        this.studyGroup = studyGroup;
        this.position = position;
        this.academicDiscipline = academicDiscipline;
        this.criteriaScore = criteriaScore;
        this.schedule = schedule;
    }

    public Visiting() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPurposeOfTheVisit() {
        return purposeOfTheVisit;
    }

    public void setPurposeOfTheVisit(String purposeOfTheVisit) {
        this.purposeOfTheVisit = purposeOfTheVisit;
    }

    public int getNumberOfStudents() {
        return numberOfStudents;
    }

    public void setNumberOfStudents(int numberOfStudents) {
        this.numberOfStudents = numberOfStudents;
    }

    public String getLessonTopic() {
        return lessonTopic;
    }

    public void setLessonTopic(String lessonTopic) {
        this.lessonTopic = lessonTopic;
    }

    public String getPurposeOfTheLesson() {
        return purposeOfTheLesson;
    }

    public void setPurposeOfTheLesson(String purposeOfTheLesson) {
        this.purposeOfTheLesson = purposeOfTheLesson;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public StudyGroup getStudyGroup() {
        return studyGroup;
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

    public AcademicDiscipline getAcademicDiscipline() {
        return academicDiscipline;
    }

    public void setAcademicDiscipline(AcademicDiscipline academicDiscipline) {
        this.academicDiscipline = academicDiscipline;
    }

    public Set<CriteriaScore> getCriteriaScore() {
        return criteriaScore;
    }

    public void setCriteriaScore(Set<CriteriaScore> criteriaScore) {
        this.criteriaScore = criteriaScore;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Visiting visiting = (Visiting) o;
        return numberOfStudents == visiting.numberOfStudents && Objects.equals(id, visiting.id) && Objects.equals(purposeOfTheVisit, visiting.purposeOfTheVisit) && Objects.equals(lessonTopic, visiting.lessonTopic) && Objects.equals(purposeOfTheLesson, visiting.purposeOfTheLesson) && Objects.equals(date, visiting.date) && Objects.equals(studyGroup, visiting.studyGroup) && Objects.equals(position, visiting.position) && Objects.equals(academicDiscipline, visiting.academicDiscipline) && Objects.equals(criteriaScore, visiting.criteriaScore) && Objects.equals(schedule, visiting.schedule);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, purposeOfTheVisit, numberOfStudents, lessonTopic, purposeOfTheLesson, date, studyGroup, position, academicDiscipline, criteriaScore, schedule);
    }
}


