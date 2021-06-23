package Classes;

import java.util.*;
import java.lang.*;

public class CalculateTax {

    private static double totalTax = Constants.ZERO;
    ArrayList<Item> items = new ArrayList<Item>();

    public void takeInputs() {
        boolean enterMoreItems = true;
        Scanner sc = new Scanner(System.in);
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
                typeDidNotEnter = checkIfTypeIsValid(type);
            } while(typeDidNotEnter);

            ObjectType typeValue = ObjectType.valueOf(type.toUpperCase());
            double calculatedTax = calculateTax(price, quantity, typeValue);
            items.add(new Item(name, price, quantity, typeValue, calculatedTax));

            System.out.println(Constants.ENTER_ANOTHER_ITEM);
            String response = sc.nextLine();
            if (!(response.equalsIgnoreCase(Constants.Y))) {
                enterMoreItems = false;
            }
        } while(enterMoreItems);
    }

    private boolean checkIfTypeIsValid(String type) {
        ObjectType typeValue = ObjectType.valueOf(type.toUpperCase());
        if ((!type.isEmpty()) && (typeCheckFunc(typeValue))) {
            return false;
        } else {
            return true;
        }
    }

    private boolean typeCheckFunc(ObjectType type) {
        if (type == Constants.RAW || type == Constants.MANUFACTURED || type == Constants.IMPORTED) {
            return true;
        }
        return false;
    }

    private double calculateTax(double price, int quantity, ObjectType type) {
        double itemCost = price * quantity;
        switch (type) {
            case RAW:
                double tax = (itemCost) * Constants.PAISA125;
                totalTax += tax;
                return tax;
            case MANUFACTURED:
                tax = ((itemCost) * Constants.PAISA125) + (Constants.PAISA2 * (itemCost + ((itemCost) * Constants.PAISA125)));
                totalTax += tax;
                return tax;
            case IMPORTED:
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
                return ((tax) + surcharge);
            default:
                break;
        }
        return Constants.ZERO;
    }

    public void printData() {
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
