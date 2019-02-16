package by.vladyka.epam.service.impl;

import by.vladyka.epam.dao.exception.DAOException;
import by.vladyka.epam.dao.DAOMenuXml;
import by.vladyka.epam.dao.XMLDaoFactory;
import by.vladyka.epam.entity.Dish;
import by.vladyka.epam.service.CommandFactory;
import by.vladyka.epam.service.exception.ServiceException;
import by.vladyka.epam.entity.menu.Menu;
import by.vladyka.epam.service.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


public class ParseCommand implements Command {
    private static final Logger logger = LogManager.getLogger(ParseCommand.class);

    @Override
    public String execute(HttpServletRequest req) throws ServiceException {
        logger.error("Parsing xml started");
        HttpSession session = req.getSession(true);

        String parser = req.getParameter("parser");
        XMLDaoFactory factoryDAO = XMLDaoFactory.getInstance();
        DAOMenuXml daoMenuParser;
        try {
            daoMenuParser = factoryDAO.getDAOParser(parser);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        List<Menu> requestedMenu;
        try {
            requestedMenu = daoMenuParser.getMenu();
        } catch (DAOException e) {
            throw  new ServiceException(e);
        }
        session.setAttribute("allMenu", requestedMenu);
        logger.error("Parsing xml is done");
        String pageJSP = CommandFactory.getInstance().getCommand("SHOW_MENU").execute(req);

        return pageJSP;
    }
}
