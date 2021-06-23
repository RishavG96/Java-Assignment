package Classes;

public class Item {
    private String name;
    private double price;
    private int quantity;
    private ObjectType type;
    private double tax;

    Item() {
        this.name = Constants.EMPTY;
        this.price = Constants.ZERO;
        this.quantity = Constants.ZERO_INT;
        this.type = Constants.RAW;
    }

    Item(String name, double price, int quantity, ObjectType type, double tax) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.type = type;
        this.tax = tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public String returnName() {
        return name;
    }

    public double returnPrice() {
        return price;
    }

    public int returnQuantity() {
        return quantity;
    }

    public String returnType() {
        return type.toString();
    }

    public double returnTax() {
        return tax;
    }
}
