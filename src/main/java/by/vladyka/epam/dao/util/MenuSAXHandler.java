package by.vladyka.epam.dao.util;


import by.vladyka.epam.entity.Currency;
import by.vladyka.epam.entity.Description;
import by.vladyka.epam.entity.Dish;
import by.vladyka.epam.entity.MenuTagName;
import by.vladyka.epam.entity.menu.BreakfastMenu;
import by.vladyka.epam.entity.menu.ColdSnackMenu;
import by.vladyka.epam.entity.menu.HotSnackMenu;
import by.vladyka.epam.entity.menu.Menu;
import org.xml.sax.Attributes;

import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

import static by.vladyka.epam.entity.MenuTagName.*;


public class MenuSAXHandler extends DefaultHandler  {

    private Dish dish;
    private Description description;
    private List<Description> descriptions;
    private StringBuilder text;
    private Menu breakfastMenu;
    private Menu coldSnackMenu;
    private Menu hotSnackMenu;
    private List<Menu> menuList;
    private MenuTagName currentMenuType;

    public List<Menu> getMenu() {
        return menuList;
    }

    @Override
    public void startDocument() {
        System.out.println("SAX parsing started");
    }

    @Override
    public void endDocument() {
        System.out.println("SAX parsing ended");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        text = new StringBuilder();

        switch (getEnumElement(localName)) {
            case MENU: {
                return;
            }
            case BREAKFAST_MENU: {
                breakfastMenu = new BreakfastMenu();
                breakfastMenu.setDishes(new ArrayList<>());
                currentMenuType = BREAKFAST_MENU;
                break;
            }
            case COLD_SNACK_MENU: {
                coldSnackMenu = new ColdSnackMenu();
                coldSnackMenu.setDishes(new ArrayList<>());
                currentMenuType = COLD_SNACK_MENU;
                break;
            }
            case HOT_SNACK_MENU: {
                hotSnackMenu = new HotSnackMenu();
                hotSnackMenu.setDishes(new ArrayList<>());
                currentMenuType = HOT_SNACK_MENU;
                break;
            }
            case DISH: {
                dish = new Dish();
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
            default:
                break;
        }
        // getting attributes
        for (int i = 0; i < attributes.getLength(); i++) {
            String attributeName = attributes.getLocalName(i);
            String attributeValue = attributes.getValue(i);
            switch (getEnumElement(attributeName)) {
                case ID: {
                    dish.setId(attributeValue);
                    break;
                }
                case CURRENCY: {
                    dish.setCurency(Currency.getEnumCurrency(attributeValue));
                    break;
                }
                case DESCRIPTION: {
                    description.setDescription(attributeValue);
                    break;
                }
                case PRICE: {
                    description.setPrice(Integer.valueOf(attributeValue));
                    break;
                }
                default:
                    break;
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        // getting tags values
        switch (getEnumElement(localName)) {

            case PHOTO: {
                dish.setPhoto(text.toString());
                break;
            }
            case NAME: {
                dish.setName(text.toString());
                break;
            }
            case DESCRIPTION: {
                descriptions.add(description);
                break;
            }
            case DESCRIPTION_PRICE:{
                dish.setDescription(descriptions);
                break;
            }
            case PORTION: {
                dish.setPortion(text.toString());
                break;
            }
            case DISH: {
                switch (currentMenuType) {
                    case HOT_SNACK_MENU: {
                        hotSnackMenu.addDish(dish);
                        break;
                    }
                    case COLD_SNACK_MENU: {
                        coldSnackMenu.addDish(dish);
                        break;
                    }
                    case BREAKFAST_MENU: {
                        breakfastMenu.addDish(dish);
                        break;
                    }
                }
            }
            case MENU: {
                menuList = new ArrayList<>();
                menuList.add(breakfastMenu);
                menuList.add(hotSnackMenu);
                menuList.add(coldSnackMenu);
            }

            default:
                break;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        text.append(ch, start, length);
    }
}
