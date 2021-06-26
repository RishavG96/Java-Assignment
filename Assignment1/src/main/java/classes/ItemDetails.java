package classes;

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
  * Default constructor.
  */
  private ItemDetails() {
    this.name = Constant.EMPTY;
    this.price = Constant.ZERO;
    this.quantity = Constant.ZERO_INT;
    this.type = Constant.RAW;
  }

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
  public String returnName() {
    return name;
  }

  /**
  *  return Price.
  */
  public double returnPrice() {
    return price;
  }

  /**
  *  return Quantity.
  */
  public int returnQuantity() {
    return quantity;
  }

  /**
  *  return Type.
  */
  public String returnType() {
    return type.toString();
  }
  
  /**
  *  return Tax.
  */
  public double returnTax() {
    return tax;
  }
}
