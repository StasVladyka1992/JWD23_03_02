package by.vladyka.epam.dao;

import by.vladyka.epam.entity.menu.Menu;
import org.xml.sax.SAXException;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public interface DAOMenuParser {

    List<Menu> startParsing() throws SAXException, IOException, XMLStreamException;
    default String getXMLRelativeAddress(String resourceFileName){
        URL resource = DAOMenuParser.class.getResource("/" + resourceFileName);
        return resource.getPath();
    }
}
