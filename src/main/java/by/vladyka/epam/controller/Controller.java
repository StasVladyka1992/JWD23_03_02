package by.vladyka.epam.controller;


import by.vladyka.epam.model.Command;
import by.vladyka.epam.model.CommandFactory;
import org.xml.sax.SAXException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;

public class Controller extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String commandName = req.getParameter("command");
        CommandFactory factory = CommandFactory.getInstance();
        Command command = factory.getCommand(commandName);
        String addressPage  = null;
        try {
            addressPage = command.execute(req);
        }
         catch (SAXException e) {
            addressPage=JspName.ERROR_PAGE;
        }
        catch (XMLStreamException e){
            addressPage=JspName.ERROR_PAGE;
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher(addressPage);
        dispatcher.forward(req, resp);
    }
}
