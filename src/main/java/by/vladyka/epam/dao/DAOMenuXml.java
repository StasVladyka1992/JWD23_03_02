package by.vladyka.epam.dao;

import by.vladyka.epam.entity.menu.Menu;


import java.net.URL;

public interface DAOMenuXml extends DAOMenu <Menu>   {

    default String getXMLRelativeAddress(String resourceFileName){
        URL resource = DAOMenuXml.class.getResource("/" + resourceFileName);
        return resource.getPath();
    }
}
