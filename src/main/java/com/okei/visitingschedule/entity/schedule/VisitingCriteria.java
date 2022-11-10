package com.okei.visitingschedule.entity.schedule;

import javax.persistence.*;

@Entity
public class VisitingCriteria {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String critariaName;
    private String valueOfOnePoint;
    private String valueOfTwoPoint;
    private String valueOfThreePoint;
    private int score;

    public VisitingCriteria() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public VisitingCriteria(String critariaName, String valueOfOnePoint, String valueOfTwoPoint, String valueOfThreePoint) {
        this.critariaName = critariaName;
        this.valueOfOnePoint = valueOfOnePoint;
        this.valueOfTwoPoint = valueOfTwoPoint;
        this.valueOfThreePoint = valueOfThreePoint;
    }

    public String getCritariaName() {
        return critariaName;
    }

    public void setCritariaName(String critariaName) {
        this.critariaName = critariaName;
    }

    public void setValueOfOnePoint(String valueOfOnePoint) {
        this.valueOfOnePoint = valueOfOnePoint;
    }

    public void setValueOfTwoPoint(String valueOfTwoPoint) {
        this.valueOfTwoPoint = valueOfTwoPoint;
    }

    public void setValueOfThreePoint(String valueOfThreePoint) {
        this.valueOfThreePoint = valueOfThreePoint;
    }

    public int getScore() {
        return score;
    }

    public String getValueOfOnePoint() {
        return valueOfOnePoint;
    }

    public String getValueOfTwoPoint() {
        return valueOfTwoPoint;
    }

    public String getValueOfThreePoint() {
        return valueOfThreePoint;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return critariaName;
    }
}
