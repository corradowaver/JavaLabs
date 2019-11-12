import java.util.ArrayList;
import java.util.HashMap;

public class Main {

  public static void main(String[] args) {
    ArrayList<Transaction> transactionsArray = Reader.getTransactionsArray();
    for (Transaction trans : transactionsArray) {
      System.out.println(String.format("Счет отправителя: %s, Счет получателя: %s, Денюшки %s",
                                        trans.getSenderAccount(), trans.getGetterAccount(), trans.getMoneyAmount()));
    }

    HashMap<Long, Account> accountsList = new HashMap<Long, Account>();
    Account kek = new Account(1234, 1000);
    Account lol = new Account(2345, 0);
    accountsList.put(kek.getId(), kek);
    accountsList.put(lol.getId(), lol);
    Handler.setAccountsList(accountsList);
    Handler.startHandling();
  }
}
