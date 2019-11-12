import java.io.File;

public class Main {
  public static void main(String args[]) {
    CustomMap.put("kek", "lol");
    CustomMap.put(123, "lol2");
    CustomMap.put("somekey", 9999.999);
    String[] array = {"frist", "second", "third"};
    CustomMap.put(9090, array);
    File file = new File("kek.properties");
    CustomMap.writeProperties(file);
    System.out.println(CustomMap.get(123));
  }
}