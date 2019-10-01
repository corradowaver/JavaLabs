import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Second {
  static String message = "none";
  static Scanner scan = new Scanner(System.in);

  public static void main(String[] args) {

    while (message.length() % 3 != 0) {
     System.out.println("Enter string (length must be divided by 3 with 0 remainder)");
      message = scan.next();
    }

    String[] newArray = separation(message);
    for (int i = 0; i < newArray.length; i++) {
      newArray[i] = randMidChar(newArray[i]);
    }
    Arrays.sort(newArray);

    for (String x : newArray) {
      System.out.println(x);
    }
    scan.close();
  }

  public static String[] separation(String message) {
    return message.split("(?<=\\G...)");
  }

  public static String randMidChar(String message) {
    StringBuilder newMessage = new StringBuilder(message);
    Random rnd = new Random();
    char newChar = '0';
    while (newChar == newMessage.charAt(0) || newChar == newMessage.charAt(1) 
      || newChar == newMessage.charAt(2) || newChar == '0') {
      newChar = (char) (rnd.nextInt(26) + 'a');
    }
    newMessage.setCharAt(1, newChar);
    return newMessage.toString();
  }
}
