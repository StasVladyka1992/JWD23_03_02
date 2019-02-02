package by.vladyka.epam.dao;

import by.vladyka.epam.dao.exception.DAOException;
import by.vladyka.epam.dao.impl.MenuDOMParser;
import by.vladyka.epam.dao.impl.MenuSAXParser;
import by.vladyka.epam.dao.impl.MenuStAXParser;

public class XMLDaoFactory {
    private static final XMLDaoFactory instance = new XMLDaoFactory();

    public static XMLDaoFactory getInstance() {
        return instance;
    }

    private XMLDaoFactory() {}

    public DAOMenuParser getDAOParser(String parser) throws DAOException{
        ParserType parserType = ParserType.valueOf(parser);
        switch (parserType) {
            case StAX: {
                return new MenuStAXParser();
            }
            case SAX: {
                return new MenuSAXParser();
            }
            case DOM: {
                return new MenuDOMParser();
            }
            default:
                throw new DAOException("Unsupported parser type");
        }
    }

    private enum ParserType {
        DOM, SAX, StAX
    }

}

