package PROJECTS;
import java.util.Scanner;
class BankAccount {
        private double balance;

        public BankAccount(double initialBalance) {
            if (initialBalance < 0) {
                throw new IllegalArgumentException("Initial balance can't be negative.");
            }
            this.balance = initialBalance;
        }

        public double getBalance() {
            return balance;
        }

        public boolean withdraw(double amount) {
            if (amount <= 0) {
                System.out.println("Withdrawal amount must be greater than zero.");
                return false;
            }
            if (amount > balance) {
                System.out.println("Insufficient balance!");
                return false;
            }
            balance -= amount;
            return true;
        }

        public boolean deposit(double amount) {
            if (amount <= 0) {
                System.out.println("Deposit amount must be greater than zero.");
                return false;
            }
            balance += amount;
            return true;
        }
    }

    class ATM {
        private BankAccount account;

        public ATM(BankAccount account) {
            this.account = account;
        }

        public void withdraw(double amount) {
            if (account.withdraw(amount)) {
                System.out.println("Withdrawal of $" + amount + " successful.");
            } else {
                System.out.println("Withdrawal failed.");
            }
        }

        public void deposit(double amount) {
            if (account.deposit(amount)) {
                System.out.println("Deposit of $" + amount + " successful.");
            } else {
                System.out.println("Deposit failed.");
            }
        }

        public void checkBalance() {
            System.out.println("Current balance: $" + account.getBalance());
        }
    }

    public class ATMApp {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            BankAccount userAccount = new BankAccount(1000.00);  // Initial balance
            ATM atm = new ATM(userAccount);

            int choice;
            do {
                System.out.println("\n====== ATM MENU ======");
                System.out.println("1. Withdraw");
                System.out.println("2. Deposit");
                System.out.println("3. Check Balance");
                System.out.println("4. Exit");
                System.out.print("Choose an option: ");

                while (!scanner.hasNextInt()) {
                    System.out.print("Invalid input. Enter a number: ");
                    scanner.next();
                }
                choice = scanner.nextInt();

                switch (choice) {
                    case 1 -> {
                        System.out.print("Enter amount to withdraw: ");
                        double amount = readPositiveDouble(scanner);
                        atm.withdraw(amount);
                    }
                    case 2 -> {
                        System.out.print("Enter amount to deposit: ");
                        double amount = readPositiveDouble(scanner);
                        atm.deposit(amount);
                    }
                    case 3 -> atm.checkBalance();
                    case 4 -> System.out.println("Thank you for using the ATM.");
                    default -> System.out.println("Invalid option. Please try again.");
                }
            } while (choice != 4);
        }

        private static double readPositiveDouble(Scanner scanner) {
            while (!scanner.hasNextDouble()) {
                System.out.print("Invalid input. Enter a positive number: ");
                scanner.next();
            }
            double amount = scanner.nextDouble();
            if (amount <= 0) {
                System.out.print("Amount must be greater than zero. Try again: ");
                return readPositiveDouble(scanner);
            }
            return amount;
        }
}

