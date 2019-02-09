package by.vladyka.epam.controller;
import by.vladyka.epam.service.Command;
import by.vladyka.epam.service.CommandFactory;

import by.vladyka.epam.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

public class Controller extends HttpServlet {
    private static Logger logger = LogManager.getLogger(Controller.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        CommandFactory commandFactory = CommandFactory.getInstance();
        Command command = commandFactory.getCommand(req.getParameter("command"));
        String page;
        try {
            page = command.execute(req);
        } catch (ServiceException e) {
            logger.error(e);
            throw new ServletException();
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher(page);
        try{
            dispatcher.forward(req, resp);
        }
        catch (IOException ex){
            logger.error(ex);
            throw new IOException();
        }
    }

}
