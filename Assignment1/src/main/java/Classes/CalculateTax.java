package Classes;

import java.util.*;
import java.lang.*;

public class CalculateTax {

    private static double totalTax = Constants.ZERO;

    public void takeInputs(Item[] items) {
        boolean enterMoreItems = true;
        Scanner sc = new Scanner(System.in);
        int totalItemCount = Constants.ZERO_INT;
        do {
            System.out.println(Constants.ENTER_NAME);
            String name = sc.nextLine();
            System.out.println(Constants.ENTER_PRICE);
            double price = sc.nextDouble();
            System.out.println(Constants.ENTER_QUANTITY);
            int quantity = sc.nextInt();
            sc.nextLine();
            boolean typeDidNotEnter = true;
            String type = "";
            do {
                System.out.println(Constants.ENTER_TYPE);
                type = sc.nextLine();
                if ((!type.isEmpty()) && (typeCheckFunc(type))) {
                    typeDidNotEnter = false;
                } else {
                    typeDidNotEnter = true;
                }
            } while(typeDidNotEnter);

            items[totalItemCount] = new Item(name, price, quantity, type);
            calculateTax(price, quantity, type, items[totalItemCount++]);

            System.out.println(Constants.ENTER_ANOTHER_ITEM);
            String response = sc.nextLine();
            if (!(response.equalsIgnoreCase(Constants.Y))) {
                enterMoreItems = false;
            }
        } while(enterMoreItems);
    }

    private boolean typeCheckFunc(String type) {
        if (type.equalsIgnoreCase(Constants.RAW) || type.equalsIgnoreCase(Constants.MANUFACTURED) || type.equalsIgnoreCase(Constants.IMPORTED)) {
            return true;
        }
        return false;
    }

    private void calculateTax(double price, int quantity, String type, Item item) {
        double itemCost = price * quantity;
        switch (type) {
            case Constants.RAW:
                double tax = (itemCost) * Constants.PAISA125;
                totalTax += tax;
                item.setTax(tax);
            case Constants.MANUFACTURED:
                tax = ((itemCost) * Constants.PAISA125) + (Constants.PAISA2 * (itemCost + ((itemCost) * Constants.PAISA125)));
                totalTax += tax;
                item.setTax(tax);
            case Constants.IMPORTED:
                double surcharge = Constants.ZERO;
                tax = Constants.PAISA1 * itemCost;
                if (tax <= Constants.HUNDRED) {
                    surcharge = Constants.FIVE;
                } else if (tax > Constants.HUNDRED && tax <= Constants.TWO_HUNDRED) {
                    surcharge = Constants.TEN;
                } else {
                    surcharge = Constants.PAISA5 * tax;
                }
                totalTax += (tax) + surcharge;
                item.setTax((tax) + surcharge);
            default:
                break;
        }
    }

    public void printData(Item[] items) {
        for (Item item: items) {
            if (item == null || item.returnType().isEmpty()) {
                break;
            }
            System.out.println("-----------------------------------------------------------------------------");
            System.out.println("Name: " + item.returnName() + " || Price: " + item.returnPrice() + " || Quantity: " + item.returnQuantity() + " || Type: " + item.returnType() + " || Tax: " + item.returnTax());
            System.out.println("-----------------------------------------------------------------------------");
        }
        System.out.println(Constants.FINAL_TAX + totalTax);
    }
}
