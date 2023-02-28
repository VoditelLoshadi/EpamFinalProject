package ua.epam.elearn.selection.committee.model.services;


import org.apache.logging.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.epam.elearn.selection.committee.model.dao.database.MailManager;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailService {
    Logger logger = LoggerFactory.getLogger(MailService.class);

    private final String BUDGET_MESSAGE = "Dear enrollee, \n" +
            "Congratulations! With great pleasure we offer you a budget place in our educational institution. \n" +
            "Sincerely, \n" +
            "Yutushui Alexandr, \n" +
            "Director of Admission";

    private final String CONTRACT_MESSAGE = "Dear enrollee, \n" +
            "Congratulations! With great pleasure we offer you a contract place in our educational institution. \n" +
            "Sincerely, \n" +
            "Yutushui Alexandr, \n" +
            "Director of Admission";

    private final String BAD_MESSAGE = "Dear enrollee, \n" +
            "We are very sorry to tell you this, but you did not pass the competition. We hope to see you in the next recruitment.\n" +
            "Sincerely, \n" +
            "Yutushui Alexandr, \n" +
            "Director of Admission";

    private final String FROM = "habatynchik@gmail.com";
    private final String MESSAGE_TITTLE = "University selection-committee";

    public void sendBudgetMessage(String recipientMail) {
        sendTest(recipientMail, BUDGET_MESSAGE);
    }

    public void sendContractMessage(String recipientMail) {
        sendTest(recipientMail, CONTRACT_MESSAGE);
    }

    public void sendBadMessage(String recipientMail) {
        sendTest(recipientMail, BAD_MESSAGE);
    }

    private void sendTest(String recipientMail, String text) {
        try {
            MimeMessage message = new MimeMessage(MailManager.getInstance().getSession());

            message.setFrom(new InternetAddress(FROM));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipientMail));
            message.setSubject(MESSAGE_TITTLE);
            message.setText(text);
            Transport.send(message);

            logger.info("Message successful send to {}", recipientMail);
        } catch (MessagingException mex) {
            logger.warn("Message was not sent to {}", recipientMail);
            mex.printStackTrace();
        }
    }


}
