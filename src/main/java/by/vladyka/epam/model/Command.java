package by.vladyka.epam.model;


import org.xml.sax.SAXException;

import javax.servlet.http.HttpServletRequest;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.util.List;

public interface Command{
    String execute(HttpServletRequest request) throws IOException, SAXException, XMLStreamException;
}
