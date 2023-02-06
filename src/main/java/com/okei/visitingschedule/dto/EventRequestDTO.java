package com.okei.visitingschedule.dto;

import com.okei.visitingschedule.entity.schedule.Conclusion;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.okei.visitingschedule.entity.schedule.Event} entity
 */
public class EventRequestDTO implements Serializable{

    private final String name;
    private final Long conclusionId;


    public EventRequestDTO(String name, Long conclusionId) {
        this.name = name;
        this.conclusionId = conclusionId;
    }

    public String getName() {
        return name;
    }


    public Long getConclusionId() {
        return conclusionId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventRequestDTO that = (EventRequestDTO) o;
        return Objects.equals(name, that.name) && Objects.equals(conclusionId, that.conclusionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, conclusionId);
    }

    @Override
    public String toString() {
        return "EventRequestDTO{" +
                "name='" + name + '\'' +
                ", conclusionId=" + conclusionId +
                '}';
    }
}
