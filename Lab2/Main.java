public class Main {
  static AnimalManager manager = new AnimalManager();

  public static void main(String[] args) {

    Predator shark = new Predator(111, "Shark");
    Herbivore goose = new Herbivore(222, "Goose");
    Omnivore pony = new Omnivore(333, "Pony");
    Predator dino = new Predator(444, "Dino");
    Herbivore rhino = new Herbivore(555, "Rhino");

    manager.addAnimal(shark);
    manager.addAnimal(goose);
    manager.addAnimal(pony);
    manager.addAnimal(dino);
    manager.addAnimal(rhino);

    System.out.println("---Sorted list of animals: ---");
    manager.sortAnimals();
    manager.printList();
    System.out.println("\n---First five animal names from the list---");
    manager.printFirstFive();
    System.out.println("\n---Last three animal ids from the list---");
    manager.printLastThree();
  }
}
