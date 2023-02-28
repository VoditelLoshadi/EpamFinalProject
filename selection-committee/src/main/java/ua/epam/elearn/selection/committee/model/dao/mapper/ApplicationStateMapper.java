package ua.epam.elearn.selection.committee.model.dao.mapper;

import ua.epam.elearn.selection.committee.model.entity.enums.ApplicationState;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ApplicationStateMapper {

    public ApplicationState extractFromResultSet(ResultSet rs) throws SQLException {
        return ApplicationState.getValue(rs.getLong(Fields.ID));

    }
}
