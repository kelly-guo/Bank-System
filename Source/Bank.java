
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Bank {

    Map<String, ArrayList<Account>> accounts;
    Set<Customer> users;

    public Bank() {
        accounts = new HashMap<>();
        users = new HashSet<>();

    }

    public ArrayList<Account> searchByUser(Customer user) {
        String ID = user.getID();
        if (accounts.containsKey(ID)) {
            return accounts.get(ID);

        } else {
            System.out.println("This ID is not in the system!");
        }
        return null;

    }

    public boolean containsId(String Id) {
        return accounts.containsKey(Id);
    }

    public Customer getCustomerById(String Id) {
        for (Customer customer : users) {
            if (customer.getID().equals(Id)) {
                return customer;
            }
        }
        return null;

    }

    public void addAccount(Customer customer, Account account) {
        accounts.get(customer.getID()).add(account);
    }

    public void addUser(Customer customer) {
        if (!accounts.containsKey(customer.getID())) {
            accounts.put(customer.getID(), new ArrayList<>());
            users.add(customer);
        } else {
            System.out.println("This ID already exists!");
        }
    }

    public String formatCreditCard(long num) {
        String digits = String.format("%012d", num);
        return (formatCard(digits));

    }

    public String formatCard(String num) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < num.length(); i++) {
            if (i > 0 && i % 4 == 0) {
                sb.append(' ');
            }
            sb.append(num.charAt(i));

        }
        return sb.toString();
    }

}
