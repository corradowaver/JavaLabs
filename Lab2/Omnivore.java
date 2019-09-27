public class Omnivore extends Animal {

  Omnivore(int id, String name) {
    this.id = id;
    this.name = name;
  }

  Omnivore(Omnivore animal) {
    this.id = animal.id;
    this.name = animal.name;
  }

  @Override
  public int calculateFood() {
    return id * 1;
  }
}
