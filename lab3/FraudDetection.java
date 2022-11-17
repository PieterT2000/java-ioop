/**
 * FraudDetection
 */
public class FraudDetection {

  static boolean suspiciousActivityCheck(CardAccount suspectAccount) {
    Object[] last3TransactionsArray = suspectAccount.getLast3Transactions().toArray();
    double sumOfLast3Transactions = 0;
    for (Object transaction : last3TransactionsArray) {
      sumOfLast3Transactions += Double.parseDouble(transaction.toString());
    }
    System.out.println("Sum: " + sumOfLast3Transactions + ", MaxWithdrawal: " + suspectAccount.getMaxWithdrawal());

    return sumOfLast3Transactions > (suspectAccount.getMaxWithdrawal() / 2);
  }
}