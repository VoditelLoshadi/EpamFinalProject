package ua.epam.elearn.selection.committee.model.dao.mapper;

import ua.epam.elearn.selection.committee.model.entity.Faculty;
import ua.epam.elearn.selection.committee.model.entity.Recruitment;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RecruitmentMapper {

    private final String FACULTY_NAME = "faculty.name";

    public Recruitment extractRecruitmentFromResultSet(ResultSet rs) throws SQLException {

        return new Recruitment.Builder()
                .addId(rs.getLong(Fields.ID))
                .addName(rs.getString(Fields.NAME))
                .addFacultyId(rs.getLong(Fields.FACULTY_ID))
                .addStartDate(rs.getDate(Fields.START_DATE).toLocalDate())
                .addEndDate(rs.getDate(Fields.END_DATE).toLocalDate())
                .addClosed(rs.getBoolean(Fields.CLOSED))
                .build();
    }


    public Faculty extractFacultyFromResultSet(ResultSet rs) throws SQLException {

        return new Faculty.Builder()
                .addId(rs.getLong(Fields.FACULTY_ID))
                .addName(rs.getString(8))
                .addBudgetCapacity(rs.getLong(Fields.BUDGET_CAPACITY))
                .addGeneralCapacity(rs.getLong(Fields.GENERAL_CAPACITY))
                .build();
    }
}
