package by.vladyka.epam.service.impl;

import by.vladyka.epam.service.util.MenuSetter;
import by.vladyka.epam.service.util.QueryParameter;
import by.vladyka.epam.entity.Dish;
import by.vladyka.epam.entity.menu.Menu;
import by.vladyka.epam.service.Command;
import by.vladyka.epam.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import static by.vladyka.epam.service.util.QueryParameter.getEnumParameter;

public class ShowMenuCommand implements Command {
    private static final Logger logger = LogManager.getLogger(ShowMenuCommand.class);
    private static int ITEMS_ON_PAGE =5;

    @Override
    public String execute(HttpServletRequest req) throws ServiceException {
        logger.info("Started extracting and setting request parameters for menu page");
        //default values
        int currentPage = 1;

        HttpSession session = req.getSession(false);

        Enumeration<String> enumerations = req.getParameterNames();
        while (enumerations.hasMoreElements()) {
            String parameterName = enumerations.nextElement();
            QueryParameter enumParameter = getEnumParameter(parameterName);
            switch (enumParameter) {
                case LANGUAGE: {
                    String language = req.getParameter(parameterName);
                    session.setAttribute("local", language);
                    break;
                }
                case MENU_TYPE: {
                    String menuType = req.getParameter(parameterName);
                    session.setAttribute("menuType", menuType);
                    break;
                }
                case CURRENT_PAGE: {
                    currentPage = Integer.parseInt(req.getParameter(parameterName));
                    break;
                }
            }
        }

        req.setAttribute("currentPage", currentPage);

        setMenuToShow(req);
        setPagesNumber(req);
        setDishesToShow(req);
        logger.info("Extracting and setting request parameters for menu page is done");
        return "WEB-INF/jsp/menu.jsp";
    }

    private void setMenuToShow(HttpServletRequest req) throws ServiceException {
        HttpSession session = req.getSession(false);
        List<Menu> allMenu = (List<Menu>) session.getAttribute("allMenu");
        String menuType = (String) session.getAttribute("menuType");
        List<Dish> menuToShow = MenuSetter.setMenuList(allMenu, menuType);
        session.setAttribute("menuToShow", menuToShow);
    }

    private void setPagesNumber(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        List<Dish> allMenuToShow = (List<Dish>)session.getAttribute("menuToShow");
        if (allMenuToShow.size() % 5 == 0 && allMenuToShow.size() >= 5) {
            req.setAttribute("pagesNumber", allMenuToShow.size() / 5);
        } else {
            req.setAttribute("pagesNumber", (allMenuToShow.size() / 5 + 1));
        }
    }

    private void setDishesToShow(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        List<Dish> menuToShow =  (List<Dish>)session.getAttribute("menuToShow");
        int currentPage = (Integer) req.getAttribute("currentPage");
        int lastDish = (currentPage-1)*ITEMS_ON_PAGE;
        List<Dish> dishesToShow = new ArrayList<>();
        //5 - dishes on the page
        for (int i = lastDish; i < menuToShow.size() && i < 5 * currentPage; i++) {
            dishesToShow.add(menuToShow.get(i));
        }
        req.setAttribute("menu", dishesToShow);
    }


}
