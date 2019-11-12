public class Transaction {
  
  public Transaction() {};
  public Transaction(long senderAccount, long getterAccount, double moneyAmount) {
    this.senderAccount = senderAccount;
    this.getterAccount = (getterAccount);
    this.moneyAmount = moneyAmount;
  }

  private long senderAccount;
  private long getterAccount;
  private double moneyAmount;

  public long getSenderAccount() {
    return senderAccount;
  }

  public void setSenderAccount(long senderAccount) {
    this.senderAccount = senderAccount;
  }

  public long getGetterAccount() {
    return getterAccount;
  }

  public void setGetterAccount(long getterAccount) {
    this.getterAccount = getterAccount;
  }

  public double getMoneyAmount() {
    return moneyAmount;
  }

  public void setMoneyAmount(double moneyAmount) {
    this.moneyAmount = moneyAmount;
  }
}
