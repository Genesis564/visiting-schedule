package com.okei.visitingschedule.entity.schedule;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "criteria_score")
public class CriteriaScore {

    @EmbeddedId
    CriteriaScoreKey id;

    @ManyToOne
    @MapsId("visitingId")
    @JoinColumn(name = "visiting_id")
    Visiting visiting;

    @ManyToOne
    @MapsId("visitingCriteriaId")
    @JoinColumn(name = "criteria_id")
    VisitingCriteria visitingCriteria;

     int score;

    public CriteriaScore(CriteriaScoreKey id, Visiting visiting, VisitingCriteria visitingCriteria, int score) {
        this.id = id;
        this.visiting = visiting;
        this.visitingCriteria = visitingCriteria;
        this.score = score;
    }

    public CriteriaScore() {
    }

    public CriteriaScoreKey getId() {
        return id;
    }

    public void setId(CriteriaScoreKey id) {
        this.id = id;
    }

    public Visiting getVisiting() {
        return visiting;
    }

    public void setVisiting(Visiting visiting) {
        this.visiting = visiting;
    }

    public VisitingCriteria getVisitingCriteria() {
        return visitingCriteria;
    }

    public void setVisitingCriteria(VisitingCriteria visitingCriteria) {
        this.visitingCriteria = visitingCriteria;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CriteriaScore that = (CriteriaScore) o;
        return score == that.score && Objects.equals(id, that.id) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, score);
    }
}
