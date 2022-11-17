/**
 * DebitCardAccount
 */
public class DebitCardAccount extends CardAccount {
  public DebitCardAccount(int[] accountNumber, String accountHolderName) {
    super(accountNumber, accountHolderName);
  }

  public void processTransaction(char type, double amount) {
    double balance = super.getBalance();
    if (type == 'd' && balance - amount < 0) {
      System.out.println("Transaction declined - debit account cannot go below 0");
    } else {
      super.processTransaction(type, amount);
    }
  }
}