
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

class Main {

    private static int getIntInput(Scanner scanner, String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                int value = Integer.parseInt(input);
                if (value < min || value > max) {
                    System.out.println("Please enter a number between " + min + " and " + max + ".");
                } else {
                    return value;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
            }
        }
    }

    private static int getIntInput(Scanner scanner, int min, int max) {
        while (true) {
            String input = scanner.nextLine().trim();
            try {
                int value = Integer.parseInt(input);
                if (value < min || value > max) {
                    System.out.println("Please enter a number between " + min + " and " + max + ".");
                } else {
                    return value;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
            }
        }
    }

    private static int getIntInput(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                int value = Integer.parseInt(input);
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
            }
        }
    }

    private static double getDoubleInput(Scanner scanner, String prompt, double min) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                double value = Double.parseDouble(input);
                if (value < min) {
                    System.out.println("Please enter a number greater than or equal to " + min + ".");
                } else {
                    return value;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
            }
        }
    }

    private static double getDoubleInput(Scanner scanner, double min) {
        while (true) {
            String input = scanner.nextLine().trim();
            try {
                double value = Double.parseDouble(input);
                if (value < min) {
                    System.out.println("Please enter a number greater than or equal to " + min + ".");
                } else {
                    return value;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Bank bank = new Bank();
        boolean loggedIn = false;
        Customer current = null;
        boolean accountSelected = false;
        Account currentAccount = null;

        while (true) {
            if (!loggedIn) {

                System.out.println("Welcome to the Java Bank!");
                System.out.println("1 - Log In");
                System.out.println("2 - Create a user");
                System.out.println("3 - Exit");
                int choice = getIntInput(scanner, "Enter your choice: ", 1, 3);

                if (choice == 1) {
                    System.out.println("Enter in your User ID: ");
                    String userId = scanner.nextLine().trim();
                    boolean userExist = bank.containsId(userId);
                    if (userExist == false) {
                        System.out.println("This ID is not valid!");
                    }

                    System.out.println("Enter in your password: ");
                    String userPass = scanner.nextLine().trim();
                    Customer customer = bank.getCustomerById(userId);
                    if (customer != null) {
                        if (customer.getPassword().equals(userPass)) {
                            System.out.println("Log in success!");
                            current = customer;
                            loggedIn = true;
                        } else {
                            System.out.println("Password was incorrect!");
                        }
                    } else {
                        System.out.println("User ID is incorrect!");
                    }
                } else if (choice == 2) {
                    System.out.println("Enter in your User ID:");
                    String userId = scanner.nextLine().trim();
                    if (bank.containsId(userId)) {
                        System.out.println("This ID has been taken!");
                    } else {
                        System.out.println("Enter in your name:");
                        String name = scanner.nextLine().trim();
                        System.out.println("Enter in your password:");
                        String password = scanner.nextLine().trim();
                        Customer customer = new Customer(name, userId, password);
                        bank.addUser(customer);
                        System.out.println("Success!");

                    }

                }
            } else if (loggedIn && accountSelected == false) {
                List<Account> accountList = bank.searchByUser(current);
                if (accountList.isEmpty()) {
                    System.out.println("Create an account");
                    System.out.println("1 - Savings");
                    System.out.println("2 - Checking");
                    int accountType = getIntInput(scanner, "Enter your choice: ", 1, 2);
                    if (accountType == 1) {
                        System.out.println("Would you like to deposit money?");
                        System.out.println("1 - Yes");
                        System.out.println("2 - No");
                        int fillAccount = getIntInput(scanner, "Enter your choice: ", 1, 2);
                        if (fillAccount == 1) {
                            System.out.println("How much would you like to deposit?");
                            double depositAmount = getDoubleInput(scanner, 0.01);
                            System.out.println("What would you like to name the account?");
                            String accName = scanner.nextLine().trim();
                            Savings acc = new Savings(0.025, current, depositAmount, accName);
                            System.out.println("Your annual interest rate is 2.5%");
                            accountList.add(acc);

                        } else {
                            System.out.println("What would you like to name the account?");
                            String accName = scanner.nextLine().trim();
                            Savings acc = new Savings(0.025, current, 0, accName);
                            accountList.add(acc);
                        }
                    } else if (accountType == 2) {
                        System.out.println("Name this account: ");
                        String checkName = scanner.nextLine().trim();
                        System.out.println("Would you like to deposit money?");
                        System.out.println("1 - Yes");
                        System.out.println("2 - No");
                        int depositMoney = getIntInput(scanner, "Enter your choice: ", 1, 2);
                        if (depositMoney == 1) {
                            System.out.println("How much would you like to deposit?");
                            double depositAmount = getDoubleInput(scanner, 0.01);
                            CheckingAccount acc = new CheckingAccount(current, depositAmount, checkName);
                            System.out.println("Success!");
                            accountList.add(acc);
                        } else {
                            CheckingAccount acc = new CheckingAccount(current, 0, checkName);
                            System.out.println("Success!");
                            accountList.add(acc);
                        }

                    }

                } else {
                    System.out.println("Select your account: ");
                    for (int j = 0; j < accountList.size(); j++) {
                        Account account = accountList.get(j);
                        System.out.println(account.getName());
                    }
                    System.out.println("Type the name of the account you wish to select: ");
                    String accountName = scanner.nextLine().trim();
                    for (int i = 0; i < accountList.size(); i++) {
                        Account account = accountList.get(i);
                        if (account.getName().equals(accountName)) {
                            currentAccount = account;
                            break;
                        }
                    }
                    if (currentAccount == null) {
                        System.out.println("Wrong name!");
                    } else {
                        accountSelected = true;
                    }
                }
            } else {
                System.out.println("1 - Deposit");
                System.out.println("2 - Withdraw");
                System.out.println("3 - View card statement");
                if (currentAccount instanceof CheckingAccount) {
                    System.out.println("4 - Link a debit card");
                    if (((CheckingAccount) currentAccount).hasCard()) {
                        System.out.println("5 - Card settings");
                        System.out.println("6 - Authorize a debit card purchase");
                    }
                }
                System.out.println("7 - View Transactions");
                System.out.println("8 - View Banking Report");
                System.out.println("9 - Log out");

                int actionType = getIntInput(scanner, "Enter your choice: ", 1, 9);

                if (actionType == 1) {
                    System.out.println("How much would you like to deposit?");
                    double depositAmount = getDoubleInput(scanner, 0.01);
                    if (depositAmount < 0 || depositAmount == 0) {
                        System.out.println("Enter a number above 0!");
                    } else {
                        currentAccount.deposit(depositAmount);
                    }
                } else if (actionType == 2) {
                    System.out.println("Enter in how much you would like to withdraw");
                    double withdrawAmount = getDoubleInput(scanner, 0.01);
                    if (withdrawAmount < 0 || withdrawAmount == 0) {
                        System.out.println("Enter a number above 0!");
                    } else {
                        currentAccount.withdraw(withdrawAmount);
                    }

                } else if (actionType == 3) {
                    System.out.println("You spent " + currentAccount.getPurchaseTotal() + " last month");
                } else if (actionType == 4) {
                    long min = 100_000_000_000L;
                    long max = 999999999999L;
                    long cardNum = min + (long) (Math.random() * (max - min + 1));
                    int cvv = 100 + (int) (Math.random() * 900);
                    LocalDate expiry = LocalDate.now().plusYears(3);
                    int pin = getIntInput(scanner, "Enter your pin (Choose a complex password): ");
                    DebitCard card = new DebitCard(cardNum, (CheckingAccount) currentAccount, cvv, false, 5000, 0, expiry, "Valid", pin);
                    String formatted = bank.formatCreditCard(cardNum);
                    System.out.println("Your card number is: " + "5127 " + formatted);
                    System.out.println("Your cvv is: " + cvv);
                    System.out.println("Your expiry date is: " + expiry);
                    System.out.println("Note these all down!");
                    ((CheckingAccount) currentAccount).setCard(card);

                } else if (actionType == 5) {
                    DebitCard card = ((CheckingAccount) currentAccount).getCard();
                    if (card.getIsFrozen()) {
                        System.out.println("1 - Unfreeze card");
                    } else {
                        System.out.println("1 - Freeze card");
                    }
                    System.out.println("2 - View Status");
                    System.out.println("3 - Renew Card");
                    System.out.println("Select option: ");
                    int cardOption = getIntInput(scanner, "Enter your choice: ", 1, 3);

                    if (cardOption == 1 && card.getIsFrozen()) {
                        card.unfreezeCard();
                        System.out.println("Your card is now unfrozen!");
                    } else if (cardOption == 1 && !card.getIsFrozen()) {
                        card.freezeCard();
                        System.out.println("Your card is now frozen!");
                    } else if (cardOption == 2) {
                        if (card.getStatus().equals("Valid")) {
                            System.out.println("Your card is valid");
                        } else {
                            System.out.println("Your card is invalid");
                        }
                    } else if (cardOption == 3) {
                        System.out.println("Renewing card...");
                        card.renew();
                        System.out.println("Your card is now valid for three more years!");

                    }

                } else if (actionType == 6) {
                    DebitCard card = ((CheckingAccount) currentAccount).getCard();
                    System.out.println("Enter purchase amount: ");
                    double purchaseAmount = Double.parseDouble(scanner.nextLine().trim());
                    System.out.println("What category of spending?");
                    System.out.println("1 - Entertainment");
                    System.out.println("2 - Food");
                    System.out.println("3 - Utilities");
                    System.out.println("4 - Housing");
                    System.out.println("5 - Personal Items");
                    int categoryType = getIntInput(scanner, "Enter your choice: ", 1, 5);

                    String category = "";
                    if (categoryType == 1) {
                        category = "Entertainment";
                    } else if (categoryType == 2) {
                        category = "Food";
                    } else if (categoryType == 3) {
                        category = "Utilities";
                    } else if (categoryType == 4) {
                        category = "Housing";
                    } else {
                        category = "Personal";
                    }
                    int pin = getIntInput(scanner, "Enter your pin: ");
                    card.authorizePurchase(purchaseAmount, pin, category);

                } else if (actionType == 7) {
                    System.out.println("Would you like to sort by type?");
                    System.out.println("1 - Withdrawals");
                    System.out.println("2 - Deposits");
                    System.out.println("3 - All");
                    System.out.println("4 - Purchases");
                    int type = getIntInput(scanner, "Enter your choice: ", 1, 4);

                    if (type == 1) {
                        currentAccount.getTransactions("Withdraw");
                    } else if (type == 2) {
                        currentAccount.getTransactions("Deposit");
                    } else if (type == 3) {
                        currentAccount.getTransactions();
                    } else if (type == 4) {
                        currentAccount.getTransactions("Purchase");
                    }
                } else if (actionType == 8) {
                    currentAccount.getBankReport();
                } else if (actionType == 9) {
                    loggedIn = false;
                    accountSelected = false;
                    break;
                }

            }

        }

    }

}
