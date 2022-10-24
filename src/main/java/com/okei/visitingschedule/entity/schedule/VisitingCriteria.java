package com.okei.visitingschedule.entity.schedule;

public enum VisitingCriteria {
    CRITERIA1("Some criteria"),
    CRITERIA2("Another criteria");

    private String critariaName;

    VisitingCriteria(String critariaName) {
        this.critariaName = critariaName;
    }

    public String getCritariaName() {
        return critariaName;
    }

    @Override
    public String toString() {
        return critariaName;
    }
}
