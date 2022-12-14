package com.okei.visitingschedule.entity.schedule;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CriteriaScoreKey implements Serializable {
    private static final long serialVersionUID = -7586775913601298443L;
    @Column(name = "visiting_id")
    Long visitingId;
    @Column(name = "criteria_id")
    Long visitingCriteriaId;

    public CriteriaScoreKey(Long visitingId, Long visitingCriteriaId) {
        this.visitingId = visitingId;
        this.visitingCriteriaId = visitingCriteriaId;
    }

    public CriteriaScoreKey() {
    }

    public Long getVisitingId() {
        return visitingId;
    }

    public void setVisitingId(Long visitingId) {
        this.visitingId = visitingId;
    }

    public Long getVisitingCriteriaId() {
        return visitingCriteriaId;
    }

    public void setVisitingCriteriaId(Long visitingCriteriaId) {
        this.visitingCriteriaId = visitingCriteriaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CriteriaScoreKey that = (CriteriaScoreKey) o;
        return Objects.equals(visitingId, that.visitingId) && Objects.equals(visitingCriteriaId, that.visitingCriteriaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(visitingId, visitingCriteriaId);
    }
}
