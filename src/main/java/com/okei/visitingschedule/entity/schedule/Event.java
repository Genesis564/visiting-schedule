package com.okei.visitingschedule.entity.schedule;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(length = 9999)
    private String eventName;

    @ManyToOne
    @JoinColumn(name="concolusion_id")
    private Conclusion conclusion;
    private boolean completionMark;

    public Event(String eventName, Conclusion conclusion) {
        this.eventName = eventName;
        this.conclusion = conclusion;
        this.completionMark = false;
    }

    public Event() {
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public boolean isCompletionMark() {
        return completionMark;
    }

    public void setCompletionMark(boolean completionMark) {
        this.completionMark = completionMark;
    }

    public Conclusion getConclusion() {
        return conclusion;
    }

    public void setConclusion(Conclusion conclusion) {
        this.conclusion = conclusion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return completionMark == event.completionMark && Objects.equals(id, event.id) && Objects.equals(eventName, event.eventName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, eventName, completionMark);
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", name='" + eventName + '\'' +
                ", completionMark=" + completionMark +
                '}';
    }
}
