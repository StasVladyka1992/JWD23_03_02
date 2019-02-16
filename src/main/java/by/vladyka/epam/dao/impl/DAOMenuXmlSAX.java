package by.vladyka.epam.dao.impl;


import by.vladyka.epam.dao.DAOMenuXml;
import by.vladyka.epam.dao.exception.DAOException;
import by.vladyka.epam.dao.util.MenuSAXHandler;
import by.vladyka.epam.entity.Dish;
import by.vladyka.epam.entity.menu.Menu;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.List;

public class DAOMenuXmlSAX implements DAOMenuXml {
    @Override
    public List<Menu> getMenu() throws DAOException {
        XMLReader saxParser;
        try {
            saxParser = XMLReaderFactory.createXMLReader();
        } catch (SAXException e) {
            throw  new DAOException(e);
        }
        MenuSAXHandler menuSAXHandler = new MenuSAXHandler();
        saxParser.setContentHandler(menuSAXHandler);
        try {
            saxParser.parse(new InputSource(getXMLRelativeAddress("menu.xml")));
        } catch (IOException|SAXException e) {
            e.printStackTrace();
        }
        List<Menu> menuList = menuSAXHandler.getMenu();

    return menuList;}
}
