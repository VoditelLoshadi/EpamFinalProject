package ua.epam.elearn.selection.committee.model.services;


import org.apache.logging.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.epam.elearn.selection.committee.model.dao.RecruitmentDao;
import ua.epam.elearn.selection.committee.model.dto.RecruitmentDto;
import ua.epam.elearn.selection.committee.model.entity.Application;
import ua.epam.elearn.selection.committee.model.entity.Faculty;
import ua.epam.elearn.selection.committee.model.entity.Recruitment;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class RecruitmentService {
    private static final int PAGE_SIZE = 4;

    Logger logger = LoggerFactory.getLogger(RecruitmentService.class);

    RecruitmentDao recruitmentDao;

    public RecruitmentService(RecruitmentDao recruitmentDao) {
        this.recruitmentDao = recruitmentDao;
    }

    public List<Recruitment> getAllRecruitments() {
        return recruitmentDao.getAllRecruitments();
    }

    public List<Recruitment> getAllOpenedOverdueRecruitments() {
        return recruitmentDao.getAllOpenedOverdueRecruitments();
    }

    public Recruitment getRecruitment(long id) {
        return recruitmentDao.getRecruitmentById(id);
    }

    public Recruitment getRecruitmentByApplicationId(long applicationId) {
        return recruitmentDao.getRecruitmentByApplicationId(applicationId);
    }

    public List<Recruitment> getAllRecruitmentsByFacultyId(long facultyId) {
        return recruitmentDao.getAllRecruitmentsByFacultyId(facultyId);
    }

    public Map<Recruitment, Faculty> getPaginationAllRecruitmentsWithFaculties(String[] filters, String order, int pageNum) {

        int offset = PAGE_SIZE * (pageNum - 1);

        return recruitmentDao.getPaginationAllRecruitmentsWithFaculties(filters, order, PAGE_SIZE, offset);
    }

    public int getCountOfFaculties(String[] filters) {
        return (int) Math.ceil(recruitmentDao.getCountOfFacultiesByFilter(filters) / (double) PAGE_SIZE);
    }

    public List<Recruitment> getAllNowOpenedRecruitmentsByFacultyId(long facultyId) {
        return recruitmentDao.getNowOpenedRecruitmentsByFacultyId(facultyId);
    }

    public Application getRecruitmentApplicationStatusByUserId(long recruitmentId, long userId) {
        return recruitmentDao.getRecruitmentApplicationStatusByUserId(recruitmentId, userId);
    }

    public void addNewRecruitment(RecruitmentDto recruitmentDto) {
        Recruitment recruitment = new Recruitment(recruitmentDto);
        recruitmentDao.addRecruitment(recruitment);
        logger.info("Has been created new Recruitment ({})", recruitment.getId());
    }

    public void closeRecruitment(long recruitmentId) {
        recruitmentDao.updateRecruitmentDate(recruitmentId, LocalDate.now());
        recruitmentDao.closeRecruitment(recruitmentId);
        logger.info("Recruitment ({}) was closed", recruitmentId);
    }


}
