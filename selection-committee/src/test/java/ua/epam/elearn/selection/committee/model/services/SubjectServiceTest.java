package ua.epam.elearn.selection.committee.model.services;

import org.junit.Before;
import org.junit.Test;
import ua.epam.elearn.selection.committee.model.dao.SubjectDao;
import ua.epam.elearn.selection.committee.model.dto.SubjectDto;
import ua.epam.elearn.selection.committee.model.entity.Subject;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class SubjectServiceTest {

    private SubjectService subjectService;
    private SubjectDao subjectDao;

    private Subject SUBJECT;
    private SubjectDto SUBJECT_DTO;

    @Before
    public void setUp() throws Exception {
        subjectDao = mock(SubjectDao.class);
        subjectService = new SubjectService(subjectDao);

        SUBJECT = new Subject.Builder()
                .addNameEn("English")
                .addNameRu("Английский")
                .addNameUk("Английский")
                .build();

        SUBJECT_DTO = new SubjectDto(
                "English",
                "Английский",
                "Английский"
        );
    }

    @Test
    public void checkAddNewSubject() {
        assertDoesNotThrow(() -> subjectService.addNewSubject(SUBJECT_DTO));
        verify(subjectDao, times(1)).createSubject(SUBJECT);
    }

    @Test
    public void checkGetAllSubjects() {
        when(subjectDao.getAllSubjects()).thenReturn(Collections.singletonList(SUBJECT));
        assertNotNull(subjectService.getAllSubjects());
    }

    @Test
    public void checkGetRequiredSubjects() {
        when(subjectDao.getRequiredSubjectsByFacultyId(100L)).thenReturn(Collections.singletonList(new Subject()));
        assertNotNull(subjectService.getRequiredSubjects(100L));
    }

    @Test
    public void checkGetRequiredSubjectsByRecruitmentId() {
        when(subjectDao.getRequiredSubjectsByRecruitmentId(100L)).thenReturn(Collections.singletonList(new Subject()));
        assertNotNull(subjectService.getRequiredSubjectsByRecruitmentId(100L));
    }



}
