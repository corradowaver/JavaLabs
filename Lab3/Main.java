public class Main {
  public static MyStringBuilder sb;

  public static void main(String[] args) {
    sb = new MyStringBuilder("test");
    String test1 = sb.toString();
    sb.append​("1");
    sb.undo();
    String test2 = sb.toString();

    System.out.println(test1 + test2);
    System.out.println(test1.equals(test2) ? true : false);
  }
}
