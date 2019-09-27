public class Herbivore extends Animal {

  Herbivore(int id, String name) {
    this.id = id;
    this.name = name;
  }

  Herbivore(int id, Herbivore animal) {
    this.id = animal.id;
    this.name = animal.name;
  }

  @Override
  public int calculateFood() {
    return id * 1;
  }
}
