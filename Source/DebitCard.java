
import java.time.LocalDate;

public class DebitCard {

    private long cardNumber; //always starts with 5217
    private CheckingAccount linkedAccount;
    private int cvv;
    private boolean isFrozen;
    private int dailyLimit;
    private int dailyUsed;
    private LocalDate expiry;
    private String status; //Valid, Invalid
    private int pin;

    public DebitCard(long cardNumber, CheckingAccount linkedAccount, int cvv, boolean isFrozen, int dailyLimit, int dailyUsed, LocalDate expiry, String status, int pin) {
        this.cardNumber = cardNumber;
        this.linkedAccount = linkedAccount;
        this.cvv = cvv;
        this.isFrozen = isFrozen;
        this.dailyLimit = dailyLimit;
        this.dailyUsed = dailyUsed;
        this.expiry = expiry;
        this.status = status;
        this.pin = pin;
    }

    public void authorizePurchase(double amount, int pin) {
        if (LocalDate.now().isAfter(expiry)) {
            System.out.println("Your card is expired");
            status = "Invalid";
        } else if (pin == this.pin && amount <= linkedAccount.getBalance() && status.equals("Valid") && isFrozen == false && dailyUsed + amount <= dailyLimit) {
            linkedAccount.withdraw(amount);
            Transaction purchase = new Transaction(LocalDate.now(), "Purchase", amount);
            linkedAccount.addTransaction(purchase);
            dailyUsed += amount;

        } else {
            System.out.println("This purchase could not go through!");

        }
    }

    public void authorizePurchase(double amount, int pin, String category) {
        if (LocalDate.now().isAfter(expiry)) {
            System.out.println("Your card is expired");
            status = "Invalid";
        } else if (pin == this.pin && amount <= linkedAccount.getBalance() && status.equals("Valid") && isFrozen == false && dailyUsed + amount <= dailyLimit) {
            linkedAccount.withdraw(amount);
            Transaction purchase = new Transaction(LocalDate.now(), "Purchase", amount, category);
            linkedAccount.addTransaction(purchase);
            dailyUsed += amount;
            System.out.println("Purchase of " + amount + " was successful!");

        } else {
            System.out.println("This purchase could not go through!");

        }
    }

    public void freezeCard() {
        this.isFrozen = true;
    }

    public void unfreezeCard() {
        this.isFrozen = false;
    }

    public boolean getIsFrozen() {
        return isFrozen;
    }

    public String getStatus() {
        return status;
    }

    public void renew() {
        expiry.plusYears(3);
    }

}
