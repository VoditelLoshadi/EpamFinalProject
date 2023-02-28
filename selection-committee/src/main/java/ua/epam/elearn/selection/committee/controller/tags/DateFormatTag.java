package ua.epam.elearn.selection.committee.controller.tags;


import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.tagext.SimpleTagSupport;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateFormatTag extends SimpleTagSupport {

    private LocalDate date;
    private final DateTimeFormatter dateFormatUK = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private final DateTimeFormatter dateFormatUSA = DateTimeFormatter.ofPattern("MM-dd-yyyy");

    public void setDateTime(LocalDate date) {
        this.date = date;
    }

    @Override
    public void doTag() throws JspException, IOException {
        if (getJspContext().findAttribute("lang").equals("uk")) {
            getJspContext().getOut().write(date.format(dateFormatUK));
        } else if (getJspContext().findAttribute("lang").equals("ru")) {
            getJspContext().getOut().write(date.format(dateFormatUK));
        } else
            getJspContext().getOut().write(date.format(dateFormatUSA));
    }
}
