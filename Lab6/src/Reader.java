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

  private static ArrayList<Transaction> transactionsList;

  public static void init() {
    transactionsList = new ArrayList<Transaction>();
    Path pathXML = Paths.get("./resources/Data.xml");
    Path pathTXT = Paths.get("./resources/Data.txt");
    if (Files.exists(pathXML) && Files.isReadable(pathXML)) {
      try {
        readFromXMLFile();
      } catch (ParserConfigurationException | SAXException | IOException e) {
        e.printStackTrace();
      }
    } else if (Files.exists(pathTXT) && Files.isReadable(pathTXT)) {
      readFromTxtFile();
    }
  }

  private static class XMLHandler extends DefaultHandler {
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
      if (qName.equals("Transaction")) {
        try {
        long senderAccount = Long.parseLong(attributes.getValue("senderAccount"));
        long getterAccount = Long.parseLong(attributes.getValue("getterAccount"));
        double moneyAmount = Double.parseDouble(attributes.getValue("moneyAmount"));
        transactionsList.add(new Transaction(senderAccount, getterAccount, moneyAmount));
        } catch (NumberFormatException e) {
          System.err.println("Failed reading " + e.getMessage());;
        }
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
        transactionsList.add(transaction);
      });
    } catch (IOException e) {
      System.out.println("Reading failed");
      e.printStackTrace();
    }
  }

  public static ArrayList<Transaction> getTransactionsList() {
    return transactionsList;
  }

  public static void setTransactionsList(ArrayList<Transaction> transactionsList) {
    Reader.transactionsList = transactionsList;
  }
}
