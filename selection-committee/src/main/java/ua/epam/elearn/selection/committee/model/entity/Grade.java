package ua.epam.elearn.selection.committee.model.entity;

import java.util.List;
import java.util.Objects;

public class Grade {
    private long grade;
    private long subjectId;
    private long applicationId;

    public Grade() {
    }

    public Grade(long grade, long subjectId, long applicationId) {
        this.grade = grade;
        this.subjectId = subjectId;
        this.applicationId = applicationId;
    }

    public long getGrade() {
        return grade;
    }

    public void setGrade(long grade) {
        this.grade = grade;
    }

    public long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(long subjectId) {
        this.subjectId = subjectId;
    }

    public long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(long applicationId) {
        this.applicationId = applicationId;
    }

    public static class Builder {

        private final Grade newGrade;


        public Builder() {
            this.newGrade = new Grade();
        }



        public Builder addGrade(long grade) {
            newGrade.setGrade(grade);
            return this;
        }

        public Builder addSubjectId(long subjectId) {
            newGrade.setSubjectId(subjectId);
            return this;
        }
        public Builder addApplicationId(long applicationId) {
            newGrade.setApplicationId(applicationId);
            return this;
        }

        public Grade build() {
            return newGrade;
        }
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Grade grade1 = (Grade) o;
        return grade == grade1.grade && subjectId == grade1.subjectId && applicationId == grade1.applicationId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(grade, subjectId, applicationId);
    }

    @Override
    public String toString() {
        return "Grade{" +
                "grade=" + grade +
                ", subjectId=" + subjectId +
                ", applicationId=" + applicationId +
                '}';
    }
}
