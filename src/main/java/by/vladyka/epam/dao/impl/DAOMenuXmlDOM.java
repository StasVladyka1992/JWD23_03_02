package by.vladyka.epam.dao.impl;

import by.vladyka.epam.dao.DAOMenuXml;
import by.vladyka.epam.dao.exception.DAOException;
import by.vladyka.epam.entity.Dish;
import by.vladyka.epam.dao.util.MenuTagName;
import by.vladyka.epam.entity.menu.BreakfastMenu;
import by.vladyka.epam.entity.menu.ColdSnackMenu;
import by.vladyka.epam.entity.menu.HotSnackMenu;
import by.vladyka.epam.entity.menu.Menu;
import com.sun.org.apache.xerces.internal.parsers.DOMParser;

import static by.vladyka.epam.entity.Currency.*;

import by.vladyka.epam.entity.Description;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DAOMenuXmlDOM implements DAOMenuXml {
    @Override
    public List<Menu> getMenu() throws DAOException {
        List<Menu> menuList = new ArrayList<>();

        DOMParser parser = new DOMParser();
        try {
            parser.parse(getXMLRelativeAddress("menu.xml"));
        } catch (SAXException|IOException e) {
            throw new DAOException(e);
        }
        Document document = parser.getDocument();

        //getting root element XML(menu)
        Element root = document.getDocumentElement();

        MenuTagName[] tagNames =MenuTagName.values();
        for (int i = 0; i <tagNames.length; i++) {
            if (tagNames[i].toString().toLowerCase().contains("_menu")){
               Menu menu =  getDishesFromMenuType(tagNames[i], root);
               menuList.add(menu);
            }
        }
    return menuList;}


    private Menu getDishesFromMenuType(MenuTagName menuTagName, Element root) {
        Menu menuType;
        switch (menuTagName) {
            case BREAKFAST_MENU: {
                menuType = new BreakfastMenu();
                break;
            }
            case HOT_SNACK_MENU: {
                menuType = new HotSnackMenu();
                break;
            }
            case COLD_SNACK_MENU: {
                menuType = new ColdSnackMenu();
                break;
            }
            default:
                return null;
        }

        menuType.setDishes(new ArrayList<>());

        String menuTypeName= menuTagName.toString().toLowerCase();

        NodeList menusList = root.getElementsByTagName("xns:"+menuTypeName);
        Element menuTypeElement = (Element) menusList.item(0);

        NodeList dishesNode = menuTypeElement.getElementsByTagName("xns:dish");
        for (int i = 0; i < dishesNode.getLength(); i++) {
            Dish dish = new Dish();
            Element dishElement = (Element) dishesNode.item(i);
            dish.setId(dishElement.getAttribute("xns:id"));
            //получение аттрибутов
            dish.setCurency(getEnumCurrency(dishElement.getAttribute("xns:currency")));
            dish.setPhoto(dishElement.getElementsByTagName("xns:photo").item(0).getNodeValue());

            //extracting photo
            Element photo = (Element) dishElement.getElementsByTagName("xns:photo").item(0);
            dish.setPhoto(photo.getTextContent());

            //extracting name
            Element name = (Element) dishElement.getElementsByTagName("xns:name").item(0);
            dish.setName(name.getTextContent());

            //extracting portion
            Element portion = (Element) dishElement.getElementsByTagName("xns:portion").item(0);
            dish.setPortion(portion.getTextContent());

            //extracting description
            Element descriptionPrice = (Element) dishElement.getElementsByTagName("xns:description_price").item(0);
            dish.setAdditionalInfo(descriptionPrice.getAttribute("xns:additionalInfo"));


            NodeList descriptionsFromDescriptionPrice = dishElement.getElementsByTagName("xns:description");
            List<Description> descriptions = new ArrayList<>();
            for (int j = 0; j < descriptionsFromDescriptionPrice.getLength(); j++) {
                Element element = (Element) descriptionsFromDescriptionPrice.item(j);
                Description description = new Description();
                description.setDescription(element.getAttribute("xns:description"));

                if (element.hasAttribute("xns:price")) {
                    String price = element.getAttribute("xns:price");
                    description.setPrice(Integer.parseInt(price));
                }

                descriptions.add(description);
            }
            dish.setDescription(descriptions);
            menuType.addDish(dish);
        }
        return menuType;
    }
}



