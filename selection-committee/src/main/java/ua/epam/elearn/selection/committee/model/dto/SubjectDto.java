package ua.epam.elearn.selection.committee.model.dto;

import java.util.Objects;

public class SubjectDto {

    private String nameEn;
    private String nameRu;
    private String nameUk;

    public SubjectDto() {
    }

    public SubjectDto(String nameEn, String nameRu, String nameUk) {
        this.nameEn = nameEn;
        this.nameRu = nameRu;
        this.nameUk = nameUk;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubjectDto that = (SubjectDto) o;
        return Objects.equals(nameEn, that.nameEn) && Objects.equals(nameRu, that.nameRu) && Objects.equals(nameUk, that.nameUk);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameEn, nameRu, nameUk);
    }
}
