package ua.epam.elearn.selection.committee.model.dao;

import ua.epam.elearn.selection.committee.model.dao.impl.DaoFactoryImpl;

public abstract class DaoFactory {

    private static volatile DaoFactory daoFactory;

    protected DaoFactory() {
    }

    public abstract UserDao createUserDao();
    public abstract FacultyDao createFacultyDao();
    public abstract SubjectDao createSubjectDao();
    public abstract RecruitmentDao createRecruitmentDao();
    public abstract ApplicationDao createApplicationDao();

    public static DaoFactory getInstance() {
        if (daoFactory == null) {
            synchronized (DaoFactory.class) {
                if (daoFactory == null) {
                    daoFactory = new DaoFactoryImpl();
                }
            }
        }
        return daoFactory;
    }

}