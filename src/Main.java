import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        BankOperations bank = new BankOperations();
        InterestCalculator simpleInterest = new SimpleInterestCalculator();

        // Добавляем клиентов
        bank.addClient("Иванов", "123456",
                new BankAccount("1001", 10000, LocalDate.of(2022, 1, 1), 0.05, simpleInterest),
                new BankAccount("1002", 20000, LocalDate.of(2023, 5, 15), 0.07, simpleInterest)
        );
        bank.addClient("Петров", "654321",
                new BankAccount("2001", 15000, LocalDate.of(2023, 2, 10), 0.06, simpleInterest),
                new BankAccount("2002", 30000, LocalDate.of(2024, 1, 1), 0.08, simpleInterest),
                new BankAccount("2003", 1000, LocalDate.of(2021, 1, 1),LocalDate.of(2022, 1, 1) ,0.08, simpleInterest)
        );
        bank.printClients();

        // Находим клиента с наибольшей суммой процентов
        Client maxInterestClient = bank.findClientWithMaxInterest();
        double maxInterestAmount = bank.getMaxInterestAmount();
        if (maxInterestClient != null) {
            System.out.println("\nКлиент с наибольшей суммой процентов: " + maxInterestClient.getLastName() +
                    ", сумма: " + maxInterestAmount);
        }

        // Находим счет с минимальным вкладом и год открытия
        System.out.println("\n" + bank.findMinDepositAccount());

        // Перевод средств
        try {
            bank.transferMoney("1001", "2001", 5000);
            System.out.println("Выполнено, новые данные:");
            bank.printClients();
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        // Добавляем новых клиентов
        bank.addClient("Сидоров", "987654",
                new BankAccount("3001", 25000, LocalDate.of(2023, 10, 1), 0.04, simpleInterest));
        System.out.println("\nПосле добавления новых клиентов:");
        bank.printClients();

        // Закрываем счет
      Client petrov = bank.findClientByAccount("2003");
      if (petrov != null) {
        petrov.getAccounts().stream().filter(account -> account.getAccountNumber().equals("2003")).findFirst().ifPresent(account -> account.closeAccount(LocalDate.now()));
      }


        // Удаляем клиентов, закрывших все счета
        bank.removeClosedAccountClients();
        System.out.println("\nПосле удаления закрывших счета:");
        bank.printClients();

        // Находим клиентов с долгосрочными вкладами
        List<Client> longTermClients = bank.findClientsWithLongTermDeposits(2);
        System.out.println("\nКлиенты с вкладами на 2 года и более:");
        longTermClients.forEach(client -> System.out.println(client.getLastName()));
    }
}
