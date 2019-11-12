public class Account {
  Account(long id, double money) {
    this.id = id;
    this.money = money;
  }
  
  private long id;
  private double money;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public double getMoney() {
    return money;
  }

  public void setMoney(double money) {
    this.money = money;
  }
}
