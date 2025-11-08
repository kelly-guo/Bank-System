
import java.time.LocalDate;
import java.time.Period;

public class Savings extends Account {

    private double interest; //annual
    private LocalDate creation;
    private LocalDate lastInterest;

    public Savings(double interest, Customer owner, double balance, String name) {
        super(owner, balance, name);
        this.interest = interest;
        this.creation = LocalDate.now();
    }

    public void monthlyInterest() {
        double money = (double) getBalance() * (interest / 12);
        if (lastInterest == null || Period.between(lastInterest, LocalDate.now()).getMonths() >= 1) {
            deposit(money);

        }

    }

}
