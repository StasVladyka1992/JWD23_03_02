package by.vladyka.epam.service.impl;

import by.vladyka.epam.service.Command;
import javax.servlet.http.HttpServletRequest;

public class NoSuchCommand implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        return "error.jsp";
    }
}
