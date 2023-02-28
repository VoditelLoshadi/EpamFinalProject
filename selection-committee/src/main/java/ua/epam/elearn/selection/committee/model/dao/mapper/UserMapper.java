package ua.epam.elearn.selection.committee.model.dao.mapper;

import ua.epam.elearn.selection.committee.model.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper {

    public User extractFromResultSet(ResultSet rs) throws SQLException {

        return new User.Builder()
                .addId(rs.getLong(Fields.ID))
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
}
