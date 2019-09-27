import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Second {
  static String message = "none";

  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    while (message.length() % 3 != 0) {
      message = scan.next();
    }
    String[] newArray = separation(message);
    for (int i = 0; i < newArray.length; i++) {
      newArray[i] = randMidChar(newArray[i]);
    }
    Arrays.sort(newArray);
    scan.close();
  }

  public static String[] separation(String message) {
    return message.split("(?<=\\G...)");
  }

  public static String randMidChar(String message) {
    StringBuilder newMessage = new StringBuilder(message);
    newMessage.setCharAt(1, randomChar(newMessage.charAt(1)));
    return newMessage.toString();
  }

  public static char randomChar(char a) {
    Random rnd = new Random();
    char c = a;
    while (a == c) {
      c = (char) (rnd.nextInt(26) + 'a');
    }
    return c;
  }
}
