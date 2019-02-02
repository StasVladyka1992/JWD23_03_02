package by.vladyka.epam.service;


import by.vladyka.epam.service.exception.ServiceException;
import by.vladyka.epam.entity.menu.Menu;

import java.util.List;

public interface Command{
    List<Menu> execute(String parser) throws ServiceException;
}
