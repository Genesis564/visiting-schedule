package com.okei.visitingschedule.entity.schedule;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
public class Conclusion {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    private String virtuesOfOccupation;
    private String problems;

    @OneToMany(mappedBy = "conclusion")
    Set<Event> events;

    public Conclusion(String virtuesOfOccupation, String problems, Set<Event> events) {
        this.virtuesOfOccupation = virtuesOfOccupation;
        this.problems = problems;
        this.events = events;
    }

    public Conclusion(String virtuesOfOccupation, String problems) {
        this.virtuesOfOccupation = virtuesOfOccupation;
        this.problems = problems;
    }

    public Conclusion() {
    }

    public String getVirtuesOfOccupation() {
        return virtuesOfOccupation;
    }

    public void setVirtuesOfOccupation(String virtuesOfOccupation) {
        this.virtuesOfOccupation = virtuesOfOccupation;
    }

    public String getProblems() {
        return problems;
    }

    public void setProblems(String problems) {
        this.problems = problems;
    }

    public Set<Event> getEvents() {
        return events;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
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
        Conclusion that = (Conclusion) o;
        return Objects.equals(id, that.id) && Objects.equals(virtuesOfOccupation, that.virtuesOfOccupation) && Objects.equals(problems, that.problems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, virtuesOfOccupation, problems);
    }

    @Override
    public String toString() {
        return "Conclusion{" +
                "id=" + id +
                ", virtuesOfOccupation='" + virtuesOfOccupation + '\'' +
                ", problems='" + problems + '\'' +
                '}';
    }
}
