package com.okei.visitingschedule.entity.schedule;

public enum VisitingCriteria {
    CRITERIA1("Some criteria"),
    CRITERIA2("Another criteria");

    private String critariaName;
    private int score;

    VisitingCriteria(String critariaName) {
        this.critariaName = critariaName;
    }

    public String getCritariaName() {
        return critariaName;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return critariaName;
    }
}
