package ua.epam.elearn.selection.committee.model.dao.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class MailManager {

    private static MailManager instance;
    private final Session session;

    private MailManager() {


        // Assuming you are sending email from through gmails smtp
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Get the Session object.// and pass username and password
        session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("yutaleksandr21@gmail.com", "gcmwtezdyoiahdfy");
            }
        });
        // Used to debug SMTP issues
        session.setDebug(true);
    }

    public static synchronized MailManager getInstance() {

        if (instance == null) {
            instance = new MailManager();
        }
        return instance;
    }

    public Session getSession()  {
        return session;
    }


}
