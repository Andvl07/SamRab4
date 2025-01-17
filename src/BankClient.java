import java.util.List;
import java.util.ArrayList;

class BankClient {
    String lastName;
    String passportNumber;
    List<Account> accounts; // Изменено на List<Account>

    public BankClient(String lastName, String passportNumber) {
        this.lastName = lastName;
        this.passportNumber = passportNumber;
        this.accounts = new ArrayList<>();
    }

    public void addAccount(Account account) {
        this.accounts.add(account);
    }

    public List<Account> getAccounts() {
        return this.accounts;
    }

    public String getLastName() {
        return lastName;
    }

    public boolean hasOpenAccounts() {
        return accounts.stream().anyMatch(account -> !account.isClosed());
    }

    @Override
    public String toString() {
        return "BankClient{" +
                "lastName='" + lastName + '\'' +
                ", passportNumber='" + passportNumber + '\'' +
                ", accounts=" + accounts +
                '}';
    }
}