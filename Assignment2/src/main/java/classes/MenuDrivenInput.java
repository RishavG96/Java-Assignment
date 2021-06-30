package classes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Menu driven input class.
 */
public class MenuDrivenInput {

  private final String DATA = "Name: %s Age: %s Roll no.: %s Subjects Entered: %s";
  
  /**
   * Arraylist of users.
   */
  private static final ArrayList<UserDetails> USERS = new ArrayList<UserDetails>();
  
  /**
   * driving function.
   */
  public void driveProgram() {
    boolean addMoreInputs = Boolean.FALSE;
    final Scanner scanner = new Scanner(System.in);
    do {
      System.out.println("1. Add User Details");
      System.out.println("2. Display User Details");
      System.out.println("3. Delete User Details");
      System.out.println("4. Save User Details");
      System.out.println("5. Exit");
      final int userInput = scanner.nextInt();
      scanner.nextLine();
      switch (userInput) {
        case 1:
          addUserDetails(scanner);
          break;
        case 2:
          displayUserDetails();
          break;
        case 3:
          deleteUserDetails(scanner);
          break;
        case 4:
          try {
            serializeUser();
          } catch (IOException e) {
            System.out.println("Error in storing.");
          }
          break;
        default:
          break;
      }
  
      addMoreInputs = checkForContinue(scanner);
    } while (addMoreInputs);
    scanner.close();
  }

  /**
   * Check for continue func.
   */
  private boolean checkForContinue(final Scanner scanner) {
    boolean toContinue = Boolean.FALSE;
    System.out.println("Do you want to continue(y/n)");
    final String choice = scanner.nextLine();
    if (choice.equalsIgnoreCase(Constant.YES)) {
      toContinue = Boolean.TRUE;
    }
    return toContinue;
  }

  /**
   * Add User details.
   */
  private void addUserDetails(final Scanner scanner) {
    System.out.println("Enter Name");
    final String name = scanner.nextLine();
    System.out.println("Enter Age");
    final int age = scanner.nextInt();
    System.out.println("Enter Roll No.");
    final int rollNo = scanner.nextInt();
    scanner.nextLine();
    System.out.println("Enter Subjects with spaces: {A, B, C, D, E, F}");
    final String subjects = scanner.nextLine();
    final ArrayList<SubjectList> subjectsEntered = mapStringToEnum(subjects);
  
    USERS.add(new UserDetails(name, age, rollNo, subjectsEntered));
    sortUsers();
  }
  
  private void sortUsers() {
    Collections.sort(USERS, userRollNo);
  }

  private final Comparator<UserDetails> userRollNo = new Comparator<UserDetails>() {
  
    /**
     * compare func.
     */
    @Override public int compare(final UserDetails user1, final UserDetails user2) {
      final String name1 = user1.getName();
      final String name2 = user2.getName();
      if (name1.equalsIgnoreCase(name2)) {
        final int roll1 = user1.getRollNo();
        final int roll2 = user2.getRollNo();
  
        // For ascending order
        return roll1 - roll2;
      } else {
        return name1.compareTo(name2);
      }
    }
  };

  /**
   * Convert user input to enum.
   */
  private ArrayList<SubjectList> mapStringToEnum(final String subjects) {
    final String[] splitString = subjects.split(Constant.SPACE);
    final ArrayList<SubjectList> listOfSubjects = new ArrayList<>();
    for (final String subject: splitString) {
      if (subject.equalsIgnoreCase(Constant.SUBJECT_A)) {
        listOfSubjects.add(SubjectList.A);
      }
      if (subject.equalsIgnoreCase(Constant.SUBJECT_B)) {
        listOfSubjects.add(SubjectList.B);
      }
      if (subject.equalsIgnoreCase(Constant.SUBJECT_C)) {
        listOfSubjects.add(SubjectList.C);
      }
      if (subject.equalsIgnoreCase(Constant.SUBJECT_D)) {
        listOfSubjects.add(SubjectList.D);
      }
      if (subject.equalsIgnoreCase(Constant.SUBJECT_E)) {
        listOfSubjects.add(SubjectList.E);
      }
      if (subject.equalsIgnoreCase(Constant.SUBJECT_F)) {
        listOfSubjects.add(SubjectList.F);
      }
    }
    return listOfSubjects;
  }

  private void displayUserDetails() {
    System.out.println("______________Displaying Data______________");
    for (final UserDetails user: USERS) {
      System.out.println(String.format(DATA, user.getName(), user.getAge(), user.getRollNo(), user.getCourses()));
    }
    System.out.println("___________________________________________");
  }

  private void deleteUserDetails(final Scanner scanner) {
    System.out.println("Enter Roll no of the user you want to delete");
    final int rollNo = scanner.nextInt();
    scanner.nextLine();
    int counterForIndex = 0;
    boolean userDeleted = Boolean.FALSE;
    for (final UserDetails user: USERS) {
      if (user.getRollNo() == rollNo) {
        USERS.remove(counterForIndex);
        userDeleted = Boolean.TRUE;
        System.out.println("The user is deleted.");
        break;
      }
      counterForIndex++;
    }
    if (!userDeleted) {
      System.out.println("The user is not there in the database.");
    }
  }
  
  private void serializeUser() throws IOException {
    BufferedWriter store = new BufferedWriter(new FileWriter("Store.txt"));
  
    for (final UserDetails user: USERS) {
      store.write(user.getName());
      store.newLine();
  
      store.write(String.valueOf(user.getRollNo()));
      store.newLine();
  
      store.write(String.valueOf(user.getAge()));
      store.newLine();
  
      store.write(user.getCourses());
      store.newLine();
  
      store.write(Constant.DASH);
      store.newLine();
    }
    store.write(Constant.END);
    store.newLine();
    store.close();
  }

  /**
   *Deserialise user for data.
   */
  public void deserialiseUser() throws IOException {
    
    final BufferedReader store = new BufferedReader(new FileReader("Store.txt"));
    do {
      final String name = store.readLine();
      if (name.equalsIgnoreCase(Constant.END)) {
        break;
      }
      final int rollNo = Integer.parseInt(store.readLine());
      final int age = Integer.parseInt(store.readLine());
      final String subjects = store.readLine();
      
      final StringBuilder builder = new StringBuilder(subjects);
      builder.deleteCharAt(0);
      builder.deleteCharAt(subjects.length() - 2);
      final ArrayList<SubjectList> subjectsEntered = mapStringToEnum(builder.toString());
      final UserDetails user = new UserDetails(name, rollNo, age, subjectsEntered);
      USERS.add(user);
    } while (store.readLine() != Constant.END);
    store.close();
  }
  
}
