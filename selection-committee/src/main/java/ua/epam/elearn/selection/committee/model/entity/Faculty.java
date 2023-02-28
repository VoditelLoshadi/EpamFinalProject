package ua.epam.elearn.selection.committee.model.entity;

import ua.epam.elearn.selection.committee.model.dto.FacultyDto;
import ua.epam.elearn.selection.committee.model.dto.SubjectDto;

import java.io.Serializable;
import java.util.List;

public class Faculty implements Serializable {
    private static final long serialVersionUID = 42L;

    private long id;
    private String name;
    private long generalCapacity;
    private long budgetCapacity;

    public Faculty() {
    }

    public Faculty(FacultyDto facultyDto) {
        this.name = facultyDto.getName();
        this.generalCapacity = Long.parseLong(facultyDto.getGeneralCapacity());
        this.budgetCapacity = Long.parseLong(facultyDto.getBudgetCapacity());
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

    public long getGeneralCapacity() {
        return generalCapacity;
    }

    public void setGeneralCapacity(long generalCapacity) {
        this.generalCapacity = generalCapacity;
    }

    public long getBudgetCapacity() {
        return budgetCapacity;
    }

    public void setBudgetCapacity(long budgetCapacity) {
        this.budgetCapacity = budgetCapacity;
    }

    public static class Builder {

        private final Faculty newFaculty;


        public Builder() {
            this.newFaculty = new Faculty();
        }

        public Builder addId(long id) {
            newFaculty.setId(id);
            return this;
        }

        public Builder addName(String name) {
            newFaculty.setName(name);
            return this;
        }

        public Builder addGeneralCapacity(long generalCapacity) {
            newFaculty.setGeneralCapacity(generalCapacity);
            return this;
        }

        public Builder addBudgetCapacity(long budgetCapacity) {
            newFaculty.setBudgetCapacity(budgetCapacity);
            return this;
        }



        public Faculty build() {
            return newFaculty;
        }
    }

    @Override
    public String toString() {
        return "Faculty{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", generalCapacity=" + generalCapacity +
                ", budgetCapacity=" + budgetCapacity +
                '}';
    }
}
