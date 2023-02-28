package ua.epam.elearn.selection.committee.model.services;


import org.apache.logging.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.epam.elearn.selection.committee.model.dao.FacultyDao;
import ua.epam.elearn.selection.committee.model.dto.FacultyDto;
import ua.epam.elearn.selection.committee.model.entity.Faculty;
import ua.epam.elearn.selection.committee.model.exception.admin.FacultyNameIsReservedException;

import java.util.List;

public class FacultyService {

    private final Logger logger = LoggerFactory.getLogger(FacultyService.class);

    private static final int PAGE_SIZE = 4;

    private final FacultyDao facultyDao;

    public FacultyService(FacultyDao facultyDao) {
        this.facultyDao = facultyDao;
    }

    public List<Faculty> findAllFaculties() {
        return facultyDao.getAllFaculties();
    }

    public int getCountOfFaculties() {
        return (int) Math.ceil(facultyDao.getAllFacultiesSize()  / (double) PAGE_SIZE);
    }

    public List<Faculty> getPaginationAllFaculties(String order, int pageNum) {
        int offset = PAGE_SIZE * (pageNum - 1) ;
        return facultyDao.getPaginationAllFaculties(order, PAGE_SIZE, offset);
    }

    public Faculty findFacultyByName(String name) {
        return facultyDao.getFacultyByName(name);
    }

    public Faculty getFacultyById(Long id) {
        return facultyDao.getFacultyById(id);
    }

    public void addNewFaculty(FacultyDto facultyDto) throws FacultyNameIsReservedException {
        checkFacultyNameIsUnique(facultyDto.getName());
        Faculty faculty = new Faculty(facultyDto);
        facultyDao.addFaculty(faculty);
    }

    public boolean isExistedByFacultyId(Long facultyId) {
        return facultyDao.isExistedByFacultyId(facultyId);
    }

    private void checkFacultyNameIsUnique(String facultyName) throws FacultyNameIsReservedException {
        if (facultyDao.getFacultyByName(facultyName) != null) throw new FacultyNameIsReservedException();
    }

    public void deleteFaculty(Long id) {

        facultyDao.delete(id);
        logger.info("Faculty (id = {}) has been deleted", id);
    }

    public void updateFaculty(FacultyDto facultyDto) throws FacultyNameIsReservedException {

        if (facultyDao.getFacultyByName(facultyDto.getName())!= null && facultyDao.getFacultyByName(facultyDto.getName()).getId()!=Long.parseLong(facultyDto.getId())){

            throw new FacultyNameIsReservedException();
        }

        Faculty faculty = new Faculty(facultyDto);
        faculty.setId(Long.parseLong(facultyDto.getId()));
        
        facultyDao.update(faculty);
        logger.info("Faculty (id = {}) has been updated", faculty.getId());
    }
    /*

    public List<Faculty> findAllFacultiesWhereOpenedRecruitments() {
        return facultyDao.getAllFacultiesWhereOpenedRecruitments();
    }

     */
}
