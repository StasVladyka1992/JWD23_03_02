package by.vladyka.epam.entity;

public class Description {
    private String description;
    private int price;


    public Description(String description, int price) {
        this.description = description;
        this.price = price;
    }

    public Description() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Description)) return false;

        Description that = (Description) o;

        if (price != that.price) return false;
        return description.equals(that.description);
    }

    @Override
    public int hashCode() {
        int result = description.hashCode();
        result = 31 * result + price;
        return result;
    }

    @Override
    public String toString() {
        return "Description{" +
                "description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}
