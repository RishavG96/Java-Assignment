package classes;

/**
 * Node class.
 */
public class Node {
  
  /**
   * Id member.
   */
  private int id;

  /**
   * Name member.
   */
  private String name;

  /**
   * Node constructor.
   * @param id id
   * @param name name
   */
  public Node(int id, String name) {
    this.id = id;
    this.name = name;
  }

  /**
   * return id.
   * @return ID
   */
  public int getId() {
    return id;
  }

  /**
   * return name.
   * @return name
   */
  public String getName() {
    return name;
  }
}
