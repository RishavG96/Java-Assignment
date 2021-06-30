package classes;

/**
 * Created by rishav.
 */
public abstract class MainInitialiser {

  /**
  * Main function.
  */
  public static void main(final String[] args) {

    final CalculateTax calculateTax = new CalculateTax();
    calculateTax.takeInputs();

    calculateTax.printData();
  }
}
