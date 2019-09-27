import java.lang.reflect.Method;
import java.util.Scanner;

public class UserInterface {
  private static Scanner sc = new Scanner(System.in);
  private Library lib = new Library();

  public Method startMenu() {
    System.out.println("\nWelcome to the library.");
    System.out.println("1 - Show all books\n2 - Find books by...\n3 - Add book\n4 - Edit book\n5 - Remove book\n");
    int reply = sc.nextInt();
    switch (reply) {
      case 1: 
        printBookList();
        break;
      case 2: 
        findBook();
        break;
      case 3:
        addBook();
        break;
      case 4:
        editBook();
        break;
      case 5: 
        removeBook();
        break;
      default:
        System.err.println("lol");
    }

    return startMenu();
  }

  private Method removeBook() {
    System.out.println("Enter id of the book: ");
    int id = sc.nextInt();
    System.out.print("Are you sure? Book : ");
    lib.findBook(1, Integer.toString(id));
    System.out.print(" will be deleted [y/n] ? ");
    String reply = sc.next();
    System.out.println(reply);
    if (reply.equals("y") || reply.equals("yes")) {
      lib.removeBook(id);
    }

    return startMenu();
  }

  private Method editBook() {
    System.out.println("Enter id of the book: ");
    int id = sc.nextInt();
    System.out.println("What do you want to change?\n1 - id\n2 - Author\n3 - Name\n4 - Year");
    int key = sc.nextInt();
    System.out.println("New value?");
    String value = sc.next();

    lib.editBook(id, key, value);

    return startMenu();
  }

  private Method addBook() {
    System.out.println("id?");
    int id = sc.nextInt();
    System.out.println("Author?");
    String author = sc.next();
    System.out.println("Name?");
    String name = sc.next();
    System.out.println("Year?");
    int year = sc.nextInt();

    lib.addBook(id, author, name, year);

    return startMenu();
  }

  public void printBookList() {
    lib.printBookList();
  }

  public Method findBook() {
    System.out.println("Enter key for find:\n1 - id\n2 - Author\n3 - Name\n4 - Year");
    int key = sc.nextInt();
    System.out.println("Enter the value");
    String value = sc.next();
    lib.findBook(key, value);

    return startMenu();
  }
}
