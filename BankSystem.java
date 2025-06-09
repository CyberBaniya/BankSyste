import java.util.*;

class BankAccount {
    String accountHolder;
    String accountNumber;
    double balance;

    public BankAccount(String accountHolder, String accountNumber) {
        this.accountHolder = accountHolder;
        this.accountNumber = accountNumber;
        this.balance = 0.0;
    }

    public void deposit(double amount) {
        balance += amount;
        System.out.println(" Deposited: ₹" + amount);
    }

    public void withdraw(double amount) {
        if (amount > balance) {
            System.out.println(" Insufficient balance.");
        } else {
            balance -= amount;
            System.out.println("Withdrawn: ₹" + amount);
        }
    }

    public void checkBalance() {
        System.out.println(" Current Balance: ₹" + balance);
    }
}

public class BankSystem {
    static Scanner sc = new Scanner(System.in);
    static HashMap<String, BankAccount> accounts = new HashMap<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n===== Welcome to IndoLike Bank =====");
            System.out.println("1. Create Account");
            System.out.println("2. Check Balance");
            System.out.println("3. Deposit");
            System.out.println("4. Withdraw");
            System.out.println("5. Transfer");
            System.out.println("6. Exit");
            System.out.print(" Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine();  

            switch (choice) {
                case 1 -> createAccount();
                case 2 -> checkBalance();
                case 3 -> deposit();
                case 4 -> withdraw();
                case 5 -> transfer();
                case 6 -> {
                    System.out.println(" Thank you for using IndoLike Bank!");
                    return;
                }
                default -> System.out.println(" Invalid option. Try again.");
            }
        }
    }

    static void createAccount() {
        System.out.print("Enter Account Holder Name: ");
        String name = sc.nextLine();
        String accNumber = UUID.randomUUID().toString().substring(0, 8);
        BankAccount newAccount = new BankAccount(name, accNumber);
        accounts.put(accNumber, newAccount);
        System.out.println(" Account created successfully! Your Account Number: " + accNumber);
    }

    static BankAccount findAccount(String accNumber) {
        return accounts.get(accNumber);
    }

    static void checkBalance() {
        System.out.print("Enter Account Number: ");
        String acc = sc.nextLine();
        BankAccount account = findAccount(acc);
        if (account != null) {
            account.checkBalance();
        } else {
            System.out.println(" Account not found.");
        }
    }

    static void deposit() {
        System.out.print("Enter Account Number: ");
        String acc = sc.nextLine();
        BankAccount account = findAccount(acc);
        if (account != null) {
            System.out.print("Enter amount to deposit: ₹");
            double amount = sc.nextDouble();
            account.deposit(amount);
        } else {
            System.out.println(" Account not found.");
        }
    }

    static void withdraw() {
        System.out.print("Enter Account Number: ");
        String acc = sc.nextLine();
        BankAccount account = findAccount(acc);
        if (account != null) {
            System.out.print("Enter amount to withdraw: ₹");
            double amount = sc.nextDouble();
            account.withdraw(amount);
        } else {
            System.out.println(" Account not found.");
        }
    }

    static void transfer() {
        System.out.print("Enter Your Account Number: ");
        String fromAcc = sc.nextLine();
        System.out.print("Enter Recipient Account Number: ");
        String toAcc = sc.nextLine();

        BankAccount sender = findAccount(fromAcc);
        BankAccount receiver = findAccount(toAcc);

        if (sender != null && receiver != null) {
            System.out.print("Enter amount to transfer: ₹");
            double amount = sc.nextDouble();
            if (sender.balance >= amount) {
                sender.withdraw(amount);
                receiver.deposit(amount);
                System.out.println(" Transferred ₹" + amount + " to " + receiver.accountHolder);
            } else {
                System.out.println(" Insufficient balance.");
            }
        } else {
            System.out.println(" One or both accounts not found.");
        }
    }
}
