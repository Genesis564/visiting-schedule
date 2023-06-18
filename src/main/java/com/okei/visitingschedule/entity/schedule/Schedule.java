package com.okei.visitingschedule.entity.schedule;

import com.okei.visitingschedule.entity.User;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "schedule")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @ElementCollection(targetClass = Status.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "schedule_status", joinColumns = @JoinColumn(name = "schedule_id"))
    @Enumerated(EnumType.STRING)
    private Set<Status> status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "visiter_user_id")
    private User visitorUser;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "visited_user_id")
    private User visitedUser;
    private String visitingWeek;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "visiting_id")
    private Visiting visiting;
    public Schedule(Set<Status> status, User visitorUser, User visitedUser, String visitingWeek) {
        this.status = status;
        this.visitorUser = visitorUser;
        this.visitedUser = visitedUser;
        this.visitingWeek = visitingWeek;
    }
    public Schedule() {
    }
    public String getVisitingWeek() {
        return visitingWeek;
    }
    public void setVisitingWeek(String visitingWeek) {
        this.visitingWeek = visitingWeek;
    }
    public User getVisitorUser() {
        return visitorUser;
    }
    public void setVisitorUser(User visiterUser) {
        this.visitorUser = visiterUser;
    }
    public User getVisitedUser() {
        return visitedUser;
    }
    public void setVisitedUser(User visitedUser) {
        this.visitedUser = visitedUser;
    }
    public Set<Status> getStatus() {
        return status;
    }
    public void setStatus(Set<Status> status) {
        this.status = status;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Visiting getVisiting() {
        return visiting;
    }

    public void setVisiting(Visiting visiting) {
        this.visiting = visiting;
    }

    public boolean isVisited(){
        if (!(this.getVisiting()==null)){
            return true;
        }
        return false;
    }
    public boolean isOverdue(){
        if (this.getStatus().contains(Status.OVERDUE)){
            return true;
        }
        return false;
    }

    public boolean isWaitingToConfirm(){
        return this.getStatus().contains(Status.WAITING_TO_CONFIRM);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Schedule schedule = (Schedule) o;
        return Objects.equals(id, schedule.id) && Objects.equals(status, schedule.status)
                && Objects.equals(visitingWeek, schedule.visitingWeek);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status, visitingWeek);
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "id=" + id +
                ", status=" + status +
                ", visitingWeek='" + visitingWeek + '\'' +
                '}';
    }
}
