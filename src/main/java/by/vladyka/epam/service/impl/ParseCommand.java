package by.vladyka.epam.service.impl;

import by.vladyka.epam.dao.DAOMenuParser;
import by.vladyka.epam.dao.XMLDaoFactory;
import by.vladyka.epam.dao.exception.DAOException;
import by.vladyka.epam.service.exception.ServiceException;
import by.vladyka.epam.entity.menu.Menu;
import by.vladyka.epam.service.Command;
import org.xml.sax.SAXException;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.util.List;

public class ParseCommand implements Command {
    private DAOMenuParser chosenParser;

    @Override
    public List<Menu> execute(String parser) throws ServiceException {
        XMLDaoFactory factory = XMLDaoFactory.getInstance();
        List<Menu> menuList;
        try {
            chosenParser = factory.getDAOParser(parser);
            menuList = chosenParser.startParsing();
        }
        catch (DAOException|SAXException|IOException|XMLStreamException ex){
            throw new ServiceException (ex);
        }
      return menuList;
    }


}
