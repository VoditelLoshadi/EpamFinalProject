package ua.epam.elearn.selection.committee.controller.tags;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.tagext.SimpleTagSupport;
import ua.epam.elearn.selection.committee.model.entity.Subject;


import java.io.IOException;

public class SubjectLanguageTag extends SimpleTagSupport {

    private Subject subject;

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();

        if (getJspContext().findAttribute("lang").equals("uk")) {
            out.write(subject.getNameUk());
        } else if (getJspContext().findAttribute("lang").equals("ru")) {
            out.write(subject.getNameRu());
        } else {
            out.write(subject.getNameEn());
        }

    }
}
