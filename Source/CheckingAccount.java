
public class CheckingAccount extends Account {

    private DebitCard linkedCard;

    public CheckingAccount(DebitCard linkedCard, Customer owner, double balance, String name) {
        super(owner, balance, name);
        this.linkedCard = linkedCard;
    }

    public CheckingAccount(Customer owner, double balance, String name) {
        super(owner, balance, name);
    }

    public boolean hasCard() {
        if (linkedCard != null) {
            return true;
        }

        return false;
    }

    public DebitCard getCard() {
        if (linkedCard != null) {
            return linkedCard;
        }
        return null;
    }

    public void setCard(DebitCard card) {
        linkedCard = card;
    }

}
