package classes;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by rishav.
 */
public class ItemDetails {

  /**
  * name.
  */
  private final String name;

  /**
  * price.
  */
  private final double price;

  /**
  * quantity.
  */
  private final int quantity;

  /**
  * type.
  */
  private final ObjectType type;

  /**
  * tax.
  */
  private double tax;

  /**
  *  constructor.
  */
  public ItemDetails(final String name, final double price, final int quantity,
                     final ObjectType type, final double tax) {
    this.name = name;
    this.price = price;
    this.quantity = quantity;
    this.type = type;
    this.tax = tax;
  }

  /**
  *  set tax.
  */
  public void setTax(final double tax) {
    this.tax = tax;
  }

  /**
   *  return name.
   */
  public String getName() {
    return name;
  }
  
  /**
   *  return Price.
   */
  public double getPrice() {
    return price;
  }
  
  /**
   *  return Quantity.
   */
  public int getQuantity() {
    return quantity;
  }
  
  /**
   *  return Type.
   */
  public String getType() {
    return type.toString();
  }
  
  /**
   *  return Tax.
   */
  public double getTax() {
    return tax;
  }
}
