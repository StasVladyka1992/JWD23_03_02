package by.vladyka.epam.dao;

import by.vladyka.epam.dao.exception.DAOException;
import by.vladyka.epam.entity.Dish;
import by.vladyka.epam.entity.menu.Menu;

import java.util.List;

public interface DAOMenu <T extends Menu> {
    List<T> getMenu() throws DAOException;
}
