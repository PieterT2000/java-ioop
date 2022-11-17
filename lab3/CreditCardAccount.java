/**
 * CreditCardAccount
 */
public class CreditCardAccount extends CardAccount {
  private double overdraft;

  public CreditCardAccount(int[] accountNumber, String accountHolderName, double overdraft) {
    super(accountNumber, accountHolderName);
    this.overdraft = overdraft;
  }

  public void processTransaction(char type, double amount) {
    double balance = super.getBalance();
    if (type == 'd' && balance - amount < -this.overdraft) {
      System.out.println("Transaction declined - overdraft limit reached");
    } else {
      super.processTransaction(type, amount);
    }
  }
}