import java.util.ArrayList;
import java.util.HashMap;

public class Handler {
  static {
    transactionsArray = Reader.getTransactionsArray();
  }

  private static ArrayList<Transaction> transactionsArray;
  private static HashMap<Long, Account> accountsList;

  public static void startHandling() {
    for (Transaction trans : transactionsArray) {
      handleTransaction(trans);
    }
  }

  private static void handleTransaction(Transaction trans) {
    Account sender = accountsList.get(trans.getSenderAccount());
    Account getter = accountsList.get(trans.getGetterAccount());
    if (sender == null || getter == null) {
      System.err.println("Transaction canceled: no such account");
      return;
    }
    double money = trans.getMoneyAmount();
    if (money > sender.getMoney()) {
      System.err.println("Transaction canceled: not enough money");
      return;
    }
    accountsList.computeIfPresent(sender.getId(), (k, v) -> new Account(sender.getId(), sender.getMoney() - money));
    accountsList.computeIfPresent(getter.getId(), (k, v) -> new Account(getter.getId(), getter.getMoney() + money));
    System.err.println("Transaction passed");
  }

  public static HashMap<Long, Account> getAccountsList() {
    return accountsList;
  }

  public static void setAccountsList(HashMap<Long, Account> newAccountsList) {
    accountsList = newAccountsList;
  }

}
