package com.okei.visitingschedule.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.okei.visitingschedule.entity.schedule.StudyGroup} entity
 */
public class StudyGroupRequestDto implements Serializable {
    private final String groupName;

    public StudyGroupRequestDto(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupName() {
        return groupName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudyGroupRequestDto entity = (StudyGroupRequestDto) o;
        return Objects.equals(this.groupName, entity.groupName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupName);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "groupName = " + groupName + ")";
    }
}