package ua.epam.elearn.selection.committee.model.services;

import org.junit.Before;
import org.junit.Test;
import ua.epam.elearn.selection.committee.model.dao.RecruitmentDao;
import ua.epam.elearn.selection.committee.model.dao.UserDao;
import ua.epam.elearn.selection.committee.model.dto.RecruitmentDto;
import ua.epam.elearn.selection.committee.model.dto.UserDto;
import ua.epam.elearn.selection.committee.model.entity.Recruitment;
import ua.epam.elearn.selection.committee.model.entity.Subject;
import ua.epam.elearn.selection.committee.model.entity.User;
import ua.epam.elearn.selection.committee.model.services.util.SHA256PasswordEncoder;

import java.time.LocalDate;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class RecruitmentServiceTest {

    private RecruitmentService recruitmentService;
    private RecruitmentDao recruitmentDao;


    private RecruitmentDto recruitmentDto;
    private Recruitment recruitment;

    @Before
    public void setUp() throws Exception {
        recruitmentDao = mock(RecruitmentDao.class);
        recruitmentService = new RecruitmentService(recruitmentDao);

        recruitment = new Recruitment.Builder()
                .addName("Recruitment")
                .addFacultyId(100L)
                .addStartDate(LocalDate.now())
                .addEndDate(LocalDate.now().plusDays(20))
                .build();

        recruitmentDto = new RecruitmentDto(
                "Recruitment",
                "100",
                LocalDate.now().toString(),
                LocalDate.now().plusDays(20).toString());
    }

    @Test
    public void checkGetAllRecruitments() {
        when(recruitmentDao.getAllRecruitments()).thenReturn(Collections.singletonList(recruitment));
        assertNotNull(recruitmentService.getAllRecruitments());
    }

    @Test
    public void checkGetAllOpenedOverdueRecruitments() {
        when(recruitmentDao.getAllOpenedOverdueRecruitments()).thenReturn(Collections.singletonList(recruitment));
        assertNotNull(recruitmentService.getAllOpenedOverdueRecruitments());
    }

    @Test
    public void checkCloseRecruitment() {
        when(recruitmentDao.closeRecruitment(100L)).thenReturn(true);
        assertDoesNotThrow(() -> recruitmentService.closeRecruitment(100L));
    }

    @Test
    public void checkAddNewRecruitment() {
        assertDoesNotThrow(() -> recruitmentService.addNewRecruitment(recruitmentDto));
        verify(recruitmentDao, times(1)).addRecruitment(recruitment);
    }
}
