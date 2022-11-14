package com.okei.visitingschedule.entity.schedule;

import javax.persistence.*;

@Entity
public class CriteriaScore {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private int score;

    public CriteriaScore(int score) {
        this.score = score;
    }

    public CriteriaScore() {
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
