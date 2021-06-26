package classes;

import java.util.ArrayList;
import java.util.Scanner;


/**
 * Created by rishav.
 */
public class CalculateTax {

  /**
  * Takes total Tax.
  */
  private static double totalTax = Constant.ZERO;

  /**
  * Takes ArrayList.
  */
  private final ArrayList<ItemDetails> items = new ArrayList<ItemDetails>();

  /**
  * Takes input.
  */
  public void takeInputs() {
    boolean enterMoreItems = true;
    final Scanner scanner = new Scanner(System.in);
    do {
      System.out.println(Constant.ENTER_NAME);
      final String name = scanner.nextLine();
      System.out.println(Constant.ENTER_PRICE);
      final double price = scanner.nextDouble();
      System.out.println(Constant.ENTER_QUANTITY);
      final int quantity = scanner.nextInt();
      scanner.nextLine();
      boolean typeDidNotEnter = true;
      String type = "";
      do {
        System.out.println(Constant.ENTER_TYPE);
        type = scanner.nextLine();
        typeDidNotEnter = checkIfTypeIsValid(type);
      } while (typeDidNotEnter);
      final ObjectType typeValue = ObjectType.valueOf(type.toUpperCase());
      final double calculatedTax = calculateTax(price, quantity, typeValue);
      items.add(new ItemDetails(name, price, quantity, typeValue, calculatedTax));
    
      System.out.println(Constant.ENTER_ANOTHER_ITEM);
      final String response = scanner.nextLine();
      if (!(response.equalsIgnoreCase(Constant.YES))) {
        enterMoreItems = false;
      }
    } while (enterMoreItems);
    scanner.close();
  }

  private boolean checkIfTypeIsValid(final String type) {
    boolean isValid = true;
    final ObjectType typeValue = ObjectType.valueOf(type.toUpperCase());
    if (!type.isEmpty() && typeCheckFunc(typeValue)) {
      isValid = false;
    }
    return isValid;
  }

  private boolean typeCheckFunc(final ObjectType type) {
    boolean isValidType = false;
    if (type == Constant.RAW || type == Constant.MANUFACTURED || type == Constant.IMPORTED) {
      isValidType = true;
    }
    return isValidType;
  }

  private double calculateTax(final double price, final int quantity, final ObjectType type) {
    final double itemCost = price * quantity;
    double taxAmt = Constant.ZERO;
    switch (type) {
      case RAW:
        double tax = (itemCost) * Constant.PAISA125;
        totalTax += tax;
        taxAmt = tax;
        break;
      case MANUFACTURED:
        final double taxCharge = (itemCost) * Constant.PAISA125;
        tax = taxCharge + (Constant.PAISA2 * (itemCost + ((itemCost) * Constant.PAISA125)));
        totalTax += tax;
        taxAmt = tax;
        break;
      case IMPORTED:
        double surcharge = Constant.ZERO;
        tax = Constant.PAISA1 * itemCost;
        if (tax <= Constant.HUNDRED) {
          surcharge = Constant.FIVE;
        } else if (tax > Constant.HUNDRED && tax <= Constant.TWO_HUNDRED) {
          surcharge = Constant.TEN;
        } else {
          surcharge = Constant.PAISA5 * tax;
        }
        totalTax += tax + surcharge;
        taxAmt = tax + surcharge;
        break;
      default:
        break;
    }
    return taxAmt;
  }
  
  /**
  * Prints Data.
  */
  public void printData() {
    for (final ItemDetails item: items) {
      if (item == null || item.returnType().isEmpty()) {
        break;
      }
      
      System.out.println("Name: " + item.returnName() + " || Price: " + item.returnPrice()
                         + " || Quantity: " + item.returnQuantity()
                           + " || Type: " + item.returnType() + " || Tax: " + item.returnTax());
      
    }
    System.out.println(Constant.FINAL_TAX + totalTax);
  }
}
