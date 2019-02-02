package by.vladyka.epam.controller;
import by.vladyka.epam.controller.util.MenuSetter;
import by.vladyka.epam.controller.util.QueryParameter;
import by.vladyka.epam.entity.Dish;
import by.vladyka.epam.entity.menu.Menu;
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
import java.util.*;

import static by.vladyka.epam.controller.util.QueryParameter.*;

public class Controller extends HttpServlet {
    private static Logger logger = LogManager.getLogger(Controller.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //extracting parameters from request
        List<Menu> allMenu;
        String command = "";
        String menuType = "";
        int pagesNumber;
        int lastDish = 0;
        int currentPage = 1;
        String parser = "";
        String language;

        logger.info("Started extracting parameters from query");
        Enumeration<String> enumerations = req.getParameterNames();
        while (enumerations.hasMoreElements()) {
            String parameterName = enumerations.nextElement();
            QueryParameter enumParameter = getEnumParameter(parameterName);
            switch (enumParameter) {
                case PARSER: {
                    parser = req.getParameter(parameterName);
                    req.getSession(true).setAttribute("parser", parser);
                    break;
                }
                case COMMAND: {
                    command = req.getParameter(parameterName);
                    break;
                }
                case MENU_TYPE: {
                    menuType = req.getParameter(parameterName);
                    break;
                }
                case LAST_DISH: {
                    lastDish = Integer.parseInt(req.getParameter(parameterName));
                    break;
                }
                case CURRENT_PAGE: {
                    currentPage = Integer.parseInt(req.getParameter(parameterName));
                    break;
                }
                case LANGUAGE: {
                    language = req.getParameter(parameterName);
                    req.getSession(true).setAttribute("local", language);
                }
            }
        }
        logger.info("Extracting is done");
        //extracting whole menu
        CommandFactory factory = CommandFactory.getInstance();
        Command commandName = factory.getCommand(command);
        try {
            allMenu = commandName.execute(parser);

        } catch (ServiceException ex) {
            logger.error(ex);
            throw new ServletException();
        }

        logger.info("Setting parameters to request started");
        //menu to show on jsp
        List<Dish> fullParametrizedMenu = MenuSetter.setMenuList(allMenu, menuType);
        List<Dish> menuToShow = new ArrayList<>();
        int counter = 5;
        for (int i = lastDish; i < fullParametrizedMenu.size() && i < counter * currentPage; i++) {
            menuToShow.add(fullParametrizedMenu.get(i));
        }
        req.setAttribute("menu", menuToShow);
        //pagesNumber
        if (fullParametrizedMenu.size() % 5 == 0 && fullParametrizedMenu.size() >= 5) {
            pagesNumber = fullParametrizedMenu.size() / 5;
        } else {
            pagesNumber = fullParametrizedMenu.size() / 5 + 1;
        }
        req.setAttribute("pagesNumber", pagesNumber);
        //lastDish
        req.setAttribute("lastDish", lastDish);
        //currentPage
        req.setAttribute("currentPage", currentPage);
        //menuType
        req.setAttribute("menuType", menuType);
        //parser
        req.setAttribute("parser", parser);
        logger.info("Setting parameters to request is done");
        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/jsp/menu.jsp");
        try{
            dispatcher.forward(req, resp);
        }
        catch (IOException ex){
            logger.error(ex);
            throw  new IOException();
        }
    }


}
