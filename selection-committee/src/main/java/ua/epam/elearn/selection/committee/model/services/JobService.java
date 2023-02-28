package ua.epam.elearn.selection.committee.model.services;


import org.apache.logging.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.epam.elearn.selection.committee.model.entity.Recruitment;

import java.util.List;

public class JobService implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(JobService.class);

    private final RecruitmentService recruitmentService;
    private final ApplicationService applicationService;

    public JobService() {
        recruitmentService = ServiceFactory.getInstance().createRecruitmentService();
        applicationService = ServiceFactory.getInstance().createApplicationService();
    }

    public JobService(RecruitmentService recruitmentService, ApplicationService applicationService) {
        this.recruitmentService = recruitmentService;
        this.applicationService = applicationService;
    }

    @Override
    public void run() {

        List<Recruitment> recruitmentList = recruitmentService.getAllOpenedOverdueRecruitments();
        for (Recruitment recruitment : recruitmentList) {
            logger.info("JobService found an expired recruitment {} ", recruitment);

            recruitmentService.closeRecruitment(recruitment.getId());
            applicationService.finalizeApplication(recruitment.getId());
        }
    }


}
