package by.vladyka.epam.service;


import by.vladyka.epam.service.exception.ServiceException;
import javax.servlet.http.HttpServletRequest;


public interface Command{
    String execute(HttpServletRequest req) throws ServiceException;
}
