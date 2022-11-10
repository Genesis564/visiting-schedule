package com.okei.visitingschedule.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.okei.visitingschedule.entity.schedule.VisitingCriteria} entity
 */
public class VisitingCriteriaRequestDTO implements Serializable {
    private final String critariaName;
    private final String valueOfOnePoint;
    private final String valueOfTwoPoint;
    private final String valueOfThreePoint;

    public VisitingCriteriaRequestDTO(String critariaName, String valueOfOnePoint, String valueOfTwoPoint, String valueOfThreePoint) {
        this.critariaName = critariaName;
        this.valueOfOnePoint = valueOfOnePoint;
        this.valueOfTwoPoint = valueOfTwoPoint;
        this.valueOfThreePoint = valueOfThreePoint;
    }

    public String getCritariaName() {
        return critariaName;
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
        VisitingCriteriaRequestDTO entity = (VisitingCriteriaRequestDTO) o;
        return Objects.equals(this.critariaName, entity.critariaName) &&
                Objects.equals(this.valueOfOnePoint, entity.valueOfOnePoint) &&
                Objects.equals(this.valueOfTwoPoint, entity.valueOfTwoPoint) &&
                Objects.equals(this.valueOfThreePoint, entity.valueOfThreePoint);
    }

    @Override
    public int hashCode() {
        return Objects.hash(critariaName, valueOfOnePoint, valueOfTwoPoint, valueOfThreePoint);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "critariaName = " + critariaName + ", " +
                "valueOfOnePoint = " + valueOfOnePoint + ", " +
                "valueOfTwoPoint = " + valueOfTwoPoint + ", " +
                "valueOfThreePoint = " + valueOfThreePoint + ")";
    }
}