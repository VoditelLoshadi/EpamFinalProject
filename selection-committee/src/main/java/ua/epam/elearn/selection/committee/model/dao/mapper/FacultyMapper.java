package ua.epam.elearn.selection.committee.model.dao.mapper;

import ua.epam.elearn.selection.committee.model.entity.Faculty;
import ua.epam.elearn.selection.committee.model.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FacultyMapper {

    public Faculty extractFromResultSet(ResultSet rs) throws SQLException {

        return new Faculty.Builder()
                .addId(rs.getLong(Fields.ID))
                .addName(rs.getString(Fields.NAME))
                .addGeneralCapacity(rs.getLong(Fields.GENERAL_CAPACITY))
                .addBudgetCapacity(rs.getLong(Fields.BUDGET_CAPACITY))
                .build();
    }
}
