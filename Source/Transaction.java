
import java.time.LocalDate;

public class Transaction {

    private LocalDate time;
    private String type; //Deposit, Withdrawal, Purchase
    private double amount;
    private String category;//Entertainment, Housing, Food, Personal,Utilities

    public Transaction(LocalDate time, String type, double amount, String category) {
        this.time = time;
        this.type = type;
        this.amount = amount;
        this.category = category;
    }

    public Transaction(LocalDate time, String type, double amount) {
        this.time = time;
        this.type = type;
        this.amount = amount;
    }

    public void printTransaction() {
        if (type.equals("Deposit")) {
            System.out.println("You deposited " + amount + "on " + time);
        } else if (type.equals("Withdraw")) {
            System.out.println("You withdrew " + amount + "on " + time);
        } else if (type.equals("Purchase")) {
            System.out.println("You spent " + amount + "on " + time);
            if (category.equals("Entertainment")) {
                System.out.println("This purchase was for entertainment");
            } else if (category.equals("Housing")) {
                System.out.println("This purchase was for housing");
            } else if (category.equals("Food")) {
                System.out.println("This purchase was for food");
            } else if (category.equals("Personal")) {
                System.out.println("This purchase was for personal items");
            } else if (category.equals("Utilities")) {
                System.out.println("This purchase was for utility");
            }
        }
        System.out.println("");

    }

    public String getType() {
        return type;
    }

    public LocalDate getDate() {
        return time;
    }

    public double getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;

    }

}
