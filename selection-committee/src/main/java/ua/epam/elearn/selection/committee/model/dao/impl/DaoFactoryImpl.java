package ua.epam.elearn.selection.committee.model.dao.impl;

import ua.epam.elearn.selection.committee.model.dao.*;

public class DaoFactoryImpl extends DaoFactory {

    public DaoFactoryImpl() {
    }

    @Override
    public UserDao createUserDao() {
        return new UserDaoImpl();
    }

    @Override
    public FacultyDao createFacultyDao() {
        return new FacultyDaoImpl();
    }

    @Override
    public SubjectDao createSubjectDao() {
        return new SubjectDaoImp();
    }

    @Override
    public RecruitmentDao createRecruitmentDao() {
        return new RecruitmentDaoImpl();
    }

    @Override
    public ApplicationDao createApplicationDao() {
        return new ApplicationDaoImpl();
    }

}
