package by.vladyka.epam.controller.util;

import by.vladyka.epam.entity.Dish;
import by.vladyka.epam.entity.menu.Menu;

import java.util.List;

public final class MenuSetter {
    public static List<Dish> setMenuList(List<Menu> allMenu, String menuType) {
        for (Menu currentMenu :
                allMenu) {
            if (currentMenu.getClass().getSimpleName().equals(menuType)) {
                return currentMenu.getDishes();
            }
        }
        return null;
    }

}
