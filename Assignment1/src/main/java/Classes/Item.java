package Classes;

public class Item {
    String name;
    double price;
    int quantity;
    String type;
    double tax;

    Item() {
        this.name = "";
        this.price = 0.0;
        this.quantity = 0;
        this.type = "";
    }

    Item(String name, double price, int quantity, String type) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.type = type;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }
}
