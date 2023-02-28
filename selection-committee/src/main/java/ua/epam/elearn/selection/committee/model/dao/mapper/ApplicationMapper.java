package ua.epam.elearn.selection.committee.model.dao.mapper;

import ua.epam.elearn.selection.committee.model.entity.Application;
import ua.epam.elearn.selection.committee.model.entity.Recruitment;
import ua.epam.elearn.selection.committee.model.entity.User;
import ua.epam.elearn.selection.committee.model.entity.enums.ApplicationState;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ApplicationMapper {

    private final ApplicationStateMapper applicationStateMapper = new ApplicationStateMapper();

    public Application extractFromResultSet(ResultSet rs) throws SQLException {

        return new Application.Builder()
                .addId(rs.getLong(Fields.ID))
                .addUser(extractUserResultSet(rs))
                .addRecruitment(extractRecruitmentFromResultSet(rs))
                .addState(extractApplicationStateFromResultSet(rs))
                .addAverageGrade(rs.getLong(Fields.GRADE))
                .build();
    }

    private User extractUserResultSet(ResultSet rs) throws SQLException {
        return new User.Builder()
                .addId(rs.getLong(Fields.USER_ID))
                .addLogin(rs.getString(Fields.LOGIN))
                .addEmail(rs.getString(Fields.EMAIL))
                .addPassword(rs.getString(Fields.PASSWORD))
                .addFirstName(rs.getString(Fields.FIRST_NAME))
                .addSecondName(rs.getString(Fields.SECOND_NAME))
                .addPatronymic(rs.getString(Fields.PATRONYMIC))
                .addCity(rs.getString(Fields.CITY))
                .addRegion(rs.getString(Fields.REGION))
                .addInstitution(rs.getString(Fields.INSTITUTION))
                .addBlocked(rs.getBoolean(Fields.IS_BLOCKED))
                .addRoles(rs.getLong(Fields.ROLE_ID))
                .build();

    }

    private Recruitment extractRecruitmentFromResultSet(ResultSet rs) throws SQLException {
        return new Recruitment.Builder()
                .addId(rs.getLong(Fields.RECRUITMENT_ID))
                .addName(rs.getString(Fields.NAME))
                .addFacultyId(rs.getLong(Fields.FACULTY_ID))
                .addStartDate(rs.getDate(Fields.START_DATE).toLocalDate())
                .addEndDate(rs.getDate(Fields.END_DATE).toLocalDate())
                .addClosed(rs.getBoolean(Fields.CLOSED))
                .build();
    }

    private ApplicationState extractApplicationStateFromResultSet(ResultSet rs) throws SQLException {
        return ApplicationState.getValue(rs.getLong(Fields.APPLICATION_STATE_ID));
    }
}
