import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class BankOperations {
    private List<Client> clients;

    public BankOperations() {
        this.clients = new ArrayList<>();
    }
  
    public void addClient(Client client) {
        this.clients.add(client);
    }
    
    public void addClient(String lastName, String passportNumber, Account account) {
         BankClient client = new BankClient(lastName, passportNumber);
        client.addAccount(account);
        this.clients.add(client);
    }

    public void addClient(String lastName, String passportNumber, Account... accounts) {
        BankClient client = new BankClient(lastName, passportNumber);
        for (Account account : accounts) {
            client.addAccount(account);
        }
        this.clients.add(client);
    }

    public void printClients(){
        for (Client client : clients){
            System.out.println(client);
        }
    }

    public Client findClientByAccount(String accountNumber) {
        return clients.stream()
                .filter(client -> client.getAccounts().stream().anyMatch(account -> account.getAccountNumber().equals(accountNumber)))
                .findFirst()
                .orElse(null);
    }

   public double calculateTotalInterestForClient(Client client) {
        return client.getAccounts().stream()
                .filter(account -> !account.isClosed())
                .mapToDouble(Account::calculateInterest)
                .sum();
    }


    public Client findClientWithMaxInterest() {
        return clients.stream()
                .max(Comparator.comparingDouble(this::calculateTotalInterestForClient))
                .orElse(null);
    }

    public double getMaxInterestAmount() {
        Client clientWithMaxInterest = findClientWithMaxInterest();
        if (clientWithMaxInterest != null) {
            return calculateTotalInterestForClient(clientWithMaxInterest);
        } else {
            return 0;
        }
    }

    public String findMinDepositAccount() {
       return clients.stream()
                .flatMap(client -> client.getAccounts().stream())
               .filter(account -> !account.isClosed())
                .min(Comparator.comparing(Account::getDepositAmount))
                .map(account -> "Account Number: " + account.getAccountNumber() +
                        ", Opening Year: " + account.getOpeningDate().getYear())
                .orElse("No accounts found");
    }


    public void transferMoney(String fromAccountNumber, String toAccountNumber, double amount) {
      Account fromAccount = findAccountByNumber(fromAccountNumber);
      Account toAccount = findAccountByNumber(toAccountNumber);
        if(fromAccount == null || toAccount == null)
            throw new IllegalArgumentException("Счет не найден");
        if(fromAccount.getDepositAmount() < amount)
            throw new IllegalArgumentException("Недостаточно средств");
        fromAccount.setDepositAmount(fromAccount.getDepositAmount() - amount);
        toAccount.setDepositAmount(toAccount.getDepositAmount() + amount);
       System.out.println("Перевод выполнен!");
    }


    private Account findAccountByNumber(String accountNumber) {
        return clients.stream()
                .flatMap(client -> client.getAccounts().stream())
                .filter(account -> account.getAccountNumber().equals(accountNumber))
                .findFirst()
                .orElse(null);
    }

    public void removeClosedAccountClients() {
        clients.removeIf(client -> !client.hasOpenAccounts());
    }

    public List<Client> findClientsWithLongTermDeposits(int years) {
     return clients.stream()
             .filter(client -> client.getAccounts().stream()
                     .filter(account -> !account.isClosed())
                     .anyMatch(account ->
                             Period.between(account.getOpeningDate(), LocalDate.now()).getYears() >= years))
             .collect(Collectors.toList());
    }
    public List<Client> getClients() {
        return this.clients;
    }
}