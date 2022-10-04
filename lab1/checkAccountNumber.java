void checkAccountNumber(int[] account) {
  String accountString = "";
  for (int digit : account) {
    accountString += digit;
  }
  System.out.println("Checking account: " + accountString);

  //banks 
  int[] batwest = new int[] {4,3,3,4,2,1};
  int[] boyds = new int[] {2,0,0,1,2,3};

  String bank;
  int[] bankNumber = Arrays.copyOfRange(account, 0, 6);
  if(Arrays.equals(bankNumber, batwest)) {
    bank = "batwest";
  } else if(Arrays.equals(bankNumber, boyds)) {
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
    if(digit < 0 || digit > 9) {
      invalidDigits = true;
      break;
    }
    if(digit > 0) {
      nonZeroDigitsInPersonalAccountNumber += 1;
    }
  }

  boolean personalAccountNumberValid = !invalidDigits && nonZeroDigitsInPersonalAccountNumber == checksum;
  // boolean accountNumber;
  if(personalAccountNumberValid) {
    System.out.println("Personal account number valid");
  } else {
    System.out.println("Personal account number not valid");
  }

  if(bank != "unknown" && personalAccountNumberValid) {
    System.out.println("Account number valid");
  } else {
    System.out.println("Account number not valid"); 
  }
}