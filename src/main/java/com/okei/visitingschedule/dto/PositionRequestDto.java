package com.okei.visitingschedule.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.okei.visitingschedule.entity.schedule.Position} entity
 */
public class PositionRequestDto implements Serializable {
    private final String positionName;

    public PositionRequestDto(String positionName) {
        this.positionName = positionName;
    }

    public String getPositionName() {
        return positionName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PositionRequestDto entity = (PositionRequestDto) o;
        return Objects.equals(this.positionName, entity.positionName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(positionName);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "positionName = " + positionName + ")";
    }
}