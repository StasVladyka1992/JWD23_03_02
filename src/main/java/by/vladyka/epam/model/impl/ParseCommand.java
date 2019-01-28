package by.vladyka.epam.model.impl;

import by.vladyka.epam.dao.DAOMenuParser;
import by.vladyka.epam.dao.XMLDaoFactory;
import by.vladyka.epam.entity.menu.Menu;
import by.vladyka.epam.model.Command;
import org.xml.sax.SAXException;

import javax.servlet.http.HttpServletRequest;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.util.List;

public class ParseCommand implements Command {
    private DAOMenuParser chosenParser;

    @Override
    public String execute(HttpServletRequest request) throws IOException, SAXException, XMLStreamException{
        XMLDaoFactory factory = XMLDaoFactory.getInstance();
        chosenParser = factory.getDAOParser(request.getParameter("parser"));
        List<Menu> menuList = null;
        menuList = chosenParser.startParsing();
        request.setAttribute("menu", menuList);
        String page = "WEB-INF/jsp/menu.jsp";
        return page;
    }


}
