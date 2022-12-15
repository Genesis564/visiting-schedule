package com.okei.visitingschedule.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * A DTO for the {@link com.okei.visitingschedule.entity.schedule.Visiting} entity
 */
public class VisitingRequestDTO implements Serializable {
    private final String purposeOfTheVisit;
    private final int numberOfStudents;
    private final String lessonTopic;
    private final String purposeOfTheLesson;
    private final String date;
    private final Long studyGroupId;
    private final Long positionId;
    private final Long academicDisciplineId;
    private final List<Long> criterionIds;
    private final List<Integer> criteriaScoreIds;
    private final Long scheduleId;

    public VisitingRequestDTO(String purposeOfTheVisit, int numberOfStudents, String lessonTopic, String purposeOfTheLesson, String date, Long studyGroupId, Long positionId, Long academicDisciplineId, List<Long> criterionIds, List<Integer> criteriaScoreIds, Long scheduleId) {
        this.purposeOfTheVisit = purposeOfTheVisit;
        this.numberOfStudents = numberOfStudents;
        this.lessonTopic = lessonTopic;
        this.purposeOfTheLesson = purposeOfTheLesson;
        this.date = date;
        this.studyGroupId = studyGroupId;
        this.positionId = positionId;
        this.academicDisciplineId = academicDisciplineId;
        this.criterionIds = criterionIds;
        this.criteriaScoreIds = criteriaScoreIds;
        this.scheduleId = scheduleId;
    }

    public String getPurposeOfTheVisit() {
        return purposeOfTheVisit;
    }

    public int getNumberOfStudents() {
        return numberOfStudents;
    }

    public String getLessonTopic() {
        return lessonTopic;
    }

    public String getPurposeOfTheLesson() {
        return purposeOfTheLesson;
    }

    public String getDate() {
        return date;
    }

    public Long getStudyGroupId() {
        return studyGroupId;
    }

    public Long getPositionId() {
        return positionId;
    }

    public Long getAcademicDisciplineId() {
        return academicDisciplineId;
    }

    public List<Long> getCriterionIds() {
        return criterionIds;
    }

    public List<Integer> getCriteriaScoreIds() {
        return criteriaScoreIds;
    }

    public Long getScheduleId() {
        return scheduleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VisitingRequestDTO entity = (VisitingRequestDTO) o;
        return Objects.equals(this.purposeOfTheVisit, entity.purposeOfTheVisit) &&
                Objects.equals(this.numberOfStudents, entity.numberOfStudents) &&
                Objects.equals(this.lessonTopic, entity.lessonTopic) &&
                Objects.equals(this.purposeOfTheLesson, entity.purposeOfTheLesson) &&
                Objects.equals(this.date, entity.date) &&
                Objects.equals(this.studyGroupId, entity.studyGroupId) &&
                Objects.equals(this.positionId, entity.positionId) &&
                Objects.equals(this.academicDisciplineId, entity.academicDisciplineId) &&
                Objects.equals(this.criterionIds, entity.criterionIds) &&
                Objects.equals(this.criteriaScoreIds, entity.criteriaScoreIds) &&
                Objects.equals(this.scheduleId, entity.scheduleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(purposeOfTheVisit, numberOfStudents, lessonTopic, purposeOfTheLesson, date, studyGroupId, positionId, academicDisciplineId, criterionIds, criteriaScoreIds, scheduleId);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "purposeOfTheVisit = " + purposeOfTheVisit + ", " +
                "numberOfStudents = " + numberOfStudents + ", " +
                "lessonTopic = " + lessonTopic + ", " +
                "PurposeOfTheLesson = " + purposeOfTheLesson + ", " +
                "date = " + date + ", " +
                "studyGroupId = " + studyGroupId + ", " +
                "positionId = " + positionId + ", " +
                "academicDisciplineId = " + academicDisciplineId + ", " +
                "criterionIds = " + criterionIds + ", " +
                "criteriaScoreIds = " + criteriaScoreIds + ", " +
                "scheduleId = " + scheduleId + ")";
    }
}