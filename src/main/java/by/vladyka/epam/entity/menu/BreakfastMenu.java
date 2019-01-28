package by.vladyka.epam.entity.menu;



import by.vladyka.epam.entity.Dish;

import java.util.List;

public class BreakfastMenu extends Menu {
    public BreakfastMenu() {
    }

    public List<Dish> getDishes() {
        return super.getDishes();
    }

    public void setDishes(List<Dish> dishes) {
        super.setDishes(dishes);
    }

    public void addDish(Dish dish) {
        super.addDish(dish);
    }

    public Dish getDish(int index) {
        return super.getDish(index);
    }

}
