import java.util.List;
import java.util.ArrayList;

class BankClient implements Client{
    String lastName;
    String passportNumber;
    List<Account> accounts;

    public BankClient(String lastName, String passportNumber) {
        this.lastName = lastName;
        this.passportNumber = passportNumber;
        this.accounts = new ArrayList<>();
    }

    @Override
    public void addAccount(Account account) {
        this.accounts.add(account);
    }

    @Override
    public List<Account> getAccounts() {
        return this.accounts;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
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