void logTransaction(char type, int amount, int balance) {
  int newBalance = balance - amount;

  switch (type) {
    case 'c':
      System.out.println("Credited: £" + amount);
      break;
    case 'd':
      System.out.println("Debited: £" + amount);
      break;
    case 'f':
      newBalance = balance;
      System.out.println("Transaction has been flagged so no action taken.");
      break;
    default:
      break;
  }
  System.out.println("Account balance: £" + newBalance);
}