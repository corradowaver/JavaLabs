import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AnimalManager implements AutoCloseable {
  public List<Animal> animalList = new ArrayList<>();;
  File file = new File("rsc/animalsfile");

  AnimalManager() {
    readAnimalList();
  }

  public void printList() {
    for (Animal x : animalList) {
      System.out.println(x.id + " " + x.name + " " + x.calculateFood() + " " + x.getClass().getSimpleName());
    }
  }

  public void printFirstFive() {
    for (int i = 0; i < 5 && i < animalList.size(); i++) {
      System.out.println(animalList.get(i).getName());
    }
  }

  public void printLastThree() {
    for (int i = animalList.size() - 1; i > animalList.size() - 4 && i >= 0; i--) {
      System.out.println(animalList.get(i).getId());
    }
  }

  public void sortAnimals() {
    animalList.sort(new Comparator<Animal>() {
      public int compare(Animal animal1, Animal animal2) {
        int result = Integer.compare(animal2.calculateFood(), animal1.calculateFood());
        return result == 0 ? animal1.getName().compareTo(animal2.getName()) : result;
      }
    });
    writeAnimalList(animalList);
  }

  public void addAnimal(Animal newAnimal) {
    animalList.add(newAnimal);
    writeAnimalList(animalList);
  }

  public void removeAnimal(int id) {
    for (Animal x : animalList) {
      if (x.getId() == id) {
        animalList.remove(x);
        writeAnimalList(animalList);
        break;
      }
    }
  }

  private void readAnimalList() {
    file.getParentFile().mkdir();
    try (FileInputStream fin = new FileInputStream(file);
         ObjectInputStream ois = new ObjectInputStream(fin)) {
      file.createNewFile();
      animalList = (List<Animal>) ois.readObject();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
			System.out.println("FILE WILL BE CREATED AUTOMATICALLY\nDont worry.");
    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  private void writeAnimalList(List<Animal> animals) {
    file.getParentFile().mkdir();
    try (FileOutputStream fout = new FileOutputStream(file);
         ObjectOutputStream	oos = new ObjectOutputStream(fout)) {
      file.createNewFile();
      oos.writeObject(animals);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      System.out.println("FILE WILL BE CREATED AUTOMATICALLY\nDont worry.");
		} catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void close() throws Exception {
    System.out.println("Closed");
  }
}