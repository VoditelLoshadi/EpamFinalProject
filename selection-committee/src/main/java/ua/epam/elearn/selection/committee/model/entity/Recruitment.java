package ua.epam.elearn.selection.committee.model.entity;

import ua.epam.elearn.selection.committee.model.dto.RecruitmentDto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class Recruitment implements Serializable {
    private static final long serialVersionUID = 100L;

    private long id;
    private String name;
    private long facultyId;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean closed;

    public Recruitment() {
    }

    public Recruitment(RecruitmentDto recruitmentDto) {
        this.name = recruitmentDto.getName();
        this.facultyId = Long.parseLong(recruitmentDto.getFacultyId());
        this.startDate = LocalDate.parse(recruitmentDto.getStartDate());
        this.endDate = LocalDate.parse(recruitmentDto.getEndDate());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(long facultyId) {
        this.facultyId = facultyId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public static class Builder {
        private final Recruitment newRecruitment;

        public Builder() {
            this.newRecruitment = new Recruitment();
        }

        public Builder addId(long id) {
            newRecruitment.setId(id);
            return this;
        }

        public Builder addName(String name) {
            newRecruitment.setName(name);
            return this;
        }

        public Builder addFacultyId(long facultyId) {
            newRecruitment.setFacultyId(facultyId);
            return this;
        }

        public Builder addStartDate(LocalDate startDate) {
            newRecruitment.setStartDate(startDate);
            return this;
        }

        public Builder addEndDate(LocalDate endDate) {
            newRecruitment.setEndDate(endDate);
            return this;
        }

        public Builder addClosed(boolean closed) {
            newRecruitment.setClosed(closed);
            return this;
        }

        public Recruitment build() {
            return newRecruitment;
        }

    }

    @Override
    public String toString() {
        return "Recruitment{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", facultyId=" + facultyId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", closed=" + closed +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recruitment that = (Recruitment) o;
        return id == that.id && facultyId == that.facultyId && closed == that.closed && Objects.equals(name, that.name) && Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, facultyId, startDate, endDate, closed);
    }
}
