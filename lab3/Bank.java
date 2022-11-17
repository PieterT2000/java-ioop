import java.util.Arrays;

public class Bank {
  private CardAccount[] cardAccounts;

  public static void main(String[] args) {
    // Bank bank = new Bank();
    // bank.handleTransaction(new int[] { 4, 3, 3, 4, 2, 1, 6, 7, 5, 5, 4, 4, 8, 0,
    // 0, 7 }, "A Smith", 20, 'c');
    // bank.handleTransaction(new int[] { 4, 3, 3, 4, 2, 1, 6, 7, 5, 5, 4, 4, 8, 0,
    // 0, 7 }, "A Smith", 30, 'c');
    // CardAccount accountToBeChecked = bank
    // .handleTransaction(new int[] { 4, 3, 3, 4, 2, 1, 6, 7, 5, 5, 4, 4, 8, 0, 0, 7
    // }, "A Smith", 24, 'd');
    // bank.handleTransaction(new int[] { 4, 3, 3, 4, 2, 1, 6, 7, 5, 5, 4, 4, 8, 5,
    // 3, 9 }, "O Jones", 100, 'c');
    // System.out.println(FraudDetection.suspiciousActivityCheck(accountToBeChecked));
  }

  Bank() {
    int[] accountOne = new int[] { 4, 3, 3, 4, 2, 1, 6, 7, 5, 5, 4, 4, 8, 0, 0, 7 };
    int[] accountTwo = new int[] { 4, 3, 3, 4, 2, 1, 6, 7, 5, 5, 4, 4, 8, 5, 3, 9 };
    int[] accountThree = new int[] { 4, 3, 3, 4, 2, 1, 0, 7, 0, 5, 4, 4, 8, 0, 0, 5 };
    String nameOne = "Alice Smith";
    String nameTwo = "Osian Jones";
    String nameThree = "Claire Bevan";

    this.cardAccounts = new CardAccount[] { new CardAccount(accountOne, nameOne),
        new CreditCardAccount(accountTwo, nameTwo, 50),
        new CardAccount(accountThree, nameThree) };
  }

  CardAccount[] getCardAccounts() {
    return this.cardAccounts;
  }

  public CardAccount handleTransaction(int[] accountNumber,
      String name, double amount, char type) {
    // find CardAccount object by account number
    CardAccount foundAccount = null;

    for (CardAccount account : this.cardAccounts) {
      if (Arrays.equals(account.getAccountNumber(), accountNumber)) {
        foundAccount = account;
        break;
      }
    }

    CardAccount account = foundAccount;
    if (account != null) {
      if (account.checkName(name)) {
        account.processTransaction(type, amount);
        return account;
      } else {
        System.out.println("Incorrect name");
        return account;
      }
    } else {
      System.out.println("No matching account found");
      return null;
    }
  }
}