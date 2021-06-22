package Classes;

public class Main {

    public static void main(String[] args) {
        Item[] items = new Item[10];
        CalculateTax calculateTax = new CalculateTax();
        calculateTax.takeInputs(items);

        calculateTax.printData(items);
    }
}
