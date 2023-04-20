package com.okei.visitingschedule.entity.schedule;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
@Entity
@Table(name="visiting_criteria")
public class VisitingCriteria{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String criteriaName;
    @Column(length = 9999)
    private String valueOfOnePoint;
    @Column(length = 9999)
    private String valueOfTwoPoint;
    @Column(length = 9999)
    private String valueOfThreePoint;

    @ElementCollection(targetClass = CriteriaType.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "criteria_type", joinColumns = @JoinColumn(name = "criteria_id"))
    @Enumerated(EnumType.STRING)
    private Set<CriteriaType> criteriaTypes;
    @OneToMany(mappedBy = "visitingCriteria")
    Set<CriteriaScore> criteriaScore;

    public VisitingCriteria(String criteriaName, String valueOfOnePoint, String valueOfTwoPoint, String valueOfThreePoint,Set<CriteriaType> types) {
        this.criteriaName = criteriaName;
        this.valueOfOnePoint = valueOfOnePoint;
        this.valueOfTwoPoint = valueOfTwoPoint;
        this.valueOfThreePoint = valueOfThreePoint;
        this.criteriaTypes = types;
    }

    public VisitingCriteria() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCriteriaName() {
        return criteriaName;
    }

    public void setCriteriaName(String criteriaName) {
        this.criteriaName = criteriaName;
    }

    public String getValueOfOnePoint() {
        return valueOfOnePoint;
    }

    public void setValueOfOnePoint(String valueOfOnePoint) {
        this.valueOfOnePoint = valueOfOnePoint;
    }

    public String getValueOfTwoPoint() {
        return valueOfTwoPoint;
    }

    public void setValueOfTwoPoint(String valueOfTwoPoint) {
        this.valueOfTwoPoint = valueOfTwoPoint;
    }

    public String getValueOfThreePoint() {
        return valueOfThreePoint;
    }

    public void setValueOfThreePoint(String valueOfThreePoint) {
        this.valueOfThreePoint = valueOfThreePoint;
    }

    public Set<CriteriaScore> getCriteriaScore() {
        return criteriaScore;
    }

    public void setCriteriaScore(Set<CriteriaScore> criteriaScore) {
        this.criteriaScore = criteriaScore;
    }

    public Set<CriteriaType> getCriteriaTypes() {
        return criteriaTypes;
    }

    public void setCriteriaTypes(Set<CriteriaType> criteriaTypes) {
        this.criteriaTypes = criteriaTypes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VisitingCriteria that = (VisitingCriteria) o;
        return Objects.equals(id, that.id) && Objects.equals(criteriaName, that.criteriaName) && Objects.equals(valueOfOnePoint, that.valueOfOnePoint) && Objects.equals(valueOfTwoPoint, that.valueOfTwoPoint) && Objects.equals(valueOfThreePoint, that.valueOfThreePoint);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, criteriaName, valueOfOnePoint, valueOfTwoPoint, valueOfThreePoint);
    }

    @Override
    public String toString() {
        return criteriaName;
    }
}
