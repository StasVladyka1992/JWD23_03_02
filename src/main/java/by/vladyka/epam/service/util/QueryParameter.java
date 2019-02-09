package by.vladyka.epam.service.util;

import by.vladyka.epam.service.exception.ServiceException;

public enum QueryParameter {
    PARSER, COMMAND, MENU_TYPE, LAST_DISH, CURRENT_PAGE, LANGUAGE;

    public static QueryParameter getEnumParameter(String parameter) throws ServiceException{
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
                throw new ServiceException("Unsupported parameter");
            }
        }
    }
}
