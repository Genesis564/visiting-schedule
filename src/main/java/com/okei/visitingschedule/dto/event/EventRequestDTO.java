package com.okei.visitingschedule.dto.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.okei.visitingschedule.entity.schedule.Event} entity
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class EventRequestDTO implements Serializable{
    private Long eventId;
    private String eventName;

    public EventRequestDTO(Long eventId, String eventName) {
        this.eventId = eventId;
        this.eventName = eventName;
    }

    public EventRequestDTO() {
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventRequestDTO that = (EventRequestDTO) o;
        return Objects.equals(eventId, that.eventId) && Objects.equals(eventName, that.eventName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventId, eventName);
    }

    @Override
    public String toString() {
        return "EventRequestDTO{" +
                "eventId=" + eventId +
                ", eventName='" + eventName + '\'' +
                '}';
    }
}
