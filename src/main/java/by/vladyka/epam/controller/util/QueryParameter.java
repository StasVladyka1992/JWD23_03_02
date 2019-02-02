package by.vladyka.epam.controller.util;

public enum QueryParameter {
    PARSER, COMMAND, MENU_TYPE, LAST_DISH, CURRENT_PAGE, LANGUAGE;

    public static QueryParameter getEnumParameter(String parameter){
        switch (parameter){
            case ("command"):{
                return COMMAND;
            }
            case ("menuType"):{
                return MENU_TYPE;
            }
            case ("parser"):{
                return PARSER;
            }
            case ("language"):{
                return LANGUAGE;
            }
            case ("lastDish"):{
                return LAST_DISH;
            }
            case ("currentPage"):{
                return CURRENT_PAGE;
            }
            default:{
                return null;
            }
        }
    }
}
