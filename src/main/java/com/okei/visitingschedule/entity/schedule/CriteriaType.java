package com.okei.visitingschedule.entity.schedule;

public enum CriteriaType {
    THEORY("Теоретическое занятие"),
    PRACTICE("Практическое занятие"),
    PHYSICAL_CULTURE("Физическая культура");

    private String typeName;

    CriteriaType(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }


    @Override
    public String toString() {
        return "CriteriaType{" +
                "typeName='" + typeName + '\'' +
                '}';
    }
}
