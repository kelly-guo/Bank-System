
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Account {

    private Customer owner;
    private double balance;
    private List<Transaction> log;
    private String name;

    public Account(Customer owner, double balance, String name) {
        this.owner = owner;
        this.balance = balance;
        this.name = name;
        log = new ArrayList<>();
    }

    public void deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
            Transaction deposit = new Transaction(LocalDate.now(), "Deposit", amount);
            log.add(0, deposit);
        } else {
            System.out.println("Please enter an amount greater than 0!");
        }
    }

    public void withdraw(double amount) {
        if (balance < amount) {
            Transaction withdraw = new Transaction(LocalDate.now(), "Withdraw", amount);
            log.add(0, withdraw);
            System.out.println("Your balance is less than your withdrawal amount!");
        } else {
            this.balance -= amount;
        }
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public void addTransaction(Transaction transaction) {
        log.add(0, transaction);
    }

    public void getTransactions() {
        for (int i = 0; i < log.size(); i++) {
            log.get(i).printTransaction();
        }
    }

    public void getTransactions(String type) {
        for (int i = 0; i < log.size(); i++) {
            Transaction trans = log.get(i);
            if (trans.getType().equals(type)) {
                log.get(i).printTransaction();
            };
        }
    }

    public double getPurchaseTotal() {
        double sum = 0;

        for (int i = 0; i < log.size(); i++) {
            Transaction curr = log.get(i);
            if (curr.getType().equals("Purchase")) {
                sum += curr.getAmount();
            }
        }

        return sum;
    }

    public void getBankReport() {
        double foodPercent = 0;
        double totalTransactions = 0;//monthly
        double enterPercent = 0;
        double utilPercent = 0;
        double housePercent = 0;
        double persPercent = 0;
        for (int i = 0; i < log.size(); i++) {
            Transaction curr = log.get(i);
            if (curr.getDate().isBefore(LocalDate.now().minusMonths(1))) {
                continue;
            } else {
                totalTransactions += curr.getAmount();
                if (curr.getCategory().equals("Food")) {
                    foodPercent += curr.getAmount();
                } else if (curr.getCategory().equals("Entertainment")) {
                    enterPercent += curr.getAmount();
                } else if (curr.getCategory().equals("Utilities")) {
                    utilPercent += curr.getAmount();
                } else if (curr.getCategory().equals("Housing")) {
                    housePercent += curr.getAmount();
                } else {
                    persPercent += curr.getAmount();
                }
            }
        }

        if (totalTransactions == 0) {
            System.out.println("No transactions this month");
            return;
        }
        foodPercent = (foodPercent / totalTransactions) * 100;
        enterPercent = (enterPercent / totalTransactions) * 100;
        utilPercent = (utilPercent / totalTransactions) * 100;
        housePercent = (housePercent / totalTransactions) * 100;
        persPercent = (persPercent / totalTransactions) * 100;
        System.out.println("You spent " + foodPercent + "% of your total purchase amount on food in a month");
        System.out.println("You spent " + enterPercent + "% of your total purchase amount on entertainment in a month");
        System.out.println("You spent " + utilPercent + "% of your total purchase amount on utilities in a month");
        System.out.println("You spent " + housePercent + "% of your total purchase amount on housing in a month");
        System.out.println("You spent " + persPercent + "% of your total purchase amount on personal items in a month");

    }

}
