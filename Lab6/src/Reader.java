import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Reader {
  static {
    init();
  }

  private static ArrayList<Transaction> transactionsArray;

  public static void init() {
    transactionsArray = new ArrayList<Transaction>();
    Path pathXML = Paths.get("./resources/Data.xml");
    Path pathTXT = Paths.get("./resources/Data.txt");
    if (Files.exists(pathXML) && Files.isReadable(pathXML)) {
      try {
        readFromXMLFile();
      } catch (ParserConfigurationException | SAXException | IOException | NumberFormatException e) {
        e.printStackTrace();
      }
    } else if (Files.exists(pathTXT) && Files.isReadable(pathTXT)) {
      readFromTxtFile();
    }
  }

  private static class XMLHandler extends DefaultHandler {
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException, NumberFormatException {
      if (qName.equals("Transaction")) {
          long senderAccount = Long.parseLong(attributes.getValue("senderAccount"));
          long getterAccount = Long.parseLong(attributes.getValue("getterAccount"));
          double moneyAmount = Double.parseDouble(attributes.getValue("moneyAmount"));
          transactionsArray.add(new Transaction(senderAccount, getterAccount, moneyAmount));
      }
    }
  }

  private static void readFromXMLFile() throws ParserConfigurationException, SAXException, IOException {
    SAXParserFactory factory = SAXParserFactory.newInstance();
    SAXParser parser = factory.newSAXParser();
    XMLHandler handler = new XMLHandler();
    parser.parse(new File("./resources/Data.xml"), handler);
  }

  private static void readFromTxtFile() {
    Path path = Paths.get("./resources/Data.txt");
    try (Stream<String> lines = Files.lines(path)) {
      lines.forEach(s -> {
        Transaction transaction = new Transaction();
        Arrays.asList(s.replaceAll(" ", "").split(",")).forEach(line -> {
          String[] args = line.split(":");
          switch (args[0]) {
          case "senderAccount":
            transaction.setSenderAccount(Long.parseLong(args[1]));
            break;
          case "getterAccount":
            transaction.setGetterAccount(Long.parseLong(args[1]));
            break;
          case "moneyAmount":
            transaction.setMoneyAmount(Double.parseDouble(args[1]));
            break;
          default:
            break;
          }
        });
        transactionsArray.add(transaction);
      });
    } catch (IOException ex) {
      System.out.println("Reading failed");
      ex.printStackTrace();
    }
  }

  public static ArrayList<Transaction> getTransactionsArray() {
    return transactionsArray;
  }

  public static void setTransactionsArray(ArrayList<Transaction> transactionsArray) {
    Reader.transactionsArray = transactionsArray;
  }
}
