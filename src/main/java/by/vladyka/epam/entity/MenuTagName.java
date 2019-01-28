package by.vladyka.epam.entity;

public enum MenuTagName {
    HOT_SNACK_MENU, COLD_SNACK_MENU, BREAKFAST_MENU, DISH,
    PHOTO, NAME, PORTION,
    DESCRIPTION_PRICE, MENU,
    //Attributes
    ID, CURRENCY,
    DESCRIPTION, PRICE;

    public static MenuTagName getEnumElement (String element){
        switch (element){
            case "hot_snack_menu":{
                return HOT_SNACK_MENU;
            }
            case "cold_snack_menu":{
                return COLD_SNACK_MENU;
            }
            case "breakfast_menu":{
                return BREAKFAST_MENU;
            }
            case "dish":{
                return DISH;
            }
            case "photo":{
                return PHOTO;
            }
            case "name":{
                return NAME;
            }
            case "portion":{
                return PORTION;
            }
            case "description_price":{
                return DESCRIPTION_PRICE;
            }
            case "menu":{
                return MENU;
            }
            case "id":{
                return ID;
            }
            case "currency":{
                return CURRENCY;
            }
            case "description":{
                return DESCRIPTION;
            }
            case "price":{
                return PRICE;
            }
            //TODO сделать свое исключение
            default: throw new IllegalArgumentException();
        }

    }

}
