import java.util.ArrayList;
import java.util.HashMap;

public class Main {

  public static void main(String[] args) throws InterruptedException {
    ArrayList<Transaction> transactionsList = Reader.getTransactionsList();
    transactionsList.forEach(trans -> {
      System.out.println(String.format("Счет отправителя: %s, Счет получателя: %s, Денюшки %s",
          trans.getSenderAccount(), trans.getGetterAccount(), trans.getMoneyAmount()));
    });

    Account moneyDealer = new Account(99999999, 999999);
    Account mrGetter1 = new Account(11111111, 0);
    Account mrGetter2 = new Account(22222222, 0);
    Account mrGetter3 = new Account(33333333, 0);
    Account mrGetter4 = new Account(44444444, 0);

    HashMap<Long, Account> accountsList = new HashMap<Long, Account>();

    accountsList.put(moneyDealer.getId(), moneyDealer);
    accountsList.put(mrGetter1.getId(), mrGetter1);
    accountsList.put(mrGetter2.getId(), mrGetter2);
    accountsList.put(mrGetter3.getId(), mrGetter3);
    accountsList.put(mrGetter4.getId(), mrGetter4);

    Handler handler = new Handler();
    handler.setAccountsList(accountsList);

    System.out.println("\n! Before handling all transactions: !");
    handler.getAccountsList().forEach((id, account) -> {
      System.out.println("__________\nid: " + id + "\nMoney: " + account.getMoney());
    });

    handler.startHandling();

    Thread.sleep(1000);
    System.out.println("\n! After handling all transactions: !");
    handler.getAccountsList().forEach((id, account) -> {
      System.out.println("__________\nid: " + id + "\nMoney: " + account.getMoney());
    });

  }
}
