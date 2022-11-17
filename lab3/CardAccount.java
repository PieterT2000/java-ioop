import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class CardAccount {
	private int[] accountNumber;
	private double balance = 0;
	private String accountHolderName;
	private double maxWithdrawal = 0;
	private Queue<Double> last3TransactionsQueue = new LinkedList<>();

	public CardAccount(
			int[] accountNumber, String accountHolderName) {
		this.setAccountNumber(accountNumber);
		this.accountHolderName = accountHolderName;
	}

	public void setAccountNumber(int[] accountNumber) {
		System.out.println("Checking account:" + CardAccount.outputNumberFormatted(accountNumber));
		int[] batwest = { 4, 3, 3, 4, 2, 1 };
		int checkSum = 0;
		boolean validBank = true;
		boolean validPersonal = true;

		for (int position = 0; position < 6; position++) {
			if (accountNumber[position] != batwest[position]) {
				validBank = false;
			}
		}

		if (validBank) {
			System.out.println("The bank managing the account is batwest");
		} else {
			System.out.println("The bank managing the account is unrecognised");
		}

		for (int position = 6; position < 15; position++) {
			if (accountNumber[position] != 0) {
				checkSum++;
			} else if (accountNumber[position] < 0) {
				validBank = false;
			}
		}

		if (checkSum != accountNumber[15]) {
			validPersonal = false;
			System.out.println("Personal account number not valid");
		} else {
			System.out.println("Personal account number valid");
		}

		if (validPersonal && validBank) {
			this.accountNumber = accountNumber;
			System.out.println("Account number valid");
		} else {
			System.out.println("Account number not valid");
		}
	}

	static public String outputNumberFormatted(int[] accountNumber) {
		// Modify me for Task 3!
		String number = "";
		for (int position = 0; position < 16; position++) {
			if (position % 4 == 0 && position != 0) {
				number += " ";
			}
			number += accountNumber[position];
		}
		return number;
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

	public void processTransaction(char type, double amount) {
		switch (type) {
			case 'c':
				balance += amount;
				System.out.println("Credited: £" + amount);
				System.out.println("Account balance: £" + balance);
				if (balance > maxWithdrawal) {
					maxWithdrawal = balance;
				}
				break;
			case 'd':
				balance -= amount;
				System.out.println("Debited: £" + amount);
				System.out.println("Account balance: £" + balance);
				break;
			case 'f':
				System.out.println("Account has been flagged so no action taken.");
				System.out.println("Account balance: £" + balance);
				break;
		}

		last3TransactionsQueue.add(type == 'd' ? -amount : amount);

		if (last3TransactionsQueue.size() > 3) {
			last3TransactionsQueue.remove();

		}
	}

	public int[] getAccountNumber() {
		return accountNumber;
	}

	public String getAccountHolderName() {
		return accountHolderName;
	}

	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName = accountHolderName;
	}

	public double getBalance() {
		return balance;
	}

	public double getMaxWithdrawal() {
		return this.maxWithdrawal;
	}

	public Queue<Double> getLast3Transactions() {
		return this.last3TransactionsQueue;
	}

	public static void main(String[] args) {

	}

}
