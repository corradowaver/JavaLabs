import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;

public class AnimalManager {
  public List<Animal> animalList;

  AnimalManager() {
    animalList = readAnimalList();
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

  private List<Animal> readAnimalList() {
    List<Animal> animals = null;

    FileInputStream fin = null;
    ObjectInputStream ois = null;

    try {

      fin = new FileInputStream("animallist");
      ois = new ObjectInputStream(fin);
      animals = (List<Animal>) ois.readObject();

    } catch (FileNotFoundException e) {
      animals = new ArrayList<Animal>();
      writeAnimalList(animals);
    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
      animals = new ArrayList<Animal>();
      writeAnimalList(animals);
    } finally {
      if (fin != null) {
        try {
          fin.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }

      if (ois != null) {
        try {
          ois.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }

    }
    return animals;
  }

  private void writeAnimalList(List<Animal> animals) {
    FileOutputStream fout = null;
    ObjectOutputStream oos = null;

    try {

      fout = new FileOutputStream("animallist");
      oos = new ObjectOutputStream(fout);
      oos.writeObject(animals);

      System.out.println("Done");
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (fout != null) {
        try {
          fout.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      if (oos != null) {
        try {
          oos.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }
}