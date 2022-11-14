package com.okei.visitingschedule.dto;

import com.okei.visitingschedule.entity.schedule.Status;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * A DTO for the {@link com.okei.visitingschedule.entity.schedule.Visiting} entity
 */
public class VisitingRequestDTO implements Serializable {
    private final String date;
    private final Set<Status> status;
    private final Long studyGroupId;
    private final Long positionId;
    private final Long academicDisciplineId;
    private final List<Long> criterionIds;
    private final List<Long> criteriaScoreIds;

    public VisitingRequestDTO(String date, Set<Status> status, Long studyGroupId, Long positionId, Long academicDisciplineId, List<Long> criterionIds, List<Long> criteriaScoreIds) {
        this.date = date;
        this.status = status;
        this.studyGroupId = studyGroupId;
        this.positionId = positionId;
        this.academicDisciplineId = academicDisciplineId;
        this.criterionIds = criterionIds;
        this.criteriaScoreIds = criteriaScoreIds;
    }

    public String getDate() {
        return date;
    }

    public Set<Status> getStatus() {
        return status;
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

    public List<Long> getCriteriaScoreIds() {
        return criteriaScoreIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VisitingRequestDTO entity = (VisitingRequestDTO) o;
        return Objects.equals(this.date, entity.date) &&
                Objects.equals(this.status, entity.status) &&
                Objects.equals(this.studyGroupId, entity.studyGroupId) &&
                Objects.equals(this.positionId, entity.positionId) &&
                Objects.equals(this.academicDisciplineId, entity.academicDisciplineId) &&
                Objects.equals(this.criterionIds, entity.criterionIds) &&
                Objects.equals(this.criteriaScoreIds, entity.criteriaScoreIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, status, studyGroupId, positionId, academicDisciplineId, criterionIds, criteriaScoreIds);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "date = " + date + ", " +
                "status = " + status + ", " +
                "studyGroupId = " + studyGroupId + ", " +
                "positionId = " + positionId + ", " +
                "academicDisciplineId = " + academicDisciplineId + ", " +
                "criterionIds = " + criterionIds + ", " +
                "criteriaScoreIds = " + criteriaScoreIds + ")";
    }
}