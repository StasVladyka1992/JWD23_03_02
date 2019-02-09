package by.vladyka.epam.dao;

import by.vladyka.epam.dao.exception.DAOException;
import by.vladyka.epam.dao.impl.DAOMenuXmlDOM;
import by.vladyka.epam.dao.impl.DAOMenuXmlSAX;
import by.vladyka.epam.dao.impl.DAOMenuXmlStAX;

public class XMLDaoFactory {
    private static final XMLDaoFactory instance = new XMLDaoFactory();

    public static XMLDaoFactory getInstance() {
        return instance;
    }

    private XMLDaoFactory() {}

    public DAOMenuXml getDAOParser(String parser) throws DAOException{
        ParserType parserType = ParserType.valueOf(parser);
        switch (parserType) {
            case StAX: {
                return new DAOMenuXmlStAX();
            }
            case SAX: {
                return new DAOMenuXmlSAX();
            }
            case DOM: {
                return new DAOMenuXmlDOM();
            }
            default:
                throw new DAOException("Unsupported parser type");
        }
    }

    private enum ParserType {
        DOM, SAX, StAX
    }

}

