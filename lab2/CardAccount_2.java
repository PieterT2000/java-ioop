import java.util.Arrays;

class CardAccount_2 {
  int[] accountNumber;
  double balance;
  String accountHolderName;

  // public static void main(String[] args) {
  // System.out.println("CardAccount.main()");
  // CardAccount account = new CardAccount(null, "P. van Tuijl");
  // account.setAccountNumber(new int[] { 2, 3, 3, 4, 2, 0, 6, 7, 5, 3, 4, 0, 8,
  // 0, 0, 7 });
  // account.processTransaction('c', 10);
  // account.processTransaction('c', 10);
  // account.processTransaction('f', 20);
  // System.out.println(account.checkName("P van Tuijl"));
  // System.out.println(account.checkName("j doe"));
  // System.out.println(account.checkName("p. van tuijl"));
  // System.out.println(account.checkName("p Vantuijl"));
  // System.out.println(account.checkName("p d tuijl"));
  // }

  CardAccount_2(int[] number, String name) {
    this.accountHolderName = name;
    this.setAccountNumber(number);
  }

  public void setAccountNumber(int[] accountNumber) {
    if (checkAccountNumber(accountNumber)) {
      this.accountNumber = accountNumber;
    }
  }

  public int[] getAccountNumber() {
    return this.accountNumber;
  }

  public double getBalance() {
    return this.balance;
  }

  public String getAccountHolderName() {
    return this.accountHolderName;
  }

  public void processTransaction(char type, double amount) {
    switch (type) {
      case 'c':
        this.balance += amount;
        System.out.println("Credited: £" + amount);
        break;
      case 'd':
        this.balance -= amount;
        System.out.println("Debited: £" + amount);
        break;
      case 'f':
        System.out.println("Transaction has been flagged so no action taken.");
        break;
      default:
        break;
    }
    System.out.println("Account balance: £" + this.balance);
  }

  public boolean checkName(String name) {
    String accountName = this.accountHolderName.toLowerCase();
    name = name.toLowerCase();

    char origInitial = accountName.charAt(0);
    char initial = name.charAt(0);
    String[] origNameSplit = accountName.split(" ");
    String[] nameSplit = name.split(" ");
    String origLastName = String.join(" ", Arrays.copyOfRange(origNameSplit, 1, origNameSplit.length));
    String lastName = String.join(" ", Arrays.copyOfRange(nameSplit, 1, nameSplit.length));

    if (accountName.equals(name)) {
      return true;
    } else if (initial == origInitial
        && lastName.equals(origLastName)) {
      return true;
    } else {
      return false;
    }
  }

  boolean checkAccountNumber(int[] account) {
    if (account == null) {
      return false;
    }

    String accountString = "";
    for (int digit : account) {
      accountString += digit;
    }
    System.out.println("Checking account: " + accountString);

    // banks
    int[] batwest = new int[] { 4, 3, 3, 4, 2, 1 };
    int[] boyds = new int[] { 2, 0, 0, 1, 2, 3 };

    String bank;
    int[] bankNumber = Arrays.copyOfRange(account, 0, 6);
    if (Arrays.equals(bankNumber, batwest)) {
      bank = "batwest";
    } else if (Arrays.equals(bankNumber, boyds)) {
      bank = "boyds";
    } else {
      bank = "unknown";
    }
    System.out.println("The bank managing the account is " + bank);

    int[] personalAccountNumber = Arrays.copyOfRange(account, 6, 15);
    int checksum = account[15];

    boolean invalidDigits = false;
    int nonZeroDigitsInPersonalAccountNumber = 0;
    for (int digit : personalAccountNumber) {
      if (digit < 0 || digit > 9) {
        invalidDigits = true;
        break;
      }
      if (digit > 0) {
        nonZeroDigitsInPersonalAccountNumber += 1;
      }
    }

    boolean personalAccountNumberValid = !invalidDigits && nonZeroDigitsInPersonalAccountNumber == checksum;
    // boolean accountNumber;
    if (personalAccountNumberValid) {
      System.out.println("Personal account number valid");
    } else {
      System.out.println("Personal account number not valid");
    }

    if (bank != "unknown" && personalAccountNumberValid) {
      System.out.println("Account number valid");
    } else {
      System.out.println("Account number not valid");
    }

    return bank != "unknown" && personalAccountNumberValid;
  }
}