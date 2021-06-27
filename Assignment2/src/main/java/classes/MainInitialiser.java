package classes;

import java.io.IOException;

/**
 * Main class.
 */
public class MainInitialiser {

  /**
  * Main function.
  */
  public static void main(final String[] args) {
    final MenuDrivenInput inputs = new MenuDrivenInput();
    try {
      inputs.deserialiseUser();
    } catch (IOException e) {
      System.out.println("Error retrieving.");
    }
    inputs.driveProgram();
  }
}
