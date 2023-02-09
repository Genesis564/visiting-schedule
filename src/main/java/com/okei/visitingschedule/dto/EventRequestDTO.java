package com.okei.visitingschedule.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * A DTO for the {@link com.okei.visitingschedule.entity.schedule.Event} entity
 */
public class EventRequestDTO implements Serializable{

    private final List<String> eventNames;



    public EventRequestDTO(List<String> eventNames) {
        this.eventNames = eventNames;
    }

    public List<String> getEventNames() {
        return eventNames;
    }





    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventRequestDTO that = (EventRequestDTO) o;
        return Objects.equals(eventNames, that.eventNames) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventNames);
    }

    @Override
    public String toString() {
        return "EventRequestDTO{" +
                "name='" + eventNames + '\'' +
                '}';
    }
}
