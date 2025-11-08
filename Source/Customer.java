
public class Customer {

    private String name;
    private String ID;
    private String password;

    public Customer(String name, String ID, String password) {
        this.name = name;
        this.ID = ID;
        this.password = password;
    }

    public String getID() {
        return ID;
    }

    public String getPassword() {
        return password;
    }

}
