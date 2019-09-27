public class Predator extends Animal {

  Predator(int id, String name) {
    this.id = id;
    this.name = name;
  }

  Predator(int id, Predator animal) {
    this.id = animal.id;
    this.name = animal.name;
  }

  @Override
  public int calculateFood() {
    return id * 1;
  }
}
