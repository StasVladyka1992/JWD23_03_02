package by.vladyka.epam.entity;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class Dish implements Serializable {
    private String photo;
    private String name;
    private List<Description> description;
    private String portion;
    private Currency curency;
    private String id;

    public Dish(String photo, String name, List<Description> description, String portion, Currency curency, String id) {
        this.photo = photo;
        this.name = name;
        this.description = description;
        this.portion = portion;
        this.curency = curency;
        this.id = id;
    }

    public Dish() {
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Description> getDescription() {
        return description;
    }

    public void setDescription(List<Description> description) {
        this.description = description;
    }

    public String getPortion() {
        return portion;
    }

    public void setPortion(String portion) {
        this.portion = portion;
    }

    public Currency getCurency() {
        return curency;
    }

    public void setCurency(Currency curency) {
        this.curency = curency;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dish)) return false;

        Dish dish = (Dish) o;

        if (!photo.equals(dish.photo)) return false;
        if (!name.equals(dish.name)) return false;
        if (!description.equals(dish.description)) return false;
        if (!portion.equals(dish.portion)) return false;
        if (curency != dish.curency) return false;
        return id.equals(dish.id);
    }

    @Override
    public int hashCode() {
        int result = photo.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + portion.hashCode();
        result = 31 * result + curency.hashCode();
        result = 31 * result + id.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "photo='" + photo + '\'' +
                ", name='" + name + '\'' +
                ", description=" + Arrays.toString(description.toArray()) +
                ", portion='" + portion + '\'' +
                ", curency=" + curency +
                ", id='" + id + '\'' +
                '}';
    }
}
