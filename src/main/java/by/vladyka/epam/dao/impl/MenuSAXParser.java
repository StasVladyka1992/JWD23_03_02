package by.vladyka.epam.dao.impl;


import by.vladyka.epam.dao.DAOMenuParser;
import by.vladyka.epam.dao.util.MenuSAXHandler;
import by.vladyka.epam.entity.Dish;
import by.vladyka.epam.entity.menu.Menu;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.List;

public class MenuSAXParser implements DAOMenuParser {
    @Override
    public List<Menu> startParsing() throws SAXException, IOException {
        XMLReader saxParser = XMLReaderFactory.createXMLReader();
        MenuSAXHandler menuSAXHandler = new MenuSAXHandler();
        saxParser.setContentHandler(menuSAXHandler);
        saxParser.parse(new InputSource(getXMLRaltiveAddress()));
        List<Menu> menuList = menuSAXHandler.getMenu();
//        for (Menu menu:
//                menuList) {
//            List<Dish> list = menu.getDishes();
//            for (Dish dish:
//                    list) {
//                System.out.println(dish.toString());
//            }
//        }
    return menuList;}
}
