import java.util.ArrayList;
import java.util.HashMap;

public class Handler {
  private static final ArrayList<Transaction> transactionsList = Reader.getTransactionsList();;
  private HashMap<Long, Account> accountsList;

  public void startHandling() {
    for (Transaction trans : transactionsList) {
      startHandlingByThread(trans);
    }
  }

  private void startHandlingByThread(Transaction trans) {
    Thread thread = new Thread(() -> {
      synchronized (this) {
        handleTransaction(trans);
      }
    });
    thread.start();
    try {
      thread.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  private void handleTransaction(Transaction trans) {
    Account sender = accountsList.get(trans.getSenderAccount());
    Account getter = accountsList.get(trans.getGetterAccount());
    double money = trans.getMoneyAmount();
    if (sender == null || getter == null) {
      System.err.println("Transaction canceled: no such account");
      return;
    }
    if (money > sender.getMoney()) {
      System.err.println("Transaction canceled: not enough money");
      return;
    }
    accountsList.computeIfPresent(sender.getId(), (k, v) -> new Account(sender.getId(), sender.getMoney() - money));
    accountsList.computeIfPresent(getter.getId(), (k, v) -> new Account(getter.getId(), getter.getMoney() + money));
    System.err.println("Transaction passed");
  }

  public HashMap<Long, Account> getAccountsList() {
    return accountsList;
  }

  public void setAccountsList(HashMap<Long, Account> accountsList) {
    this.accountsList = accountsList;
  }
}
