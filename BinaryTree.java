import java.util.*;

class Node 
{
  int data;
  Node left, right;

  public Node(int item) 
  {
    data = item;
    left = right = null;
  }
}

public class BinaryTree 
{
  Node root;

  BinaryTree() 
  {
    root = null;
  }

  void insert(int data) 
  {
    root = insertRec(root, data);
  }

  Node insertRec(Node root, int data) 
  {
    if (root == null) 
    {
      root = new Node(data);
      return root;
    }
    if (data < root.data)
      root.left = insertRec(root.left, data);
    else if (data > root.data)
      root.right = insertRec(root.right, data);
    return root;
  }

  void delete(int data) 
  {
    root = deleteRec(root, data);
  }

  Node deleteRec(Node root, int data) 
  {
    if (root == null)
      return root;
    if (data < root.data)
      root.left = deleteRec(root.left, data);
    else if (data > root.data)
      root.right = deleteRec(root.right, data);
    else 
    {
      if (root.left == null)
        return root.right;
      else if (root.right == null)
        return root.left;
      root.data = minValue(root.right);
      root.right = deleteRec(root.right, root.data);
    }
    return root;
  }

  int minValue(Node root) 
  {
    int minValue = root.data;
    while (root.left != null) 
    {
      minValue = root.left.data;
      root = root.left;
    }
    return minValue;
  }

  void inorder() 
  {
    inorderRec(root);
  }

  void inorderRec(Node root) 
  {
    if (root != null) 
    {
      inorderRec(root.left);
      System.out.print(root.data + " ");
      inorderRec(root.right);
    }
  }

  void preorder() 
  {
    preorderRec(root);
  }

  void preorderRec(Node root) 
  {
    if (root != null) 
    {
      System.out.print(root.data + " ");
      preorderRec(root.left);
      preorderRec(root.right);
    }
  }

  void postorder() 
  {
    postorderRec(root);
  }

  void postorderRec(Node root) 
  {
    if (root != null) {
      postorderRec(root.left);
      postorderRec(root.right);
      System.out.print(root.data + " ");
    }
  }

  void search(int data) 
  {
    if (searchRec(root, data))
      System.out.println(data + " found in the tree");
    else
      System.out.println(data + " not found in the tree");
  }

  boolean searchRec(Node root, int data) 
  {
    if (root == null)
      return false;
    if (root.data == data)
      return true;
    if (data < root.data)
      return searchRec(root.left, data);
    return searchRec(root.right, data);
  }

  
  void inputTree() 
  {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter the number of elements you want to insert: ");
    int n = scanner.nextInt();

    System.out.println("Enter the elements:");
    for (int i = 0; i < n; i++) 
    {
      System.out.print("Element " + (i + 1) + ": ");
      int data = scanner.nextInt();
      insert(data);
    }

    System.out.println("Binary Tree created successfully!");
  }

  public static void main(String[] args) 
  {
    Scanner scanner = new Scanner(System.in);
    BinaryTree tree = new BinaryTree();

    int choice = 0;
    while (choice != 7) 
    {
      System.out.println("\n1. Insert");
      System.out.println("2. Delete");
      System.out.println("3. Search");
      System.out.println("4. Pre-order");
      System.out.println("5. In-order");
      System.out.println("6. Post-order");
      System.out.println("7. Exit");
      System.out.println("8. Input Tree");
      System.out.print("Enter your choice: ");
      choice = scanner.nextInt();

      switch (choice) 
      {
        case 1:
          System.out.print("Enter value to insert: ");
          tree.insert(scanner.nextInt());
          break;
        case 2:
          System.out.print("Enter value to delete: ");
          tree.delete(scanner.nextInt());
          break;
        case 3:
          System.out.print("Enter value to search: ");
          tree.search(scanner.nextInt());
          break;
        case 4:
          System.out.print("Pre-order traversal: ");
          tree.preorder();
          break;
        case 5:
          System.out.print("In-order traversal: ");
          tree.inorder();
          break;
        case 6:
          System.out.print("Post-order traversal: ");
          tree.postorder();
          break;
        case 7:
          System.out.println("Exiting program...");
          break;
        case 8:
          tree.inputTree();
          break;
        default:
          System.out.println("Invalid choice");
      }
    }
    scanner.close();
  }
}
