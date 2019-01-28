package by.vladyka.epam.entity.menu;



import by.vladyka.epam.entity.Dish;

import java.util.List;

public abstract class Menu {
    private List <Dish> dishes;

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public void addDish(Dish dish){dishes.add(dish);}

    public Dish getDish(int index){
        return dishes.get(index);
    }

}
