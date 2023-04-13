package com.okei.visitingschedule.dto;

import com.okei.visitingschedule.entity.User;


public class ScheduleRequestDto{

    private User visitorUser;
    private User visitedUser;
    private String visitingWeek;


    public ScheduleRequestDto( User visitorUser, User visitedUser, String visitingWeek) {
        this.visitorUser = visitorUser;
        this.visitedUser = visitedUser;
        this.visitingWeek = visitingWeek;
    }


    public User getVisitorUser() {
        return visitorUser;
    }

    public void setVisitorUser(User visitorUser) {
        this.visitorUser = visitorUser;
    }

    public User getVisitedUser() {
        return visitedUser;
    }

    public void setVisitedUser(User visitedUser) {
        this.visitedUser = visitedUser;
    }

    public String getVisitingWeek() {
        return visitingWeek;
    }

    public void setVisitingWeek(String visitingWeek) {
        this.visitingWeek = visitingWeek;
    }
}