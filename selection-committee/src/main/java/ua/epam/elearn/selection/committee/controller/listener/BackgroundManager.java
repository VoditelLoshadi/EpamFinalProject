package ua.epam.elearn.selection.committee.controller.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.epam.elearn.selection.committee.model.services.JobService;


import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class BackgroundManager implements ServletContextListener {
    private static final Logger logger = LoggerFactory.getLogger(BackgroundManager.class);

    private ScheduledExecutorService scheduler;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        scheduler = Executors.newSingleThreadScheduledExecutor();

        scheduler.scheduleAtFixedRate(new JobService(), 0, 1, TimeUnit.HOURS);
        logger.info("Scheduled Executor initialized");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        scheduler.shutdownNow();
        logger.info("Scheduled Executor destroyed");
    }
}
