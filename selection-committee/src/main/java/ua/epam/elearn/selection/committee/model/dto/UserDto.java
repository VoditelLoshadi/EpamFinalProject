package ua.epam.elearn.selection.committee.model.dto;

import java.util.Objects;

public class UserDto {

    private String login;
    private String email;
    private String password;
    private String passwordCopy;
    private String firstName;
    private String secondName;
    private String patronymic;
    private String city;
    private String region;
    private String institution;
    private long roleId;

    public UserDto() {
    }


    public UserDto(String login, String email, String password, String passwordCopy, String firstName, String secondName, String patronymic, String city, String region, String institution) {
        this.login = login;
        this.email = email;
        this.password = password;
        this.passwordCopy = passwordCopy;
        this.firstName = firstName;
        this.secondName = secondName;
        this.patronymic = patronymic;
        this.city = city;
        this.region = region;
        this.institution = institution;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordCopy() {
        return passwordCopy;
    }

    public void setPasswordCopy(String passwordCopy) {
        this.passwordCopy = passwordCopy;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return Objects.equals(login, userDto.login) && Objects.equals(email, userDto.email) && Objects.equals(password, userDto.password) && Objects.equals(passwordCopy, userDto.passwordCopy) && Objects.equals(firstName, userDto.firstName) && Objects.equals(secondName, userDto.secondName) && Objects.equals(patronymic, userDto.patronymic) && Objects.equals(city, userDto.city) && Objects.equals(region, userDto.region) && Objects.equals(institution, userDto.institution);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, email, password, passwordCopy, firstName, secondName, patronymic, city, region, institution);
    }
}
