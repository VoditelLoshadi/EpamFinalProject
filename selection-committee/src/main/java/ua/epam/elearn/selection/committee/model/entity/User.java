package ua.epam.elearn.selection.committee.model.entity;

import ua.epam.elearn.selection.committee.model.dto.UserDto;
import ua.epam.elearn.selection.committee.model.entity.enums.Role;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {
    private static final long serialVersionUID = 47L;

    private long id;
    private String login;
    private String email;
    private transient String password;
    private String firstName;
    private String secondName;
    private String patronymic;
    private String city;
    private String region;
    private String institution;
    private boolean blocked = false;
    private long roleId;

    public User() {
    }

    public User(UserDto userDto) {
        this.login = userDto.getLogin();
        this.email = userDto.getEmail();
        this.password = userDto.getPassword();
        this.firstName = userDto.getFirstName();
        this.secondName = userDto.getSecondName();
        this.patronymic = userDto.getPatronymic();
        this.city = userDto.getCity();
        this.region = userDto.getRegion();
        this.institution = userDto.getInstitution();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public static class Builder{
        private final User newUser;

        public Builder() {
            newUser = new User();
        }

        public Builder addId(long id){
            newUser.setId(id);
            return this;
        }

        public Builder addLogin(String login){
            newUser.setLogin(login);
            return this;
        }

        public Builder addEmail(String email){
            newUser.setEmail(email);
            return this;
        }

        public Builder addPassword(String password){
            newUser.setPassword(password);
            return this;
        }

        public Builder addFirstName(String firstName){
            newUser.setFirstName(firstName);
            return this;
        }

        public Builder addSecondName(String secondName){
            newUser.setSecondName(secondName);
            return this;
        }

        public Builder addPatronymic(String patronymic){
            newUser.setPatronymic(patronymic);
            return this;
        }

        public Builder addCity(String city){
            newUser.setCity(city);
            return this;
        }
        public Builder addRegion(String region){
            newUser.setRegion(region);
            return this;
        }

        public Builder addInstitution(String institution){
            newUser.setInstitution(institution);
            return this;
        }

        public Builder addBlocked(boolean blocked){
            newUser.setBlocked(blocked);
            return this;
        }

        public Builder addRoles(long role){
            newUser.setRoleId(role);
            return this;
        }

        public User build(){
            return newUser;
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", city='" + city + '\'' +
                ", region='" + region + '\'' +
                ", institution='" + institution + '\'' +
                ", blocked=" + blocked +
                ", role=" + roleId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && blocked == user.blocked && roleId == user.roleId && Objects.equals(login, user.login) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(firstName, user.firstName) && Objects.equals(secondName, user.secondName) && Objects.equals(patronymic, user.patronymic) && Objects.equals(city, user.city) && Objects.equals(region, user.region) && Objects.equals(institution, user.institution);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, email, password, firstName, secondName, patronymic, city, region, institution, blocked, roleId);
    }
}
