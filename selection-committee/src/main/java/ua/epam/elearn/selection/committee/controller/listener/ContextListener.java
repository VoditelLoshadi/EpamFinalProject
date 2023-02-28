package ua.epam.elearn.selection.committee.controller.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

import org.apache.logging.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.epam.elearn.selection.committee.model.dao.database.DBManager;
import ua.epam.elearn.selection.committee.model.dao.database.MailManager;



public class ContextListener implements ServletContextListener {

    private static final Logger logger = LoggerFactory.getLogger(ContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        if (DBManager.getInstance() == null) {
            logger.error("Unable to get database connection");
        } else {
            logger.info("Database connection received");
        }

        if (MailManager.getInstance() == null) {
            logger.error("Unable to get mail connection");
        } else {
            logger.info("Mail connection received");
        }




    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

        DBManager.getInstance().closeConnection();
        logger.debug("Database connection closed");
    }
}
