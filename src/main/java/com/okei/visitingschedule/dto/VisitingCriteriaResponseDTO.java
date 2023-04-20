package com.okei.visitingschedule.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.okei.visitingschedule.entity.schedule.VisitingCriteria} entity
 */
public class VisitingCriteriaResponseDTO implements Serializable {
    private final String criteriaName;
    private final String valueOfOnePoint;
    private final String valueOfTwoPoint;
    private final String valueOfThreePoint;

    public VisitingCriteriaResponseDTO(String criteriaName, String valueOfOnePoint, String valueOfTwoPoint, String valueOfThreePoint) {
        this.criteriaName = criteriaName;
        this.valueOfOnePoint = valueOfOnePoint;
        this.valueOfTwoPoint = valueOfTwoPoint;
        this.valueOfThreePoint = valueOfThreePoint;
    }

    public String getCriteriaName() {
        return criteriaName;
    }

    public String getValueOfOnePoint() {
        return valueOfOnePoint;
    }

    public String getValueOfTwoPoint() {
        return valueOfTwoPoint;
    }

    public String getValueOfThreePoint() {
        return valueOfThreePoint;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VisitingCriteriaResponseDTO entity = (VisitingCriteriaResponseDTO) o;
        return Objects.equals(this.criteriaName, entity.criteriaName) &&
                Objects.equals(this.valueOfOnePoint, entity.valueOfOnePoint) &&
                Objects.equals(this.valueOfTwoPoint, entity.valueOfTwoPoint) &&
                Objects.equals(this.valueOfThreePoint, entity.valueOfThreePoint);
    }

    @Override
    public int hashCode() {
        return Objects.hash(criteriaName, valueOfOnePoint, valueOfTwoPoint, valueOfThreePoint);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "criteriaName = " + criteriaName + ", " +
                "valueOfOnePoint = " + valueOfOnePoint + ", " +
                "valueOfTwoPoint = " + valueOfTwoPoint + ", " +
                "valueOfThreePoint = " + valueOfThreePoint + ")";
    }
}