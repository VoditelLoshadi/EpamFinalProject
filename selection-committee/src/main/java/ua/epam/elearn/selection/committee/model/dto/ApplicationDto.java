package ua.epam.elearn.selection.committee.model.dto;

import ua.epam.elearn.selection.committee.model.entity.enums.ApplicationState;

import java.util.Arrays;

public class ApplicationDto {

    String userId;
    String recruitmentId;
    String state;
    String[] grades;

    public ApplicationDto() {
    }

    public ApplicationDto(String userId, String recruitmentId) {
        this.userId = userId;
        this.recruitmentId = recruitmentId;
    }


    public ApplicationDto(String userId, String recruitmentId, String[] grades) {
        this.userId = userId;
        this.recruitmentId = recruitmentId;
        this.grades = grades;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRecruitmentId() {
        return recruitmentId;
    }

    public void setRecruitmentId(String recruitmentId) {
        this.recruitmentId = recruitmentId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String[] getGrades() {
        return grades;
    }

    public void setGrades(String[] grades) {
        this.grades = grades;
    }

    @Override
    public String toString() {
        return "ApplicationDto{" +
                "userId='" + userId + '\'' +
                ", recruitmentId='" + recruitmentId + '\'' +
                ", state='" + state + '\'' +
                ", grades=" + Arrays.toString(grades) +
                '}';
    }
}
