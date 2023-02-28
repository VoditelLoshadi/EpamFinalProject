package ua.epam.elearn.selection.committee.model.entity;

import ua.epam.elearn.selection.committee.model.dto.ApplicationDto;
import ua.epam.elearn.selection.committee.model.entity.enums.ApplicationState;

import java.io.Serializable;
import java.util.List;

public class Application implements Serializable {
    private static final long serialVersionUID = 999L;

    private long id;
    private User user;
    private Recruitment recruitment;
    private ApplicationState state;
    private double averageGrade;
    private List<Subject> subjects;

    public Application() {
    }

    public Application(ApplicationDto applicationDto) {
        this.user = new User.Builder().addId(Long.parseLong(applicationDto.getUserId())).build();
        this.recruitment = new Recruitment.Builder().addId(Long.parseLong(applicationDto.getRecruitmentId())).build();
        this.state = ApplicationState.valueOf(applicationDto.getState());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Recruitment getRecruitment() {
        return recruitment;
    }

    public void setRecruitment(Recruitment recruitment) {
        this.recruitment = recruitment;
    }

    public ApplicationState getState() {
        return state;
    }

    public void setState(ApplicationState state) {
        this.state = state;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public double getAverageGrade() {
        return averageGrade;
    }

    public void setAverageGrade(double averageGrade) {
        this.averageGrade = averageGrade;
    }

    public static class Builder {

        private final Application newApplication;


        public Builder() {
            this.newApplication = new Application();
        }

        public Builder addId(long id) {
            newApplication.setId(id);
            return this;
        }

        public Builder addUser(User user) {
            newApplication.setUser(user);
            return this;
        }


        public Builder addRecruitment(Recruitment recruitment) {
            newApplication.setRecruitment(recruitment);
            return this;
        }


        public Builder addState(ApplicationState state) {
            newApplication.setState(state);
            return this;
        }

        public Builder addAverageGrade(double averageGrade) {
            newApplication.setAverageGrade(averageGrade);
            return this;
        }

        public Builder addSubjects(List<Subject> subjects) {
            newApplication.setSubjects(subjects);
            return this;
        }


        public Application build() {
            return newApplication;
        }
    }
}
