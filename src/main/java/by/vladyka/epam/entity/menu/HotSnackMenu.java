package by.vladyka.epam.entity.menu;


import by.vladyka.epam.entity.Dish;

import java.util.List;

public class HotSnackMenu extends Menu {

    public HotSnackMenu() {
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


//        HotSnackMenu menu1 = new HotSnackMenu(new ArrayList<>());
//        ArrayList <Description> list1 = new ArrayList<>();
//        list1.add(new Description("творожная",2));
//        Dish dish1 = new Dish("photo1", "Запеканка", list1, "222", Currency.BYN, "21" );
//        menu1.addDish(dish1);
//
//        HotSnackMenu menu2 = new HotSnackMenu(new ArrayList<>());
//        ArrayList <Description> list2 = new ArrayList<>();
//        Dish dish2 = new Dish("photo1", "Запеканка", list1, "222", Currency.BYN, "21" );
//        menu2.addDish(dish2);
//
//        System.out.println(menu1.equals(menu2));

}
