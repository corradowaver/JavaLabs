public class Main {
  static AnimalManager manager = new AnimalManager();

  public static void main(String[] args) {
    manager.sortAnimals();
    manager.printList();
    manager.printFirstFive();
    manager.printLastThree();
  }
}
