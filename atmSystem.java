import java.util.*;


public class atmSystem {
    private ArrayList<userAccount> accountList;
    
    public atmSystem() {   
        accountList = new ArrayList<>();
    }

    public void addAccount(userAccount newAccount) {
        accountList.add(newAccount);
    }

    private userAccount findAccount(String accountNumber) {
        for (userAccount account : accountList) {
            if (account.getAccountNumber().equals(accountNumber))
                return account;
        }
        return null;
    }
    
    Scanner scanner = new Scanner(System.in);

    public void menu() {
        System.out.print("Enter Card Number: ");
        String cardNumber = scanner.next();

        userAccount account = findAccount(cardNumber);	
        if (account == null) {
            System.out.println("Invalid Card Number/Account not found. Try Again");
            return;
        }

        System.out.print("Enter PIN: ");
        String inputPin = scanner.next();

        if (!account.verifyPin(inputPin)) {
            System.out.println("Invalid PIN. Try Again");
            return;
        }

        int choice;
        do {
            System.out.println("\nATM Menu\n1. Cash Withdrawal\n2. Cash Deposit\n3. Balance Enquiry\n4. Exit");
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter Amount to Withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    if (account.withdraw(withdrawAmount))
                        System.out.print("Amount Withdrawn Successfully");
                    else
                        System.out.println("Insufficient Balance");
                    break;

                case 2:
                    System.out.print("Enter Amount to Deposit: ");
                    double depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount);
                    System.out.println("Amount Deposited Successfully");
                    break;

                case 3:
                    System.out.println("Available Balance is Rs. " + account.checkBalance());
                    break;

                case 4:
                    System.out.println("Transaction Completed");
                    break;
            }
        } while (choice != 4);
    }

    public static void main(String args[]) {
        atmSystem atm = new atmSystem();

        atm.addAccount(new userAccount("1234", "1234", 10000));
        atm.addAccount(new userAccount("5678", "4321", 1800));
        atm.addAccount(new userAccount("1793", "1793", 10500));
        atm.addAccount(new userAccount("3971", "3971", 22500));
        atm.addAccount(new userAccount("5155", "5551", 515500));

        atm.menu();
    }
}