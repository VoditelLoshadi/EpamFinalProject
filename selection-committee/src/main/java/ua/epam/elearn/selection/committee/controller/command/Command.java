package ua.epam.elearn.selection.committee.controller.command;


import jakarta.servlet.http.HttpServletRequest;

public interface Command {

    String execute(HttpServletRequest request);

}
