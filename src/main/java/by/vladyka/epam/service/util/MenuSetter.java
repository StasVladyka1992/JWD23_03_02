package by.vladyka.epam.service.util;

import by.vladyka.epam.entity.Dish;
import by.vladyka.epam.entity.menu.Menu;
import by.vladyka.epam.service.exception.ServiceException;

import java.util.List;

public final class MenuSetter {
    public static List<Dish> setMenuList(List<Menu> allMenu, String menuType) throws ServiceException {
        for (Menu currentMenu :
                allMenu) {
            if (currentMenu.getClass().getSimpleName().equals(menuType)) {
                return currentMenu.getDishes();
            }
        }
        throw new ServiceException("No suitable menu wasn't found");
    }

}
