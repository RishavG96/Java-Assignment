package classes;

import java.util.ArrayList;

/**
 * User Details Class.
 */
public class UserDetails {

  /**
   * Name.
   */
  private final String name;

  /**
   * Age.
   */
  private final int age;
  
  /**
   * roll No.
   */
  private final int rollNo;
  
  /**
   * Set of courses he is interested in.
   */
  private final ArrayList<SubjectList> courses;

  /**
   * Constructor with params.
   */
  public UserDetails(final String name, final  int age, final  int rollNo,
                     final ArrayList<SubjectList> courses) {
    this.name = name;
    this.age = age;
    this.rollNo = rollNo;
    this.courses = courses;
  }

  /**
   * Name getter.
   */
  public String getName() {
    return name;
  }
  
  /**
   * Age getter.
   */
  public int getAge() {
    return age;
  }
  
  /**
   * Roll no Getter.
   */
  public int getRollNo() {
    return rollNo;
  }
  
  /**
   * Course getter.
   */
  public String getCourses() {
    return courses.toString();
  }
}
