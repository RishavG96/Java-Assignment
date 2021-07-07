package classes;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

/**
 * Dependency graph class.
 */
public class DependencyGraph {

  /**
   * ANCESTOR string.
   */
  private static final String ancestor = "The parent of node %s is %s ";
  
  /**
   * Descendants string.
   */
  private static final String descendants = "The child of node %s is %s ";

  /**
   * Takes input function.
   */
  public void takeInput() {
  
    final Hashtable<Integer,String> record  = new Hashtable<Integer,String>();
    final ArrayList<Integer> parent = new ArrayList<Integer>();
    final ArrayList<Integer> child = new ArrayList<Integer>();
    
    final Scanner scanner = new Scanner(System.in);
    boolean enterMore = true;
    try {
      while (enterMore) {
        System.out.println("1.the immediate parents\n2.the immediate children\n"
                             + "3.the ancestors\n4.the descendants\n"
                             + "5.Delete a whole dependencies from node\n"
                             + "6.delete a dependency\n7.Add new dependency\n8.AddNode");
        final int userInput = scanner.nextInt();
        switch (userInput) {
          case 1:
            System.out.println("the immediate parents of node id: ");
            int search  = scanner.nextInt();
            searchParent(parent, child, search);
            break;
          case 2:
            System.out.println("the immediate children of  node id: ");
            search  = scanner.nextInt();
            searchChild(parent, child, search);
            break;
          case 3:
            System.out.println("the ancestors of node id: ");
            search  = scanner.nextInt();
            searchAncestors(parent, child, search);
            break;
          case 4:
            System.out.println("the descendants of node id: ");
            search  = scanner.nextInt();
            searchDescendants(parent, child, search);
            break;
          case 5:
            System.out.println("Delete a whole dependencies from a tree of node id: ");
            search  = scanner.nextInt();
            deleteNode(parent, child, search);
            break;
          case 6:
            System.out.println("delete a dependency to a tree of node ids(eg 1,2):  ");
            final String[] nodeList = scanner.next().split(Constants.COMMA);
            deleteRelationNodes(parent, child, nodeList);
            break;
          case 7:
            System.out.println("Add new dependency to a tree of node ids(eg 1,2):  ");
            final String[] nodeLists = scanner.next().split(Constants.COMMA);
            addRelationNodes(parent, child, nodeLists);
            break;
          case 8:
            System.out.println("a new node to tree:  ");
            createNewNode(record);
            break;
          default:
            System.out.println("Invalid Entry!");
            break;
        }
        System.out.println("Want To continue(y/n)?");
        final String toContinue = scanner.next();
        if (toContinue.equalsIgnoreCase(Constants.YES)) {
          enterMore = true;
        } else {
          enterMore = false;
        }
      }
    } catch (Exception ex) {
      System.out.println(ex.getMessage());
    }
  }

  private void createNewNode(final Hashtable<Integer,String> nodes) {
    try {
      final Scanner scanner = new Scanner(System.in);
      
      System.out.println("Node ID:");
      final int id = scanner.nextInt();
      System.out.println("Node Name:");
      final String name = scanner.next();
      final Node node = new Node(id, name);
      if (nodes.containsKey(node.getId())) {
        throw new Exception("Already in use");
      } else {
        nodes.put(node.getId(),node.getName());
      }
      scanner.close();
    } catch (Exception ex) {
      System.out.println(ex.getMessage());
    }
  }

  private void searchParent(final ArrayList<Integer> parent,
                            final ArrayList<Integer> child, final int nodeId) {
    for (int index = 0; index < parent.size(); index++) {
      if (nodeId == child.get(index)) {
        System.out.println(String.format(ancestor, nodeId, parent.get(index)));
      }
    }
  }

  private void searchChild( final ArrayList<Integer> parent,
                            final ArrayList<Integer> child, final int nodeId) {
    for (int index = 0; index < parent.size(); index++) {
      if (nodeId == parent.get(index)) {
        System.out.println(String.format(descendants, nodeId, child.get(index)));
      }
    }
  }

  private void searchAncestors( final ArrayList<Integer> parent,
                                final ArrayList<Integer> child, final int nodeId) {
    final ArrayList<Integer> ancestorList = new ArrayList<Integer>();
    ancestorList.add(nodeId);
    for (int index = 0; index < ancestorList.size(); index++) {
      if (child.contains(ancestorList.get(index))) {
        ancestorList.remove(child.indexOf(ancestorList.get(index)));
        ancestorList.add(parent.get(index));
        System.out.print(parent.get(index) + "  ");
        index = 0;
      }
    }
    System.out.println();
  }

  private void searchDescendants( final ArrayList<Integer> parent,
                                  final ArrayList<Integer> child, final int nodeId) {
    final ArrayList<Integer> descendantsList = new ArrayList<Integer>();
    descendantsList.add(nodeId);
    for (int index = 0; index < descendantsList.size(); index++) {
      if (parent.contains(descendantsList.get(index))) {
        descendantsList.remove(parent.indexOf(descendantsList.get(index)));
        descendantsList.add(child.get(index));
        System.out.print(child.get(index) + "  ");
        index = 0;
      }
    }
    System.out.println();
  }

  private void deleteNode( final ArrayList<Integer> parent,
                           final ArrayList<Integer> child, final int nodeId) {
    final ArrayList<Integer> nodeArray = new ArrayList<Integer>(nodeId);
    for (int index = 0; index < nodeArray.size(); index++) {
      int node = nodeArray.get(index);
      if (parent.contains(node)) {
        nodeArray.remove(parent.indexOf(node));
        parent.remove(parent.indexOf(node));
        nodeArray.add(child.get(index));
        child.remove(child.indexOf(child.get(index)));
        System.out.println(child.get(index) + "  ");
        System.out.println("The node is deleted.");
        index = 0;
      }
    }
  }

  private void deleteRelationNodes( final ArrayList<Integer> parent,
                                    final ArrayList<Integer> child, final String[] relations) {
    int index;
    for (index = 0; index < parent.size(); index++) {
      if (parent.get(index) == Integer.parseInt(relations[0])
            && child.get(index) == Integer.parseInt(relations[1])) {
        parent.remove(index);
        child.remove(index);
        System.out.println("Relationship Deleted");
        break;
      }
    }
    if (index == parent.size()) {
      System.out.println("Not found");
    }
  }
  
  private void addRelationNodes( final ArrayList<Integer> parent,
                                 final ArrayList<Integer> child, final String[] relations) {
    int index;
    for (index = 0; index < parent.size(); index++) {
      if (parent.get(index) == Integer.parseInt(relations[0])
            && child.get(index) == Integer.parseInt(relations[1])) {
        System.out.println("Relationship Already Exist");
        break;
      }
    }
    if (index == parent.size()) {
      parent.add(Integer.parseInt(relations[0]));
      child.add(Integer.parseInt(relations[1]));
      System.out.println("Relationship Added");
    }
  }
}
