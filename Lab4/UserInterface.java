import java.util.Scanner;

public class UserInterface {
  private static Scanner sc = new Scanner(System.in);

  public static void mainMenu() {
    System.out.println("---File Manager 2.0---\nEnter following commands + FILE NAME to...\n    'cd' - go to folder\n" 
                       +"    'mkfile' - create file\n    'rmfile' - remove file\n    'write' - write to text file\n"
                       + "Commands with no arguments:\n    'getpath' - see where you are\n    'ls' - show files in folder\n    'exit' - leave :c");
    while (true) {
      System.out.println("\n___Type command:___");
      String choice = sc.next();
      switch (choice) {
        case "cd" :
          cd(sc.next());
          break;
        case "ls" :
          ls();
          break;
        case "mkfile" :
          mkfile(sc.next());
          break;
        case "rmfile" : 
          rmfile(sc.next());
          break;
        case "getpath" : 
          getPath();
          break;
        case "write" :
          String name = sc.next();
          System.out.println("Do you want to overwrite file? [y/n]");
          Boolean appendable = sc.next().equals("y") ? false : true;
          System.out.println("Enter data you want to write: ");
          sc.nextLine();
          String data = sc.nextLine();
          writeToFile(name, data, appendable);
          break;
        case "exit" :
          System.exit(0);
          break;
        default :
          System.out.println("NaC (not a command)");
          break;
      }
    }
  }

  public static void writeToFile(String name, String data, Boolean appendable) {
    String output = FileManager.writeToFile(name, data, appendable) ? "-success" : "!!!went wrong!!!";
    System.out.println(output);
  }

  public static void rmfile(String name) {
    String output = FileManager.rmfile(name) ? "-File was deleted." : "-Cannot delete file.";
    System.out.println(output);
  }

  public static void mkfile(String name) {
    String output = FileManager.mkfile(name) ? "-File was created." : "-File already exists.";
    System.out.println(output);
  }

  public static void getPath() {
    System.out.println("-Current path is : " + FileManager.getPath().toString());
  }

  public static void cd(String arg) {
    String output = FileManager.cd(arg) ? "-success" : "!!!went wrong!!!";
    System.out.println(output);
  }

  public static void ls() {
    FileManager.ls()
      .forEach(x -> {
        System.out.println(x.getFileName());
      });
  }
}
