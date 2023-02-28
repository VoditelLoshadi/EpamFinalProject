package ua.epam.elearn.selection.committee.model.entity;

import ua.epam.elearn.selection.committee.model.dto.SubjectDto;

import java.io.Serializable;
import java.util.Objects;

public class Subject implements Serializable {
    private static final long serialVersionUID = 2041275512219239990L;

    private long id;
    private String nameEn;
    private String nameRu;
    private String nameUk;
    private Grade grade;

    public Subject() {
    }

    public Subject(SubjectDto subjectDto) {
        this.nameEn = subjectDto.getNameEn();
        this.nameRu = subjectDto.getNameRu();
        this.nameUk = subjectDto.getNameUk();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getNameRu() {
        return nameRu;
    }

    public void setNameRu(String nameRu) {
        this.nameRu = nameRu;
    }

    public String getNameUk() {
        return nameUk;
    }

    public void setNameUk(String nameUk) {
        this.nameUk = nameUk;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public static class Builder {
        private final Subject newSubject;

        public Builder() {
            this.newSubject = new Subject();
        }

        public Builder addId(long id) {
            newSubject.setId(id);
            return this;
        }

        public Builder addNameEn(String name) {
            newSubject.setNameEn(name);
            return this;
        }

        public Builder addNameRu(String name) {
            newSubject.setNameRu(name);
            return this;
        }

        public Builder addNameUk(String name) {
            newSubject.setNameUk(name);
            return this;
        }

        public Builder addGrade(Grade grade) {
            newSubject.setGrade(grade);
            return this;
        }

        public Subject build() {
            return newSubject;
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subject subject = (Subject) o;
        return id == subject.id && Objects.equals(nameEn, subject.nameEn) && Objects.equals(nameRu, subject.nameRu) && Objects.equals(nameUk, subject.nameUk) && Objects.equals(grade, subject.grade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameEn, nameRu, nameUk, grade);
    }

    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", nameEn='" + nameEn + '\'' +
                ", nameRu='" + nameRu + '\'' +
                ", nameUk='" + nameUk + '\'' +
                ", grade=" + grade +
                '}';
    }
}
