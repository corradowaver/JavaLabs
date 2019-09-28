import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Library implements AutoCloseable {
	private List<Book> bookList;

	Library() {
		bookList = readBookList();
	}

	private List<Book> readBookList() {
		List<Book> books = null;

		FileInputStream fin = null;
		ObjectInputStream ois = null;

		try {

			fin = new FileInputStream("./rsc/booksfile");
			ois = new ObjectInputStream(fin);
			books = (List<Book>) ois.readObject();

		} catch (FileNotFoundException e) {
			books = new ArrayList<Book>();
			writeBookList(books);
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			books = new ArrayList<Book>();
			writeBookList(books);
		}
		return books;
	}

	private void writeBookList(List<Book> books) {
		FileOutputStream fout = null;
		ObjectOutputStream oos = null;

		try {
			fout = new FileOutputStream("./rsc/booksfile");
			oos = new ObjectOutputStream(fout);
			oos.writeObject(books);

			System.out.println("Done");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void printBookList() {
		for (Book x : bookList) {
			printBook(x);
		}
	}

	public void printBook(Book x) {
		System.out.println(x.getId() + " " + x.getAuthor() + " " + x.getName() + " " + x.getYear());
	}

	public void addBook(Book newBook) {
		bookList.add(newBook);
		writeBookList(bookList);
	}

	public void addBook(int id, String author, String name, int year) {
		addBook(new Book(id, author, name, year));
	}

	public void removeBook(int id) {
		for (Book x : bookList) {
			if (x.getId() == id) {
				System.out.println(bookList.get(bookList.indexOf(x)).getName());
				bookList.remove(x);
				writeBookList(bookList);
				break;
			}
		}
	}

	public void editBook(int id, int key, String value) {
		for (Book x : bookList) {
			if (x.getId() == id) {
				int index = bookList.indexOf(x);
				switch (key) {
				case 1:
					bookList.get(index).setId(Integer.parseInt(value));
					break;
				case 2:
					bookList.get(index).setAuthor(value);
					break;
				case 3:
					bookList.get(index).setName(value);
					break;
				case 4:
					bookList.get(index).setYear(Integer.parseInt(value));
					break;
				default:
					break;
				}
				writeBookList(bookList);
				break;
			}
		}
	}

	public void findBook(int key, String value) {
		for (Book x : bookList) {
			switch (key) {
			case 1:
				if (x.getId() == Integer.parseInt(value)) {
					printBook(x);
				}
				break;
			case 2:
				if (x.getAuthor() == value) {
					printBook(x);
				}
				break;
			case 3:
				if (x.getName() == value) {
					printBook(x);
				}
				break;
			case 4:
				if (x.getYear() == Integer.parseInt(value)) {
					printBook(x);
				}
				break;
			default:
				break;
			}
		}
	}

	@Override
	public void close() throws Exception {
		System.out.println("Closed");
	}
}
