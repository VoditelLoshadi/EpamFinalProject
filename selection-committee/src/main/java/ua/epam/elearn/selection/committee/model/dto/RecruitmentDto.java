package ua.epam.elearn.selection.committee.model.dto;

import java.time.LocalDateTime;
import java.util.Objects;

public class RecruitmentDto {

    private String name;
    private String facultyId;
    private String startDate;
    private String endDate;
    private boolean closed;

    public RecruitmentDto() {
    }

    public RecruitmentDto(String name, String facultyId, String startDate, String endDate) {
        this.name = name;
        this.facultyId = facultyId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public RecruitmentDto(String name, String facultyId, String startDate, String endDate, boolean closed) {
        this.name = name;
        this.facultyId = facultyId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.closed = closed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(String facultyId) {
        this.facultyId = facultyId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecruitmentDto that = (RecruitmentDto) o;
        return closed == that.closed && Objects.equals(name, that.name) && Objects.equals(facultyId, that.facultyId) && Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, facultyId, startDate, endDate, closed);
    }
}
