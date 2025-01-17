import java.util.List;

interface Client {
    String getLastName();
    List<Account> getAccounts();
  void addAccount(Account account);
    boolean hasOpenAccounts();
}