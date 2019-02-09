package by.vladyka.epam.dao.impl;


import static by.vladyka.epam.dao.util.MenuTagName.getEnumElement;
import static javax.xml.stream.XMLStreamConstants.*;


import by.vladyka.epam.dao.DAOMenuXml;
import by.vladyka.epam.dao.exception.DAOException;
import by.vladyka.epam.entity.Currency;
import by.vladyka.epam.entity.Description;
import by.vladyka.epam.entity.Dish;
import by.vladyka.epam.dao.util.MenuTagName;
import by.vladyka.epam.entity.menu.BreakfastMenu;
import by.vladyka.epam.entity.menu.ColdSnackMenu;
import by.vladyka.epam.entity.menu.HotSnackMenu;
import by.vladyka.epam.entity.menu.Menu;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class DAOMenuXmlStAX implements DAOMenuXml {
    @Override
    public List<Menu> getMenu() throws DAOException {
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        InputStream input;
        List<Menu> menuList;
        try {
            input = new FileInputStream(getXMLRelativeAddress("menu.xml"));
            XMLStreamReader reader = xmlInputFactory.createXMLStreamReader(input);
            menuList= process(reader);
        }
        catch (FileNotFoundException|XMLStreamException e) {
            throw new DAOException(e);
        }
        return menuList;
    }

    private static List<Menu> process(XMLStreamReader reader) throws XMLStreamException {
        Description description = null;
        List<Description> descriptions = null;
        Menu breakfastMenu = null;
        Menu coldSnackMenu = null;
        Menu hotSnackMenu = null;
        List<Menu> menuList = null;
        List<Dish> dishes = null;
        Dish dish = null;
        MenuTagName elementName = null;

        while (reader.hasNext()) {

            //есть класс, XMLStreamConstants, где хранятся цифры, которые может вернуть метод next();
            int type = reader.next();
            switch (type) {
                case START_ELEMENT: {
                    elementName = getEnumElement(reader.getLocalName());
                    switch (elementName) {
                        case MENU: {
                            menuList = new ArrayList<>();
                            break;
                        }
                        case BREAKFAST_MENU: {
                            breakfastMenu = new BreakfastMenu();
                            dishes = new ArrayList<>();
                            break;
                        }
                        case HOT_SNACK_MENU: {
                            hotSnackMenu = new HotSnackMenu();
                            dishes = new ArrayList<>();
                            break;
                        }
                        case COLD_SNACK_MENU: {
                            coldSnackMenu = new ColdSnackMenu();
                            dishes = new ArrayList<>();
                            break;
                        }
                        case DISH: {
                            dish = new Dish();
                            for (int i = 0; i < reader.getAttributeCount(); i++) {
                                String attributeValue = reader.getAttributeValue(i);
                                MenuTagName enumElement = getEnumElement(reader.getAttributeLocalName(i));
                                switch (enumElement) {
                                    case ID: {
                                        dish.setId(attributeValue);
                                        break;
                                    }
                                    case CURRENCY: {
                                        dish.setCurency(Currency.getEnumCurrency(attributeValue));
                                        break;
                                    }
                                }
                            }
                            break;
                        }
                        case DESCRIPTION_PRICE: {
                            descriptions = new ArrayList<>();
                            String attributeValue = reader.getAttributeValue(0);
                            if (attributeValue==null){
                                break;
                            }
                            MenuTagName enumElement = getEnumElement(reader.getAttributeLocalName(0));
                            switch (enumElement) {
                                case ADDITIONAL_INFO: {
                                    dish.setAdditionalInfo(attributeValue);
                                    break;
                                }
                            }
                            break;
                        }
                        case DESCRIPTION: {
                            description = new Description();
                            for (int i = 0; i < reader.getAttributeCount(); i++) {
                                MenuTagName enumElement = getEnumElement(reader.getAttributeLocalName(i));
                                String attributeValue = reader.getAttributeValue(i);
                                switch (enumElement) {
                                    case DESCRIPTION: {
                                        description.setDescription(attributeValue);
                                        break;
                                    }
                                    case PRICE: {
                                        description.setPrice(Integer.valueOf(attributeValue));
                                        break;
                                    }
                                }
                            }
                            break;
                        }
                    }
                    break;
                }

                case CHARACTERS: {
                    String text = reader.getText().trim();
                    if (text.isEmpty()) {
                        break;
                    }
                    switch (elementName) {
                        case NAME: {
                            dish.setName(text);
                            break;
                        }
                        case PHOTO: {
                            dish.setPhoto(text);
                            break;
                        }
                        case DESCRIPTION_PRICE: {
                            descriptions = new ArrayList<>();
                            break;
                        }
                        case DESCRIPTION: {
                            description = new Description();
                            break;
                        }
                        case PORTION: {
                            dish.setPortion(text);
                            break;
                        }
                    }
                    break;
                }
                case END_ELEMENT: {
                    elementName = getEnumElement(reader.getLocalName());
                    switch (elementName) {
                        case DISH: {
                            dishes.add(dish);
                            break;
                        }
                        case HOT_SNACK_MENU: {
                            hotSnackMenu.setDishes(dishes);
                            break;
                        }
                        case COLD_SNACK_MENU: {
                            coldSnackMenu.setDishes(dishes);
                            break;
                        }
                        case BREAKFAST_MENU: {
                            breakfastMenu.setDishes(dishes);
                            break;
                        }
                        case DESCRIPTION: {
                            descriptions.add(description);
                            break;
                        }
                        case MENU: {
                            menuList.add(hotSnackMenu);
                            menuList.add(coldSnackMenu);
                            menuList.add(breakfastMenu);
                            break;
                        }
                        case DESCRIPTION_PRICE: {
                            dish.setDescription(descriptions);
                            break;
                        }
                    }
                }
            }
        }
        return menuList;
    }

}
