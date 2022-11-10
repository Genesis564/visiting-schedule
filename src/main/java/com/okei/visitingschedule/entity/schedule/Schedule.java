package com.okei.visitingschedule.entity.schedule;

import com.okei.visitingschedule.entity.User;

import javax.persistence.*;

@Entity
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "visiter_user_id")
    private User visitorUser;
    @ManyToOne
    @JoinColumn(name = "visited_user_id")
    private User visitedUser;
    private String visitingWeek;

    @OneToOne
    @JoinColumn(name = "visiting_id")
    private Visiting visiting;


    public Schedule(User visitorUser, User visitedUser, String visitingWeek, Visiting visiting) {
        this.visitorUser = visitorUser;
        this.visitedUser = visitedUser;
        this.visitingWeek = visitingWeek;
        this.visiting = visiting;
    }

    public Schedule() {
    }

    public Visiting getVisiting() {
        return visiting;
    }

    public void setVisiting(Visiting visiting) {
        this.visiting = visiting;
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
