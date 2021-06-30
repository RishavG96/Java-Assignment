package classes;

import java.util.ArrayList;
import java.util.Scanner;


/**
 * Created by rishav.
 */
public class CalculateTax {

  /**
   * String formatter.
   */
  private final String DATA = "Name: %s || Price: %s || Quantity: %s || Type: %s || Tax: %s";
  
  /**
  * Takes ArrayList.
  */
  private final ArrayList<ItemDetails> ITEMS = new ArrayList<ItemDetails>();

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
      ITEMS.add(new ItemDetails(name, price, quantity, typeValue, calculatedTax));
    
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
    if (type == ObjectType.RAW || type == ObjectType.MANUFACTURED || type == ObjectType.IMPORTED) {
      isValidType = true;
    }
    return isValidType;
  }

  private double calculateTax(final double price, final int quantity, final ObjectType type) {
    final double itemCost = price * quantity;
    double taxAmt = Constant.ZERO;
    double basicTax = Constant.ZERO;
    switch (type) {
      case RAW:
        taxAmt = (itemCost) * Constant.PAISA125;
        break;
      case MANUFACTURED:
        final double taxCharge = (itemCost) * Constant.PAISA125;
        taxAmt = taxCharge + (Constant.PAISA2 * (itemCost + ((itemCost) * Constant.PAISA125)));
        break;
      case IMPORTED:
        double surcharge = Constant.ZERO;
        basicTax = Constant.PAISA1 * itemCost;
        if (basicTax <= Constant.HUNDRED) {
          surcharge = Constant.FIVE;
        } else if (basicTax > Constant.HUNDRED && basicTax <= Constant.TWO_HUNDRED) {
          surcharge = Constant.TEN;
        } else {
          surcharge = Constant.PAISA5 * basicTax;
        }
        
        taxAmt = basicTax + surcharge;
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
    for (final ItemDetails item: ITEMS) {
      System.out.println(String.format(DATA, item.getName(), item.getPrice(), item.getQuantity(), item.getType(), item.getTax()));
    }
  }
}
