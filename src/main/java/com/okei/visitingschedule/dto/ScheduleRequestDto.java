package com.okei.visitingschedule.dto;

import com.okei.visitingschedule.entity.schedule.Status;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;


public class ScheduleRequestDto implements Serializable {
    private final String date;
    private final Set<Status> status;
    private final Long studyGroupId;
    private final Long positionId;
    private final Long academicDisciplineId;

    public ScheduleRequestDto(String date, Set<Status> status, Long studyGroupId, Long positionId, Long academicDisciplineId) {
        this.date = date;
        this.status = status;
        this.studyGroupId = studyGroupId;
        this.positionId = positionId;
        this.academicDisciplineId = academicDisciplineId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScheduleRequestDto entity = (ScheduleRequestDto) o;
        return Objects.equals(this.date, entity.date) &&
                Objects.equals(this.status, entity.status) &&
                Objects.equals(this.studyGroupId, entity.studyGroupId) &&
                Objects.equals(this.positionId, entity.positionId) &&
                Objects.equals(this.academicDisciplineId, entity.academicDisciplineId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, status, studyGroupId, positionId, academicDisciplineId);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "date = " + date + ", " +
                "status = " + status + ", " +
                "studyGroupId = " + studyGroupId + ", " +
                "positionId = " + positionId + ", " +
                "academicDisciplineId = " + academicDisciplineId + ")";
    }
}