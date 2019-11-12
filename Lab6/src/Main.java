import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.CompletableFuture;

public class Main {

  public static void main(String[] args) {
    ArrayList<Transaction> transactionsList = Reader.getTransactionsList();

    for (Transaction trans : transactionsList) {
      System.out.println(String.format("Счет отправителя: %s, Счет получателя: %s, Денюшки %s",
          trans.getSenderAccount(), trans.getGetterAccount(), trans.getMoneyAmount()));
    }

    HashMap<Long, Account> accountsList = new HashMap<Long, Account>();
    Account mrSender = new Account(88888888, 1000);
    Account mrGetter = new Account(44444444, 0);
    accountsList.put(mrSender.getId(), mrSender);
    accountsList.put(mrGetter.getId(), mrGetter);

    Handler handler = new Handler();
    handler.setAccountsList(accountsList);

    System.out.println(handler.getAccountsList()
        .get(mrSender.getId())
        .getMoney()
    );
    System.out.println(handler.getAccountsList()
        .get(mrGetter.getId())
        .getMoney()
    );

    handler.startHandling();

    CompletableFuture.runAsync(() -> {
      System.out.println(handler.getAccountsList()
          .get(mrSender.getId())
          .getMoney()
      );
      System.out.println(handler.getAccountsList()
          .get(mrGetter.getId())
          .getMoney()
      );
    });
  }
}
