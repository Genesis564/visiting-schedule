package com.okei.visitingschedule.entity.schedule;

import com.okei.visitingschedule.entity.User;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="schedule")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ElementCollection(targetClass = Status.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "schedule_status", joinColumns = @JoinColumn(name = "schedule_id"))
    @Enumerated(EnumType.STRING)
    private Set<Status> status;

    @ManyToOne
    @JoinColumn(name = "visiter_user_id")
    private User visitorUser;
    @ManyToOne
    @JoinColumn(name = "visited_user_id")
    private User visitedUser;
    private String visitingWeek;


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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
