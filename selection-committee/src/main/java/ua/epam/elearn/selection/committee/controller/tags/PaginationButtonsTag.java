package ua.epam.elearn.selection.committee.controller.tags;


import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.tagext.SimpleTagSupport;

import java.io.IOException;

public class PaginationButtonsTag extends SimpleTagSupport {

    private int currentPage;
    private int pages;

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();

        try {
            if (pages > 1) {
                out.print(getPaginationStr());
            } else {
                // out.print("<input type='hidden' id='page' name='page' value='1'/>");
            }

        } catch (java.io.IOException ex) {
            throw new JspException("Error in Paginator tag", ex);
        }

    }

    private String getPaginationStr() {
        StringBuilder sb = new StringBuilder();

        String firstButton = "";
        String prevButton = "";
        String nextButton = "";
        String lastButton = "";
        String current = String.format("<li class=\"page-item myselect active\" value=\"%d\"><a class=\"page-link\" href=\"#\">%d</a></li>", currentPage, currentPage);

        // first
        if (currentPage > 1) {
            firstButton = " <li class=\"page-item myselect \" value=\"1\"><a class=\"page-link\" href=\"#\">1</a></li>";
        }

        // prev
        if (currentPage > 2) {

            prevButton = String.format("<li class=\"page-item myselect \" value=\"%d\"><a class=\"page-link\" href=\"#\">%d</a></li>", (currentPage - 1), (currentPage - 1));
        }

        // next
        if (currentPage < pages - 1) {
            nextButton = String.format("<li class=\"page-item myselect \" value=\"%d\"><a class=\"page-link\" href=\"#\">%d</a></li>", (currentPage + 1), (currentPage + 1));
        }

        // last
        if (currentPage < pages) {

            lastButton = String.format("<li class=\"page-item myselect \" value=\"%d\"><a class=\"page-link\" href=\"#\">%d</a></li>", pages, pages);

        }

        sb.append("<nav aria-label=\"Page navigation example\">\n" +
                "        <ul class=\"pagination justify-content-center\">");
        sb.append(firstButton)
                .append(prevButton)
                .append(current)
                .append(nextButton)
                .append(lastButton);
        sb.append("</ul>\n" +
                "    </nav>");

        return sb.toString();
    }
}
