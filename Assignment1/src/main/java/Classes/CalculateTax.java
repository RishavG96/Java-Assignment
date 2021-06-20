package Classes;

import java.util.*;
import java.lang.*;

public class CalculateTax {

    static double totalTax = 0.0;
    static int totalItems = 0;

    public static void main(String[] args) {
        Item[] items = new Item[10];
        takeInputs(items);

        for (Item item: items) {
            if (item == null || item.type.isEmpty()) {
                break;
            }
            System.out.println("-----------------------------------------------------------------------------");
            System.out.println("Name: " + item.name + " || Price: " + item.price + " || Quantity: " + item.quantity + " || Type: " + item.type + " || Tax: " + item.tax);
            System.out.println("-----------------------------------------------------------------------------");
        }
        System.out.println("Final tax :" + totalTax);
    }

    private static void takeInputs(Item[] items) {
        boolean enterMoreItems = true;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("Enter name:");
            String name = sc.nextLine();
            System.out.println("Price of the item:");
            double price = sc.nextDouble();
            System.out.println("Quantity of item:");
            int quantity = sc.nextInt();
            sc.nextLine();
            boolean typeDidNotEnter = true;
            String type = "";
            do {
                System.out.println("Type of the item");
                type = sc.nextLine();
                if ((!type.isEmpty()) && (typeCheckFunc(type))) {
                    typeDidNotEnter = false;
                } else {
                    typeDidNotEnter = true;
                }
            } while(typeDidNotEnter);

            items[totalItems] = new Item(name, price, quantity, type);
            calculateTax(price, quantity, type, items[totalItems++]);

            System.out.println("Do you want to add another item(y/n)?");
            String response = sc.nextLine();
            if (!(response.equalsIgnoreCase("y"))) {
                enterMoreItems = false;
            }
        } while(enterMoreItems);
    }

    private static boolean typeCheckFunc(String type) {
        if (type.equalsIgnoreCase("raw") || type.equalsIgnoreCase("manufactured") || type.equalsIgnoreCase("imported")) {
            return true;
        }
        return false;
    }

    private static void calculateTax(double price, int quantity, String type, Item item) {
        double itemCost = price * quantity;
        if (type.equalsIgnoreCase("raw")) {
            double tax = (itemCost) * 0.125;
            totalTax += tax;
            item.setTax(tax);
        } else if (type.equalsIgnoreCase("manufactured")) {
            double tax = ((itemCost) * 0.125) + (0.02 * (itemCost + ((itemCost) * 0.125)));
            totalTax += tax;
            item.setTax(tax);
        } else if (type.equalsIgnoreCase("imported")) {
            double surcharge = 0.0;
            double tax = 0.1 * itemCost;
            if (tax <= 100) {
                surcharge = 5;
            } else if ( tax > 100 && tax <=200) {
                surcharge = 10;
            } else {
                surcharge = 0.05 * tax;
            }
            totalTax += (tax) + surcharge;
            item.setTax((tax) + surcharge);
        }
    }
}
